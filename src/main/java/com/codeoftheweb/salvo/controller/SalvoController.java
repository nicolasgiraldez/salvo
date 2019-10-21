package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.*;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/games")
    public List<Object> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return games.stream().map(Game::toDTO).collect(Collectors.toList());
    }

    private List<Map> gamePlayerList(Set<GamePlayer> gamePlayers) {
        return gamePlayers.stream()
                .map(gamePlayer -> gamePlayer.toDTO())
                .collect(Collectors.toList());
    }

    private List<Map> shipsList(Set<Ship> ships) {
        return ships.stream()
                .map(ship -> ship.toDTO())
                .collect(Collectors.toList());
    }

    private List<Map> salvoesList(Set<Salvo> salvoes) {
        return salvoes.stream()
                .map(salvo -> salvo.toDTO())
                .collect(Collectors.toList());
    }

    private List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @RequestMapping("/game_view/{gamePlayerID}")
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerID) {
        GamePlayer gamePlayer = gamePlayerRepository.getOne(gamePlayerID);
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


}