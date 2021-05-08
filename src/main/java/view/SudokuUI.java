package view;

import model.Type;
import model.Ninesquare;
import model.Puzzle;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class SudokuUI implements ActionListener {

    private JFrame frame;
    private Ninesquare ninesquare;
    private SudokuField sudokufield;
    private JPanel sudokuPanel;
    private Puzzle puzzle;
    private static final Font FONT = new Font("Arial", Font.BOLD,20);

    public SudokuUI(Puzzle puzzle) {

        this.frame = new JFrame("Sudoku");
        this.frame.setLayout(new BorderLayout());
        this.puzzle=puzzle;

        final Container container = this.frame.getContentPane();

        if (puzzle.getType() == Type.SAMURAI) {
            this.sudokufield = new SamuraiField(puzzle);
        } else if (puzzle.getType() == Type.CLASSIC) {
            this.sudokufield = new NinesquareField(puzzle);
        }

        sudokuPanel = new JPanel(new BorderLayout(10,10));

        sudokuPanel.setLayout(new FlowLayout());
        sudokuPanel.setBackground(new Color(120,120,255));

        sudokuPanel.add(sudokufield);

        SudokuListener listener = new SudokuListener(sudokufield);
        sudokufield.addMouseListener(listener);
        this.frame.addKeyListener(listener);

        container.add(sudokuPanel);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setBackground(new Color (131,111,255));

        createMenu();

        this.frame.setFocusable(true);
        this.frame.isFocused();
        this.frame.requestFocusInWindow();
        this.frame.requestFocus();
        this.frame.setResizable(true);
        this.frame.pack();
        this.frame.setVisible(true);


    }

    public void createMenu(){

        JPanel menupanel = new JPanel();
        menupanel.setPreferredSize(new Dimension(200, 600));
        menupanel.setLayout(new GridLayout(6,1, 20, 20));
        menupanel.setBackground(new Color(120,120,255));

        JButton load = new JButton("Load");
        JButton create = new JButton("Create");
        JButton solve = new JButton("Solve");
        JButton hint = new JButton("Hint");
        JButton help = new JButton("Help");
        JButton exit = new JButton("Exit");



        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();

                fc.showOpenDialog(null);
            }
        });

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        hint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudokufield.repaint();
            }
        });

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null,
                        "Hier kommt der Hilfetext zur Unterstützung für Anfänger",
                        "Sudoku Help",3);

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        load.setFocusable(false);
        create.setFocusable(false);
        solve.setFocusable(false);
        hint.setFocusable(false);
        help.setFocusable(false);
        exit.setFocusable(false);

        menupanel.add(load);
        menupanel.add(create);
        menupanel.add(solve);
        menupanel.add(hint);
        menupanel.add(help);
        menupanel.add(exit);

        this.frame.add(menupanel, BorderLayout.WEST);

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
