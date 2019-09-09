package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
		return (args) -> {

			// save a couple of players
			Player jackBauer = new Player("j.bauer@ctu.gov", "Jack Bauer");
			Player chloeOBrian = new Player("c.obrian@ctu.gov", "Chloe O'Brian");
			Player kimBauer = new Player("kim_bauer@gmail.com", "Kim Bauer");
			Player tonyAlmeida = new Player("t.almeida@ctu.gov", "Tony Almeida");

			playerRepository.save(jackBauer);
			playerRepository.save(chloeOBrian);
			playerRepository.save(kimBauer);
			playerRepository.save(tonyAlmeida);

			// save a couple of games
			Date date = new Date();
			Game game1 = new Game(date);
			Game game2 = new Game(Date.from(date.toInstant().plusSeconds(3600)));
			Game game3 = new Game(Date.from(date.toInstant().plusSeconds(7200)));
			Game game4 = new Game();
			Game game5 = new Game();
			Game game6 = new Game();
			Game game7 = new Game();
			Game game8 = new Game();

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
			gameRepository.save(game4);
			gameRepository.save(game5);
			gameRepository.save(game6);
			gameRepository.save(game7);
			gameRepository.save(game8);

			// save a couple of game players
			GamePlayer gameplayer1 = new GamePlayer(game1, jackBauer);
			GamePlayer gameplayer2 = new GamePlayer(game1, chloeOBrian);
			GamePlayer gameplayer3 = new GamePlayer(game2, jackBauer);
			GamePlayer gameplayer4 = new GamePlayer(game2, chloeOBrian);
			GamePlayer gameplayer5 = new GamePlayer(game3, chloeOBrian);
			GamePlayer gameplayer6 = new GamePlayer(game3, tonyAlmeida);
			GamePlayer gameplayer7 = new GamePlayer(game4, chloeOBrian);
			GamePlayer gameplayer8 = new GamePlayer(game4, jackBauer);
			GamePlayer gameplayer9 = new GamePlayer(game5, tonyAlmeida);
			GamePlayer gameplayer10 = new GamePlayer(game5, jackBauer);
			GamePlayer gameplayer11 = new GamePlayer(game6, kimBauer);
			GamePlayer gameplayer12 = new GamePlayer(game7, tonyAlmeida);
			GamePlayer gameplayer13 = new GamePlayer(game8, kimBauer);
			GamePlayer gameplayer14 = new GamePlayer(game8, tonyAlmeida);

			gamePlayerRepository.save(gameplayer1);
			gamePlayerRepository.save(gameplayer2);
			gamePlayerRepository.save(gameplayer3);
			gamePlayerRepository.save(gameplayer4);
			gamePlayerRepository.save(gameplayer5);
			gamePlayerRepository.save(gameplayer6);
			gamePlayerRepository.save(gameplayer7);
			gamePlayerRepository.save(gameplayer8);
			gamePlayerRepository.save(gameplayer9);
			gamePlayerRepository.save(gameplayer10);
			gamePlayerRepository.save(gameplayer11);
			gamePlayerRepository.save(gameplayer12);
			gamePlayerRepository.save(gameplayer13);
			gamePlayerRepository.save(gameplayer14);

		};
	}
}
