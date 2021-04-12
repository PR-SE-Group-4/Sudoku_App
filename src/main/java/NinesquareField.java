import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NinesquareField extends JPanel {

    Ninesquare ninesquare = new Ninesquare();
    Graphics2D graphics;
    private int selectedCol;
    private int selectedRow;


    public NinesquareField() {
        this.setPreferredSize(new Dimension(450, 450));
        selectedRow = -1;
        selectedCol = -1;
        this.addMouseListener(new SudokuPanelMouseAdapter());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        graphics.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        graphics.setColor(new Color(0.0f, 0.0f, 0.0f));
        int tileWidth = 50;
        int tileHeight = 50;

        for (int x = 0; x <= this.getWidth(); x += tileWidth) {
            if ((x / tileWidth) % 3 == 0) {
                graphics.setStroke(new BasicStroke(2));
                graphics.drawLine(x, 0, x, this.getHeight());

            } else {
                graphics.setStroke(new BasicStroke(2));
                graphics.drawLine(x, 0, x, this.getHeight());
            }
        }

        for (int y = 0; y <= this.getHeight(); y += tileHeight) {

            if ((y / tileHeight) % 3 == 0) {
                graphics.setStroke(new BasicStroke(2));
                graphics.drawLine(0, y, this.getWidth(), y);

            } else {
                graphics.setStroke(new BasicStroke(2));
                graphics.drawLine(0, y, this.getWidth(), y);

            }
        }

        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                String input = String.valueOf(ninesquare.getTile(row, col).getEntry());

                Graphics2D coloredtile = (Graphics2D) g;
                int square = ninesquare.getColor(row, col);

                switch (square) {
                    case 0:
                        coloredtile.setColor(Color.BLACK);
                        break;
                    case 1:
                        coloredtile.setColor(Color.GREEN);
                        break;
                    case 2:
                        coloredtile.setColor(Color.GRAY);
                        break;
                    case 3:
                        coloredtile.setColor(Color.blue);
                        break;
                    case 4:
                        coloredtile.setColor(Color.red);
                        break;
                    case 5:
                        coloredtile.setColor(Color.yellow);
                        break;
                    case 6:
                        coloredtile.setColor(Color.magenta);
                        break;
                    case 7:
                        coloredtile.setColor(Color.pink);
                        break;
                    case 8:
                        coloredtile.setColor(Color.CYAN);
                        break;
                    case 9:
                        coloredtile.setColor(Color.ORANGE);
                        break;
                    default:
                        break;
                }

                graphics.fillRect(col * tileWidth + 1, row * tileHeight + 1, tileWidth - 1, tileHeight - 1);

                if (!input.equals("0")) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(input, (col * tileWidth) + (tileWidth / 2) - 5, (row * tileHeight) + (tileHeight / 2) + 10);

                }

            }
        }

    }

    public void inputActionListener(String buttonValue) {
        if (selectedCol != -1 && selectedRow != -1) {
            ninesquare.setEntry(selectedRow, selectedCol, buttonValue);
            repaint();
        }
    }


    public class EntryActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputActionListener(((JButton) e.getSource()).getText());
        }

    }

    private class SudokuPanelMouseAdapter extends MouseInputAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                int slotWidth = 50;
                int slotHeight = 50;
                selectedRow = e.getY() / slotHeight;
                selectedCol = e.getX() / slotWidth;
                e.getComponent().repaint();
            }
        }
    }


}