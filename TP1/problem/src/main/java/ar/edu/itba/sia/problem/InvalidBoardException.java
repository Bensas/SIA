package ar.edu.itba.sia.problem;

public class InvalidBoardException extends Exception{
    @Override
    public String getMessage() {
        return "There was a problem while parsing the board";
    }
}
