package ar.edu.itba.sia.problem;

public class Position {
    int x;
    int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    Position(Position original){
        this.x = original.x;
        this.y = original.y;
    }

    public void move(Direction dir){
        switch(dir){
            case Up:
                this.y += 1;
                break;
            case Down:
                this.y -= 1;
                break;
            case Left:
                this.x -= 1;
                break;
            case Right:
                this.x += 1;
                break;
        }
    }

    public void moveInOppositeDirection(Direction dir){
        switch(dir){
            case Up:
                this.y -= 1;
                break;
            case Down:
                this.y += 1;
                break;
            case Left:
                this.x += 1;
                break;
            case Right:
                this.x -= 1;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position))
            return false;
        return ((Position)obj).x == x && ((Position)obj).y == y;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + this.x;
        hash = hash * 31 + this.y;
        return hash;
    }
}
