package app;

import model.Puzzle;


public class App {

    public static void main(String[] args) throws Exception {

        Loader.setup();
        Puzzle puzzle = Loader.loadPuzzle(0);

        view.SudokuUI sudokuUI = new view.SudokuUI(puzzle);
        System.out.println(puzzle);
        System.out.println(puzzle.export());
    }




}

