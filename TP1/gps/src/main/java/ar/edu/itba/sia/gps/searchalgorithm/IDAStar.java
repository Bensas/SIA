package ar.edu.itba.sia.gps.searchalgorithm;

import ar.edu.itba.sia.gps.GPSNode;

import java.util.List;
import java.util.stream.Collectors;

public class IDAStar implements SearchAlgorithm {

    private int currentDepth = 1; // In IDA* we define the depth of a node as the sum h+g.

    @Override
    public boolean findSolution(List<GPSNode> candidates, List<GPSNode> borderNodes) {
        List<GPSNode> idaCandidates = candidates.stream().filter(n -> n.getCost() + n.getHeuristicValue() < currentDepth)
                                                        .collect(Collectors.toList());
        borderNodes.addAll(0, idaCandidates);
        if(borderNodes.isEmpty()) { // No nodes remain whose depth is less than currentDepth, start over.
            currentDepth++;
            return true;
        }
        return false;
    }
}
