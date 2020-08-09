package ar.edu.itba.sia.problem;

import ar.edu.itba.sia.interfaces.Problem;
import ar.edu.itba.sia.interfaces.Rule;
import ar.edu.itba.sia.interfaces.State;

import java.util.ArrayList;
import java.util.List;

public class SokobanProblem implements Problem {
    SokobanState initialState;
    List<Rule> rules = new ArrayList<>();

    SokobanProblem(SokobanState initialState){
        this.initialState = initialState;
        for (Direction dir: Direction.values())
            rules.add(new SokobanRule(dir));
    }

    @Override
    public State getInitState() {
        return initialState;
    }

    @Override
    public boolean isGoal(State state) {
        if (!(state instanceof SokobanState))
            return false;
        for (Position cubePosition: ((SokobanState) state).getCubePositions())
            if (((SokobanState) state).getBoard().elemAt(cubePosition.x, cubePosition.y) != Element.Target)
                return false;
        return true;
    }

    @Override
    public List<Rule> getRules() {
        return rules;
    }
}
