package model;

public abstract class Puzzle {

    protected String name;
    protected  Difficulty difficulty;
    protected Type type;
    protected boolean solved;
    protected int timeUsed;
    protected String contentString;

    public Puzzle(){
        this.name = null;
        this.difficulty = null;
        this.type = null;
        this.solved = false;
        this.timeUsed = 0;
        this.contentString = null;
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

    public abstract int getBelongsToArea(int nsqFieldNr, int row, int col);

    public abstract void setEntry(int nsqFieldNr, int selectedRow, int selectedCol, int entry);

    public abstract void deleteEntry(int nsqFieldNr, int selectedRow, int selectedCol);

    public abstract Tile [] getRow(int nsqFieldNr, int row);

    public abstract Tile [] getCol(int nsqFieldNr, int col);

    public abstract Tile [] getArea(int nsqFieldNr, int row, int col);

    public int getTimeUsed() { return timeUsed; }

    public void setTimeUsed(int timeUsed) {
        this.timeUsed = timeUsed;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {this.type = type; }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void  setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public abstract String export();

    public abstract boolean isSolved();

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}


