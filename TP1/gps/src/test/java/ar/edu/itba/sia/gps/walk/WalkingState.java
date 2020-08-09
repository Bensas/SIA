package ar.edu.itba.sia.gps.walk;

import ar.edu.itba.sia.gps.Pair;
import ar.edu.itba.sia.interfaces.State;

import java.util.Objects;

public class WalkingState implements State {
    private final Pair location;
    private final Pair target;

    public WalkingState(Pair location, Pair target) {
        this.location = location;
        this.target = target;
    }

    @Override
    public String getRepresentation() {
        return location.toString() + " to " + target.toString();
    }

    public Pair getLocation() {
        return location;
    }

    public Pair getTarget(){
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalkingState that = (WalkingState) o;
        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    public boolean isGoal(){
        return location.equals(target);
    }
}
