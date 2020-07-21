package model.score;

import model.Player;

public class GameScore extends Score {

    private String gameWinner;

    public GameScore(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean canApply() {
        if(player1.hasWonGame(player2)) {
            gameWinner = player1.getName();
            return true;
        }
        if(player2.hasWonGame(player1)) {
            gameWinner = player2.getName();
            return true;
        }
        return false;
    }

    @Override
    public String display() {
       return gameWinner+" Win game";
    }
}
