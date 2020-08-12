package ar.edu.itba.sia.interfaces;

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
