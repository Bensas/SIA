package ar.edu.itba.sia.problem;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BoardParserTest {
    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test: " + description.getMethodName());
        }
    };

    @Test
    public void testEmptyBoard() {
        String board = "    \n  @  \n     \n  \n       \n \n \n   \n  \n "; //MaxWidth: 7, MaxHeight: 10
        String inputFileName = createFileFromString("testEmptyBoard.sok", board);
        SokobanState result = BoardParser.getStateFromFile(inputFileName);
        System.out.println(result.getRepresentation());
    }

    /*
          ###
          #.#
      #####.#####
     ##         ##
    ##  # # # #  ##
    #  ##     ##  #
    # ##  # #  ## #
    #     $@$     #
    ####  ###  ####
       #### ####
     */
    @Test
    public void testSimpleLevel(){
        String board = "      ###\n      #.#\n  #####.#####\n ##         ##\n##  # # # #  ##\n#  ##     ##  #\n# ##  # #  ## #\n#     $@$     #\n####  ###  ####\n   #### ####";
        String inputFileName = createFileFromString("testSimpleLevel.sok", board);
        SokobanState result = BoardParser.getStateFromFile(inputFileName);
        System.out.println(result.getRepresentation());
    }

    private String createFileFromString(String fileName, String contents){
        try {
            File result = new File(fileName);
            if (result.createNewFile()) {
                System.out.println("File created: " + result.getName());
            } else {
                System.out.println("File " + result.getName() +" already exists. Overwriting it...");
            }
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(contents);
            myWriter.close();
            System.out.println("Successfully wrote to " + result.getName());
            return result.getName();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}
