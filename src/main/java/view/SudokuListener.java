package view;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;

/**
 * The SudokuListener class handles Mouse-Input and Keyboard-Input
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */

public class SudokuListener extends MouseInputAdapter implements KeyListener {

    static int x;
    static int y;
    SudokuField sudokuField;

    /**
     * Constructor for the class SudokuListener
     * @param sudokuField current sudokufield
     */
    public SudokuListener(SudokuField sudokuField) {

        this.sudokuField = sudokuField;
        x = -1;
        y = -1;
    }

    /**
     * Sets selected row and col depending on the coordinates when mouse is clicked
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            y = e.getY();
            x = e.getX();
            sudokuField.setSelectedRowCol(x, y);
            e.getComponent().repaint();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Calls inputActionListener with coordinates and value when key is released
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int value = -1;
        if (x > -1 && y > -1) {
            if (e.getKeyCode() == KeyEvent.VK_NUMPAD1 || e.getKeyCode() == KeyEvent.VK_1) {
                value = 1;
                System.out.println("pressed 1");
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2 || e.getKeyCode() == KeyEvent.VK_2) {
                value = 2;
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3 || e.getKeyCode() == KeyEvent.VK_3) {
                value = 3;
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4 || e.getKeyCode() == KeyEvent.VK_4) {
                value = 4;
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5 || e.getKeyCode() == KeyEvent.VK_5) {
                value = 5;
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6 || e.getKeyCode() == KeyEvent.VK_6) {
                value = 6;
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7 || e.getKeyCode() == KeyEvent.VK_7) {
                value = 7;
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8 || e.getKeyCode() == KeyEvent.VK_8) {
                value = 8;
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9 || e.getKeyCode() == KeyEvent.VK_9) {
                value = 9;
            }
            else  {
                value = 0;
            }
            sudokuField.inputActionListener(x, y, value);
            e.getComponent().repaint();
        } else {
            System.out.println("Kein Feld ausgewählt");
        }

    }
}
