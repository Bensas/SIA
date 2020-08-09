package ar.edu.itba.sia.problem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BoardParser {

    public static SokobanState getStateFromFile(String filePath){
        ArrayList<Character> fileChars = new ArrayList<>();
        try{
            FileReader reader = new FileReader(filePath);
            int currentChar;
            int currentX = 1;
            int currentY = 1;
            int maxValue = 0;
            while ((currentChar = reader.read()) != -1){
                fileChars.add((char)currentChar);
                currentX++;
                if (currentChar == '\n'){
                    if (currentX > maxValue)
                        maxValue = currentX;
                    currentX = 1;
                    currentY++;
                }
            }
            if (currentY > maxValue)
                maxValue = currentY;
            return getBoardFromCharList(maxValue, fileChars);
        } catch (FileNotFoundException e){
            System.out.println("Board file at " + filePath + " was not found! :(");
            return null;
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static SokobanState getBoardFromCharList(int boardWidth, List<Character> input){
        Board board = new Board(boardWidth);
        HashSet<Position> cubePositions = new HashSet<>();
        Position playerPosition = null;
        int currentX = 0;
        int currentY = boardWidth - 1;
        try{
            for (Character currentChar: input){
                switch (currentChar){
                    case '#':
                        board.setElemAt(currentX, currentY, Element.Wall);
                        currentX++;
                        break;
                    case ' ':
                        currentX++;
                        break;
                    case '.':
                        board.setElemAt(currentX, currentY, Element.Target);
                        currentX++;
                        break;
                    case '\n':
                        currentX = 0;
                        currentY--;
                        break;
                    case '@':
                        if (playerPosition != null)
                            throw new DuplicatePlayerException();
                        playerPosition = new Position(currentX, currentY);
                        currentX++;
                        break;
                    case '$':
                        cubePositions.add(new Position(currentX, currentY));
                        currentX++;
                        break;
                }
            }
            SokobanState result = new SokobanState(board);
            result.setCubePositions(cubePositions);
            result.setPlayerPosition(playerPosition);
            return result;
        } catch (InvalidBoardException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
