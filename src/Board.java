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
            boolean outOfBounce = board[row - 1][col] < 0 && board[row + 1][col] > 10 && board[row][col - 1] < 0 && board[row][col + 1] > 10;

            if (horizontal && col + size - 1 < 10) {
                for (int i = 0; i < size; i++) {
                    if(!outOfBounce) {
                        if (board[row - 1][col + i] == 1 || board[row][col + i] == 1 || board[row + 1][col + i] == 1) {
                            isFree = false;
                            break;
                        }
                    }
                }
                if(isFree) {
                    for (int i = 0; i < size; i++) {
                        board[row][col + i] = 1;
                    }
                    placed = true;
                }

            } else if (!horizontal && row + size - 1 < 10) {
                for (int i = 0; i < size; i++) {
                    if(!outOfBounce) {
                        if (board[row + i][col - 1] == 1 || board[row + i][col] == 1 || board[row + i][col + 1] == 1) {
                            isFree = false;
                            break;
                        }
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

