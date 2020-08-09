package ar.edu.itba.sia.gps.searchalgorithm;

import ar.edu.itba.sia.gps.GPSNode;

import java.util.*;

public class DFSAlgorithm implements SearchAlgorithm {
    @Override
    public boolean findSolution(List<GPSNode> candidates, List<GPSNode> borderNodes) {
        borderNodes.addAll(0, candidates);
        return false;
    }

    @Override
    public String toString() {
        return "DFS";
    }
}
