import java.util.Random;
import java.util.Scanner;

public class Main{
    static final Random RANDOM = new Random();

    public static void main(String[] args){
        Board playersBoard = new Board();
        Board enemiesBoard = new Board();
        Scanner scanner = new Scanner(System.in);
        boolean debugMode = false;



        for (int size = 1; size <= 4; size++) {
            for (int i = 1; i <= 5 - size; i++) {
                enemiesBoard.placeShip(size);
            }
        }
        int enemiesShipcellsLeft = enemiesBoard.shipCellCount();

        for (int size = 1; size <= 4; size++) {
            for (int i = 1; i <= 5 - size; i++) {
                playersBoard.placeShip(size);
            }
        }
        int playersShipcellsLeft = playersBoard.shipCellCount();

        playersBoard.printBoard(true);
        enemiesBoard.printBoard(debugMode);



        while (enemiesShipcellsLeft != 0 && playersShipcellsLeft != 0){
            boolean playersTurn = true;
            boolean enemiesTurn = false;

            while (playersTurn){
                Coordinate coord = readCoordinate(scanner);
                Board.Result playersShot = enemiesBoard.shot(coord);
                enemiesShipcellsLeft = enemiesBoard.shipCellCount();

                if (enemiesShipcellsLeft == 0){
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
                        enemiesTurn = true;
                        break;
                }
                enemiesBoard.printBoard(debugMode);
                System.out.println("Enemies Ship cells left: " + enemiesShipcellsLeft);
            }

            while(enemiesTurn){
                Coordinate botscoord = randomCoordinate(playersBoard);
                Board.Result botsshot = playersBoard.shot(botscoord);
                playersShipcellsLeft = playersBoard.shipCellCount();

                System.out.println("Bot shot at:" + botscoord.toString());

                if(playersShipcellsLeft == 0){
                    System.out.println("You`ve lost!");
                    break;
                }

                switch(botsshot){
                    case HIT:
                        System.out.println("Bot has hit, he gets one more turn!");
                        break;
                    case MISS:
                        System.out.println("Bot has miss, he loses his turn");
                        enemiesTurn = false;
                        playersTurn = true;
                        break;
                    case ALREADY_SHOT:
                        System.out.println("Bot tried an already shot cell");
                        break;
                }

                playersBoard.printBoard(true);
                System.out.println("Your Ship cells left: " + playersShipcellsLeft);
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

    public static Coordinate randomCoordinate(Board board){
        Coordinate botscoord = new Coordinate();
        do {
            botscoord.row = RANDOM.nextInt(Board.SIZE);
            botscoord.col = RANDOM.nextInt(Board.SIZE);
        }while (board.wasShot(botscoord));

        return botscoord;
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