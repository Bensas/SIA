package ar.edu.itba.sia.gps.searchalgorithm;

import ar.edu.itba.sia.gps.GPSNode;

import java.util.List;

public class IterativeDeepeningSearch implements SearchAlgorithm {

    private int currentHeight = 1;

    @Override
    public String toString() {
        return "IDDFS";
    }

    @Override
    public boolean findSolution(List<GPSNode> candidates, List<GPSNode> borderNodes) {
        if(!candidates.isEmpty() && candidateLevelSurpassesCurrentMaxHeight(candidates))
            borderNodes.addAll(0, candidates);
        if(borderNodes.isEmpty()) {
            currentHeight++;
            return true;
        }
        return false;
    }

    private boolean candidateLevelSurpassesCurrentMaxHeight(List<GPSNode> candidates) {
        return candidates.get(0).getDepth() <= currentHeight;
    }
}
