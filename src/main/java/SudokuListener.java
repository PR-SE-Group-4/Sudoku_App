import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SudokuListener extends MouseInputAdapter implements KeyListener, ActionListener {

    static int x;
    static int y;
    static int value;
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
        BufferedReader num = new BufferedReader(new InputStreamReader(System.in));
        try {
            value = Integer.parseInt(num.readLine());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

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
