package ar.edu.itba.sia.main;

import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.interfaces.EmptyHeuristic;
import ar.edu.itba.sia.interfaces.Heuristic;
import ar.edu.itba.sia.interfaces.SearchStrategy;
import ar.edu.itba.sia.problem.BoardParser;
import ar.edu.itba.sia.problem.SokobanProblem;
import ar.edu.itba.sia.problem.SokobanState;
import ar.edu.itba.sia.problem.heuristic.DistanceHeuristic;
import ar.edu.itba.sia.problem.heuristic.InPlaceHeuristic;
import ar.edu.itba.sia.problem.heuristic.PathHeuristic;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainTest
{
    @Test
    public void testAllStrategiesAndHeuristics() {
        Path resourceDirectory = Paths.get("src","test", "java", "resources");
        System.out.println(resourceDirectory.toString() + "/informatika01.sok");
        final SokobanState state = BoardParser.getStateFromFile(resourceDirectory.toString() + "/informatika01.sok");
        ArrayList<Heuristic> heuristics = new ArrayList<>();
        heuristics.add(new EmptyHeuristic());
        heuristics.add(new InPlaceHeuristic());
        heuristics.add(new DistanceHeuristic());
        heuristics.add(new PathHeuristic());

        for (SearchStrategy strategy: SearchStrategy.values()){
            GPSEngine searchEngine = new GPSEngine(strategy);
            for (Heuristic heuristic: heuristics){
                System.out.println("Running with " + strategy.name() + " uring heuristic: " + heuristic.getClass().getSimpleName());
                searchEngine.findSolution(new SokobanProblem(state), heuristic);
//                searchEngine
                System.out.println("Finished running!");
            }
        }
        System.out.println("Main test ran successfully!");
    }
}
