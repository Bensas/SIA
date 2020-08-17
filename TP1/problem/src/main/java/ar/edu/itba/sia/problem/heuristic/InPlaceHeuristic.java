package ar.edu.itba.sia.problem.heuristic;

import ar.edu.itba.sia.interfaces.Heuristic;
import ar.edu.itba.sia.interfaces.State;
import ar.edu.itba.sia.problem.Board;
import ar.edu.itba.sia.problem.Element;
import ar.edu.itba.sia.problem.Position;
import ar.edu.itba.sia.problem.SokobanState;

import java.util.HashSet;

public class InPlaceHeuristic implements Heuristic {
    @Override
    public Integer getValue(State state) {
        if(!(state instanceof SokobanState)) {
            throw new IllegalArgumentException("State must be of type SokobanState");
        }
        SokobanState sokobanState = (SokobanState) state;
        int ret = sokobanState.getCubePositions().size();
        for (Position p : sokobanState.getCubePositions()) {
            if(isAtTarget(p, sokobanState.getBoard()))
                ret--;
        }
        return ret;
    }

    private boolean isAtTarget(Position p, Board board) {
        return board.getElemAt(p.getX(), p.getY()) == Element.Target;
    }

    @Override
    public String getName() {
        return "In place";
    }

    @Override
    public String toString() {
        return "The count of all blocks that aren't at a goal.";
    }
}
