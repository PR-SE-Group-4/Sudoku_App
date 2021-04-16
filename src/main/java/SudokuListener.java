import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;

public class SudokuListener extends MouseInputAdapter implements KeyListener, ActionListener {

    int x;
    int y;
    int value;
    SudokuField sudokuField;

    public SudokuListener(SudokuField sudokuField) {
        this.sudokuField = sudokuField;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            y = e.getY();
            x = e.getX();

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        value = Integer.valueOf(((JButton) e.getSource()).getText());
        sudokuField.inputActionListener(x, y, value);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
