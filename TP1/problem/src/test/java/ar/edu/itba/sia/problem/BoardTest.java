package ar.edu.itba.sia.problem;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    @Test
    public void testEqualsEmptyBoard(){
        Board board1 = new Board(5);
        Board board2 = new Board(5);
        Assert.assertEquals(board1, board2);
    }

    @Test
    public void testEqualsNonEmptyBoard(){
        Element[][] grid = new Element[][]{
                {Element.Wall, Element.Wall, Element.Wall},
                {Element.Wall, Element.Target, Element.Wall},
                {Element.Wall, Element.Empty, Element.Wall}
        };
        Board board1 = new Board(grid);
        Board board2 = new Board(grid);
        Assert.assertEquals(board1, board2);
    }
}
