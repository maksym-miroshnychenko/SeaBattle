public class Main{
    public static void main(String[] args){
    Board board = new Board();

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                board.setBoard(i,j,0);
            }
        }
        board.placeShip(1);
        board.printBoard();
    }
}