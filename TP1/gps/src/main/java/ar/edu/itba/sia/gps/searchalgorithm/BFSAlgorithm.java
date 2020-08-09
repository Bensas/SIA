package ar.edu.itba.sia.gps.searchalgorithm;

import ar.edu.itba.sia.gps.GPSNode;

import java.util.*;

public class BFSAlgorithm implements SearchAlgorithm {
    @Override
    public boolean findSolution(List<GPSNode> candidates, List<GPSNode> borderNodes) {
        borderNodes.addAll(candidates);
        return false;
    }

    @Override
    public String toString() {
        return "BFS";
    }
}
