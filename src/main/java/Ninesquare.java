
public class Ninesquare extends Puzzle {
    private Tile[][] content;


    //dummy Konstruktor
    Ninesquare() {
        super("dummy", Difficulty.EASY, Type.CLASSIC, false, "dummystring");
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
    public Ninesquare (String name, Difficulty difficulty, Type type, boolean solved, String contentString) {
        super(name, difficulty, type, solved, contentString);
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

    public int getInput (int row, int col) {
        return content[row][col].getEntry();
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
        for (Tile[] i : content) {
            for (Tile j : i) {
                sb.append(j.export()).append("-");
            }
        //    sb.append("_");
        }
        return sb.toString();
    }





}