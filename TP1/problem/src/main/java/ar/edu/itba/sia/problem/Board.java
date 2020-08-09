package ar.edu.itba.sia.problem;

import java.util.Arrays;

public class Board {
    private Element[][] grid;

    Board(Element[][] grid){
        this.grid = grid;
    }

    Board(Board board){
        this.grid = Arrays.stream(board.grid).map(i -> Arrays.copyOf(i, i.length)).toArray(Element[][]::new);
    }

    public Element elemAt(int x, int y){
        return grid[grid.length - 1 - y][x];
    }

    public Element elemAt(Position pos){
        if (pos.x > this.getWidth() - 1 || pos.y > this.getHeight() - 1 || pos.x < 0 || pos.y < 0)
            return null;
            //throw new BoardPositionOutOfBoundsException();
        return grid[grid.length - 1 - pos.y][pos.x];
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
        return elemAt(pos) != Element.Wall;
    }
}
