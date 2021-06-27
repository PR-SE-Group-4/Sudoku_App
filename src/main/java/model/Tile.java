package model;

/**
 * The Tile class represents a single tile of a Sudoku-Puzzle
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */

public class Tile {
    private boolean changeable;
    private boolean filled;
    private boolean conflicted = false;
    private final int belongsToArea;
    private int entry;
    private boolean hint = false;


    /**
     *Contructor of the class Tile
     * @param belongsToArea area a Tile belongs to
     * @param entry entry of a Tile
     * @param changeable if a Tile is changeable
     */
    public Tile(int belongsToArea, int entry, boolean changeable) {
        this.belongsToArea = belongsToArea;
        this.entry = entry;
        this.filled = entry != 0;
        this.changeable = changeable;
    }

    /**
     * Empty Constructor of the class Tile
     */
    public Tile() {
        this.belongsToArea = 10;
        this.entry = 0;
        this.changeable = true;
        this.filled = false;
    }

    /**
     * Returns if a Tile contains an entry
     * @return empty
     */
    public boolean empty() {
        if(!changeable) {
            return false;
        } else {
            this.entry = 0;
            this.filled = false;
            return true;
        }
    }

    /**
     * Returns if a Tile is changeable
     * @return changeable
     */
    public boolean isChangeable() {
        return changeable;
    }

    /**
     * Sets if a Tile is changeable or not
     * @param changeable changeable
     */
    public void setChangeable(boolean changeable) { this.changeable = changeable; }

    /**
     * Returns if a Tile is filled or not
     * @return filled
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Returns the area a Tile belongs to
     * @return area a Tile belongs to
     */
    public int getBelongsToArea() {
        return belongsToArea;
    }

    /**
     * Returns the entry of a Tile
     * @return entry
     */
    public int getEntry() {
        return entry;
    }

    /**
     * Returns if a Tile is in conflict with others
     * @return conflicted
     */
    public boolean isConflicted() {
        return conflicted;
    }

    /**
     * Sets a Tile conflicted or not conflicted
     * @param conflicted conflicted
     */
    public void setConflicted(boolean conflicted) {
        this.conflicted = conflicted;
    }

    /**
     * Sets an entry if Tile is changeable
     * @param entry entry
     */
    public void setEntry(int entry) {
        if (this.changeable) {
            if (entry >= 1 && entry <= 9) {
                this.entry = entry;
                this.filled = true;
            } else {
                this.entry = 0;
                this.filled = false;
            }
        }
    }

    /**
     * Retruns if the entry of a Tile was a hint or not
     * @return hint
     */
    public boolean isHint() { return hint;   }

    /**
     * Sets a Tile to be a hint
     * @param hint hint
     */
    public void setHint(boolean hint) {this.hint = hint;}

    /**
     * Converts a Tile object to a String
     * @return String of a Tile object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch(belongsToArea){
            case 0:
                sb.append("\u001B[0m");
                break;
            case 1:
                sb.append("\u001B[30m");
                break;
            case 2:
                sb.append("\u001B[31m");
                break;
            case 3:
                sb.append("\u001B[32m");
                break;
            case 4:
                sb.append("\u001B[33m");
                break;
            case 5:
                sb.append("\u001B[34m");
                break;
            case 6:
                sb.append("\u001B[35m");
                break;
            case 7:
                sb.append("\u001B[36m");
                break;
            case 8:
                sb.append("\u001B[37m");
                break;
            case 9:
                sb.append("\033[0;92m");
                break;
            default:
                break;
        }
        if (!filled) {
            sb.append("   ");
        } else if(changeable) {
            sb.append((conflicted ? "X" : " ")).append(entry).append(belongsToArea);
        } else {
            sb.append("[").append(entry).append("]");
        }
        return sb.toString();
    }

    /**
     * Returns an Export-String of a Tile object, is needed for saving a Puzzle to disc
     * @return Export-String
     */
    public String export() {
        return belongsToArea + (changeable ? "U" : "O") + entry;
    }
}