package model;

public class Player {
    private String name;
    private int nbPoints;
    private int nbGames;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public int getNbGames() {
        return nbGames;
    }


    public void winPoint() {
        nbPoints++;
    }

    public void winGame() {
        nbPoints = 0;
        nbGames++;
    }

    public String getGameScore() {
        switch(nbPoints) {
            case 0: return "0";
            case 1: return "15";
            case 2: return "30";
            case 3: return "40";
        }
        return null;
    }

    public boolean canWinGame() {
        return getNbPoints() > 3 ;
    }

    public boolean hasDeuceAdvantage(Player opponent) {
        int pointsDiff = nbPoints - opponent.getNbPoints();
        return canWinGame() && pointsDiff == 1;
    }

    public boolean hasWonGame(Player opponent) {
        boolean hasEnoughDiff = nbPoints - opponent.getNbPoints() >= 2;
        return canWinGame() && hasEnoughDiff;
    }

    public boolean hasWonSet(Player opponent) {
        boolean classicVictory = (nbGames == 6 && opponent.nbGames < 5);
        boolean overtime = (nbGames == 7 && opponent.nbGames >= 5);
        return classicVictory || overtime;
    }

    public void resetPoints() {
        nbPoints = 0;
    }
}
