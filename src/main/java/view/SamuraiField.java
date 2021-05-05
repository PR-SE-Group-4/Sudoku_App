package view;

import model.Puzzle;

import java.awt.*;



public class SamuraiField extends SudokuField {

    NinesquareField ninesquare1;
    NinesquareField ninesquare2;
    NinesquareField ninesquare3;
    NinesquareField ninesquare4;
    NinesquareField ninesquare5;

    public SamuraiField(Puzzle puzzle) {
        super(puzzle);
        this.setPreferredSize(new Dimension(900, 900));
        this.setBackground(new Color(120,120,255));


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

                graphics.fillRect(startx + col * tileWidth , starty + row * tileHeight, tileWidth , tileHeight);

                if (!input.equals("0")) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(input, startx + (col * tileWidth) + (tileWidth / 2) - 5, starty + (row * tileHeight) + (tileHeight / 2) + 10);

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
