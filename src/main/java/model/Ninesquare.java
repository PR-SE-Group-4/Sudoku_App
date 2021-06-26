package model;

/**
 * The Ninesquare class implements the class Puzzle and represents a 9x9 sudoku.
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */
public class Ninesquare extends Puzzle {
    private Tile[][] content;

    /**
     * Empty constructor for creating an empty Ninesquare
     */
    public Ninesquare () {
        //calls the standard constructor with all entry set 0
        this("new Classic", Difficulty.NORMAL, Type.CLASSIC, false, 0, "1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-1U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5U0-5U0-6U0-6U0-6U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-7U0-7U0-7U0-8U0-8U0-8U0-9U0-9U0-9U0-");
    }

    /**
     * Constructor for the class Ninesquare, gets content as String and builds a Tile-array
     * @param name name of Ninesquare
     * @param difficulty difficulty of Ninesquare
     * @param type type of Ninesquare (CLASSIC or FREEFORM)
     * @param solved is the Ninesquare already solved
     * @param timeUsed time already used playing
     * @param contentString content of the Ninesquare
     */

    public Ninesquare (String name, Difficulty difficulty, Type type, boolean solved, int timeUsed, String contentString) {
        super(name, difficulty, type, solved, timeUsed, contentString);
        //Parser
        content = new Tile[9][9];
        for (int row = 0; row < 9 ; row++) {
            for (int col = 0; col < 9; col++) {
                content[row][col] = new Tile(
                        contentString.charAt(row*36 + col*4)-48,
                        contentString.charAt(row*36 + col*4 + 2)-48,
                        (contentString.charAt(row*36 + col*4 + 1) == 'U')
                );
            }

        }
    }

    /**
     * Returns Tile at given position
     * @param nsqFieldNr ninesquarefieldnumber (irrelevant for Ninesquare)
     * @param row row
     * @param col column
     * @return Tile at a given position
     */

    @Override
    public Tile getTile(int nsqFieldNr, int row, int col) {
        return content[row][col];
    }

    /**
     * Returns the area a Tile belongs to as an int
     * @param nsqFieldNr ninesquarefieldnumber (irrelevant for Ninesquare)
     * @param row row
     * @param col column
     * @return area a Tile belongs to
     */

    @Override
    public int getBelongsToArea(int nsqFieldNr, int row, int col) {return content[row][col].getBelongsToArea();
    }

    /**
     * Sets an entry for at a given position and checks if it leads to conflicts with other entries
     * @param nsqFieldNr ninesquarefieldnumber (irrelevant for Ninesquare)
     * @param selectedRow row
     * @param selectedCol column
     * @param entry entry that should be set
     */
    @Override
    public void setEntry(int nsqFieldNr, int selectedRow, int selectedCol, int entry) {
        content[selectedRow][selectedCol].setEntry(entry);
        checkConflicts ();
    }

    /**
     * Deletes an entry at a given position and checks if there are conflicts within the other entries
     * @param nsqFieldNr ninesquarefieldnumber (irrelevant for Ninesquare)
     * @param selectedRow row
     * @param selectedCol column
     */

    @Override
    public void deleteEntry(int nsqFieldNr, int selectedRow, int selectedCol) {
        content[selectedRow][selectedCol].empty();
        checkConflicts();
    }

    /**
     * Returns a selected row of a Ninesquare as Tile-Array
     * @param nsqFieldNr ninesquarefieldnumber (irrelevant for Ninesquare)
     * @param row row
     * @return Tiles of a row as Array
     */

    @Override
    public Tile[] getRow(int nsqFieldNr, int row) {
        Tile [] tiles = new Tile [9];
        for (int i = 0; i < 9 ; i++) {
            tiles [i] = content [row][i];
        }
        return tiles ;
    }

    /**
     * Returns a selected column of a Ninesquare as Tile-Array
     * @param nsqFieldNr ninesquarefieldnumber (irrelevant for Ninesquare)
     * @param col column
     * @return Tiles of a column as Array
     */

    @Override
    public Tile[] getCol(int nsqFieldNr, int col) {
        Tile [] tiles = new Tile [9];
        for (int i = 0; i < 9 ; i++) {
            tiles [i] = content [i][col];
        }
        return tiles ;
    }

    /**
     * Returns all Tiles that belong to the same area as the Tile at the given position as Tile-Array
     * @param nsqFieldNr ninesquarefieldnumber (irrelevant for Ninesquare)
     * @param row row
     * @param col col
     * @return Tiles of an area as Array
     */

    @Override
    public Tile[] getArea(int nsqFieldNr, int row, int col) {
        Tile [] tiles = new Tile [9];
        int cnt = 0;

        for (int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++) {
                //check if Tile belongs to same area as the selected Tile
                if (getBelongsToArea(99,r,c) == getBelongsToArea(99,row, col)) {
                    tiles[cnt] = content[r][c];
                    cnt++;
                    //stops if all 9 Tiles from the area were found
                    if (cnt >= 9) break;
                }
            }
        }
        return tiles ;
    }

    /**
     * Returns all Tiles that belong to the given area as Tile-Array
     * @param nsqFieldNr ninesquarefieldnumber (irrelevant for Ninesquare)
     * @param area given area
     * @return Tiles of an area as Array
     */

    public Tile [] getArea(int nsqFieldNr, int area){
        Tile [] tiles = new Tile [9];
        int cnt = 0;
        for (int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++) {
                //check if Tile belongs to given area
                if (getBelongsToArea(nsqFieldNr,r,c) == area) {
                    tiles[cnt] = content[r][c];
                    cnt++;
                    //stops if all 9 Tiles from the area were found
                    if (cnt >= 9) break;
                }
            }
        }
        return tiles ;
    }

    /**
     * Checks if there are conflicted entries within a Ninesquare
     */
    private void checkConflicts() {
        //set all tiles to be not conflicted
        for (Tile[] t : content) {
            for (Tile tile : t) {
                tile.setConflicted(false);
            }
        }
        Tile[] tiles;

        //check conflicts in all rows, cols and areas
        for (int i = 0; i < 9; i++) {
            tiles = getRow(99, i);
            checkConflicts(tiles);

            tiles = getCol(99, i);
            checkConflicts(tiles);

            tiles = getArea(99, i+1);
            checkConflicts(tiles);
        }
        isSolved();
    }

    /**
     * Checks if there are conflicts (if two entries have the same value) within the given Tile-Array
     * @param tiles given Tile-Array
     */

    private void checkConflicts(Tile[] tiles){
        tiles = sortTiles(tiles);
        tiles = sortTiles(tiles);

        for (int i = 0; i < 8; i++) {
            if (tiles[i].getEntry() > 0 && tiles[i].getEntry() == tiles[i + 1].getEntry()) {
                tiles[i].setConflicted(true);
                tiles[i + 1].setConflicted(true);
            }
        }
    }

    /**
     * Returns given Tile-Array sorted by entry (asc)
     * @param tiles unsorted Tile-Array
     * @return sorted Tile-Array
     */

    private Tile [] sortTiles(Tile [] tiles){
        for (int i = 0; i < tiles.length-1; i++){
            for (int j = i+1;  j < tiles.length; j++){
                if (tiles[i].getEntry() > tiles[j].getEntry()){
                    Tile temp = tiles[i];
                    tiles[i] = tiles[j];
                    tiles[j] = temp;
                }
            }
        }
        return tiles;
    }

    /**
     * Checks if a Ninesquare is solved
     * @return if Ninesquare is solved
     */
    public boolean isSolved() {
        for (Tile[] x : content) {
            for (Tile y : x) {
                if (!y.isFilled() || y.isConflicted()) return false;
            }
        }
        solved = true;
        return true;
    }

    /**
     * Converts a Ninesquare object to a String
     * @return String of Ninesquare
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Tile[] i : content) {
            for (Tile j : i) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns an Export-String of a Ninesquare, is needed for saving a Ninesquare to disc
     * @return Export-String of a Ninesquare
     */

    public String export() {
        StringBuilder sb = new StringBuilder();
        sb.append(difficulty).append(";").append(type).append(";");
        sb.append(solved).append(";").append(String.format("%06d", timeUsed)).append(";");
        sb.append(exportContentString());
        sb.append(";");
        return sb.toString();
    }

    /**
     * Returns the content of the Tile-Array content from a Ninesquare
     * @return Content-String of a Ninesquare
     */

    public String exportContentString() {
        StringBuilder sb = new StringBuilder();
        for (Tile[] i : content) {
            for (Tile j : i) {
                sb.append(j.export()).append("-");
            }
        }
        return sb.toString();
    }

}