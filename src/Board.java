import java.util.Random;

class Board {
    Random random = new Random();
    int [][] board = new int[10][10];

    public void printBoard(){
        System.out.println("--------------------------");
        System.out.println("  A B C D E F G J H K ");
        for (int i = 0; i < 10; i++){
            System.out.print((i) + " ");
            for (int j = 0; j < 10; j++){
                String symbol = switch (board[i][j]) {
                    case 1 -> "O ";
                    case 2 -> "X ";
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
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            boolean horizontal = random.nextBoolean();
            boolean isFree = true;

            if (horizontal && col + size - 1 < 10) {
                for (int r = row - 1; r <=  row + 1; r++) {
                    for (int c = col - 1; c <= col + size; c++) {
                        if (r >= 0 && r < 10 && c >= 0 && c < 10) {
                            if(board[r][c] == 1) {
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
                        board[row][col + i] = 1;
                    }
                    placed = true;
                }

            } else if (!horizontal && row + size - 1 < 10) {
                for (int r = row - 1; r <=  row + size; r++) {
                    for (int c = col - 1; c <= col + 1; c++) {
                        if (r >= 0 && r < 10 && c >= 0 && c < 10) {
                            if (board[r][c] == 1) {
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
                        board[row + i][col] = 1;
                    }
                    placed = true;
                }
            }
        }
    }

    public boolean shot(int row, int col){
        boolean hit = false;
        if (board[row][col] == 1){
            System.out.println("You hit the target");
            hit = true;
        } else{
            System.out.println("You have missed");
            board[row][col] = 2;
        }
        return hit;
    }
}

