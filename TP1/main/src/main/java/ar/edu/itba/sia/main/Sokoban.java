package ar.edu.itba.sia.main;

import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.interfaces.Heuristic;
import ar.edu.itba.sia.interfaces.SearchStrategy;
import ar.edu.itba.sia.problem.BoardParser;
import ar.edu.itba.sia.problem.SokobanProblem;
import ar.edu.itba.sia.problem.SokobanState;
import ar.edu.itba.sia.problem.heuristic.DistanceHeuristic;
import ar.edu.itba.sia.problem.heuristic.InPlaceHeuristic;
import ar.edu.itba.sia.problem.heuristic.PathHeuristic;

import java.util.LinkedList;

public class Sokoban
{
    // args[0]: path to sokoban file.
    // args[1]: algorithm. BFS|DFS|IDDFS|GREEDY|ASTAR|IDASTAR
    // args[2]: (optional) heuristic. place|distance|path
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            properUse();
            System.out.println("\nInvalid number of arguments. Must be either 2 or 3.\n");
            return;
        }
        final SokobanState state = BoardParser.getStateFromFile(args[0]);
        if (state == null) {
            properUse();
            // error message handled by parser (probably shouldn't do it like that).
            return;
        }
        SearchStrategy searchStrategy = SearchStrategy.valueOf(args[1]);
        GPSEngine engine = new GPSEngine(searchStrategy);
        if (args.length == 3) {
            Heuristic heuristic = parseHeuristic(args[2]);
            if (heuristic == null) {
                properUse();
                System.out.println("\nInvalid heuristic given.\n");
            }
            long then = System.currentTimeMillis();
            engine.findSolution(new SokobanProblem(state), heuristic);
            long now = System.currentTimeMillis();
            printData(engine, now - then, args[0]);
            return;
        }
        if (searchStrategy == SearchStrategy.GREEDY ||
            searchStrategy == SearchStrategy.ASTAR  ||
            searchStrategy == SearchStrategy.IDASTAR) {
            properUse();
            System.out.println("\n" + searchStrategy + " requires a heuristic but none was given.\n");
            return;
        }
        long then = System.currentTimeMillis();
        engine.findSolution(new SokobanProblem(state));
        long now = System.currentTimeMillis();
        printData(engine, now - then, args[0]);
    }

    private static Heuristic parseHeuristic(String heuristic) {
        String lower = heuristic.toLowerCase();
        if (lower.equals("place")) return new InPlaceHeuristic();
        if (lower.equals("distance")) return new DistanceHeuristic();
        if (lower.equals("path")) return new PathHeuristic();
        return null;
    }

    private static boolean isInformedHeuristic(SearchStrategy searchStrategy) {
        return  searchStrategy == SearchStrategy.GREEDY ||
                searchStrategy == SearchStrategy.ASTAR  ||
                searchStrategy == SearchStrategy.IDASTAR;
    }

    private static void printData(GPSEngine engine, long time, String path) {
        if (engine.isFailed()) {
            System.out.println("The search was a failure");
            return;
        }
        printPath(engine.getSolutionNode().getPath());
        System.out.println("Done!\n");
        System.out.println("The search was a success!");
        System.out.println("algorithm: " + engine.getStrategy());
        if (engine.getHeuristic() != null && isInformedHeuristic(engine.getStrategy())) {
            System.out.println("heuristic: " + engine.getHeuristic().getName());
            // System.out.println("this heuristic arrives at its value by the method of: " + engine.getHeuristic());
        }
        System.out.println("cost: " + engine.getSolutionNode().getCost());
        System.out.println("expanded: " + engine.getBestCosts().size());
        System.out.println("frontier: " + engine.getOpen().size());
        System.out.println("time: " + time);
        System.out.println("level: " + getLevel(path));
    }

    private static void printPath(LinkedList<GPSNode> path) {
        while (!path.isEmpty()) {
            System.out.println(path.pop().getState().getRepresentation());
            System.out.println("");
        }
    }

    private static void properUse() {
        System.out.println("\nProper use: 'sokoban <file_path> <algorithm> <heuristic>'.\n" +
                "file_path: path to plain text file containing initial board configuration.\n" +
                "algorithm: algorithm for problem solver to use. Options are: BFS|DFS|IDDFS|GREEDY|ASTAR|IDASTAR.\n" +
                "heuristic: heuristic to use if algorithm is informed. Mandatory if algorithm = GREEDY|ASTAR|IDASTAR, optional otherwise.\n" +
                "           options are: distance|path|place");
    }

    private static String getLevel(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }
}
