
import java.util.Scanner;

public class ConnectFourTestClient {

    /**
     *Implements the ConnectFour game b printing the board using a String representation
     * and each player taking its turn and inputting the row and column using the scanner.
     * @param args
     */
    public static void main(String args[]) {
        ConnectFourGame game = new ConnectFourGame(ConnectFourEnum.BLACK);
 Scanner scanner = new Scanner(System.in);
 do {
 System.out.println(game.toString());
 System.out.println(game.getTurn() +
 ": Where do you want to mark? Enter row column");
 int row = scanner.nextInt();
 int column = scanner.nextInt();
 scanner.nextLine();
 game.takeTurn(row, column);

 } while (game.getGameState() == ConnectFourEnum.IN_PROGRESS);
 System.out.println( game.getGameState());
    }
}
