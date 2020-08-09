package ar.edu.itba.sia.gps.searchalgorithm;

import ar.edu.itba.sia.gps.GPSNode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CriteriaSearch {

    public void findSolution(List<GPSNode> candidates, List<GPSNode> borderNodes, Comparator<GPSNode> criteria) {
        borderNodes.addAll(candidates);
        Collections.sort(borderNodes, criteria);
    }
}
