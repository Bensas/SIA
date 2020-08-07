package ar.edu.itba.sia.gps;

import ar.edu.itba.sia.gps.eightpuzzle.E8HeuristicA;
import ar.edu.itba.sia.gps.eightpuzzle.E8HeuristicB;
import ar.edu.itba.sia.gps.eightpuzzle.E8Problem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.MessageFormat;

import static ar.edu.itba.sia.gps.utils.TestUtils.runEngineTiming;

public class HeuristicGPSTests {
	
	private static GPSEngine betterHeuristicEngine;
	private static GPSEngine worseHeuristicEngine;
	
	
	@BeforeClass
	public static void setUp(){
		betterHeuristicEngine = new GPSEngine(new E8Problem(false), SearchStrategy.ASTAR, E8HeuristicB.instance());
		worseHeuristicEngine = new GPSEngine(new E8Problem(false), SearchStrategy.ASTAR, E8HeuristicA.instance());

		System.out.println("Running Heuristic engines");
		runEngineTiming(betterHeuristicEngine, "Better heuristic");
		runEngineTiming(worseHeuristicEngine, "Worse heuristic");
		System.out.println("Heuristic engine ran, running the tests");
		
	}
	
	@Test
	public void differHDifferExplosionCount(){
		assert betterHeuristicEngine.getExplosionCounter() < worseHeuristicEngine.getExplosionCounter() :
			MessageFormat.format("If differ H, better H has less explosion count than worse H. " +
					"Better H explosion count: {0}, Worse explosion count: {1}",
				betterHeuristicEngine.getExplosionCounter(), worseHeuristicEngine.getExplosionCounter());
	}
	
	@Test
	public void differHDifferExploredStatesCount(){
		assert betterHeuristicEngine.getBestCosts().size() < worseHeuristicEngine.getBestCosts().size() :
			MessageFormat.format("If differ H, Better H has less explored states counter than worse H. " +
					"Better H explored states count: {0}, worse H explored count: {1}",
				betterHeuristicEngine.getBestCosts().size(), worseHeuristicEngine.getBestCosts().size());
	}
	
	@Test
	public void differHSameCostSolution(){
		assert betterHeuristicEngine.getSolutionNode().getCost().equals(worseHeuristicEngine.getSolutionNode().getCost()) :
			MessageFormat.format("If differ H, both H solution have same cost. " +
					"H1 explosion count: {0}, H2 explosion count: {1}",
				betterHeuristicEngine.getSolutionNode().getCost(), worseHeuristicEngine.getSolutionNode().getCost());
	}
	
	@Test
	public void generalTests(){
		GenericTests.solutionFound(betterHeuristicEngine);
		GenericTests.solutionFound(worseHeuristicEngine);
	}
	
	@AfterClass
	public static void tearDown(){
		System.out.println("Heuristic OK");
	}
}
