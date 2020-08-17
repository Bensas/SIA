package ar.edu.itba.sia.problem;

import ar.edu.itba.sia.interfaces.State;

import java.util.HashSet;
import java.util.Optional;

public class SokobanState implements State {
    private Board board;
    private HashSet<Position> cubePositions;
    private Position playerPosition;

    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }

    public HashSet<Position> getCubePositions() { return cubePositions; }
    public void setCubePositions(HashSet<Position> cubePositions) { this.cubePositions = cubePositions; }

    public void setPlayerPosition(Position playerPosition) { this.playerPosition = playerPosition; }

    public SokobanState(Board initialBoard){
        this.board = initialBoard;
        this.cubePositions = new HashSet<>();
    }
    SokobanState(SokobanState state){
        this.board = new Board(state.board);
        this.cubePositions = new HashSet<>(state.cubePositions);
        this.playerPosition = new Position(state.playerPosition);
    }

    @Override
    public String getRepresentation() {
        StringBuilder builder = new StringBuilder();
        for (int j = board.getHeight() - 1; j >= 0; j--){
            for (int i = 0; i < board.getWidth(); i++){
                if (cubePositions.contains(new Position(i, j))){
                    builder.append("$");
                } else if (playerPosition.equals(new Position(i, j))){
                    builder.append("@");
                } else {
                    builder.append(asciiRepresentation(board.getElemAt(i, j)));
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private Character asciiRepresentation(Element elem){
        switch(elem){
            case Wall:
                return '#';
            case Empty:
                return ' ';
            case Target:
                return '.';
        }
        return '?';
    }

    public Optional<State> movePlayer(Direction direction){
        SokobanState newGameState = new SokobanState(this);
        newGameState.playerPosition.move(direction);
        if (!newGameState.board.isValidPosition(newGameState.playerPosition)){
            return Optional.empty();
        }
        if (newGameState.cubePositions.contains(newGameState.playerPosition)){
            Position pushedCubePosition = new Position(newGameState.playerPosition);
            pushedCubePosition.move(direction);
            if (!newGameState.board.isValidPosition(pushedCubePosition) || newGameState.cubePositions.contains(pushedCubePosition)){
                return Optional.empty();
            }
            newGameState.cubePositions.remove(newGameState.playerPosition);
            newGameState.cubePositions.add(pushedCubePosition);
        }
        return Optional.of(newGameState);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SokobanState)) return false;
        SokobanState state = (SokobanState) obj;
        return state.board.equals(this.board) &&
                state.cubePositions.equals(this.cubePositions) &&
                state.playerPosition.equals(this.playerPosition);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + board.hashCode();
        hash = hash * 31 + cubePositions.hashCode();
        hash = hash * 31 + playerPosition.hashCode();
        return hash;
    }
}
