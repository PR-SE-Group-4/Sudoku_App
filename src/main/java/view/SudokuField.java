package view;

import model.Puzzle;

import javax.swing.*;
import java.awt.*;

public class SudokuField extends JPanel {

    protected Puzzle puzzle;
    protected Graphics2D graphics;
    protected int selectedCol;
    protected int selectedRow;
    protected int tileWidth = 50;
    protected int tileHeight = 50;



    public SudokuField(Puzzle puzzle) {
        this.puzzle = puzzle;
        selectedCol = -1;
        selectedRow = -1;

    }

    public void inputActionListener(int x, int y, int value) {


    }
}
