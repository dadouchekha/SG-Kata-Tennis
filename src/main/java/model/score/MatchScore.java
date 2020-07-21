package model.score;

import model.Player;

public class MatchScore extends Score {

    public MatchScore(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean canApply() {
        return player1.hasWonSet(player2) || player2.hasWonSet(player1);
    }

    @Override
    public String display() {
        Player winnerPlayer = player1.hasWonSet(player2) ? player1 : player2;
        return winnerPlayer.getName()+" Win Set and Match";
    }
}
