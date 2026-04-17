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
        int shipcellsLeft = board.shipCellCount();

        if (debugMode){
            board.printDebugBoard();
        } else {
            board.printBoard();
        }


        while (shipcellsLeft != 0) {
            boolean playersTurn = true;

            while (playersTurn){
                do {
                    System.out.println("Your turn, enter row then column");
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                } while (row >= Board.SIZE || row < 0 || col >= Board.SIZE || col < 0);

                int playersShot = board.shot(row, col);
                shipcellsLeft = board.shipCellCount();

                if (shipcellsLeft == 0){
                    System.out.println("You won!");
                }

                switch (playersShot) {
                    case Board.HIT:
                        System.out.println("You`ve hit, you get one more turn!");
                        break;
                    case Board.ALREADY_SHOT:
                        System.out.println("You`ve already hit this cell, turn is still yours!");
                        break;
                    case Board.MISS:
                        System.out.println("You`ve miss, you lose your turn");
                        playersTurn = false;
                        break;
                }

                if (debugMode){
                    board.printDebugBoard();
                } else {
                    board.printBoard();
                }
                System.out.println(shipcellsLeft + " Shipcells left");
            }
        }

    }
}