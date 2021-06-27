package app;

import model.Puzzle;


public class App {

    public static void main(String[] args) throws Exception {

        Loader.setup();

        new view.SudokuUI();

    }




}

