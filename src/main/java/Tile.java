public class Tile {
    private final boolean changeable;
    private boolean filled;
    private boolean conflicted = false;
    private final int color;
    private int entry;

    public Tile(int color, int entry) {
        this.color = color;
        this.entry = entry;
        this.changeable = false;
        this.filled = true;
    }

    public Tile(int color) {
        this.color = color;
        this.entry = 0;
        this.changeable = true;
        this.filled = false;
    }

    public boolean fill(int entry) {
        if (!changeable) {
            return false;
        } else {
            this.entry = entry;
            this.filled = true;
            return true;
        }
    }

    public boolean empty() {
        if(!changeable) {
            return false;
        } else {
            this.entry = 0;
            this.filled = false;
            return true;
        }
    }

    public boolean isChangeable() {
        return changeable;
    }

    public boolean isFilled() {
        return filled;
    }

    public int getColor() {
        return color;
    }

    public int getEntry() {
        return entry;
    }

    public boolean isConflicted() {
        return conflicted;
    }

    public void setConflicted(boolean conflicted) {
        this.conflicted = conflicted;
    }

    @Override
    public String toString() {
        if (!filled) {
            return "   ";
        } else if(changeable) {
            return " " + entry + " ";
        } else {
            return "[" + entry + "]";
        }
    }
}