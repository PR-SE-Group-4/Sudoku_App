package view;

import model.Puzzle;

import java.awt.*;

public class NinesquareField extends SudokuField {


    public NinesquareField(Puzzle puzzle) {
        super(puzzle);
        tileWidth = 70;
        tileHeight = 70;
        this.setPreferredSize(new Dimension(tileWidth * 9 , tileHeight * 9 ));
    }

    public void createComponent(Graphics g) {
        createComponent(g, 99);
    }


    // Get x and y coordinates to find the col and row which is selected
    public void inputActionListener(int x, int y, int value) {

        setSelectedRowCol(x,y);

        if (selectedCol != -1 && selectedRow != -1 && value != -1) {
            puzzle.setEntry(99, selectedRow, selectedCol, value);
        }
        selectedRow = -1;
        selectedCol = -1;
    }
    public void setSelectedRowCol(int x, int y) {
        selectedField = 99;
        selectedCol = x / tileWidth;
        selectedRow = y / tileHeight;

    }



}