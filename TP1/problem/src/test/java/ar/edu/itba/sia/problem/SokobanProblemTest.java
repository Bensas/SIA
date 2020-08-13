package ar.edu.itba.sia.problem;


import org.junit.Assert;
import org.junit.Test;

public class SokobanProblemTest {
    @Test
    public void testIsGoal(){
        SokobanProblem goalProblem = new SokobanProblem(getGoalState());
        Assert.assertTrue("Should be goal state!", goalProblem.isGoal(getGoalState()));
        SokobanProblem nonGoalProblem = new SokobanProblem(getNonGoalState());
        Assert.assertFalse("Should NOT be goal state!", goalProblem.isGoal(getNonGoalState()));
    }

    private SokobanState getGoalState(){
        Element[][] grid = new Element[][]{
                {Element.Wall, Element.Wall, Element.Wall},
                {Element.Wall, Element.Target, Element.Wall},
                {Element.Wall, Element.Empty, Element.Wall}
        };
        Board board = new Board(grid);
        SokobanState state = new SokobanState(board);
        state.setPlayerPosition(new Position(1, 0));
        state.getCubePositions().add(new Position(1, 1));
        return state;
    }

    private SokobanState getNonGoalState(){
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
