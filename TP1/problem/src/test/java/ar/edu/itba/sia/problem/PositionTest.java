package ar.edu.itba.sia.problem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import sun.jvm.hotspot.utilities.Assert;

import java.util.HashSet;

public class PositionTest {

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    @Test
    public void testMove(){
        Position pos = new Position(0, 0);
        pos.move(Direction.Up);
        Assert.that(pos.y == 1 && pos.x == 0, "Position shoud be (0,1)");
        pos.move(Direction.Down);
        Assert.that(pos.y == 0 && pos.x == 0, "Position shoud be (0,0)");
        pos.move(Direction.Right);
        Assert.that(pos.y == 0 && pos.x == 1, "Position shoud be (1,0)");
        pos.move(Direction.Left);
        Assert.that(pos.y == 0 && pos.x == 0, "Position shoud be (0,0)");
    }

    @Test
    public void testHash(){
        HashSet<Position> positions = new HashSet<>();
        positions.add(new Position(-203, 4));
        positions.add(new Position(0, 0));
        positions.add(new Position(-203, 4));
        Assert.that(positions.size() == 2, "Duplicate elements in HashSet (check hash function)");
    }
}


