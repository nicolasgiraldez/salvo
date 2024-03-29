package com.codeoftheweb.salvo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String email;

    private String name;

    private String password;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<Score> scores;

    public Player() {
    }

    public Player(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        this.gamePlayers.add(gamePlayer);
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public List<Game> getGames() {
        return gamePlayers.stream().map(GamePlayer::getGame).collect(toList());
    }

    public Set<Score> getScores() {
        return scores;
    }

    public Score getScore(Game game) {
        return this.getScores().stream().filter(
                score -> score.getGame().getId() == game.getId()
        ).findFirst().orElse(null);
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public void addScores(Score score) {
        score.setPlayer(this);
        this.scores.add(score);
    }

    public Double getTotalScore(){
        return getScores()
                .stream()
                .mapToDouble(Score::getScore).sum();
    }

    public Long getTotalWins(){
        return getScores()
                .stream()
                .filter(score -> score.getScore() == 1d)
                .count();
    }

    public Long getTotalLoses(){
        return getScores()
                .stream()
                .filter(score -> score.getScore() == 0d)
                .count();
    }

    public Long getTotalTies(){
        return getScores()
                .stream()
                .filter(score -> score.getScore() == 0.5d)
                .mapToDouble(Score::getScore).count();
    }

    public Map<String, Object> toDTO() {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", getId());
        dto.put("email", getEmail());
        return dto;
    }
}