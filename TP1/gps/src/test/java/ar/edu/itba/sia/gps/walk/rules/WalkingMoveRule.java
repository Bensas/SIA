package ar.edu.itba.sia.gps.walk.rules;

import ar.edu.itba.sia.gps.Pair;
import ar.edu.itba.sia.interfaces.Rule;
import ar.edu.itba.sia.interfaces.State;
import ar.edu.itba.sia.gps.walk.WalkingState;

import java.security.InvalidParameterException;
import java.util.Optional;

public abstract class WalkingMoveRule implements Rule {

    /*package*/ final Pair step;

    /*package*/ WalkingMoveRule(Pair step) {
        this.step = step;
        if (!controlStep()){
            throw new InvalidParameterException();
        }
    }

    @Override
    public Optional<State> apply(State state){
        WalkingState originState = (WalkingState) state;
        Pair location = originState.getLocation();

        WalkingState ans = new WalkingState(Pair.sum(location,step), originState.getTarget());
        return Optional.ofNullable(ans);
    }

    protected abstract boolean controlStep();

}
