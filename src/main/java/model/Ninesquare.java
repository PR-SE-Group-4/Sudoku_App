package model;

public class Ninesquare extends Puzzle {
    private Tile[][] content;


    // Empty constuctor for creating an empty new field
    public Ninesquare () {
        super();
        //Parser
        content = new Tile[9][9];
        int color = 0;
        for (int row = 0; row < 9 ; row++) {
            for (int col = 0; col < 9; col++) {

                //Default tile color
                if(col < 3 && row < 3) {color = 1;}
                else if (col > 2 && col < 6 && row < 3) {color = 2;}
                else if (col > 5 && row < 3) {color = 3;}
                else if (col < 3 && row > 2 && row < 6) {color = 4;}
                else if (col > 2 && col < 6 && row > 2 && row < 6) {color = 5;}
                else if (col > 5 && row > 2 && row < 6) {color = 6;}
                else if (col < 3 && row > 5 ) {color = 7;}
                else if (col > 2 && col < 6 && row > 5) {color = 8;}
                else if (col > 5 && row > 5) {color = 9;}

                content[row][col] = new Tile(color);
            }
        }
    }

    // "normal" constructor - gets content as string, builds model.Tile-array
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
    public int getColor(int nsqFieldNr, int row, int col) {return content[row][col].getBelongsToArea();
    }

    @Override
    public void setEntry(int nsqFieldNr, int selectedRow, int selectedCol, int entry) {
        content[selectedRow][selectedCol].setEntry(entry);
        checkConflicts ();
    }

    @Override
    public void deleteEntry(int nsqFieldNr, int selectedRow, int selectedCol) {
        content[selectedRow][selectedCol].empty();
        checkConflicts();
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
    public Tile[] getArea(int nsqFieldNr, int row, int col) {
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

    public Tile [] getArea(int nsqFieldNr, int color){
        Tile [] tiles = new Tile [9];
        int cnt = 0;
        for (int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++) {
                if (getColor(nsqFieldNr,r,c) == color) {
                    tiles[cnt] = content[r][c];
                    cnt++;
                    if (cnt >= 9) break;
                }
            }
        }
        return tiles ;
    }

    private void checkConflicts() {
        //set all tiles to be not conflicted
        for (Tile[] t : content) {
            for (Tile tile : t) {
                tile.setConflicted(false);
            }
        }
        Tile[] tiles = new Tile[9];

        //check conflicts in all rows, cols and areas
        for (int i = 0; i < 9; i++) {
            tiles = getRow(99, i);
            checkConflicts(tiles);

            tiles = getCol(99, i);
            checkConflicts(tiles);

            tiles = getArea(99, i+1);
            checkConflicts(tiles);
        }
    }

    private void checkConflicts(Tile[] tiles){

        tiles = sortTiles(tiles);

        for (int i = 0; i < 8; i++) {
            if (tiles[i].getEntry() > 0 && tiles[i].getEntry() == tiles[i + 1].getEntry()) {
                tiles[i].setConflicted(true);
                tiles[i + 1].setConflicted(true);
            }
        }
    }

    private Tile [] sortTiles(Tile [] t){
        for (int i = 0; i < t.length-1; i++){
            for (int j = i+1;  j < t.length; j++){
                if (t[i].getEntry() > t[j].getEntry()){
                    Tile temp = t[i];
                    t[i] = t[j];
                    t[j] = temp;
                }
            }
        }
        return t;
    }

    public boolean isSolved() {
        for (Tile[] x : content) {
            for (Tile y : x) {
                if (!y.isFilled()) return false;
            }
        }
        solved = true;
        return true;
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