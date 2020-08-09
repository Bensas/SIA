package ar.edu.itba.sia.gps.utils;

import ar.edu.itba.sia.gps.GPSEngine;
import ar.edu.itba.sia.interfaces.Heuristic;
import ar.edu.itba.sia.interfaces.Problem;

public class TestUtils {

    public static void runEngineTiming(GPSEngine engine, String name){
        System.out.println("Finding " + name + " solution");
        long start = System.currentTimeMillis();
        engine.findSolution();
        double time = (System.currentTimeMillis()- start)/ 1000.0;
        System.out.println(name + " time "+ time + "s");
    }
}
