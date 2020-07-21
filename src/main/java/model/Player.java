package model;

public class Player {
    private String name;
    private int nbPoints;


    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void winPoint() {
        nbPoints++;
    }

    public String getScore() {
        switch(nbPoints) {
            case 0: return "0";
            case 1: return "15";
            case 2: return "30";
            case 3: return "40";
        }
        return null;
    }

    public boolean canWin() {
        return getNbPoints() > 3 ;
    }

    public boolean hasAdvantage(Player opponent) {
        int pointsDiff = nbPoints - opponent.getNbPoints();
        return canWin() && pointsDiff == 1;
    }

    public boolean hasWon(Player opponent) {
        boolean hasEnoughDiff = nbPoints - opponent.getNbPoints() >= 2;
        return canWin() && hasEnoughDiff;
    }
}
