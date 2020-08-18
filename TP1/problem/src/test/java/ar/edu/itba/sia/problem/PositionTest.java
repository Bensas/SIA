package ar.edu.itba.sia.problem;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.HashMap;
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
        Assert.assertTrue(pos.y == 1 && pos.x == 0);
        pos.move(Direction.Down);
        Assert.assertTrue(pos.y == 0 && pos.x == 0);
        pos.move(Direction.Right);
        Assert.assertTrue(pos.y == 0 && pos.x == 1);
        pos.move(Direction.Left);
        Assert.assertTrue(pos.y == 0 && pos.x == 0);
    }

    @Test
    public void testSurroundingPositions(){
        Position mPos = new Position(3, 3);
        HashMap<Direction, Position> surroundingPos = Position.getSurroundingPositions(mPos);
        Assert.assertEquals(mPos, new Position(3, 3));
        Assert.assertEquals(surroundingPos.get(Direction.Up), new Position(3, 4));
        Assert.assertEquals(surroundingPos.get(Direction.Down), new Position(3, 2));
        Assert.assertEquals(surroundingPos.get(Direction.Left), new Position(2, 3));
        Assert.assertEquals(surroundingPos.get(Direction.Right), new Position(4, 3));
    }

    @Test
    public void testHash(){
        HashSet<Position> positions = new HashSet<>();
        positions.add(new Position(-203, 4));
        positions.add(new Position(0, 0));
        positions.add(new Position(-203, 4));
        Assert.assertTrue("Duplicate elements in HashSet (check hash function)", positions.size() == 2);
    }
}


