import javax.swing.*;
import java.awt.*;

public class NinesquareField extends JComponent {

   // private final Ninesquare ninesquare;
    private JTextField[][] tiles;
    private JPanel grid;


    public NinesquareField() {
        //this.ninesquare = ninesquare;
        tiles = new JTextField[9][9];
        grid = new JPanel();

        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                tiles[row][col] = new JTextField("x");
                grid.add(tiles[row][col]);
            }
        }





    }
}
