import java.util.Arrays;

public class Ninesquare extends Puzzle {
    private Tile[][] content;


    //dummy Konstruktor
    Ninesquare() {
        super("dummy", Difficulty.EASY, Type.CLASSIC, false, 0, "dummystring");
        content = new Tile[9][9];
        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                if ((row*9+col)%7 != 0) {
                    content[row][col] = new Tile( (col/3+1)+(row/3)*3,  (row*3+col+row/3)%9+1, false );
                } else {
                    content[row][col] = new Tile((col / 3 + 1) + (row / 3) * 3 );
                }
            }
        }
    }

    // "normal" constructor - gets content as string, builds Tile-array
    public Ninesquare (String name, Difficulty difficulty, Type type, boolean solved, int timeUsed, String contentString) {
        super(name, difficulty, type, solved, timeUsed, contentString);
        //Parser
        content = new Tile[9][9];
        System.out.println(contentString);
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

    @Override
    public Tile getTile(int nsqFieldNr, int row, int col) {
        return content[row][col];
    }

    @Override
    public int getColor(int nsqFieldNr, int row, int col) {return content[row][col].getColor();
    }

    @Override
    public void setEntry(int nsqFieldNr, int selectedRow, int selectedCol, int entry) {
        content[selectedRow][selectedCol].setEntry(entry);
        checkConflicts (selectedRow, selectedCol);
    }

    @Override
    public void deleteEntry(int nsqFieldNr, int selectedRow, int selectedCol) {
        content[selectedRow][selectedCol].empty();
        checkConflicts(selectedRow, selectedCol);
    }

    @Override
    public Tile[] getRow(int nsqFieldNr, int row) {
        Tile [] tiles = new Tile [9];
        for (int i = 0; i < 9 ; i++) {
            tiles [i] = content [row][i];
        }
        return tiles ;
    }

    @Override
    public Tile[] getCol(int nsqFieldNr, int col) {
        Tile [] tiles = new Tile [9];
        for (int i = 0; i < 9 ; i++) {
            tiles [i] = content [i][col];
        }
        return tiles ;
    }

    @Override
    public Tile[] getArea(int ninesquare, int row, int col) {
        Tile [] tiles = new Tile [9];
        int cnt = 0;
        for (int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++) {
                if (getColor(99,r,c) == getColor(99,row, col)) {
                    tiles[cnt] = content[r][c];
                    cnt++;
                    if (cnt >= 9) break;
                }
            }
        }
        return tiles ;
    }


    private void checkConflicts(int row, int col){
        checkConflicts(getRow(0,row));
        checkConflicts(getCol(0, col));
        checkConflicts(getArea(0,row, col));
    }

    private void checkConflicts(Tile[] tiles){
        tiles = sortTiles(tiles);

        tiles[0].setConflicted(false);
        for (int i = 0; i < 8; i++) {
            if (tiles[i].getEntry() > 0 && tiles[i].getEntry() == tiles[i + 1].getEntry()) {
                tiles[i].setConflicted(true);
                tiles[i+1].setConflicted(true);
            } else {
                tiles[i+1].setConflicted(false);
            }
        }
    }

    private Tile [] sortTiles(Tile [] t){
        for (int i = 0; i < t.length-1; i++){
            for (int j = i+1;  j < t.length; j++){
                if ((t[i].isFilled()) && (t[i].getEntry() > t[j].getEntry())){
                    Tile temp = t[i];
                    t[i] = t[j];
                    t[j] = temp;
                }
            }
        }
        return t;
    }

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

    public String export() {
        StringBuilder sb = new StringBuilder();
        sb.append(difficulty).append(";").append(type).append(";");
        sb.append(solved).append(";").append(String.format("%06d", timeUsed)).append(";");
        sb.append(exportContentString());
        sb.append(";");
        return sb.toString();
    }
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