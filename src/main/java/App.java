import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class App {

    public static void main(String[] args) throws Exception {

        Loader.setup();
        Puzzle puzzle = Loader.loadPuzzle(0);

        SudokuUI sudokuUI = new SudokuUI(puzzle);
        System.out.println(puzzle);
        System.out.println(puzzle.export());
    }




}

