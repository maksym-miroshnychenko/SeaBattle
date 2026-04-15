import java.util.Scanner;

public class Main{
    public static void main(String[] args){
    Board board = new Board();
    Scanner scanner = new Scanner(System.in);
    int row = 0;
    int col = 0;
    boolean debugMode = true;



        for (int size = 1; size <= 4; size++) {
            for (int i = 1; i <= 5 - size; i++) {
                board.placeShip(size);
            }
        }

        if (debugMode){
            board.printDebugBoard();
        } else {
            board.printBoard();
        }


        while (board.shipCellCount() != 0){
            do {
                System.out.println("Your turn, enter row then column");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }while (row >= Board.SIZE || row < 0 || col >= Board.SIZE || col < 0);

            board.shot(row, col);
            if(board.shot(row, col) == Board.HIT){
                do {
                    System.out.println("Your turn, enter row then column");
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                }while (row >= Board.SIZE || row < 0 || col >= Board.SIZE || col < 0);
            }

            if (debugMode){
                board.printDebugBoard();
            } else {
                board.printBoard();
            }
            System.out.println(board.shipCellCount() + " " + "Ships left");


            if (board.shipCellCount() == 0){
                System.out.println("You won!");
            }
        }

    }
}