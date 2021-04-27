public class Samurai extends Puzzle{

    private Ninesquare[] ninesquares;

    // constructor
    public Samurai (String name, Difficulty difficulty, Type type, boolean solved, int timeUsed, String contentString){
        super(name, difficulty, type, solved, timeUsed, contentString);
        //Parser
        ninesquares = new Ninesquare[5];
        System.out.println(contentString);
        String partString;
        for (int nsqFieldNr = 0; nsqFieldNr < 5; nsqFieldNr++) {
            partString = contentString.substring(0+nsqFieldNr*325, 324+nsqFieldNr*325);
            ninesquares[nsqFieldNr] = new Ninesquare(name + nsqFieldNr, difficulty, Type.CLASSIC, false, 0, partString);
        }
    }


    @Override
    public Tile getTile(int nsqFieldNr, int row, int col) {
        return ninesquares[nsqFieldNr].getTile(row, col);
    }

    //TODO ALTLAST
    @Override
    public Tile getTile(int row, int col) {
        return null;
    }

    @Override
    public int getColor(int nsqFieldNr, int row, int col) {
        return ninesquares[nsqFieldNr].getColor(99, row, col);
    }

    //TODO ALTLAST
    @Override
    public int getColor(int row, int col) {
        return 0;
    }

    @Override
    public void setEntry(int nsqFieldNr, int selectedRow, int selectedCol, int entry) {
        ninesquares[nsqFieldNr].setEntry(99, selectedRow, selectedCol, entry);
        fillOverlap(nsqFieldNr, selectedRow, selectedCol, entry);
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

    //TODO ALTLAST
    @Override
    public void setEntry(int selectedRow, int selectedCol, int entry) {
    }

    @Override
    public Tile[] getRow(int nsqFieldNr, int row) {
        return ninesquares[nsqFieldNr].getRow(99, row);
    }

    //TODO ALTLAST
    @Override
    public Tile[] getRow(int row) {
        return new Tile[0];
    }

    @Override
    public Tile[] getCol(int nsqFieldNr, int col) {
        return ninesquares[nsqFieldNr].getCol(99, col);
    }

    //TODO ALTLAST
    @Override
    public Tile[] getCol(int col) {
        return new Tile[0];
    }

    @Override
    public Tile[] getArea(int nsqFieldNr, int row, int col) {
        return ninesquares[nsqFieldNr].getArea(99, row, col);
    }

    //TODO ALTLAST
    @Override
    public Tile[] getArea(int row, int col) {
        return new Tile[0];
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(ninesquares[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    //TODO: checkConflicts, sortTiles,




}
