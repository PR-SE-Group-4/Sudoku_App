import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class SudokuUI implements ActionListener {

    private JFrame frame;
    private Ninesquare ninesquare;
    private NinesquareField sudokufield;
    private static final Font FONT = new Font("Arial", Font.BOLD,20);

    public SudokuUI() {
        this.frame = new JFrame("Sudoku");

        final Container container = this.frame.getContentPane();
        container.setLayout(new BorderLayout(1, 5));

        // Add Menu
        this.frame.setJMenuBar(this.SudokuMenu());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800 , 600);
        this.frame.setBackground(Color.gray);


        this.ninesquare = new Ninesquare();
        this.sudokufield = new NinesquareField();


        //Create Sudoku Gamefield


        /*-------------Muss noch in Class Niesqarefield---------------*/
        JTextField[][] tiles;
        JPanel grid;

        tiles = new JTextField[9][9];
        grid = new JPanel();
        Border empty = new EmptyBorder(0, 12, 0, 0);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);


        JPanel sudokuPanel = new JPanel();
        sudokuPanel.setLayout(new FlowLayout());
        sudokuPanel.setPreferredSize(new Dimension(800, 600));

        sudokuPanel.add(sudokufield);
        //Add Button Panel

        JPanel btnpanel = new JPanel();
        JButton solve = new JButton("Lösen");
        JButton hint = new JButton("Tipp");
        btnpanel.add(solve);
        btnpanel.add(hint);


        JPanel btnEntry = new JPanel();
        btnEntry.setLayout(new BoxLayout(btnEntry, BoxLayout.Y_AXIS));

        for (int i = 1; i <= 9; i++) {
            JButton b = new JButton(String.valueOf(i));
            b.addActionListener(sudokufield.new EntryActionListener());
                    btnEntry.add(b);
        }




       // this.frame.add(grid);
        this.frame.add(sudokuPanel);
        this.frame.add(btnpanel, BorderLayout.SOUTH);
        this.frame.add(btnEntry, BorderLayout.EAST);
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
