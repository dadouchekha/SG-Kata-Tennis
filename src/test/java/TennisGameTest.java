import model.Player;
import model.TennisGame;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

public class TennisGameTest {

    Player player1 = new Player("Djoko");
    Player player2 = new Player("Nadal");
    TennisGame tennisGame = null;

    @BeforeEach
    public void init() {
        tennisGame = new TennisGame(player1, player2);
    }

    @Test
    public void newGameShouldStartWithScore0ForBothPlayers() {

        Assertions.assertThat(tennisGame.getPlayer1().getNbPoints()).isEqualTo(0);
        Assertions.assertThat(tennisGame.getPlayer2().getNbPoints()).isEqualTo(0);
    }

    @ParameterizedTest(name = "{0} point(s) should display {1}")
    @CsvSource({
            "1,15",
            "2,30",
            "3,40"
    })
    public void testPlayerScore(int player1Points, String expectedScore) {
        Player player1 = new Player("Djoko");
        IntStream.range(0, player1Points).forEach(i -> player1.winPoint());
        Assertions.assertThat(player1.getScore()).isEqualTo(expectedScore);
    }

    @ParameterizedTest(name = "Score {0} to {1} should display {2}")
    @CsvSource({
            "0,4,Nadal Win game",
            "1,4,Nadal Win game",
            "2,4,Nadal Win game",
            "3,5,Nadal Win game",
            "4,6,Nadal Win game",
            "4,0,Djoko Win game",
            "4,0,Djoko Win game",
            "4,1,Djoko Win game",
            "4,2,Djoko Win game",
            "5,3,Djoko Win game",
            "6,4,Djoko Win game",
            "18,16,Djoko Win game",
    })
    public void shouldDisplayWinner(int player1Points, int player2Points, String expectedScore) {
        runGame(player1Points, player2Points);
        Assertions.assertThat(tennisGame.displayScore()).isEqualTo(expectedScore);
    }

    @ParameterizedTest(name = "Score {0} to {1} should display {2}")
    @CsvSource({
            "3,3,DEUCE",
            "4,4,DEUCE",
            "5,5,DEUCE",
            "6,6,DEUCE",
    })
    public void shouldDisplayDeuce(int player1Points, int player2Points, String expectedScore) {
        runGame(player1Points, player2Points);
        Assertions.assertThat(tennisGame.displayScore()).isEqualTo(expectedScore);
    }

    @ParameterizedTest(name = "Score {0} to {1} should display {2}")
    @CsvSource({
            "4,3,ADVANTAGE Djoko",
            "3,4,ADVANTAGE Nadal",
            "5,4,ADVANTAGE Djoko",
            "4,5,ADVANTAGE Nadal",
    })
    public void shouldDisplayAdvantage(int player1Points, int player2Points, String expectedScore) {
        runGame(player1Points, player2Points);
        Assertions.assertThat(tennisGame.displayScore()).isEqualTo(expectedScore);
    }

    @ParameterizedTest(name = "Score {0} to {1} should display {2}")
    @CsvSource({
            "0,1,0-15",
            "0,2,0-30",
            "0,3,0-40",
            "1,0,15-0",
            "2,0,30-0",
            "3,0,40-0",
            "1,0,15-0",
            "1,1,15-15",
            "1,2,15-30",
            "2,1,30-15",
            "2,2,30-30",
            "3,1,40-15",
            "3,2,40-30",
            "2,3,30-40",
    })
    public void shouldDisplayPlayerScore(int player1Points, int player2Points, String expectedScore) {
        runGame(player1Points, player2Points);
        Assertions.assertThat(tennisGame.displayScore()).isEqualTo(expectedScore);
    }

    private void runGame(int player1Points, int player2Points) {
        IntStream.range(0, player1Points).forEach( i -> tennisGame.getPlayer1().winPoint());
        IntStream.range(0, player2Points).forEach( i -> tennisGame.getPlayer2().winPoint());
    }

}
