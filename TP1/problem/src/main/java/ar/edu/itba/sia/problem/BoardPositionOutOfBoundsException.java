package ar.edu.itba.sia.problem;

public class BoardPositionOutOfBoundsException extends Exception {
    @Override
    public String getMessage() {
        return "The position provided is outside the board!";
    }
}
