import model.Player;
import model.TennisMatch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

public class TennisSetTest {

    Player player1 = null;
    Player player2 = null;
    TennisMatch tennisMatch = null;

    @BeforeEach
    public void init() {
        player1 = new Player("Djoko");
        player2 = new Player("Nadal");
        tennisMatch = new TennisMatch(player1, player2);
    }

    @Test
    public void newSetShouldStartWith0GameForBothPlayers() {

        Assertions.assertThat(tennisMatch.getPlayer1().getNbGames()).isEqualTo(0);
        Assertions.assertThat(tennisMatch.getPlayer2().getNbGames()).isEqualTo(0);
    }

    @ParameterizedTest(name = "{0} game(s) won should display {0} set won")
    @CsvSource({
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7"
    })
    public void shouldDisplayCorrectNbSetsPerGameWon(int nbGamesWon) {
        Player player1 = new Player("Djoko");
        IntStream.range(0, nbGamesWon).forEach(i -> player1.winGame());
        Assertions.assertThat(player1.getNbGames()).isEqualTo(nbGamesWon);
    }

    @ParameterizedTest(name = "Set {0} to {1} with score {2} to {3} should display {4}")
    @CsvSource({
            "6,6,0,1,TIE BREAK : 0-1",
            "6,6,1,1,TIE BREAK : 1-1",
            "6,6,2,3,TIE BREAK : 2-3",
            "6,6,5,6,TIE BREAK : 5-6",
            "6,6,11,12,TIE BREAK : 11-12",
            "6,6,12,11,TIE BREAK : 12-11",
    })
    public void shouldDisplayTieBreakScore(int player1WonSets, int player2WonSets, int player1NbPoints, int player2NbPoints, String expectedScore) {
       runSet(player1WonSets, player2WonSets);
       runGame(player1NbPoints, player2NbPoints);
        tennisMatch.selectCorrectScore();
        Assertions.assertThat(tennisMatch.displayCurrentGameScore()).isEqualTo(expectedScore);
    }

    @ParameterizedTest(name = "Set {0} to {1} with score {2} to {3} should have Set score to {4}")
    @CsvSource({
            "1,5,0,4,1-6",
            "6,6,0,6,6-7",
            "6,6,0,5,6-6",
            "6,6,5,6,6-6",
            "6,6,5,7,6-7",
            "6,6,12,10,7-6",
    })
    public void shouldDisplayCorrectSetScore(int player1WonSets, int player2WonSets, int player1NbPoints, int player2NbPoints, String expectedScore) {
        runSet(player1WonSets, player2WonSets);
        runGame(player1NbPoints, player2NbPoints);
        tennisMatch.selectCorrectScore();
        Assertions.assertThat(tennisMatch.displaySetScore()).isEqualTo(expectedScore);
    }

    private void runSet(int player1WonSets, int player2WonSets) {
        IntStream.range(0, player1WonSets).forEach( i -> tennisMatch.getPlayer1().winGame());
        IntStream.range(0, player2WonSets).forEach( i -> tennisMatch.getPlayer2().winGame());
    }

    private void runGame(int player1Points, int player2Points) {
        IntStream.range(0, player1Points).forEach( i -> tennisMatch.getPlayer1().winPoint());
        IntStream.range(0, player2Points).forEach( i -> tennisMatch.getPlayer2().winPoint());
    }
}
