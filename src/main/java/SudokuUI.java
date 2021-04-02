import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class SudokuUI implements ActionListener {

    private JFrame frame;
    private Ninesquare ninesquare;
    private JComponent sudokufield;
    private static final Font FONT = new Font("Arial", Font.TYPE1_FONT,15);

    public SudokuUI() {
        this.frame = new JFrame("Sudoku");

        final Container container = this.frame.getContentPane();
        container.setLayout(new BorderLayout(1, 5));
        this.frame.addMouseListener(this.mouseListener);

        this.frame.setJMenuBar(this.SudokuMenu());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(350 , 400);
        this.frame.setBackground(Color.gray);

        //Create Sudoku

        this.ninesquare = new Ninesquare();
        this.sudokufield = new NinesquareField();
        /*-------------Muss noch in Class Niesqarefield---------------*/
        JTextField[][] tiles;
        JPanel grid;

        tiles = new JTextField[9][9];
        grid = new JPanel();
        Border empty = new EmptyBorder(0, 12, 0, 0);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                String input = String.valueOf(ninesquare.getInput(row,col));
                if (!input.equals("0")) {
                    tiles[row][col] = new JTextField(input);
                }
                else {
                    tiles[row][col] = new JTextField("");
                }
                JTextField tile = tiles[row][col];
                tile.setBorder(border);
                tile.setBorder(empty);
                tile.setFont(FONT);
                tile.setPreferredSize(new Dimension(30,30));
                grid.add(tiles[row][col]);
            }
        }
     /*----------------------------*/
        frame.add(grid);

        this.frame.setVisible(true);
        this.frame.setResizable(false);
    }

    public JMenuBar SudokuMenu() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu game = new JMenu("Spiel");
        final JMenu help = new JMenu("Hilfe");

        JMenu newgame = new JMenu("Neues Spiel");
        JMenuItem load = new JMenuItem("Laden");
        JMenuItem export = new JMenuItem("Exportieren");
        JMenuItem classic = new JMenuItem("Classic");
        JMenuItem matrix = new JMenuItem("Matrix");
        JMenuItem close = new JMenuItem("Schließen");

        game.add(newgame);
        game.add(load);
        game.add(export);
        game.add(load);

        menuBar.add(game);
        menuBar.add(help);
        newgame.add(classic);
        newgame.add(matrix);


        return menuBar;
    }

    public static void main(final String[] args) {
        new SudokuUI();

    }

    private  final MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseReleased(final MouseEvent e) {

        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
