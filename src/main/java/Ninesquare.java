
public class Ninesquare {
    private Tile[][] content;
    private String name;
    private boolean solved;


    //dummy Konstruktor
    Ninesquare() {
        content = new Tile[9][9];
        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                content[row][col] = new Tile( (col/3+1)+(row/3)*3,  (row+col)%9+1 );
            }
        }
        name = "dummy";
        solved = false;
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






}