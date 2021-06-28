package model;

/**
 * The Samurai class implements the class Puzzle and represents a Samurai-Sudoku by representing it as an array of 5 ninesquaures.
 * A Samurai consists of 5 Ninesquares.
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */

public class Samurai extends Puzzle{

    private Ninesquare[] ninesquares;

    /**
     * Empty constructor for creating an empty Samurai
     */
    public Samurai (){
        //calls the standard constructor with all entry set 0
        this("new Samurai", Difficulty.NORMAL, Type.SAMURAI, false, 0, "1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-x1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-x1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-x1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-x1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-");
    }

    /**
     * Constructor for the class Samurai, gets content as a String and builds 5 Ninesquares
     * @param name name of Samurai
     * @param difficulty difficulty of Samurai
     * @param type type (SAMURAI)
     * @param solved is the Samurai already solved
     * @param timeUsed time already used playing
     * @param contentString content of the Samurai
     */
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

    /**
     * Returns Tile at given position
     * @param nsqFieldNr ninesquarefieldnumber
     * @param row row
     * @param col column
     * @return Tile at a given position
     */
    @Override
    public Tile getTile(int nsqFieldNr, int row, int col) {
        return ninesquares[nsqFieldNr].getTile(99, row, col);
    }

    /**
     * Returns the area a Tile belongs to as an int
     * @param nsqFieldNr ninesquarefieldnumber
     * @param row row
     * @param col column
     * @return area a Tile belongs to
     */
    @Override
    public int getBelongsToArea(int nsqFieldNr, int row, int col) {
        return ninesquares[nsqFieldNr].getBelongsToArea(99, row, col);
    }

    /**
     * Sets an entry for at a given position and checks if it leads to conflicts with other entries
     * @param nsqFieldNr ninesquarefieldnumber
     * @param selectedRow row
     * @param selectedCol column
     * @param entry entry that should be set
     */
    @Override
    public void setEntry(int nsqFieldNr, int selectedRow, int selectedCol, int entry) {
        ninesquares[nsqFieldNr].setEntry(99, selectedRow, selectedCol, entry);
        fillOverlap(nsqFieldNr, selectedRow, selectedCol, entry);
    }

    /**
     * Deletes an entry at a given position and checks if there are conflicts within the other entries
     * @param nsqFieldNr ninesquarefieldnumber
     * @param selectedRow row
     * @param selectedCol column
     */
    @Override
    public void deleteEntry(int nsqFieldNr, int selectedRow, int selectedCol) {
        ninesquares[nsqFieldNr].deleteEntry(99, selectedRow, selectedCol);
        clearOverlap(nsqFieldNr, selectedRow, selectedCol);
    }

    /**
     * Adds an entry to another Ninesquare involved where Ninesquares overlap
     * @param nsqFieldNr ninesqurefieldnumber entry was set at
     * @param selectedRow row entry was set at
     * @param selectedCol column entry was set at
     * @param entry entry that was set
     */
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
            ninesquares[2].setEntry(99, selectedRow+6, selectedCol+6, entry);
        }
    }


    /**
     * Deletes an entry from another Ninesquare invloved where Ninesquares overlap
     * @param nsqFieldNr ninesqurefield entry was deleted at
     * @param selectedRow row entry was deleted at
     * @param selectedCol column entry was deleted at
     */
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
            ninesquares[2].deleteEntry(99, selectedRow+6, selectedCol+6);
        }
    }

    /**
     * Returns Ninesquare with given ninesquarefilednumber
     * @param nsqFieldNr ninesquarefilednumber
     * @return Ninesquare with given number
     */
    public Ninesquare getNinesquare(int nsqFieldNr) {
        return ninesquares[nsqFieldNr];
    }

    /**
     * Returns a selected row of a Samurai as Tile-Array
     * @param nsqFieldNr ninesquarefieldnumber
     * @param row row
     * @return Tiles of a row as Array
     */
    @Override
    public Tile[] getRow(int nsqFieldNr, int row) {
        return ninesquares[nsqFieldNr].getRow(99, row);
    }


    /**
     * Returns a selected column of a Samurai as Tile-Array
     * @param nsqFieldNr ninesquarefieldnumber
     * @param col column
     * @return Tiles of a column as Array
     */
    @Override
    public Tile[] getCol(int nsqFieldNr, int col) {
        return ninesquares[nsqFieldNr].getCol(99, col);
    }

    /**
     * Returns all Tiles that belong to the same area as the Tile at the given position as Tile-Array
     * @param nsqFieldNr ninesquarefieldnumber
     * @param row row
     * @param col col
     * @return Tiles of an area as Array
     */
    @Override
    public Tile[] getArea(int nsqFieldNr, int row, int col) {
        return ninesquares[nsqFieldNr].getArea(99, row, col);
    }

    /**
     * Returns an Export-String of a Samurai, is needed for saving a Samurai to disc
     * @return Export-String of a Samurai
     */
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

    /**
     * Checks if a Samurai is solved
     * @return if Samurai is solved
     */
    @Override
    public boolean isSolved() {
        for (Ninesquare i : ninesquares) {
            if (!i.isSolved()) return false;
        }
        solved = true;
        return true;
    }

    public void checkConflicts() {
        for (int nsqFieldNr = 0; nsqFieldNr < 5; nsqFieldNr++){
            ninesquares[nsqFieldNr].checkConflicts();
        }
    }

    /**
     * Converts a Samurai object to a String
     * @return String of Samurai
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(ninesquares[i]);
            sb.append("\n ---------------------------" + i + "---------------------- \n");
        }
        return sb.toString();
    }






}
