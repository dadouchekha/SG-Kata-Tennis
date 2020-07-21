package model.score;


import model.Player;

public class DeuceScore extends Score {

    public DeuceScore(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean canApply() {
        return player1.getNbPoints() >= 3 && player2.getNbPoints() == player1.getNbPoints();
    }

    @Override
    public String display() {
        return "DEUCE";
    }
}
