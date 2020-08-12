package ar.edu.itba.sia.problem.heuristic;

import ar.edu.itba.sia.interfaces.Heuristic;
import ar.edu.itba.sia.interfaces.State;
import ar.edu.itba.sia.problem.Board;
import ar.edu.itba.sia.problem.Element;
import ar.edu.itba.sia.problem.Position;
import ar.edu.itba.sia.problem.SokobanState;

public class DistanceHeuristic implements Heuristic {
    @Override
    public Integer getValue(State state) {
        if(!(state instanceof SokobanState)) {
            throw new IllegalArgumentException("State must be of type SokobanState");
        }
        SokobanState sokobanState = (SokobanState) state;
        int ret = 0;
        for (Position p : sokobanState.getCubePositions()) {
            ret += shortestDistanceToTarget(p, sokobanState.getBoard());
        }
        return ret;
    }

    private int shortestDistanceToTarget(Position p, Board board) {
        int ret = board.getHeight() + board.getWidth();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getElemAt(j, i) == Element.Target && distance(i, j, p) < ret) {
                    ret = distance(i, j, p);
                }
            }
        }
        return ret;
    }

    private int distance(int i, int j, Position p) {
        return Math.abs(i - p.getX()) + Math.abs(j - p.getY());
    }
}
