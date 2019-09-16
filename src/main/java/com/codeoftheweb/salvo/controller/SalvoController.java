package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.model.Ship;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

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

    @RequestMapping("/game_view/{gamePlayerID}")
    public Map<String,Object> getGameView(@PathVariable Long gamePlayerID) {
        GamePlayer gamePlayer = gamePlayerRepository.getOne(gamePlayerID);
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getGame().getId());
        dto.put("created", gamePlayer.getGame().getCreationDate());
        dto.put("gamePlayers", gamePlayerList(gamePlayer.getGame().getGamePlayer()));
        dto.put("ships", shipsList(gamePlayer.getShips()));
        return dto;
    }


}