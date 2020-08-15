package ar.edu.itba.sia.problem;

import org.junit.Assert;
import org.junit.Test;

public class SokobanStateTest {
    @Test
    public void testEquals(){
        SokobanState state1 = getSampleState();
        SokobanState state2 = getSampleState();
        Assert.assertEquals(state1, state2);
    }

    private SokobanState getSampleState(){
        Element[][] grid = new Element[][]{
                {Element.Wall, Element.Empty, Element.Wall},
                {Element.Wall, Element.Target, Element.Wall},
                {Element.Wall, Element.Empty, Element.Wall}
        };
        Board board = new Board(grid);
        SokobanState state = new SokobanState(board);
        state.setPlayerPosition(new Position(1, 0));
        state.getCubePositions().add(new Position(1, 1));
        state.getCubePositions().add(new Position(1, 2));
        return state;
    }


}
