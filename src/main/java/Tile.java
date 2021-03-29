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
        StringBuilder sb = new StringBuilder();
        switch(color){
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
                sb.append("\033[0;91m");
                break;
            default:
                break;
        }
        if (!filled) {
            sb.append("   ");
        } else if(changeable) {
            sb.append(" ").append(entry).append(color);
        } else {
            sb.append("[").append(entry).append("]");
        }
        return sb.toString();
    }
}