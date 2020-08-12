package ar.edu.itba.sia.problem;

import java.util.Arrays;

public class Board {
    private Element[][] grid;


    Board(Board board){
        this.grid = Arrays.stream(board.grid).map(i -> Arrays.copyOf(i, i.length)).toArray(Element[][]::new);
    }

    Board(Element[][] grid){
        this.grid = grid;
    }

    Board(int boardWidth){
        grid = new Element[boardWidth][boardWidth];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = Element.Empty;
            }
        }
    }

    public Element getElemAt(int x, int y){
        return grid[grid.length - 1 - y][x];
    }

    public Element getElemAt(Position pos){
        if (pos.x > this.getWidth() - 1 || pos.y > this.getHeight() - 1 || pos.x < 0 || pos.y < 0)
            return null;
            //throw new BoardPositionOutOfBoundsException();
        return grid[grid.length - 1 - pos.y][pos.x];
    }

    public void setElemAt(int x, int y, Element element){
        grid[grid.length - 1 - y][x] = element;
    }

    public void setElemAt(Position pos, Element element){
        grid[grid.length - 1 - pos.y][pos.x] = element;
    }

    public int getWidth(){
        if (grid != null)
            return grid[0].length;
        return 0;
    }

    public int getHeight(){
        if (grid != null)
            return grid.length;
        return 0;
    }

    public boolean isValidPosition(Position pos){
        return getElemAt(pos) != Element.Wall;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Board)) return false;
        Board board = (Board) obj;
        return Arrays.deepEquals(this.grid, board.grid);
    }
}
