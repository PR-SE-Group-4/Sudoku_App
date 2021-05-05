import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;


public class SudokuListener extends MouseInputAdapter implements KeyListener, ActionListener {

    static int x;
    static int y;
    static int value;
    SudokuField sudokuField;


    public SudokuListener(SudokuField sudokuField) {

        this.sudokuField = sudokuField;
        x = -1;
        y = -1;
    }


    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            y = e.getY();
            x = e.getX();
            System.out.println("CLICKED");
           // sudokuField.inputActionListener(x, y, 1);
            e.getComponent().repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        int value = -1;
        System.out.println("Touched");
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
            sudokuField.inputActionListener(x, y, value);
        }

        System.out.println("Kein Feld ausgewählt");
    }

    @Override
    public void keyPressed(KeyEvent e) {
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
            sudokuField.inputActionListener(x, y, value);
        }

        System.out.println("Kein Feld ausgewählt");


    }

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
            sudokuField.inputActionListener(x, y, value);
        }

        System.out.println("Kein Feld ausgewählt");

    }
}
