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

    @Test
    public void testIsImpossibleState(){
        SokobanState state = getImpossibleState();
        Assert.assertTrue(SokobanState.isImpossibleState(state));
        state = getSampleState();
        Assert.assertFalse(SokobanState.isImpossibleState(state));
    }

    @Test
    public void testIsInmovable(){
        SokobanState impossibleState = getImpossibleState();
        SokobanState sampleState = getSampleState();
        Assert.assertTrue(SokobanState.isInmovable(new Position(2, 1), impossibleState));
        Assert.assertFalse(SokobanState.isInmovable(new Position(1, 3), sampleState));
    }

    private SokobanState getSampleState(){
        Element[][] grid = new Element[][]{
                {Element.Wall, Element.Wall, Element.Wall},
                {Element.Wall, Element.Empty, Element.Wall},
                {Element.Wall, Element.Empty, Element.Wall},
                {Element.Wall, Element.Empty, Element.Wall},
                {Element.Wall, Element.Target, Element.Wall},
                {Element.Wall, Element.Empty, Element.Wall}
        };
        Board board = new Board(grid);
        SokobanState state = new SokobanState(board);
        state.setPlayerPosition(new Position(1, 0));
        state.getCubePositions().add(new Position(1, 1));
        state.getCubePositions().add(new Position(1, 3));
        return state;
    }

    private SokobanState getImpossibleState(){
        Element[][] grid = new Element[][]{
                {Element.Wall, Element.Empty, Element.Wall, Element.Wall},
                {Element.Wall, Element.Target, Element.Empty, Element.Wall},
                {Element.Wall, Element.Target, Element.Empty, Element.Wall}
        };
        Board board = new Board(grid);
        SokobanState state = new SokobanState(board);
        state.setPlayerPosition(new Position(1, 0));
        state.getCubePositions().add(new Position(1, 1));
        state.getCubePositions().add(new Position(2, 1));
        return state;
    }


}
