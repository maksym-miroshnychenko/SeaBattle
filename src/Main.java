import java.util.Scanner;

public class Main{
    public static void main(String[] args){
    Board board = new Board();
    Scanner scanner = new Scanner(System.in);
    int row = 0;
    int col = 0;
    boolean gameState = false;

        board.placeShip(1);
        board.printBoard();


        while (!gameState){
            do {
                System.out.println("Your turn, enter row then colon");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }while (row >= 5 || row < 0 || col >= 5 || col < 0);

            gameState = board.shot(row, col);

            if (gameState){
                System.out.println("You won!");
            }
        }

    }
}