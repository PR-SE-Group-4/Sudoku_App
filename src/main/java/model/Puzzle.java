package model;

/**
 * A base class for Sudoku-Puzzles.
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */

public abstract class Puzzle {

    protected String name;
    protected  Difficulty difficulty;
    protected Type type;
    protected boolean solved;
    protected int timeUsed;
    protected String contentString;

    /**
     * Empty constructor for the class Puzzle
     */

    public Puzzle(){
        this.name = null;
        this.difficulty = null;
        this.type = null;
        this.solved = false;
        this.timeUsed = 0;
        this.contentString = null;
    }

    /**
     * Constructor for the class Puzzle
     * @param name name of the Puzzle
     * @param difficulty difficulty of the Puzzle
     * @param type type of the Puzzle
     * @param solved is the Puzzle already solved
     * @param timeUsed time already used playing
     * @param contentString content of the Puzzle
     */
    public Puzzle(String name, Difficulty difficulty, Type type, boolean solved, int timeUsed, String contentString) {
        this.name = name;
        this.difficulty = difficulty;
        this.type = type;
        this.solved = solved;
        this.timeUsed = timeUsed;
        this.contentString = contentString;
    }

    /**
     * Returns Tiel at a given position
     * @param nsqFieldNr ninesquarefieldnumber (important for Samurai)
     * @param row row
     * @param col column
     * @return Tile at a given position
     */

    public abstract Tile getTile(int nsqFieldNr, int row, int col);

    /**
     * Returns the area a Tile belongs to as an int
     * @param nsqFieldNr ninesquarefieldnumber (important for Samurai)
     * @param row row
     * @param col column
     * @return area a Tile belongs to
     */
    public abstract int getBelongsToArea(int nsqFieldNr, int row, int col);

    /**
     * Sets an entry for at a given position and checks if it leads to conflicts with other entries
     * @param nsqFieldNr ninesquarefieldnumber (important for Samurai)
     * @param selectedRow row
     * @param selectedCol column
     * @param entry entry that should be set
     */

    public abstract void setEntry(int nsqFieldNr, int selectedRow, int selectedCol, int entry);

    /**
     * Deletes an entry at a given position and checks if there are conflicts within the other entries
     * @param nsqFieldNr ninesquarefieldnumber (important for Samurai)
     * @param selectedRow row
     * @param selectedCol column
     */

    public abstract void deleteEntry(int nsqFieldNr, int selectedRow, int selectedCol);

    /**
     * Returns a selected row of a Puzzle as Tile-Array
     * @param nsqFieldNr ninesquarefieldnumber (important for Samurai)
     * @param row row
     * @return Tiles of a row as Array
     */

    public abstract Tile [] getRow(int nsqFieldNr, int row);

    /**
     * Returns a selected column of a Puzzle as Tile-Array
     * @param nsqFieldNr ninesquarefieldnumber (important for Samurai)
     * @param col column
     * @return Tiles of a column as Array
     */

    public abstract Tile [] getCol(int nsqFieldNr, int col);

    /**
     * Returns all Tiles that belong to the same area as the Tile at the given position as Tile-Array
     * @param nsqFieldNr ninesquarefieldnumber (important for Samurai)
     * @param row row
     * @param col col
     * @return Tiles of an area as Array
     */

    public abstract Tile [] getArea(int nsqFieldNr, int row, int col);

    /**
     * Returns the time already played
     * @return time already used playing
     */
    public int getTimeUsed() { return timeUsed; }

    /**
     * Sets the time already played
     * @param timeUsed time already used playing
     */

    public void setTimeUsed(int timeUsed) {
        this.timeUsed = timeUsed;
    }

    /**
     * Returns the type of a Puzzle
     * @return type of Puzzle
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type of a Puzzle
     * @param type type of Puzzle
     */
    public void setType(Type type) {this.type = type; }

    /**
     * Returns the difficulty of a Puzzle
     * @return difficulty of Puzzle
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty of a Puzzle
     * @param difficulty difficulty of Puzzle
     */
    public void  setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns an Export-String of a Puzzle, is needed for saving a Puzzle to disc
     * @return Export-String of a Puzzle
     */
    public abstract String export();

    /**
     * Checks if a Puzzle is already solved
     * @return if Puzzle is solved
     */
    public abstract boolean isSolved();

    /**
     * Returns the name of a Puzzle
     * @return name of Puzzle
     */
    public String getName() { return name; }

    /**
     * Sets the name of a Puzzle
     * @param name name of Puzzle
     */
    public void setName(String name) { this.name = name; }

}


