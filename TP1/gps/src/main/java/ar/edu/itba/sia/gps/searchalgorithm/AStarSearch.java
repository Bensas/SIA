package ar.edu.itba.sia.gps.searchalgorithm;

import ar.edu.itba.sia.gps.GPSNode;

import java.util.Comparator;
import java.util.List;

public class AStarSearch implements SearchAlgorithm {
    @Override
    public boolean findSolution(List<GPSNode> candidates, List<GPSNode> borderNodes) {
        Comparator<GPSNode> comparator = (n1, n2) -> {
            if (n1.getHeuristicValue() + n1.getCost() == n2.getCost() + n2.getHeuristicValue()){
                return n1.getCost() > n2.getCost() ? -1 : 1;
            }
            else{
                return (n1.getHeuristicValue() + n1.getCost()) - (n2.getCost() + n2.getHeuristicValue());
            }
        };
        new CriteriaSearch().findSolution(candidates, borderNodes, comparator);
        return false;
    }

    @Override
    public String toString() {
        return "ASTAR";
    }
}
