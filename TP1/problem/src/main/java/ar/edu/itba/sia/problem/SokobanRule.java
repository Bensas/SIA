package ar.edu.itba.sia.problem;

import ar.edu.itba.sia.interfaces.Rule;
import ar.edu.itba.sia.interfaces.State;

import java.util.Optional;

public class SokobanRule implements Rule {
    Direction direction;

    SokobanRule(Direction dir){
        this.direction = dir;
    }

    @Override
    public Integer getCost() {
        return null;
    }

    @Override
    public String getName() {
        return "Move" + direction.toString();
    }

    @Override
    public Optional<State> apply(State state) {
        if (state instanceof SokobanState)
            return ((SokobanState)state).movePlayer(direction);
        return Optional.empty();
    }
}
