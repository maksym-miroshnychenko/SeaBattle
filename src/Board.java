import java.util.Random;

class Board {
    Random random = new Random();
    int [][] board = new int[10][10];
    final int SIZE = 10;
    static final int WATER = 0;
    static final int SHIP = 1;
    static final int HIT = 2;
    static final int MISS = 3;


    public void printBoard(){
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
                            if(board[r][c] == SHIP) {
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
                        board[row][col + i] = SHIP;
                    }
                    placed = true;
                }

            } else if (!horizontal && row + size - 1 < SIZE) {
                for (int r = row - 1; r <=  row + size; r++) {
                    for (int c = col - 1; c <= col + 1; c++) {
                        if (r >= 0 && r < SIZE && c >= 0 && c < SIZE) {
                            if (board[r][c] == SHIP) {
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
                        board[row + i][col] = SHIP;
                    }
                    placed = true;
                }
            }
        }
    }

    public boolean shot(int row, int col){
        boolean hit = false;
        switch(board[row][col]){
            case WATER:
                System.out.println("You have missed");
                board[row][col] = MISS;
                hit = false;
                break;
            case SHIP:
                System.out.println("You hit the target");
                board[row][col] = HIT;
                hit = true;
                break;
            default:
                System.out.println("You`ve shot here already");

        }
        return hit;
    }

    public boolean allDestroyed(){
        for (int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(board[i][j] == SHIP){
                    return false;
                }
            }
        }
        return true;
    }
}

