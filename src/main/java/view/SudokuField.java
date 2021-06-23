package view;


import model.Puzzle;
import javax.swing.*;
import java.awt.*;

public abstract class SudokuField extends JPanel {

    protected Puzzle puzzle;
    protected Graphics2D graphics;
    protected int selectedCol;
    protected int selectedRow;
    protected int selectedField;
    protected int tileWidth;
    protected int tileHeight;
    protected boolean showConflicts;
    protected Font gameFont;
    protected Font gameFont2;
    protected int startx;
    protected int starty;



    public SudokuField(Puzzle puzzle) {
        this.puzzle = puzzle;
        selectedCol = -1;
        selectedRow = -1;
        showConflicts = false;
        gameFont = new Font("Verdana",Font.BOLD, 15);
        gameFont2 = new Font("Verdana", Font.BOLD, 17);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        graphics.setColor(new Color (175,210,245));
        graphics.fillRect(0,0,this.getWidth(), this.getHeight());
        createComponent(graphics);
    }

    protected abstract void  createComponent(Graphics g);

    protected void createComponent(Graphics g, int fieldnumber){
        setStartXY(fieldnumber);
        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                String input = String.valueOf(puzzle.getTile(fieldnumber, row, col).getEntry());
                boolean hint = puzzle.getTile(fieldnumber, row, col).isHint();

                Graphics2D coloredtile = (Graphics2D) g;
                int square = puzzle.getColor(fieldnumber, row, col);

                coloredtile.setColor(getTileColor(square));
                graphics.setFont(gameFont);
                if (!puzzle.getTile(fieldnumber, row, col).isChangeable()) {
                    graphics.setFont(gameFont2);
                }
                graphics.fillRect(startx + col * tileWidth, starty + row * tileHeight, tileWidth, tileHeight);

                if(!showConflicts && !input.equals("0") && !hint) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(input, startx + (col * tileWidth) + (tileWidth / 2) - 5, starty + (row * tileHeight) + (tileHeight / 2) + 10);

                } else if (showConflicts && !input.equals("0") && !hint){

                    boolean conflicted = puzzle.getTile(fieldnumber, row, col).isConflicted();
                    boolean overlapConflicted = overlapConflicted(fieldnumber, row, col);

                    if (conflicted || overlapConflicted ){
                        graphics.setColor(Color.RED);
                        graphics.fillRect(startx + col * tileWidth , starty + row * tileHeight, tileWidth , tileHeight);
                        graphics.setColor(Color.WHITE);
                        graphics.drawString(input, startx + (col * tileWidth) + (tileWidth / 2) - 5, starty + (row * tileHeight) + (tileHeight / 2) + 10);

                    } else {
                        graphics.setColor(Color.BLACK);
                        graphics.drawString(input, startx + (col * tileWidth) + (tileWidth / 2) - 5, starty + (row * tileHeight) + (tileHeight / 2) + 10);
                    }
                } else if (hint){
                    graphics.setColor(new Color(85,107,47));
                    graphics.fillRect(startx + col * tileWidth , starty + row * tileHeight, tileWidth , tileHeight);
                    graphics.setColor(Color.WHITE);
                    graphics.drawString(input, (col * tileWidth) + (tileWidth / 2) - 5, (row * tileHeight) + (tileHeight / 2) + 10);
                    puzzle.getTile(fieldnumber,row, col).setHint(false);
                }
                g.setColor(new Color (175,210,245));

                //Draw vertical lines
                for (int x = startx; x <= startx  +  (tileWidth * 9); x += tileWidth) {
                    if ((x / tileWidth) % 3 == 0) {
                        graphics.setStroke(new BasicStroke(4));
                        graphics.drawLine(x, starty, x, tileHeight * 9 + starty);

                    } else {
                        graphics.setStroke(new BasicStroke(1));
                        graphics.drawLine(x, starty, x, tileHeight * 9 + starty);
                    }
                }

                //Draw horizontal lines
                for (int y = starty;  y <= starty + (tileHeight * 9); y += tileHeight) {

                    if ((y / tileHeight) % 3 == 0) {
                        graphics.setStroke(new BasicStroke(4));
                        graphics.drawLine(startx, y, tileWidth * 9 + startx, y);

                    } else {
                        graphics.setStroke(new BasicStroke(1));
                        graphics.drawLine(startx, y, tileWidth * 9 + startx, y);

                    }
                }
                markSelectedTile(fieldnumber);
            }
        }
    }

    protected void markSelectedTile(int fieldnumber){
        if (selectedField == fieldnumber) {

            if (selectedRow != -1 && selectedCol != -1) {
                if (puzzle.getTile(selectedField, selectedRow, selectedCol).isChangeable()) {

                    graphics.setColor(Color.blue);

                    graphics.setStroke(new BasicStroke(3));
                    int i = 3;

                    graphics.drawLine(startx + selectedCol * tileWidth + i  , starty + selectedRow * tileHeight + i , startx +selectedCol * tileWidth + i , starty +selectedRow * tileHeight + tileHeight - i );
                    graphics.drawLine(startx +selectedCol * tileWidth + tileWidth - i , starty +selectedRow * tileHeight+ i , startx +selectedCol * tileWidth + tileWidth- i , starty +selectedRow * tileHeight + tileHeight - i );
                    graphics.drawLine(startx +selectedCol * tileWidth + i , starty +selectedRow * tileHeight + i  , startx +selectedCol * tileWidth + tileWidth- i , starty +selectedRow * tileHeight + i  );
                    graphics.drawLine(startx +selectedCol * tileWidth + i , starty +selectedRow * tileHeight + tileHeight-i , startx +selectedCol * tileWidth + tileWidth- i , starty + selectedRow * tileHeight + tileHeight - i );
                }
            }
        }
    }

    protected boolean overlapConflicted(int fieldnumber, int row, int col){
        return false;
    }

    protected void setStartXY(int fieldnumber){
        switch (fieldnumber) {
            case 0:
            case 99:
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
    }

    protected Color getTileColor(int square){
        switch (square) {
            case 0:
                return Color.BLACK;
            case 1:
                return new Color(223,255,224); //green
            case 2:
                return new Color(203,203,203); //grey
            case 3:
                return new Color(198, 226,247); //blue
            case 4:
                return new Color(255,159,159); //red
            case 5:
                return new Color(254,255,195); //yellow
            case 6:
                return new Color(252,200,238); //pink
            case 7:
                return new Color(168,238,238); //cyan
            case 8:
                return new Color(255,221,124); //orange
            case 9:
                return new Color(203,176,243); //purple
            case 10:
                return Color.WHITE;
            default:
                break;
        }
        return Color.WHITE;
    }

    public boolean isShowConflicts() {
        return showConflicts;
    }

    public void setShowConflicts(boolean showConflicts) {
        this.showConflicts = showConflicts;
    }

    public abstract void inputActionListener(int x, int y, int value);

    public abstract void setSelectedRowCol(int x, int y);
}
