import java.util.Random;

class Board {
    Random random = new Random();
    int [][] board = new int[10][10];

    public void printBoard(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
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
                for (int i = 0; i < size; i++) {
                    if(board[row][col + i] == 1) {
                        isFree = false;
                    }
                }
                for (int i = 0; i < size; i++) {
                    if(isFree){
                        board[row][col + i]= 1;
                    }
                }
                if(isFree){placed = true;}
            } else if (!horizontal && row + size - 1 < 10) {
                for (int i = 0; i < size; i++) {
                    if(board[row + i][col] == 1) {
                        isFree = false;
                    }
                }
                for (int i = 0; i < size; i++) {
                    if(isFree) {
                        board[row + i][col] = 1;
                    }
                }
                if(isFree){placed = true;}
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

