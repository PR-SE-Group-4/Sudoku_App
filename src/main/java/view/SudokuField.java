package view;

import app.Solver;
import model.Puzzle;

import javax.swing.*;
import java.awt.*;

public abstract class SudokuField extends JPanel {

    protected Puzzle puzzle;
    protected Graphics2D graphics;
    protected int selectedCol;
    protected int selectedRow;
    protected int selectedField;
    protected int tileWidth = 40;
    protected int tileHeight = 40;
    protected boolean showConflicts;
    protected Font gameFont;



    public SudokuField(Puzzle puzzle) {
        this.puzzle = puzzle;
        selectedCol = -1;
        selectedRow = -1;
        showConflicts = false;
        gameFont = new Font("Verdana", Font.BOLD, 16);


    }

    protected Color getTileColor(int square){
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
            default:
                break;
        }
        return Color.WHITE;
    }

    public boolean isShowConflicts() {
        return showConflicts;
    }

    public void setShowConflicts(boolean showConflicts) {
        this.showConflicts = showConflicts;
    }

    public abstract void inputActionListener(int x, int y, int value);

    public abstract void setSelectedRowCol(int x, int y);
}
