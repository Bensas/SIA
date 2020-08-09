public testMove(){
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