import javax.swing.*;
import java.awt.*;



public class SamuraiField extends SudokuField {

    public SamuraiField(Puzzle puzzle) {
        super(puzzle);
        this.setPreferredSize(new Dimension(900, 900));
        this.setBackground(new Color(147, 147, 227, 255));


    }
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        graphics = (Graphics2D) g;
        graphics.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        createComponent(graphics);

    }
    public void createComponent(Graphics g) {

        int startx = 0;
        int starty = 0;
        graphics = (Graphics2D) g;


        for (int fieldnumber = 0; fieldnumber < 5; fieldnumber++) {

            switch (fieldnumber) {
                case 0:
                    startx = 0;
                    starty = 0;
                    break;
                case 1:
                    startx = tileWidth * 12;
                    starty = 0;
                    break;
                case 2:
                    startx = tileWidth * 6;
                    starty = tileHeight * 6;
                    break;
                case 3:
                    startx = 0;
                    starty = tileHeight * 12;
                    break;
                case 4:
                    startx = tileWidth * 12;
                    starty = tileHeight * 12;
                    break;
                default:
                    break;
            }

        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                String input = String.valueOf(puzzle.getTile(fieldnumber, row, col).getEntry());

                Graphics2D coloredtile = (Graphics2D) g;
                int square = puzzle.getColor(fieldnumber, row, col);

                coloredtile.setColor(getTileColor(square));

                graphics.fillRect(startx + col * tileWidth , starty + row * tileHeight, tileWidth , tileHeight);

                if(!showConflicts && !input.equals("0")) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(input, startx + (col * tileWidth) + (tileWidth / 2) - 5, starty + (row * tileHeight) + (tileHeight / 2) + 10);

                } else if (showConflicts && !input.equals(("0"))){

                    boolean conflicted = puzzle.getTile(fieldnumber, row, col).isConflicted();

                    if (conflicted){
                        graphics.setColor(Color.RED);
                        graphics.drawString(input, startx + (col * tileWidth) + (tileWidth / 2) - 5, starty + (row * tileHeight) + (tileHeight / 2) + 10);

                    } else {
                        graphics.setColor(Color.BLACK);
                        graphics.drawString(input, startx + (col * tileWidth) + (tileWidth / 2) - 5, starty + (row * tileHeight) + (tileHeight / 2) + 10);
                    }
                }

                    g.setColor(Color.black);

                    for (int x = startx; x <= startx  +  (tileWidth * 9); x += tileWidth) {
                        if ((x / tileWidth) % 3 == 0) {
                            graphics.setStroke(new BasicStroke(4));
                            graphics.drawLine(x, starty, x, tileHeight * 9 + starty);

                        } else {
                            graphics.setStroke(new BasicStroke(1));
                            graphics.drawLine(x, starty, x, tileHeight * 9 + starty);
                        }
                    }

                    for (int y = starty;  y <= starty + (tileHeight * 9); y += tileHeight) {

                        if ((y / tileHeight) % 3 == 0) {
                            graphics.setStroke(new BasicStroke(4));
                            graphics.drawLine(startx, y, tileWidth * 9 + startx, y);

                        } else {
                            graphics.setStroke(new BasicStroke(1));
                            graphics.drawLine(startx, y, tileWidth * 9 + startx, y);

                        }
                    }
                }

            }
        }

    }
}
