import java.util.ArrayList;

public class Bot {
    ArrayList<Coordinate> nextCells = new ArrayList<Coordinate>(4);
    State state = State.SEARCH;

    enum State{
        SEARCH, CHASE;
    }

    public Coordinate botsNextCell(Board board){
        Coordinate botscoord = new Coordinate();
        if(state == State.SEARCH) {
            do {
                botscoord.row = Main.RANDOM.nextInt(Board.SIZE);
                botscoord.col = Main.RANDOM.nextInt(Board.SIZE);
            } while (board.wasShot(botscoord));
        }else if (state == State.CHASE && !nextCells.isEmpty()){
            botscoord = nextCells.get(Main.RANDOM.nextInt(nextCells.size()));
            nextCells.remove(botscoord);
        }
        return botscoord;
    }

    public Board.Result botShoots(Coordinate botscoord, Board board){
        Board.Result botsshot = board.shot(botscoord);
        if(botsshot == Board.Result.HIT){
            addSurroundingCells(botscoord, board);
            state = State.CHASE;
        }
        return botsshot;
    }

    public void addSurroundingCells(Coordinate botscoord, Board board) {
        int[] rows = {-1, 1, 0, 0};
        int[] cols = {0, 0, -1, 1};

        for(int i = 0; i < rows.length; i++){
            Coordinate current = new Coordinate();
            current.row = botscoord.row + rows[i];
            current.col = botscoord.col + cols[i];
            if(board.inBounds(current.row, current.col) && !board.wasShot(current)) {
                nextCells.add(current);
            }
        }
    }
}
