package model;

import io.vavr.collection.List;
import model.score.*;

import java.util.Random;

public class TennisMatch {

    private Player player1;
    private Player player2;
    private Score score;

    public TennisMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void selectCorrectScore() {
        score = generatePossibleScores().filter(Score::canApply).get();
        majGameOver();
    }

    public String displayCurrentGameScore() {
        return score.display();
    }

    public String displaySetScore() {
        return player1.getNbGames()+"-"+player2.getNbGames();
    }

    private List<Score> generatePossibleScores() {
        return List.of(
                new MatchScore(player1, player2),
                new TieBreakScore(player1, player2),
                new GameScore(player1, player2),
                new AdvantageScore(player1, player2),
                new DeuceScore(player1, player2),
                new PlayerScore(player1, player2));
    }

    public boolean isGameOver() {
        return score instanceof GameScore;
    }

    public boolean isMatchOver() {
        return score instanceof MatchScore;
    }

    private void majGameOver() {
        if(isGameOver()) {
            if(player1.hasWonGame(player2)) {
                player1.winGame();
                player2.resetPoints();
            } else {
                player2.winGame();
                player1.resetPoints();
            };
        }
    }

    public void runPoint() {
        if(!isGameOver() && !isMatchOver()) {
            Random r = new Random();
            Player winnerPlayer = r.ints(0, 2).findFirst().getAsInt() == 0 ? player1 : player2;
            System.out.println("Point for " + winnerPlayer.getName());
            winPoint(winnerPlayer);
        }

    }

    public void winPoint(Player winner) {
        winner.winPoint();
    }



}
