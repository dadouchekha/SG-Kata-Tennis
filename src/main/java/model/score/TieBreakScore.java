package model.score;


import model.Player;

public class TieBreakScore extends Score {

    public TieBreakScore(Player player1, Player player2) {
        super(player1, player2);
    }

    @Override
    public boolean canApply() {
        boolean hasSixGameBoth = player1.getNbGames() == 6 && player2.getNbGames() == 6;
        boolean tieBreakNotFinished = (player1.getNbPoints() < 6 && player2.getNbPoints() < 6);
        boolean notEnoughtPointsDiff = (player1.getNbPoints() >= 5 && player2.getNbPoints() >= 5 && Math.abs(player1.getNbPoints() - player2.getNbPoints()) < 2);
        return hasSixGameBoth  && (tieBreakNotFinished || notEnoughtPointsDiff);
    }

    @Override
    public String display() {
        return "TIE BREAK : "+player1.getNbPoints()+"-"+player2.getNbPoints();
    }
}
