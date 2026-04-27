import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        int row = 0;
        int col = 0;
        boolean debugMode = false;



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
                int[] coord = readCoordinate(scanner);
                row = coord[0];
                col = coord[1];

                int playersShot = board.shot(row, col);
                shipcellsLeft = board.shipCellCount();

                if (shipcellsLeft == 0){
                    System.out.println("You won!");
                    playersTurn = false;
                }

                switch (playersShot) {
                    case Board.RESULT_HIT:
                        System.out.println("You`ve hit, you get one more turn!");
                        break;
                    case Board.RESULT_ALREADY_SHOT:
                        System.out.println("You`ve already hit this cell, turn is still yours!");
                        break;
                    case Board.RESULT_MISS:
                        System.out.println("You`ve miss, you lose your turn");
                        playersTurn = false;
                        break;
                }

                if (debugMode){
                    board.printDebugBoard();
                } else {
                    board.printBoard();
                }
                System.out.println("Ship cells left: " + shipcellsLeft);
            }
        }

        scanner.close();
    }

    public static int[] readCoordinate(Scanner scanner){
        String input;
        do{
            System.out.println("Your turn, enter coordinate");
            input = scanner.nextLine();
        }while(!isValidInput(input));

        int [] coord = new int[2];
        coord[0] = Integer.parseInt(input.substring(1));
        coord[1] = (input.toUpperCase().charAt(0)) - 'A';

        return coord;
    }
    public static boolean isValidInput(String input){
        if(input.length() < 2){return false;}
        else if (input.toUpperCase().charAt(0) < 'A' || input.toUpperCase().charAt(0) > 'J') { return false;}
        try{
            int row = Integer.parseInt(input.substring(1));
            if(row < 0 || row >= Board.SIZE){return false;}
        } catch (Exception e){
            return false;
        }
        return true;
    }
}