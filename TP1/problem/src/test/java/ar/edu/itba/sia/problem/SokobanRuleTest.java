package ar.edu.itba.sia.problem;

import org.junit.Assert;
import org.junit.Test;

public class SokobanRuleTest {
    @Test
    public void testApplyDoesntChangeOriginalState(){
        //When the rule can be applied
        SokobanState originalState = getStateWhereUpCanBeApplied();
        SokobanRule rule = new SokobanRule(Direction.Up);
        rule.apply(originalState);
        Assert.assertEquals(originalState, getStateWhereUpCanBeApplied());

        //When the rule cannot be applied
        SokobanState originalState2 = getStateWhereUpCannotBeApplied();
        rule.apply(originalState2);
        Assert.assertEquals(originalState2, getStateWhereUpCannotBeApplied());
    }

    private SokobanState getStateWhereUpCanBeApplied(){
        Element[][] grid = new Element[][]{
                {Element.Wall, Element.Empty, Element.Wall},
                {Element.Wall, Element.Target, Element.Wall},
                {Element.Wall, Element.Empty, Element.Wall}
        };
        Board board = new Board(grid);
        SokobanState state = new SokobanState(board);
        state.setPlayerPosition(new Position(1, 0));
        state.getCubePositions().add(new Position(1, 1));
        return state;
    }

    private SokobanState getStateWhereUpCannotBeApplied(){
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
