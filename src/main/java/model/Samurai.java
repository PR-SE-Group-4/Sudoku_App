package model;

public class Samurai extends Puzzle{

    private Ninesquare[] ninesquares;

    // constructor
    public Samurai (String name, Difficulty difficulty, Type type, boolean solved, int timeUsed, String contentString){
        super(name, difficulty, type, solved, timeUsed, contentString);
        //Parser
        ninesquares = new Ninesquare[5];
        String partString;
        for (int nsqFieldNr = 0; nsqFieldNr < 5; nsqFieldNr++) {
            partString = contentString.substring(0+nsqFieldNr*325, 324+nsqFieldNr*325);
            ninesquares[nsqFieldNr] = new Ninesquare(name + nsqFieldNr, difficulty, Type.CLASSIC, false, 0, partString);
        }
    }


    @Override
    public Tile getTile(int nsqFieldNr, int row, int col) {
        return ninesquares[nsqFieldNr].getTile(99, row, col);
    }

    @Override
    public int getColor(int nsqFieldNr, int row, int col) {
        return ninesquares[nsqFieldNr].getColor(99, row, col);
    }

    @Override
    public void setEntry(int nsqFieldNr, int selectedRow, int selectedCol, int entry) {
        ninesquares[nsqFieldNr].setEntry(99, selectedRow, selectedCol, entry);
        fillOverlap(nsqFieldNr, selectedRow, selectedCol, entry);
    }

    @Override
    public void deleteEntry(int nsqFieldNr, int selectedRow, int selectedCol) {
        ninesquares[nsqFieldNr].deleteEntry(99, selectedRow, selectedCol);
        clearOverlap(nsqFieldNr, selectedRow, selectedCol);
    }

    // SAMURAI EXCLUSIVE: adds entry to another ninesquare involved
    private void fillOverlap(int nsqFieldNr, int selectedRow, int selectedCol, int entry) {
        if (nsqFieldNr == 0 &&
                selectedRow >= 6 &&
                selectedCol >= 6) { // upper left 9sq, lower right corner
            ninesquares[2].setEntry(99, selectedRow-6, selectedCol-6, entry);
        } else if (nsqFieldNr == 1 &&
                selectedRow >= 6 &&
                selectedCol < 3) {  // upper right 9sq, lower left corner
            ninesquares[2].setEntry(99, selectedRow-6, selectedCol+6, entry);
        } else if (nsqFieldNr == 2 &&
                selectedRow < 3 &&
                selectedCol < 3) { // central 9sq, upper left corner
            ninesquares[0].setEntry(99, selectedRow+6, selectedCol+6, entry);
        } else if (nsqFieldNr == 2 &&
                selectedRow < 3 &&
                selectedCol >= 6) { // central 9sq, upper right corner
            ninesquares[1].setEntry(99, selectedRow+6, selectedCol-6, entry);
        } else if (nsqFieldNr == 2 &&
                selectedRow >= 6 &&
                selectedCol < 3) { // central 9sq, lower left corner
            ninesquares[3].setEntry(99, selectedRow-6, selectedCol+6, entry);
        } else if (nsqFieldNr == 2 &&
                selectedRow >= 6 &&
                selectedCol >= 6) { // central 9sq, lower right corner
            ninesquares[4].setEntry(99, selectedRow-6, selectedCol-6, entry);
        } else if (nsqFieldNr == 3 &&
                selectedRow < 3 &&
                selectedCol >= 6) { // lower left 9sq, upper right corner
            ninesquares[2].setEntry(99, selectedRow+6, selectedCol-6, entry);
        } else if (nsqFieldNr == 4 &&
                selectedRow < 3 &&
                selectedCol < 3) { // lower right 9sq, upper left corner
            ninesquares[0].setEntry(99, selectedRow+6, selectedCol+6, entry);
        }
    }

    // SAMURAI EXCLUSIVE: deletes entry from another ninesquare involved
    private void clearOverlap(int nsqFieldNr, int selectedRow, int selectedCol){
        if (nsqFieldNr == 0 &&
                selectedRow >= 6 &&
                selectedCol >= 6) { // upper left 9sq, lower right corner
            ninesquares[2].deleteEntry(99, selectedRow-6, selectedCol-6);
        } else if (nsqFieldNr == 1 &&
                selectedRow >= 6 &&
                selectedCol < 3) {  // upper right 9sq, lower left corner
            ninesquares[2].deleteEntry(99, selectedRow-6, selectedCol+6);
        } else if (nsqFieldNr == 2 &&
                selectedRow < 3 &&
                selectedCol < 3) { // central 9sq, upper left corner
            ninesquares[0].deleteEntry(99, selectedRow+6, selectedCol+6);
        } else if (nsqFieldNr == 2 &&
                selectedRow < 3 &&
                selectedCol >= 6) { // central 9sq, upper right corner
            ninesquares[1].deleteEntry(99, selectedRow+6, selectedCol-6);
        } else if (nsqFieldNr == 2 &&
                selectedRow >= 6 &&
                selectedCol < 3) { // central 9sq, lower left corner
            ninesquares[3].deleteEntry(99, selectedRow-6, selectedCol+6);
        } else if (nsqFieldNr == 2 &&
                selectedRow >= 6 &&
                selectedCol >= 6) { // central 9sq, lower right corner
            ninesquares[4].deleteEntry(99, selectedRow-6, selectedCol-6);
        } else if (nsqFieldNr == 3 &&
                selectedRow < 3 &&
                selectedCol >= 6) { // lower left 9sq, upper right corner
            ninesquares[2].deleteEntry(99, selectedRow+6, selectedCol-6);
        } else if (nsqFieldNr == 4 &&
                selectedRow < 3 &&
                selectedCol < 3) { // lower right 9sq, upper left corner
            ninesquares[0].deleteEntry(99, selectedRow+6, selectedCol+6);
        }
    }

    @Override
    public Tile[] getRow(int nsqFieldNr, int row) {
        return ninesquares[nsqFieldNr].getRow(99, row);
    }

    @Override
    public Tile[] getCol(int nsqFieldNr, int col) {
        return ninesquares[nsqFieldNr].getCol(99, col);
    }

    @Override
    public Tile[] getArea(int nsqFieldNr, int row, int col) {
        return ninesquares[nsqFieldNr].getArea(99, row, col);
    }

    @Override
    public String export() {
        StringBuilder sb = new StringBuilder();
        sb.append(difficulty).append(";").append(type).append(";");
        sb.append(solved).append(";").append(String.format("%06d", timeUsed)).append(";");
        for (Ninesquare i : ninesquares) {
            sb.append(i.exportContentString());
            sb.append("x");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(";");
        return sb.toString();

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(ninesquares[i]);
            sb.append("\n");
        }
        return sb.toString();
    }




}
