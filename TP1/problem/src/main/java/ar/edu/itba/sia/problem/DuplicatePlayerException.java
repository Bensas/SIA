package ar.edu.itba.sia.problem;

public class DuplicatePlayerException extends InvalidBoardException{
    @Override
    public String getMessage() {
        return "More than one starting positions for the player was found!";
    }
}
