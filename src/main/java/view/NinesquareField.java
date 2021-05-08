package view;

import model.Puzzle;

import javax.swing.border.Border;
import java.awt.*;

public class NinesquareField extends SudokuField {




    public NinesquareField(Puzzle puzzle) {
        super(puzzle);
        this.setPreferredSize(new Dimension(tileWidth * 9, tileHeight * 9));
//        this.setPreferredSize(new Dimension((int) (super.getParent().getBounds().width*0.9), (int) (super.getParent().getBounds().height*0.9)));
        selectedRow = -1;
        selectedCol = -1;


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        graphics.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));

        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        graphics.setColor(new Color(0.0f, 0.0f, 0.0f));

        createComponent(graphics);

    }

    protected void paintComponent(Graphics g, int x, int y) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;

        graphics.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));

        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        graphics.setColor(new Color(0.0f, 0.0f, 0.0f));

        createComponent(graphics);

    }
        public void createComponent(Graphics g) {

            graphics = (Graphics2D) g;


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
                String input = String.valueOf(puzzle.getTile(99, row, col).getEntry());

                Graphics2D coloredtile = (Graphics2D) g;
                int square = puzzle.getColor(99,row, col);

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

    public void inputActionListener(int x, int y, int value) {

        System.out.println( x + " - " + y + " - " + value);
        selectedCol = x / tileWidth;
        selectedRow = y / tileHeight;
        if (selectedCol != -1 && selectedRow != -1) {
            puzzle.setEntry(99, selectedRow, selectedCol, value);
        }
    }



}