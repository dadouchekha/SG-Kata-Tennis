package model.score;

import model.Player;

public class GameScore extends Score {

    public GameScore(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean canApply() {
        return player1.hasWon(player2) || player2.hasWon(player1);
    }

    @Override
    public String display() {
        Player winnerPlayer = player1.hasWon(player2) ? player1 : player2;
        return winnerPlayer.getName()+" Win game";
    }
}
