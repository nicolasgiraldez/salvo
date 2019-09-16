package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.model.Game;
import com.codeoftheweb.salvo.model.GamePlayer;
import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.model.Ship;
import com.codeoftheweb.salvo.repository.GamePlayerRepository;
import com.codeoftheweb.salvo.repository.GameRepository;
import com.codeoftheweb.salvo.repository.PlayerRepository;
import com.codeoftheweb.salvo.repository.ShipRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository) {
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

			Ship ship1 = new Ship("Destroyer", Arrays.asList("H2","H3","H4"));
			Ship ship2 = new Ship("Submarine", Arrays.asList("E1","F1","G1"));
			Ship ship3 = new Ship("Patrol Boat", Arrays.asList("B4","B5"));
			Ship ship4 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship5 = new Ship("Patrol Boat", Arrays.asList("F1","F2"));
			Ship ship6 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship7 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship8 = new Ship("Submarine", Arrays.asList("A2","A3","A4"));
			Ship ship9 = new Ship("Patrol Boat", Arrays.asList("G6","H6"));
			Ship ship10 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship11 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship12 = new Ship("Submarine", Arrays.asList("A2","A3","A4"));
			Ship ship13 = new Ship("Patrol Boat", Arrays.asList("G6","H6"));
			Ship ship14 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship15 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship16 = new Ship("Submarine", Arrays.asList("A2","A3","A4"));
			Ship ship17 = new Ship("Patrol Boat", Arrays.asList("G6","H6"));
			Ship ship18 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship19 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship20 = new Ship("Submarine", Arrays.asList("A2","A3","A4"));
			Ship ship21 = new Ship("Patrol Boat", Arrays.asList("G6","H6"));
			Ship ship22 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship23 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship24 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship25 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship26 = new Ship("Submarine", Arrays.asList("A2","A3","A4"));
			Ship ship27 = new Ship("Patrol Boat", Arrays.asList("G6","H6"));

			gameplayer1.addShip(ship1);
			gameplayer1.addShip(ship2);
			gameplayer1.addShip(ship3);
			gameplayer2.addShip(ship4);
			gameplayer2.addShip(ship5);
			gameplayer3.addShip(ship6);
			gameplayer3.addShip(ship7);
			gameplayer4.addShip(ship8);
			gameplayer4.addShip(ship9);
			gameplayer5.addShip(ship10);
			gameplayer5.addShip(ship11);
			gameplayer6.addShip(ship12);
			gameplayer6.addShip(ship13);
			gameplayer7.addShip(ship14);
			gameplayer7.addShip(ship15);
			gameplayer8.addShip(ship16);
			gameplayer8.addShip(ship17);
			gameplayer9.addShip(ship18);
			gameplayer9.addShip(ship19);
			gameplayer10.addShip(ship20);
			gameplayer10.addShip(ship21);
			gameplayer11.addShip(ship22);
			gameplayer11.addShip(ship23);
			gameplayer13.addShip(ship24);
			gameplayer13.addShip(ship25);
			gameplayer14.addShip(ship26);
			gameplayer14.addShip(ship27);

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

			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);
			shipRepository.save(ship6);
			shipRepository.save(ship7);
			shipRepository.save(ship8);
			shipRepository.save(ship9);
			shipRepository.save(ship10);
			shipRepository.save(ship11);
			shipRepository.save(ship12);
			shipRepository.save(ship13);
			shipRepository.save(ship14);
			shipRepository.save(ship15);
			shipRepository.save(ship16);
			shipRepository.save(ship17);
			shipRepository.save(ship18);
			shipRepository.save(ship19);
			shipRepository.save(ship20);
			shipRepository.save(ship21);
			shipRepository.save(ship22);
			shipRepository.save(ship23);
			shipRepository.save(ship24);
			shipRepository.save(ship25);
			shipRepository.save(ship26);
			shipRepository.save(ship27);


		};
	}
}
