package ar.edu.itba.sia.interfaces;

import ar.edu.itba.sia.interfaces.Heuristic;
import ar.edu.itba.sia.interfaces.State;

public class EmptyHeuristic implements Heuristic {
    @Override
    public Integer getValue(State state) {
        return 0;
    }

    @Override
    public String toString() {
        return "Trivial heuristic";
    }
}
