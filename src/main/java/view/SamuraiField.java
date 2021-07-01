package view;

import model.Puzzle;
import java.awt.*;

/**
 * The SamuraiField class extends the class SudokuField and draws a Samurai-Puzzle
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */

public class SamuraiField extends SudokuField {

    /**
     * Constructor for the class SamuraiField
     * @param puzzle Sudoku-Puzzle that should be drawn
     */
    public SamuraiField(Puzzle puzzle) {
        super(puzzle);
        tileWidth =38;
        tileHeight = 38;
        this.setPreferredSize(new Dimension(tileWidth * 21, tileHeight * 21));
        gameFont = new Font("Verdana",Font.BOLD, 20);
        gameFont2 = new Font("Verdana", Font.BOLD, 22);

    }

    /**
     * Draws a Samurai Sudoku by calling createComponent implemented in SudokuField for each ninesquare of the Samurai-Puzzle
     * @param g Graphics
     */
    protected void createComponent(Graphics g){

        for (int fieldnumber = 0; fieldnumber < 5; fieldnumber++) {
            createComponent(g,fieldnumber);
        }
    }

    /**
     * Sets or deletes an entered entry at a given position
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @param value entered value
     */
    public void inputActionListener(int x, int y, int value) {

        setSelectedRowCol(x, y);
        if (selectedCol != -1 && selectedRow != -1 && value != -1) {
            System.out.println("fields: " + getFieldNr(x,y) + " value " + value + " row " + selectedRow + " col " + selectedCol);
            puzzle.setEntry( getFieldNr(x, y), selectedRow, selectedCol, value);

            selectedCol = -1;
            selectedRow = -1;
        }
    }

    /**
     * Returns the ninesquarefieldnumber a Tile belongs to depending on the coordinates
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @return ninesquarefieldnumber
     */
    public int getFieldNr(int x, int y) {
        int fieldnr = 99;

        if (x <= getWidth()/7*3 && y <= getHeight()/7*2 || x <= getWidth()/7*2 && y <= getHeight()/7*3) {
            fieldnr = 0;
        }
        else if ( getWidth()/7*4 < x && x <= getWidth() &&  y <= getHeight()/7*2 || getWidth()/7*5 < x && x <= getWidth() && y <= getHeight()/7*3) {
            fieldnr = 1;
        }

        else if (getWidth()/7*2 < x && x <= getWidth()/7*5 && y >= getHeight()/7*2 && y <= getHeight()/7*4 || getWidth()/7*3 < x && x < getWidth()/7*4 && getHeight()/7*4 < y && getHeight()/7*5 > y) {
            fieldnr = 2;

        }

        else if (x < getWidth()/7*3 && y <= getHeight() && y >= getHeight()/7*4) {
            fieldnr = 3;

        }

        else if (x > getWidth()/7*4 && x < getWidth() && y <= getHeight() && y >= getHeight()/7*4) {
            fieldnr = 4;

        }

        return fieldnr;

    }

    /**
     * Sets selected row and selected col depending on the coordinates and sets selected field by calling getFieldNr
     * @param x x-Coordinate
     * @param y y-Coordinate
     */
    public void setSelectedRowCol(int x, int y) {

        selectedField = getFieldNr(x,y);

        switch (selectedField) {
            case 0:
                selectedCol = x / tileWidth;
                selectedRow = y / tileHeight;

                break;
            case 1:
                selectedCol = (x - getWidth()/7*4) / tileWidth;
                selectedRow = y / tileHeight;
                break;
            case 2:
                selectedCol = (x - getWidth()/7*2) / tileWidth;
                selectedRow = (y - getHeight()/7*2) / tileHeight;
                break;
            case 3:
                selectedCol =  x / tileWidth;
                selectedRow = (y - getHeight()/7*4) / tileHeight;
                break;
            case 4:
                selectedCol = (x - getWidth()/7*4) / tileWidth;
                selectedRow = (y - getHeight()/7*4) / tileHeight;
                break;

            default:
                break;
        }

    }

    /**
     * Checks if a Tile overlaps with another one that is conflicted (for not overpainting a conflict)
     * @param nsqFieldNr ninesquarefieldnumber of the Tile
     * @param row row
     * @param col column
     * @return if an overlapping Tile is conflicted
     */
    @Override
    protected boolean overlapConflicted(int nsqFieldNr, int row, int col) {
        if (nsqFieldNr == 2 && row < 3 && col < 3) {
            return puzzle.getTile(0, row + 6, col + 6).isConflicted();
        } else if (nsqFieldNr == 2 && row < 3 && col > 5){
            return puzzle.getTile(1, row+6, col-6).isConflicted();
        } else if (nsqFieldNr == 3 && row < 3 && col > 5) {
            return puzzle.getTile(2, row + 6, col - 6).isConflicted();
        } else if (nsqFieldNr == 4 && row < 3 && col < 3) {
            return puzzle.getTile(2, row + 6, col + 6).isConflicted();
        } else {
            return false;
        }
    }
}
