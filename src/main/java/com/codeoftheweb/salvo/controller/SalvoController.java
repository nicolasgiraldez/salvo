package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.ResponseEntityMessages;
import com.codeoftheweb.salvo.model.*;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (playerRepository.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        playerRepository.save(new Player(email, name, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Player getAuthenticatedPlayer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else {
            return playerRepository.findByEmail(authentication.getName());
        }
    }

    @RequestMapping(path= "/games", method = RequestMethod.GET)
    public Object getAllGames() {
        Map<String, Object> gamesDTO = new LinkedHashMap<>();
        List<Game> games = gameRepository.findAll();
        Player player = this.getAuthenticatedPlayer();

        if (player == null) {
            gamesDTO.put("player","Guest");
            gamesDTO.put("games", games.stream().map(Game::toDTO).collect(Collectors.toList()));
        } else {
            gamesDTO.put("player",player.toDTO());
            gamesDTO.put("games", games.stream().map(Game::toDTO).collect(Collectors.toList()));
        }
        return gamesDTO;
    }

    private List<Map> gamePlayerList(Set<GamePlayer> gamePlayers) {
        return gamePlayers.stream()
                .map(GamePlayer::toDTO)
                .collect(Collectors.toList());
    }

    private List<Map> shipsList(Set<Ship> ships) {
        return ships.stream()
                .map(Ship::toDTO)
                .collect(Collectors.toList());
    }

    private List<Map> salvoesList(Set<Salvo> salvoes) {
        return salvoes.stream()
                .map(Salvo::toDTO)
                .collect(Collectors.toList());
    }

    private List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @RequestMapping("/game_view/{gamePlayerID}")
    public Object getGameView(@PathVariable Long gamePlayerID) {

        Player authenticatedPlayer = this.getAuthenticatedPlayer();
        if (authenticatedPlayer == null) {
            return this.createResponseEntity(ResponseEntityMessages.KEY_ERROR, ResponseEntityMessages.MSG_NO_LOGUEADO,
                    HttpStatus.UNAUTHORIZED);
        }

        long authenticatedPlayerId = authenticatedPlayer.getId();
        GamePlayer gamePlayer = gamePlayerRepository.getOne(gamePlayerID);

        //verifico que sea una partida en la cual se encuentra el usuario autenticado en la aplicacion
        if (gamePlayer.getPlayer().getId() ==  authenticatedPlayerId) {
            return this.generateGameView(gamePlayer);
        } else {
            return this.createResponseEntity(ResponseEntityMessages.KEY_ERROR,
                    ResponseEntityMessages.MSG_JUGADOR_DISTINTO_AL_LOGUEADO, HttpStatus.UNAUTHORIZED);
        }

    }

    private Map<String,Object> generateGameView(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getGame().getId());
        dto.put("created", gamePlayer.getGame().getCreationDate());
        dto.put("gamePlayers", gamePlayerList(gamePlayer.getGame().getGamePlayers()));
        dto.put("ships", shipsList(gamePlayer.getShips()));
        dto.put("salvoes", salvoesList(gamePlayer.getSalvoes()));
        return dto;
    }

    @RequestMapping("/leaderboard")
    public List<Map<String,Object>> getScoreView(){
        List<Map<String,Object>> finalList = new ArrayList<>();
        this.getAllPlayers().forEach(player -> {
            if(!player.getScores().isEmpty()) {
                Map<String, Object> dto = new LinkedHashMap<>();
                Map<String, Object> dtoScore = new LinkedHashMap<>();
                dtoScore.put("total", player.getTotalScore());
                dtoScore.put("won", player.getTotalWins());
                dtoScore.put("lost", player.getTotalLoses());
                dtoScore.put("tied", player.getTotalTies());
                dto.put("name",player.getEmail());
                dto.put("score",dtoScore);
                finalList.add(dto);
            }
        });
        return finalList;
    }

    @RequestMapping(value = "/myusername", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        try {
            return principal.getName();
        } catch (Exception e) {
            return "Guest";
        }
    }

    private ResponseEntity<Object> createResponseEntity(String tipoDeRespuesta, Object valor, HttpStatus httpStatus ) {
        Map<String,Object> responseMap = new LinkedHashMap<>();
        responseMap.put(tipoDeRespuesta, valor);
        return new ResponseEntity<>(responseMap, httpStatus);
    }

}