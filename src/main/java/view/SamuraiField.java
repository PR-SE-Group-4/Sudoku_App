package view;

import model.Puzzle;
import java.awt.*;



public class SamuraiField extends SudokuField {

    public SamuraiField(Puzzle puzzle) {
        super(puzzle);
        tileWidth = 40;
        tileHeight = 40;
        this.setPreferredSize(new Dimension(super.tileWidth * 21, super.tileHeight * 21));

    }

    protected void createComponent(Graphics g){

        for (int fieldnumber = 0; fieldnumber < 5; fieldnumber++) {
            createComponent(g,fieldnumber);
        }
    }

    public void inputActionListener(int x, int y, int value) {

        setSelectedRowCol(x, y);
        if (selectedCol != -1 && selectedRow != -1) {
            System.out.println("fields: " + getFieldNr(x,y) + " value " + value + " row " + selectedRow + " col " + selectedCol);
            puzzle.setEntry( getFieldNr(x, y), selectedRow, selectedCol, value);

            selectedCol = -1;
            selectedRow = -1;
        }
    }

    //Calculate fieldnumber of selected coordinates
    public int getFieldNr(int x, int y) {
        int fieldnr = 99;

        if (x <= getWidth()/7*3 && y <= getHeight()/7*2 || x <= getWidth()/7*2 && y <= getHeight()/7*3) {
            fieldnr = 0;
        }
        else if ( getWidth()/7*4 < x && x <= getWidth() &&  y <= getHeight()/7*2 || getWidth()/7*5 < x && x <= getWidth() && y <= getHeight()/7*3) {
            fieldnr = 1;
        }

        else if (getWidth()/7*2 < x && x <= getWidth()/7*5 && y >= getHeight()/7*2 && y <= getHeight()/7*4 || getWidth()/7*3 < x && x < getWidth()/7*4 && getHeight()/7*4 < y && getHeight()/7*5 > y) {
            fieldnr = 2;

        }

        else if (x < getWidth()/7*3 && y <= getHeight() && y >= getHeight()/7*4) {
            fieldnr = 3;

        }

        else if (x > getWidth()/7*4 && x < getWidth() && y <= getHeight() && y >= getHeight()/7*4) {
            fieldnr = 4;

        }

        return fieldnr;

    }

    public void setSelectedRowCol(int x, int y) {

        selectedField = getFieldNr(x,y);

        switch (selectedField) {
            case 0:
                selectedCol = x / tileWidth;
                selectedRow = y / tileHeight;

                break;
            case 1:
                selectedCol = (x - getWidth()/7*4) / tileWidth;
                selectedRow = y / tileHeight;
                break;
            case 2:
                selectedCol = (x - getWidth()/7*2) / tileWidth;
                selectedRow = (y - getHeight()/7*2) / tileHeight;
                break;
            case 3:
                selectedCol =  x / tileWidth;
                selectedRow = (y - getHeight()/7*4) / tileHeight;
                break;
            case 4:
                selectedCol = (x - getWidth()/7*4) / tileWidth;
                selectedRow = (y - getHeight()/7*4) / tileHeight;
                break;

            default:
                break;
        }

    }

    @Override
    protected boolean overlapConflicted(int nsqFieldNr, int row, int col) {
        if (nsqFieldNr == 2 && row < 3 && col < 3) {
            return puzzle.getTile(0, row + 6, col + 6).isConflicted();
        } else if (nsqFieldNr == 2 && row < 3 && col > 5){
            return puzzle.getTile(1, row+6, col-6).isConflicted();
        } else if (nsqFieldNr == 3 && row < 3 && col > 5) {
            return puzzle.getTile(2, row + 6, col - 6).isConflicted();
        } else if (nsqFieldNr == 4 && row < 3 && col < 3) {
            return puzzle.getTile(2, row + 6, col + 6).isConflicted();
        } else {
            return false;
        }
    }
}
