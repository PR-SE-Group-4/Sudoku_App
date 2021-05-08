import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuUI implements ActionListener {

    private JFrame frame;
    private SudokuField sudokufield;
    private JPanel sudokuPanel;
    private Puzzle puzzle;
    private static final Font FONT = new Font("Arial", Font.BOLD,20);

    public SudokuField getSudokufield() {
        return sudokufield;
    }

    public SudokuUI(Puzzle puzzle) {

        this.frame = new JFrame("Sudoku");
        this.puzzle=puzzle;

        final Container container = this.frame.getContentPane();
        container.setLayout(new BorderLayout(1, 5));

        if (puzzle.getType() == Type.SAMURAI) {
            this.sudokufield = new SamuraiField(puzzle);
        } else if (puzzle.getType() == Type.CLASSIC) {
            this.sudokufield = new NinesquareField(puzzle);
        }



        sudokuPanel = new JPanel();

        sudokuPanel.setLayout(new FlowLayout());
        sudokuPanel.setBackground(new Color(120,120,255));

        sudokuPanel.add(sudokufield);
        sudokuPanel.setLocation(10,10);

        container.add(sudokuPanel);


        // Add Menu
       // this.frame.setJMenuBar(this.SudokuMenu());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setBackground(new Color (131,111,255));


        JPanel menupanel = new JPanel();
        menupanel.setPreferredSize(new Dimension(200, 600));
        menupanel.setLayout(new GridLayout(6,1, 20, 20));
        menupanel.setBackground(new Color(120,120,255));

        JButton load = new JButton("Load");
        load.setSize(new Dimension(120, 40));
        load.setBackground(new Color(120,120,255));
        load.setForeground(Color.WHITE);
        JButton create = new JButton("Create");
        JButton solve = new JButton("Solve");
        JButton hint = new JButton("Hint");
        JButton help = new JButton("Help");
        JButton exit = new JButton("Exit");

        menupanel.add(load);
        menupanel.add(create);
        menupanel.add(solve);
        menupanel.add(hint);
        menupanel.add(help);
        menupanel.add(exit);

        this.frame.add(menupanel, BorderLayout.WEST);
        this.frame.setVisible(true);
        this.frame.setResizable(true);
        this.frame.pack();


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


    private  final MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseReleased(final MouseEvent e) {

        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
