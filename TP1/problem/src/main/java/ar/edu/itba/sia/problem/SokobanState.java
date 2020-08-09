package ar.edu.itba.sia.problem;

import ar.edu.itba.sia.interfaces.State;

import java.util.HashSet;
import java.util.Optional;

public class SokobanState implements State {
    private Board board;
    private HashSet<Position> cubePositions;
    private Position playerPosition;

    public Board getBoard() { return board; }
    public HashSet<Position> getCubePositions() { return cubePositions; }

    SokobanState(Board initialBoard){
        this.board = initialBoard;
    }
    SokobanState(SokobanState state){
        this.board = new Board(state.board);
        this.cubePositions = new HashSet<>(state.cubePositions);
        this.playerPosition = new Position(state.playerPosition);
    }

    @Override
    public String getRepresentation() {
        for (int i = 0; i < board.getWidth(); i++){
            for (int j = 0; j < board.getHeight(); j++){
                System.out.print(board.elemAt(i, j));
            }
            System.out.println("");
        }
        return null;
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
}
