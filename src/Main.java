import java.util.Scanner;

public class Main{
    static class Coordinate{
        int row;
        int col;
    }
    public static void main(String[] args){
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
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
                Coordinate coord = readCoordinate(scanner);
                Board.Result playersShot = board.shot(coord);
                shipcellsLeft = board.shipCellCount();

                if (shipcellsLeft == 0){
                    System.out.println("You won!");
                    break;
                }

                switch (playersShot) {
                    case HIT:
                        System.out.println("You`ve hit, you get one more turn!");
                        break;
                    case ALREADY_SHOT:
                        System.out.println("You`ve already hit this cell, turn is still yours!");
                        break;
                    case MISS:
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

    public static Coordinate readCoordinate(Scanner scanner){
        String input;
        do{
            System.out.println("Your turn, enter coordinate");
            input = scanner.nextLine();
        }while(!isValidInput(input));

        Coordinate coord = new Coordinate();
        coord.row = Integer.parseInt(input.substring(1));
        coord.col = (input.toUpperCase().charAt(0)) - 'A';

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