package view;

import model.Puzzle;

import java.awt.*;

/**
 * The NinesquareField class extends the class SudokuField and draws a Classic or a Freeform Sudoku
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */
public class NinesquareField extends SudokuField {

    /**
     * Constructor of the class NinesquareField
     * @param puzzle Sudoku-Puzzle that should be drawn
     */
    public NinesquareField(Puzzle puzzle) {
        super(puzzle);
        tileWidth = 70;
        tileHeight = 70;
        this.setPreferredSize(new Dimension(tileWidth * 9 , tileHeight * 9 ));
        gameFont = new Font("Verdana",Font.BOLD, 28);
        gameFont2 = new Font("Verdana", Font.BOLD, 30);
    }

    /**
     * Draws an Classic or Freeform Sudoku by calling createComponent implemented in SudokuField
     * @param g Graphics
     */
    @Override
    public void createComponent(Graphics g) {
        createComponent(g, 99);
    }

    /**
     * Sets or deletes an entered entry at a given position
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @param value entered value
     */
    @Override
    public void inputActionListener(int x, int y, int value) {

        setSelectedRowCol(x,y);

        if (selectedCol != -1 && selectedRow != -1 && value != -1) {
            puzzle.setEntry(99, selectedRow, selectedCol, value);
        }
        selectedRow = -1;
        selectedCol = -1;
    }

    /**
     * Sets selected row and selected col depending on the coordinates
     * @param x x-Coordinate
     * @param y y-Coordinate
     */
    @Override
    public void setSelectedRowCol(int x, int y) {
        selectedField = 99;
        selectedCol = x / tileWidth;
        selectedRow = y / tileHeight;

    }



}