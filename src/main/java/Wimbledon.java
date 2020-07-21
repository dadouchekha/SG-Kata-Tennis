import model.Player;
import model.TennisMatch;

public class Wimbledon {


    public static void main(String[] args) {

        Player player1 = new Player("Djoko");
        Player player2 = new Player("Nadal");
        TennisMatch tennisMatch = new TennisMatch(player1, player2);
        System.out.println("Start Tennis Game between " + player1.getName() + " and " + player2.getName());
        while (!tennisMatch.isMatchOver()) {
            tennisMatch.selectCorrectScore();
            System.out.println(tennisMatch.displayCurrentGameScore());
            tennisMatch.runPoint();
            if(tennisMatch.isGameOver()) {
                System.out.println("Set Score : "+tennisMatch.displaySetScore());
            }
        }

    }

}


