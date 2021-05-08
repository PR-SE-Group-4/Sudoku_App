import javax.swing.*;
import java.awt.*;

public class SudokuField extends JPanel {

    Puzzle puzzle;
    Graphics2D graphics;
    int selectedCol;
    int selectedRow;
    int tileWidth = 40;
    int tileHeight = 40;
    boolean showConflicts;



    public SudokuField(Puzzle puzzle) {
        this.puzzle = puzzle;
        selectedCol = 0;
        selectedRow = 0;
        showConflicts = false;
    }

    protected Color getTileColor(int square){
        switch (square) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.GRAY;
            case 3:
                return Color.blue;
            case 4:
                return Color.red;
            case 5:
                return Color.yellow;
            case 6:
                return Color.magenta;
            case 7:
                return Color.pink;
            case 8:
                return Color.CYAN;
            case 9:
                return Color.ORANGE;
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

    public void inputActionListener(int x, int y, int value) {

    }
}
