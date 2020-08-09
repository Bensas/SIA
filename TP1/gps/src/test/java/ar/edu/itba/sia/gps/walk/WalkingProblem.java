package ar.edu.itba.sia.gps.walk;

import ar.edu.itba.sia.gps.Pair;
import ar.edu.itba.sia.interfaces.Problem;
import ar.edu.itba.sia.interfaces.Rule;
import ar.edu.itba.sia.interfaces.State;
import ar.edu.itba.sia.gps.walk.rules.WalkingMoveDiagonalRule;
import ar.edu.itba.sia.gps.walk.rules.WalkingMoveStraightRule;

import java.util.ArrayList;
import java.util.List;

public class WalkingProblem implements Problem {

    private final WalkingState initState;
    private static List<Rule> rules;

    static {
        rules = new ArrayList<>();
        rules.add(new WalkingMoveDiagonalRule(new Pair(-1,-1)));
        rules.add(new WalkingMoveDiagonalRule(new Pair(-1,1)));
        rules.add(new WalkingMoveDiagonalRule(new Pair(1,-1)));
        rules.add(new WalkingMoveDiagonalRule(new Pair(1,1)));
        rules.add(new WalkingMoveStraightRule(new Pair(0,-1)));
        rules.add(new WalkingMoveStraightRule(new Pair(0,1)));
        rules.add(new WalkingMoveStraightRule(new Pair(-1,0)));
        rules.add(new WalkingMoveStraightRule(new Pair(1,0)));

    }

    public WalkingProblem(Pair target) {
        this.initState = new WalkingState(new Pair(0,0), target);
    }

    public WalkingProblem(){
        this(new Pair(5,7));
    }

    @Override
    public State getInitState() {
        return initState;
    }

    @Override
    public boolean isGoal(State state) {
        return ((WalkingState)state).isGoal();
    }

    @Override
    public List<Rule> getRules() {
        return rules;
    }
}
