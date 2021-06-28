package view;


import model.Puzzle;
import model.Type;

import javax.swing.*;
import java.awt.*;

/**
 * A base class for drawing a Sudoku-Field
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */
public abstract class SudokuField extends JPanel {

    protected Puzzle puzzle;
    protected Graphics2D graphics;
    protected int selectedCol;
    protected int selectedRow;
    protected int selectedField;
    protected int tileWidth;
    protected int tileHeight;
    protected boolean showConflicts;
    protected Font gameFont;
    protected Font gameFont2;

    //start coordinates for drawing a 9x9 Sudoku-Field
    protected int startx;
    protected int starty;


    /**
     * Constructor for the class SudokuField
     * @param puzzle Sudoku-Puzzle that should be drawn
     */
    public SudokuField(Puzzle puzzle) {
        this.puzzle = puzzle;
        selectedCol = -1;
        selectedRow = -1;
        showConflicts = false;
    }

    /**
     * Draws light-blue background and calls createComponente to draw Sudoku-Puzzle
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        graphics.setColor(new Color (175,210,245));
        graphics.fillRect(0,0,this.getWidth(), this.getHeight());
        createComponent(graphics);
    }

    /**
     * Abstract method to draw a SudokuField
     * @param g Graphics
     */
    protected abstract void  createComponent(Graphics g);

    /**
     * Draws a 9x9 Sudoku-Puzzle
     * @param g Graphics
     * @param fieldnumber ninesquarefield number (relevant for drawing a Samurai)
     */
    protected void createComponent(Graphics g, int fieldnumber){
        setStartXY(fieldnumber);
        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                String input = String.valueOf(puzzle.getTile(fieldnumber, row, col).getEntry());
                boolean hint = puzzle.getTile(fieldnumber, row, col).isHint();

                Graphics2D coloredtile = (Graphics2D) g;
                int square = puzzle.getBelongsToArea(fieldnumber, row, col);

                //set TileColor depending on Typ
                if (puzzle.getType() == Type.FREEFORM){
                    coloredtile.setColor(getTileColorFreeform(square));
                } else {
                    coloredtile.setColor(getTileColorClassic(square));
                }

                graphics.setFont(gameFont);

                //set Font gameFont2 if it is not changeable
                if (!puzzle.getTile(fieldnumber, row, col).isChangeable()) {
                    graphics.setFont(gameFont2);
                }
                //draw Tile color
                graphics.fillRect(startx + col * tileWidth, starty + row * tileHeight, tileWidth, tileHeight);

                //draw input
                if(!showConflicts && !input.equals("0") && !hint) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(input, startx + (col * tileWidth) + (tileWidth / 2) - 10, starty + (row * tileHeight) + (tileHeight / 2) + 10);

                } else if (showConflicts && !input.equals("0") && !hint){

                    boolean conflicted = puzzle.getTile(fieldnumber, row, col).isConflicted();
                    boolean overlapConflicted = overlapConflicted(fieldnumber, row, col);

                    if (conflicted || overlapConflicted ){
                        graphics.setColor(Color.RED);
                        graphics.fillRect(startx + col * tileWidth , starty + row * tileHeight, tileWidth , tileHeight);
                        graphics.setColor(Color.WHITE);
                        graphics.drawString(input, startx + (col * tileWidth) + (tileWidth / 2) - 5, starty + (row * tileHeight) + (tileHeight / 2) + 10);

                    } else {
                        graphics.setColor(Color.BLACK);
                        graphics.drawString(input, startx + (col * tileWidth) + (tileWidth / 2) - 5, starty + (row * tileHeight) + (tileHeight / 2) + 10);
                    }
                } else if (hint){
                    graphics.setColor(new Color(85,107,47));
                    graphics.fillRect(startx + col * tileWidth , starty + row * tileHeight, tileWidth , tileHeight);
                    graphics.setColor(Color.WHITE);
                    graphics.drawString(input, (startx + col * tileWidth) + (tileWidth / 2) - 5, (starty + row * tileHeight) + (tileHeight / 2) + 10);
                    puzzle.getTile(fieldnumber,row, col).setHint(false);
                }
                g.setColor(new Color (175,210,245));

                //Draw vertical lines
                for (int x = startx; x <= startx  +  (tileWidth * 9); x += tileWidth) {
                    if ((x / tileWidth) % 3 == 0) {
                        graphics.setStroke(new BasicStroke(4));
                        graphics.drawLine(x, starty, x, tileHeight * 9 + starty);

                    } else {
                        graphics.setStroke(new BasicStroke(1));
                        graphics.drawLine(x, starty, x, tileHeight * 9 + starty);
                    }
                }

                //Draw horizontal lines
                for (int y = starty;  y <= starty + (tileHeight * 9); y += tileHeight) {

                    if ((y / tileHeight) % 3 == 0) {
                        graphics.setStroke(new BasicStroke(4));
                        graphics.drawLine(startx, y, tileWidth * 9 + startx, y);

                    } else {
                        graphics.setStroke(new BasicStroke(1));
                        graphics.drawLine(startx, y, tileWidth * 9 + startx, y);

                    }
                }
                markSelectedTile(fieldnumber);
            }
        }
    }

    /**
     * Draws a blue frame around the selected Tile
     * @param fieldnumber ninesqurefieldnumber (relevent for drawing a Samurai)
     */
    protected void markSelectedTile(int fieldnumber){
        if (selectedField == fieldnumber) {
            if (selectedRow != -1 && selectedCol != -1) {
                if (puzzle.getTile(selectedField, selectedRow, selectedCol).isChangeable()) {
                    graphics.setColor(Color.blue);
                    graphics.setStroke(new BasicStroke(3));
                    int i = 3;

                    graphics.drawLine(startx + selectedCol * tileWidth + i  , starty + selectedRow * tileHeight + i , startx +selectedCol * tileWidth + i , starty +selectedRow * tileHeight + tileHeight - i );
                    graphics.drawLine(startx +selectedCol * tileWidth + tileWidth - i , starty +selectedRow * tileHeight+ i , startx +selectedCol * tileWidth + tileWidth- i , starty +selectedRow * tileHeight + tileHeight - i );
                    graphics.drawLine(startx +selectedCol * tileWidth + i , starty +selectedRow * tileHeight + i  , startx +selectedCol * tileWidth + tileWidth- i , starty +selectedRow * tileHeight + i  );
                    graphics.drawLine(startx +selectedCol * tileWidth + i , starty +selectedRow * tileHeight + tileHeight-i , startx +selectedCol * tileWidth + tileWidth- i , starty + selectedRow * tileHeight + tileHeight - i );
                }
            }
        }
    }

    /**
     * Returns if a Tile has an overlap that is confilcted, relevant for drawing a Samurai
     * @param fieldnumber ninesquarfieldnumber of the Tile
     * @param row row
     * @param col column
     * @return if an overlapping Tile is conflicted
     */
    protected boolean overlapConflicted(int fieldnumber, int row, int col){
        return false;
    }

    /**
     * Sets the Start-Coordinates for drawing a 9x9 Sudoku
     * @param fieldnumber ninesquarefieldnumber (relevant for drawing a Samurai
     */
    protected void setStartXY(int fieldnumber){
        switch (fieldnumber) {
            case 0:
            case 99:
                startx = 0;
                starty = 0;
                break;
            case 1:
                startx = tileWidth * 12;
                starty = 0;
                break;
            case 2:
                startx = tileWidth * 6;
                starty = tileHeight * 6;
                break;
            case 3:
                startx = 0;
                starty = tileHeight * 12;
                break;
            case 4:
                startx = tileWidth * 12;
                starty = tileHeight * 12;
                break;
            default:
                break;
        }
    }

    /**
     * Returns the Color of a Tile for Classic and Samurai, depending on the area it belongs to
     * @param square area of the Tile
     * @return Color of Tile
     */
    protected Color getTileColorClassic(int square){
        switch (square) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
                return new Color(255,221,124); //orange
            case 2:
            case 4:
            case 6:
            case 8:
                return new Color(254,255,195); //yellow
            default:
                break;
        }
        return Color.WHITE;
    }

    /**
     * Returns the Color of a Tile for Freeform, depending on the area it belongs to
     * @param square area of the Tile
     * @return Color of Tile
     */
    protected Color getTileColorFreeform(int square){
        switch (square) {
            case 0:
                return Color.BLACK;
            case 1:
                return new Color(223,255,224); //green
            case 2:
                return new Color(203,203,203); //grey
            case 3:
                return new Color(198, 226,247); //blue
            case 4:
                return new Color(255,159,159); //red
            case 5:
                return new Color(254,255,195); //yellow
            case 6:
                return new Color(252,200,238); //pink
            case 7:
                return new Color(168,238,238); //cyan
            case 8:
                return new Color(255,221,124); //orange
            case 9:
                return new Color(203,176,243); //purple
            case 10:
                return Color.WHITE;
            default:
                break;
        }
        return Color.WHITE;
    }

    /**
     * Sets showConflicts
     * @param showConflicts showConflicts
     */
    public void setShowConflicts(boolean showConflicts) {
        this.showConflicts = showConflicts;
    }

    /**
     * Sets or deletes an entered entry at a given position
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @param value entered value
     */
    public abstract void inputActionListener(int x, int y, int value);

    /**
     * Sets selected row and selected Col depending on the x- and y-Coordinate
     * @param x x-Coordinate
     * @param y y-Coordinate
     */
    public abstract void setSelectedRowCol(int x, int y);
}
