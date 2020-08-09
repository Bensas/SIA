package ar.edu.itba.sia.gps.searchalgorithm;

import ar.edu.itba.sia.gps.GPSNode;

import java.util.List;

public interface SearchAlgorithm {
    boolean findSolution(List<GPSNode> candidates, List<GPSNode> borderNodes);
    String toString();
}
