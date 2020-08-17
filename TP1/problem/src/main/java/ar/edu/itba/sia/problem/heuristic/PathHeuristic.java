package ar.edu.itba.sia.problem.heuristic;

import ar.edu.itba.sia.interfaces.Heuristic;
import ar.edu.itba.sia.interfaces.State;
import ar.edu.itba.sia.problem.Board;
import ar.edu.itba.sia.problem.Element;
import ar.edu.itba.sia.problem.Position;
import ar.edu.itba.sia.problem.SokobanState;

import java.util.LinkedList;

public class PathHeuristic implements Heuristic {
    @Override
    public Integer getValue(State state) {
        if(!(state instanceof SokobanState)) {
            throw new IllegalArgumentException("State must be of type SokobanState");
        }
        SokobanState sokobanState = (SokobanState) state;
        int ret = 0;
        for (Position p : sokobanState.getCubePositions()) {
            if (sokobanState.getBoard().getElemAt(p.getX(), p.getY()) != Element.Target)
                ret += findShortestPathToTarget(p, sokobanState.getBoard());
        }
        return ret;
    }

    private int findShortestPathToTarget(Position p, Board board) {
        LinkedList<Position> previousSteps = new LinkedList<>();
        return shortestPath(previousSteps, p, board);
    }

    private int shortestPath(LinkedList<Position> previousSteps, Position position, Board board) {
        if (position.getX() >= board.getWidth()         ||
            position.getY() >= board.getHeight()        ||
            position.getY() < 0                         ||
            position.getX() < 0                         ||
            board.getElemAt(position) == Element.Wall) {
            return board.getHeight() + board.getWidth() + 1;
        }
        if (board.getElemAt(position) == Element.Target)
            return 0;
        int ret = board.getHeight() + board.getWidth() + 1;
        previousSteps.push(position);
        Position next = new Position(position.getX(), position.getY() + 1);
        if (!previousSteps.contains(next)) {
            int distance = shortestPath(previousSteps, next, board);
            ret = Math.min(ret, distance);
        }
        next = new Position(position.getX(), position.getY() - 1);
        if (!previousSteps.contains(next)) {
            int distance = shortestPath(previousSteps, next, board);
            ret = Math.min(ret, distance);
        }
        next = new Position(position.getX() + 1, position.getY());
        if (!previousSteps.contains(next)) {
            int distance = shortestPath(previousSteps, next, board);
            ret = Math.min(ret, distance);
        }
        next = new Position(position.getX() - 1, position.getY());
        if (!previousSteps.contains(next)) {
            int distance = shortestPath(previousSteps, next, board);
            ret = Math.min(ret, distance);
        }
        previousSteps.pop();
        return ret + 1;
    }

    @Override
    public String getName() {
        return "Path";
    }

    @Override
    public String toString() {
        return "The length of the path to be traversed to get to the closest goal. Taking into account only walls.";
    }
}
