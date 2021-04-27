import javax.swing.*;
import java.awt.*;

public class SudokuField extends JPanel {

    Puzzle puzzle;
    Graphics2D graphics;
    int selectedCol;
    int selectedRow;
    int tileWidth = 40;
    int tileHeight = 40;



    public SudokuField(Puzzle puzzle) {
        this.puzzle = puzzle;
        selectedCol = 0;
        selectedRow = 0;

    }

    public void inputActionListener(int x, int y, int value) {

    }
}
