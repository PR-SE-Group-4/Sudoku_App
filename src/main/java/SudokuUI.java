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
    private SamuraiField samuraiField;
    private JPanel sudokuPanel;
    private Puzzle puzzle;
    private static final Font FONT = new Font("Arial", Font.BOLD,20);

    public SudokuUI() {

        this.frame = new JFrame("Sudoku");

        final Container container = this.frame.getContentPane();
        container.setLayout(new BorderLayout(1, 5));



        this.sudokufield = new NinesquareField(puzzle);
        this.samuraiField = new SamuraiField(puzzle);


        sudokuPanel = new JPanel();

        sudokuPanel.setLayout(new FlowLayout());

        sudokuPanel.add(sudokufield);
        sudokuPanel.setLocation(10,10);

        container.add(sudokuPanel);


        // Add Menu
        this.frame.setJMenuBar(this.SudokuMenu());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(630 , 630);
        this.frame.setBackground(Color.gray);


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
           // b.addActionListener(sudokufield.new EntryActionListener());
                    btnEntry.add(b);
        }


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

        classic.addActionListener(a -> {



        });
        JMenuItem freiform = new JMenuItem("Freiform");
        JMenuItem samurai = new JMenuItem("Samurai");
        JMenuItem close = new JMenuItem("Schließen");

        samurai.addActionListener(a -> {
            SamuraiField samuraiField = new SamuraiField(puzzle);

        });

        game.add(newgame);
        game.add(load);
        game.add(export);
        game.add(load);

        menuBar.add(game);
        menuBar.add(help);
        newgame.add(classic);
        newgame.add(freiform);
        newgame.add(samurai);


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
