import java.util.Scanner;

public class Main{
    public static void main(String[] args){
    Board board = new Board();
    Scanner scanner = new Scanner(System.in);
    int row = 0;
    int col = 0;

        board.placeShip(1);
        board.printBoard();
        do {
            System.out.println("Your turn, enter row then colon");
            row = scanner.nextInt();
            col = scanner.nextInt();
        }while (row <= 5 || col <= 5);


        board.shot(row, col);
    }
}