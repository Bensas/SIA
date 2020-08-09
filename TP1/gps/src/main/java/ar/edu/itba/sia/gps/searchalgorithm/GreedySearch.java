package ar.edu.itba.sia.gps.searchalgorithm;

import ar.edu.itba.sia.gps.GPSNode;

import java.util.Comparator;
import java.util.List;

public class GreedySearch implements SearchAlgorithm {
    @Override
    public boolean findSolution(List<GPSNode> candidates, List<GPSNode> borderNodes) {
        Comparator<GPSNode> comp = (n1, n2) -> (int) (n1.getHeuristicValue() - n2.getHeuristicValue());

        new CriteriaSearch().findSolution(candidates, borderNodes, comp);
        return false;
    }

    @Override
    public String toString() {
        return "Greedy";
    }
}
