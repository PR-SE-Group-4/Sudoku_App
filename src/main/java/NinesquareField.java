
import java.awt.*;
import java.awt.geom.GeneralPath;

public class NinesquareField extends SudokuField {




    public NinesquareField(Puzzle puzzle) {
        super(puzzle);
        this.setPreferredSize(new Dimension(360, 360));
        selectedRow = -1;
        selectedCol = -1;
        this.addMouseListener(new SudokuListener(new SudokuField(puzzle)));


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

        for (int row = 0; row <=8; row++){
            for (int col = 0; col <= 8; col++) {
                String input = String.valueOf(puzzle.getTile(99, row, col).getEntry());

                Graphics2D coloredtile = (Graphics2D) g;
                int square = puzzle.getColor(99, row, col);

                coloredtile.setColor(getTileColor(square));

                graphics.fillRect(col * tileWidth + 1, row * tileHeight + 1, tileWidth - 1, tileHeight - 1);

                if(!showConflicts && !input.equals("0")) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(input, (col * tileWidth) + (tileWidth / 2) - 5, (row * tileHeight) + (tileHeight / 2) + 10);
                } else if (showConflicts && !input.equals(("0"))){
                    boolean conflicted = puzzle.getTile(99, row, col).isConflicted();
                    if (conflicted){
                        graphics.setColor(Color.RED);
                        graphics.drawString(input, (col * tileWidth) + (tileWidth / 2) - 5, (row * tileHeight) + (tileHeight / 2) + 10);
                    } else {
                        graphics.setColor(Color.BLACK);
                        graphics.drawString(input, (col * tileWidth) + (tileWidth / 2) - 5, (row * tileHeight) + (tileHeight / 2) + 10);
                    }
                }

            }

        }
    }


    public void inputActionListener(int x, int y, int value) {

        selectedCol = x / tileWidth;
        selectedRow = y / tileHeight;
        if (selectedCol != -1 && selectedRow != -1) {
            puzzle.setEntry(99,selectedRow, selectedCol, value);
            repaint();
        }
    }



}