public abstract class Puzzle {

    protected final String name;
    protected final Difficulty difficulty;
    protected final Type type;
    protected boolean solved;
    protected int timeUsed;
    protected String contentString;




    public Puzzle() {
        this.name = "dummy";
        this.difficulty = Difficulty.EASY;
        this.type = Type.CLASSIC;
        this.solved = false;
        this.contentString = "1U0-1O2-1O3-2O4-2O5-2O6-3O7-3U0-3O9-1O4-1O5-1O6-2O7-2O8-2U0-3O1-3O2-3O3-1O7-1O8-1O9-2U0-2O2-2O3-3O4-3O5-3O6-4O2-4U0-4O4-5O5-5O6-5O7-6O8-6O9-6U0-4O5-4O6-4O7-5O8-5O9-5O1-6U0-6O3-6O4-4O8-4O9-4O1-5O2-5U0-5O4-6O5-6O6-6O7-7O3-7O4-7U0-8O6-8O7-8O8-9O9-9O1-9O2-7U0-7O7-7O8-8O9-8O1-8O2-9O3-9U0-9O5-7O9-7O1-7O2-8O3-8O4-8U0-9O6-9O7-9O8-";
    }

    public Puzzle(String name, Difficulty difficulty, Type type, boolean solved, int timeUsed, String contentString) {
        this.name = name;
        this.difficulty = difficulty;
        this.type = type;
        this.solved = solved;
        this.timeUsed = timeUsed;
        this.contentString = contentString;
    }

    public abstract Tile getTile(int nsqFieldNr, int row, int col);
    public abstract Tile getTile(int row, int col);

    public abstract int getColor(int nsqFieldNr, int row, int col);
    public abstract int getColor(int row, int col);

    public abstract void setEntry(int nsqFieldNr, int selectedRow, int selectedCol, int entry);
    public abstract void setEntry(int selectedRow, int selectedCol, int entry);

    public abstract Tile [] getRow(int nsqFieldNr, int row);
    public abstract Tile [] getRow(int row);

    public abstract Tile [] getCol(int nsqFieldNr, int col);
    public abstract Tile [] getCol(int col);

    public abstract Tile [] getArea(int nsqFieldNr, int row, int col);
    public abstract Tile [] getArea(int row, int col);

    public int getTimeUsed() { return timeUsed; }

    public Type getType() {
        return type;
    }

    public abstract String export();

}
