import java.util.Random;

class Board {
    Random random = new Random();
    int [][] board = new int[5][5];

    public void setBoard(int i, int j, int value){
        board[i][j] = value;
    }

    public void printBoard(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void placeShip(int size){
        int row = random.nextInt(5);
        int col = random.nextInt(5);

        for(int i = 0; i < size; i++){
            board[row][col] = 1;
        }
    }
}

