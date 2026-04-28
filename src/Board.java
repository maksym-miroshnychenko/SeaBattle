import java.util.Random;

class Board {
    Random random = new Random();
    Cell [][] board = new Cell[10][10];
    static final int SIZE = 10;

    Board(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                board[i][j] = Cell.WATER;
            }
        }
    }
    enum Cell {
        WATER, SHIP, HIT, MISS
    }
    enum Result {
        HIT, MISS, ALREADY_SHOT
    }


    public void printBoard(){
        System.out.println("--------------------------");
        System.out.println("  A B C D E F G H I J ");
        for (int i = 0; i < SIZE; i++){
            System.out.print((i) + " ");
            for (int j = 0; j < SIZE; j++){
                String symbol = switch (board[i][j]) {
                    case SHIP -> ". ";
                    case HIT -> "X ";
                    case MISS -> "M ";
                    default -> ". ";
                };
                System.out.print(symbol);
            }
            System.out.println("|");
        }
    }

    public void printDebugBoard(){
        System.out.println("--------------------------");
        System.out.println("  A B C D E F G H I J ");
        for (int i = 0; i < SIZE; i++){
            System.out.print((i) + " ");
            for (int j = 0; j < SIZE; j++){
                String symbol = switch (board[i][j]) {
                    case SHIP -> "O ";
                    case HIT -> "X ";
                    case MISS -> "M ";
                    default -> ". ";
                };
                System.out.print(symbol);
            }
            System.out.println("|");
        }
    }

    public void placeShip(int size){
        boolean placed = false;

        while (!placed) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            boolean horizontal = random.nextBoolean();
            boolean isFree = true;

            if (horizontal && col + size - 1 < SIZE) {
                for (int r = row - 1; r <=  row + 1; r++) {
                    for (int c = col - 1; c <= col + size; c++) {
                        if (r >= 0 && r < SIZE && c >= 0 && c < SIZE) {
                            if(board[r][c] == Cell.SHIP) {
                                isFree = false;
                                break;
                            }
                        }
                    }
                    if (!isFree){
                        break;
                    }
                }
                if(isFree) {
                    for (int i = 0; i < size; i++) {
                        board[row][col + i] = Cell.SHIP;
                    }
                    placed = true;
                }

            } else if (!horizontal && row + size - 1 < SIZE) {
                for (int r = row - 1; r <=  row + size; r++) {
                    for (int c = col - 1; c <= col + 1; c++) {
                        if (r >= 0 && r < SIZE && c >= 0 && c < SIZE) {
                            if (board[r][c] == Cell.SHIP) {
                                isFree = false;
                                break;
                            }
                        }
                    }
                    if (!isFree){
                        break;
                    }
                }
                if(isFree) {
                    for (int i = 0; i < size; i++) {
                        board[row + i][col] = Cell.SHIP;
                    }
                    placed = true;
                }
            }
        }
    }

    public Result shot(Main.Coordinate coord){
        switch(board[coord.row][coord.col]){
            case WATER:
                board[coord.row][coord.col] = Cell.MISS;
                return Result.MISS;
            case SHIP:
                board[coord.row][coord.col] = Cell.HIT;
                return Result.HIT;
            case MISS:
            case HIT:
                return Result.ALREADY_SHOT;
            default:
                return Result.ALREADY_SHOT;
        }
    }

    public int shipCellCount(){
        int shipCellCount = 0;
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(board[i][j] == Cell.SHIP){
                    shipCellCount ++;
                }
            }
        }
        return shipCellCount;
    }
}

