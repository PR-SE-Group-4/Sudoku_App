package app;

import model.Ninesquare;
import model.Puzzle;


public class App {

    public static void main(String[] args) throws Exception {

        Loader.setup();
        Puzzle puzzle = Loader.loadPuzzle(1);

        view.SudokuUI sudokuUI = new view.SudokuUI(puzzle);

        Solver.solve(puzzle);

    }




}

