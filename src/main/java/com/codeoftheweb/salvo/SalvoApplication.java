package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.model.*;
import com.codeoftheweb.salvo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class SalvoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository) {
        return (args) -> {

            // save a couple of players
            Player jackBauer = new Player("j.bauer@ctu.gov", "Jack Bauer", "24");
            Player chloeOBrian = new Player("c.obrian@ctu.gov", "Chloe O'Brian", "42");
            Player kimBauer = new Player("kim_bauer@gmail.com", "Kim Bauer", "kb");
            Player tonyAlmeida = new Player("t.almeida@ctu.gov", "Tony Almeida", "mole");

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

            Ship ship1 = new Ship("Destroyer", Arrays.asList("H2", "H3", "H4"));
            Ship ship2 = new Ship("Submarine", Arrays.asList("E1", "F1", "G1"));
            Ship ship3 = new Ship("Patrol Boat", Arrays.asList("B4", "B5"));
            Ship ship4 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship ship5 = new Ship("Patrol Boat", Arrays.asList("F1", "F2"));
            Ship ship6 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship ship7 = new Ship("Patrol Boat", Arrays.asList("C6", "C7"));
            Ship ship8 = new Ship("Submarine", Arrays.asList("A2", "A3", "A4"));
            Ship ship9 = new Ship("Patrol Boat", Arrays.asList("G6", "H6"));
            Ship ship10 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship ship11 = new Ship("Patrol Boat", Arrays.asList("C6", "C7"));
            Ship ship12 = new Ship("Submarine", Arrays.asList("A2", "A3", "A4"));
            Ship ship13 = new Ship("Patrol Boat", Arrays.asList("G6", "H6"));
            Ship ship14 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship ship15 = new Ship("Patrol Boat", Arrays.asList("C6", "C7"));
            Ship ship16 = new Ship("Submarine", Arrays.asList("A2", "A3", "A4"));
            Ship ship17 = new Ship("Patrol Boat", Arrays.asList("G6", "H6"));
            Ship ship18 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship ship19 = new Ship("Patrol Boat", Arrays.asList("C6", "C7"));
            Ship ship20 = new Ship("Submarine", Arrays.asList("A2", "A3", "A4"));
            Ship ship21 = new Ship("Patrol Boat", Arrays.asList("G6", "H6"));
            Ship ship22 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship ship23 = new Ship("Patrol Boat", Arrays.asList("C6", "C7"));
            Ship ship24 = new Ship("Destroyer", Arrays.asList("B5", "C5", "D5"));
            Ship ship25 = new Ship("Patrol Boat", Arrays.asList("C6", "C7"));
            Ship ship26 = new Ship("Submarine", Arrays.asList("A2", "A3", "A4"));
            Ship ship27 = new Ship("Patrol Boat", Arrays.asList("G6", "H6"));

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

            Salvo salvo1 = new Salvo(gameplayer1, 1, Arrays.asList("B5", "C5", "F1"));
            salvoRepository.save(salvo1);
            Salvo salvo2 = new Salvo(gameplayer2, 1, Arrays.asList("B4", "B5", "B6"));
            salvoRepository.save(salvo2);
            Salvo salvo3 = new Salvo(gameplayer1, 2, Arrays.asList("F2", "D5"));
            salvoRepository.save(salvo3);
            Salvo salvo4 = new Salvo(gameplayer2, 2, Arrays.asList("E1", "H3", "A2"));
            salvoRepository.save(salvo4);
            Salvo salvo5 = new Salvo(gameplayer3, 1, Arrays.asList("A2", "A4", "G6"));
            salvoRepository.save(salvo5);
            Salvo salvo6 = new Salvo(gameplayer4, 1, Arrays.asList("B5", "D5", "C7"));
            salvoRepository.save(salvo6);
            Salvo salvo7 = new Salvo(gameplayer3, 2, Arrays.asList("A3", "H6"));
            salvoRepository.save(salvo7);
            Salvo salvo8 = new Salvo(gameplayer4, 2, Arrays.asList("C5", "C6"));
            salvoRepository.save(salvo8);
            Salvo salvo9 = new Salvo(gameplayer5, 1, Arrays.asList("G6", "H6", "A4"));
            salvoRepository.save(salvo9);
            Salvo salvo10 = new Salvo(gameplayer6, 1, Arrays.asList("H1", "H2", "H3"));
            salvoRepository.save(salvo10);
            Salvo salvo11 = new Salvo(gameplayer5, 2, Arrays.asList("A2", "A3", "D8"));
            salvoRepository.save(salvo11);
            Salvo salvo12 = new Salvo(gameplayer6, 2, Arrays.asList("E1", "F2", "G3"));
            salvoRepository.save(salvo12);
            Salvo salvo13 = new Salvo(gameplayer7, 1, Arrays.asList("A3", "A4", "F7"));
            salvoRepository.save(salvo13);
            Salvo salvo14 = new Salvo(gameplayer8, 1, Arrays.asList("B5", "C6", "H1"));
            salvoRepository.save(salvo14);
            Salvo salvo15 = new Salvo(gameplayer7, 2, Arrays.asList("A2", "G6", "H6"));
            salvoRepository.save(salvo15);
            Salvo salvo16 = new Salvo(gameplayer8, 2, Arrays.asList("C5", "C7", "D5"));
            salvoRepository.save(salvo16);
            Salvo salvo17 = new Salvo(gameplayer9, 1, Arrays.asList("A1", "A2", "A3"));
            salvoRepository.save(salvo17);
            Salvo salvo18 = new Salvo(gameplayer10, 1, Arrays.asList("B5", "B6", "C7"));
            salvoRepository.save(salvo18);
            Salvo salvo19 = new Salvo(gameplayer9, 2, Arrays.asList("G6", "G7", "G8"));
            salvoRepository.save(salvo19);
            Salvo salvo20 = new Salvo(gameplayer10, 2, Arrays.asList("C6", "D6", "E6"));
            salvoRepository.save(salvo20);
            Salvo salvo21 = new Salvo(gameplayer10, 3, Arrays.asList("H1", "H8"));
            salvoRepository.save(salvo21);

            Score score1 = new Score(game1, jackBauer, 1.0);
            scoreRepository.save(score1);
            Score score2 = new Score(game1, chloeOBrian, 0.0);
            scoreRepository.save(score2);
            Score score3 = new Score(game2, jackBauer, 0.5);
            scoreRepository.save(score3);
            Score score4 = new Score(game2, chloeOBrian, 0.5);
            scoreRepository.save(score4);
            Score score5 = new Score(game3, chloeOBrian, 1.0);
            scoreRepository.save(score5);
            Score score6 = new Score(game3, tonyAlmeida, 0.0);
            scoreRepository.save(score6);
            Score score7 = new Score(game4, chloeOBrian, 0.5);
            scoreRepository.save(score7);
            Score score8 = new Score(game4, jackBauer, 0.5);
            scoreRepository.save(score8);

        };
    }
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName-> {
            Player player = playerRepository.findByEmail(inputName);
            if (player != null) {
                return new User(player.getEmail(), player.getPassword(),
                        AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }

}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/**").hasAuthority("USER")
                .and()
                .formLogin();

        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");

        http.headers().frameOptions().disable();

        http.logout().logoutUrl("/api/logout");

        // turn off checking for CSRF tokens
        http.csrf().disable();

        // if user is not authenticated, just send an authentication failure response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

}