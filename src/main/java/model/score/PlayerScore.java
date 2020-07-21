package model.score;


import model.Player;

public class PlayerScore extends Score {

    public PlayerScore(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean canApply() {
        return player1.getNbPoints() <=3 && player1.getNbPoints() <= 3;
    }

    @Override
    public String display() {
        return player1.getScore()+"-"+player2.getScore();
    }
}
