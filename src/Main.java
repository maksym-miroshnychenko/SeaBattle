import java.util.Scanner;

public class Main{
    public static void main(String[] args){
    Board board = new Board();
    Scanner scanner = new Scanner(System.in);
    int row = 0;
    int col = 0;
    boolean allDestroyed = false;
    boolean hit = false;


        for (int size = 1; size <= 4; size++) {
            for (int i = 1; i <= 5 - size; i++) {
                board.placeShip(size);
            }
        }

        board.printBoard();


        while (!allDestroyed){
            do {
                System.out.println("Your turn, enter row then column");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }while (row >= 10 || row < 0 || col >= 10 || col < 0);

            hit = board.shot(row, col);


            allDestroyed = board.allDestroyed();

            if (allDestroyed){
                System.out.println("You won!");
            }
        }

    }
}