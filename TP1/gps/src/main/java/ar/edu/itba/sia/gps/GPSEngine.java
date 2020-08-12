package ar.edu.itba.sia.gps;

import ar.edu.itba.sia.gps.searchalgorithm.*;
import ar.edu.itba.sia.interfaces.*;

import java.util.*;

public class GPSEngine {

    // Informational variables.
    private Heuristic heuristic;
    private long explosionCounter;
    private GPSNode solutionNode;
    private boolean isFinished;
    private boolean isFailed;
    private SearchStrategy searchStrategy;
    private Problem problem;

    // useful variables.
    private final List<GPSNode> borderNodes;
    private final Set<GPSNode> allNodes;
    private final SearchAlgorithm searchAlgorithm;
    private Map<GPSNode, Double> bestCosts;

    public GPSEngine(SearchStrategy searchStrategy) {
        this.searchAlgorithm = determineAlgorithm(searchStrategy);
        this.searchStrategy = searchStrategy;
        this.borderNodes = new LinkedList<>();
        this.allNodes = new HashSet<>();
        this.bestCosts = new HashMap<>();
    }

    /* package */ GPSEngine(Problem problem, SearchStrategy searchStrategy, Heuristic heuristic) {
        this(searchStrategy);
        this.problem = problem;
        this.heuristic = heuristic;
    }

    private SearchAlgorithm determineAlgorithm(SearchStrategy searchStrategy) {
        if (searchStrategy == SearchStrategy.ASTAR) return new AStarSearch();
        if (searchStrategy == SearchStrategy.GREEDY) return new GreedySearch();
        if (searchStrategy == SearchStrategy.BFS) return new BFSAlgorithm();
        if (searchStrategy == SearchStrategy.DFS) return new DFSAlgorithm();
        if (searchStrategy == SearchStrategy.IDDFS) return new IterativeDeepeningSearch();
        if (searchStrategy == SearchStrategy.IDASTAR) return new IDAStar();
        throw new IllegalArgumentException("invalid search strategy given: " + searchStrategy);
    }

    public void findSolution(Problem p) {
        findSolution(p, new EmptyHeuristic());
    }

    public void findSolution(Problem p, Heuristic h) {
        this.heuristic = h;
        this.explosionCounter = 0;
        search(p, h);
    }

    /* package */ void findSolution() {
        findSolution(this.problem, this.heuristic == null ? new EmptyHeuristic() : this.heuristic);
    }

    private void search(Problem p, Heuristic h) {

        State currentState = p.getInitState();
        GPSNode startNode = new GPSNode(currentState, h);
        GPSNode currentNode = startNode;
        borderNodes.add(currentNode);
        allNodes.add(currentNode);
        int previouslyExplored = 0;

        try {
            while (!p.isGoal(currentState)) {
                currentNode = borderNodes.remove(0);
                currentState = currentNode.getState();

                List<GPSNode> candidates = expand(p.getRules(), currentNode, h);

                if (searchAlgorithm.findSolution(candidates, borderNodes)) { // IDDFS says search needs to be reset.
                    if (previouslyExplored == allNodes.size()) { // We've reached the maximum depth, search failed.
                        setTestVariables(true, null);
                        return;
                    }
                    previouslyExplored = resetSearch(startNode);
                }

            }

            setTestVariables(false, currentNode);
        } catch (IndexOutOfBoundsException e) {
            setTestVariables(true, null);
        }
    }

    private int resetSearch(GPSNode startNode) {
        int previouslyExplored = allNodes.size();
        borderNodes.add(startNode);
        allNodes.clear();
        return previouslyExplored;
    }

    private List<GPSNode> expand(List<Rule> toApply, GPSNode currentNode, Heuristic heuristic) {
        this.explosionCounter++;
        LinkedList<GPSNode> candidates = new LinkedList<>();
        State currentState = currentNode.getState();
        for (Rule r : toApply) {
            Optional<State> state = r.apply(currentState);
            if (state.isPresent()) {
                State newState = state.get();
                GPSNode newNode = new GPSNode(newState,
                        currentNode.getDepth() + 1,
                        currentNode.getCost() + r.getCost(),
                        heuristic.getValue(newState),
                        currentNode,
                        r);
                if (!allNodes.contains(newNode) && noBetterCostFound(newNode)) {
                    allNodes.add(newNode);
                    bestCosts.put(newNode, newNode.getCost().doubleValue());
                    candidates.add(newNode);
                }
            }
        }
        return candidates;
    }

    private boolean noBetterCostFound(GPSNode newNode) {
        return bestCosts.get(newNode) == null || bestCosts.get(newNode) >= newNode.getCost().doubleValue();
    }

    private void setTestVariables(boolean isFailed, GPSNode solutionNode) {
        this.isFinished = true;
        this.isFailed = isFailed;
        this.solutionNode = solutionNode;
    }

    /* package */ long getExplosionCounter() {
        return this.explosionCounter;
    }

    /* package */ boolean isFailed() {
        return this.isFailed;
    }

    /* package */ boolean isFinished() {
        return this.isFinished;
    }

    /* package */ GPSNode getSolutionNode() {
        return this.solutionNode;
    }

    /* package */ SearchStrategy getStrategy() {
        return this.searchStrategy;
    }

    /* package */ Set<GPSNode> getBestCosts() {
        return this.allNodes;
    }

    /* package */ List<GPSNode> getOpen() {
        return this.borderNodes;
    }

    /* package */ Heuristic getHeuristic() {
        return this.heuristic;
    }
}
