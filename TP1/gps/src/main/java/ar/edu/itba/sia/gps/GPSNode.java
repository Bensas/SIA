package ar.edu.itba.sia.gps;

import ar.edu.itba.sia.interfaces.Heuristic;
import ar.edu.itba.sia.interfaces.Rule;
import ar.edu.itba.sia.interfaces.State;

import java.util.LinkedList;


public class GPSNode {

    private final State state;
    private final Integer cost;
    private final Integer depth;
    private final Integer heuristicValue;
    private final Rule generationRule;
    private GPSNode parent;

    public GPSNode(State initialState, Heuristic h) {
        state = initialState;
        cost = 0;
        depth = 0;
        heuristicValue = h.getValue(initialState);
        generationRule = null;
    }

    public GPSNode(State state, Integer depth, Integer cost, Integer heuristic, GPSNode parent, Rule rule) {
        this.state = state;
        this.depth = depth;
        this.cost = cost;
        this.heuristicValue = heuristic;
        this.parent = parent;
        this.generationRule = rule;
    }

    public LinkedList<GPSNode> getPath() {
        LinkedList<GPSNode> ret = new LinkedList<>();
        for (GPSNode current = this; current != null; current = current.getParent())
            ret.push(current);
        return ret;
    }

    public GPSNode getParent() {
        return parent;
    }

    public State getState() {
        return state;
    }

    public Integer getDepth() {
        return depth;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getHeuristicValue() {
        return heuristicValue;
    }

    public Rule getGenerationRule() {
        return this.generationRule;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GPSNode that = (GPSNode) o;

        return this.getState().equals(that.getState());
    }

    @Override
    public int hashCode() {
        return this.getState().hashCode();
    }


}