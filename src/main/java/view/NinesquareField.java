package view;

import model.Puzzle;

import javax.swing.border.Border;
import java.awt.*;

public class NinesquareField extends SudokuField {




    public NinesquareField(Puzzle puzzle) {
        super(puzzle);
        tileWidth = 70;
        tileHeight = 70;
        this.setPreferredSize(new Dimension(tileWidth * 9 , tileHeight * 9 ));
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





        for (int row = 0; row <=8; row++){
            for (int col = 0; col <= 8; col++) {
                String input = String.valueOf(puzzle.getTile(99, row, col).getEntry());
                boolean hint = puzzle.getTile(99, row, col).isHint();

                Graphics2D coloredtile = (Graphics2D) g;
                int square = puzzle.getColor(99, row, col);

                coloredtile.setColor(getTileColor(square));
                graphics.setFont(gameFont);
                if (puzzle.getTile(99, row, col).isChangeable() == false) {
                    graphics.setFont(gameFont2);
                }
                graphics.fillRect(col * tileWidth + 1, row * tileHeight + 1, tileWidth - 1, tileHeight - 1);

                if(!showConflicts && !input.equals("0") && !hint) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(input, (col * tileWidth) + (tileWidth / 2) - 5, (row * tileHeight) + (tileHeight / 2) + 10);
                } else if (showConflicts && !input.equals("0") && !hint){
                    boolean conflicted = puzzle.getTile(99, row, col).isConflicted();
                    if (conflicted){
                        graphics.setColor(Color.RED);
                        graphics.drawString(input, (col * tileWidth) + (tileWidth / 2) - 5, (row * tileHeight) + (tileHeight / 2) + 10);
                    } else {
                        graphics.setColor(Color.BLACK);
                        graphics.drawString(input, (col * tileWidth) + (tileWidth / 2) - 5, (row * tileHeight) + (tileHeight / 2) + 10);
                    }
                } else if (hint){
                    graphics.setColor(Color.GREEN);
                    graphics.drawString(input, (col * tileWidth) + (tileWidth / 2) - 5, (row * tileHeight) + (tileHeight / 2) + 10);
                    puzzle.getTile(99,row, col).setHint(false);
                }

            }

        }

        g.setColor(new Color (175,210,245));

        for (int x = 0; x <= this.getWidth(); x += tileWidth) {
            if ((x / tileWidth) % 3 == 0) {
                graphics.setStroke(new BasicStroke(4));
                graphics.drawLine(x, 0, x, this.getHeight());

            } else {
                graphics.setStroke(new BasicStroke(1));
                graphics.drawLine(x, 0, x, this.getHeight());
            }
        }

        for (int y = 0; y <= this.getHeight(); y += tileHeight) {

            if ((y / tileHeight) % 3 == 0) {
                graphics.setStroke(new BasicStroke(4));
                graphics.drawLine(0, y, this.getWidth(), y);

            } else {
                graphics.setStroke(new BasicStroke(1));
                graphics.drawLine(0, y, this.getWidth(), y);

            }
        }

        if (selectedCol != -1 && selectedRow != -1) {
        if(puzzle.getTile(99, selectedRow, selectedCol).isChangeable() == true) {

            graphics.setColor(Color.blue);

            graphics.setStroke(new BasicStroke(3));
            int i = 2;

            graphics.drawLine(selectedCol * tileWidth + i , selectedRow * tileHeight + i , selectedCol * tileWidth + i, selectedRow * tileHeight + tileHeight - i);
            graphics.drawLine(selectedCol * tileWidth + tileWidth - i, selectedRow * tileHeight + i, selectedCol * tileWidth + tileWidth - i, selectedRow * tileHeight + tileHeight -i);
            graphics.drawLine(selectedCol * tileWidth + i, selectedRow * tileHeight  + i, selectedCol * tileWidth + tileWidth -i, selectedRow * tileHeight + i );
            graphics.drawLine(selectedCol * tileWidth + i , selectedRow * tileHeight + tileHeight - i, selectedCol * tileWidth + tileWidth -i, selectedRow * tileHeight + tileHeight -i);

            }
        }


    }


    public void inputActionListener(int x, int y, int value) {

        System.out.println( x + " - " + y + " - " + value);
        setSelectedRowCol(x,y);

        if (selectedCol != -1 && selectedRow != -1 && value != -1) {
            puzzle.setEntry(99, selectedRow, selectedCol, value);
        }
        selectedRow = -1;
        selectedRow = -1;
    }
    public void setSelectedRowCol(int x, int y) {

        selectedCol = x / tileWidth;
        selectedRow = y / tileHeight;

    }



}