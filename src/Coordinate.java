class Coordinate {
    int row;
    int col;

    @Override
    public String toString(){
        return " " + (char) ('A' + col) + row;
    }
}
