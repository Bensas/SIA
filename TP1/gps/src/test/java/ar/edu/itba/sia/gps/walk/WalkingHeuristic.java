package ar.edu.itba.sia.gps.walk;

import ar.edu.itba.sia.interfaces.Heuristic;
import ar.edu.itba.sia.interfaces.State;

public class WalkingHeuristic implements Heuristic {

    private WalkingHeuristic() {
    }

    private static WalkingHeuristic instance;

    public static WalkingHeuristic instance() {
        if (instance == null) {
            instance = new WalkingHeuristic();
        }
        return instance;
    }

    @Override
    public Integer getValue(State state) {
        WalkingState walkingState = (WalkingState) state;
        double x = walkingState.getLocation().getX() - walkingState.getTarget().getX();
        double y = walkingState.getLocation().getY() - walkingState.getTarget().getY();
        return (int)Math.ceil(Math.sqrt(x * x + y * y));
    }
}
