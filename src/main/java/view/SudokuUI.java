package view;

import app.Loader;
import model.Type;
import model.Ninesquare;
import model.Puzzle;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.rmi.activation.ActivationInstantiator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SudokuUI implements ActionListener {

    private JFrame frame;
    private SudokuField sudokufield;
    private JPanel sudokuPanel;
    private JPanel infoPanel;
    final Container container;
    private Puzzle puzzle;
    private Color color;

    public SudokuField getSudokufield() {
        return sudokufield;
    }

    public SudokuUI(Puzzle puzzle) {

        this.frame = new JFrame("Sudoku");
        this.puzzle=puzzle;
        color = new Color (175,210,245);

        container = this.frame.getContentPane();

        sudokuPanel = new JPanel(new BorderLayout(10,10));
        infoPanel = new JPanel(new FlowLayout());

        JLabel startImage = new JLabel(new ImageIcon("src/main/java/ressources/logo.png"));

        sudokuPanel.add(startImage);
        sudokuPanel.setBackground(color);

     /*   ImageIcon backgroundImage = new ImageIcon("src/main/java/ressources/background.jpg");
        BackgroundPanel background = new JLabel();
        background.setIcon(backgroundImage);
        sudokuPanel.add(background);
      */

        container.add(sudokuPanel);

        createMenu();

        this.frame.setBackground(color);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setFocusable(true);
        this.frame.isFocused();
        this.frame.requestFocusInWindow();
        this.frame.requestFocus();
        this.frame.setResizable(true);
        this.frame.pack();
        this.frame.setVisible(true);


    }

    public void createGame() {

        sudokuPanel.removeAll();

        if (puzzle.getType() == Type.SAMURAI) {
            this.sudokufield = new SamuraiField(puzzle);
        } else if (puzzle.getType() == Type.CLASSIC) {
            this.sudokufield = new NinesquareField(puzzle);
        }

        sudokuPanel.add(sudokufield);
        SudokuListener listener = new SudokuListener(sudokufield);
        sudokufield.addMouseListener(listener);
        this.frame.addKeyListener(listener);

        sudokuPanel.setLayout(new FlowLayout());
        createInfoPanel();
        this.frame.pack();

    }

    public void createMenu(){

        JPanel menupanel = new JPanel();
        menupanel.setPreferredSize(new Dimension(200, 600));
        menupanel.setLayout(new GridLayout(6,1, 0, 0));
        menupanel.setBackground(color);

        JButton load = new JButton("Load");
        JButton create = new JButton("Create");
        JButton solve = new JButton("Solve");
        JButton hint = new JButton("Hint");
        JButton help = new JButton("Help");
        JButton exit = new JButton("Exit");

       // load.setIcon(new ImageIcon("src/main/java/ressources/buttonbackground.png"));

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

                Object[] options = {"Classic", "Samurai", "Freiform"};

                int selected = JOptionPane.showOptionDialog(null,
                        "Welches Spiel möchten Sie starten?",
                        "Spiel starten",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);
                System.out.println("Spieleart: " + selected);

                if (selected == 0) {
                    try {
                        puzzle = Loader.loadPuzzle(1);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else if (selected == 1) {
                    try {
                        puzzle = Loader.loadPuzzle(2);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else if (selected == 2) {
                    try {
                        puzzle = Loader.loadPuzzle(0);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                createGame();

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

    public void createInfoPanel() {

        infoPanel.removeAll();
        infoPanel.setBackground(color);

        JCheckBox showConflicts = new JCheckBox("Show Conflicts?");
        showConflicts.setFocusable(false);

            showConflicts.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (((JCheckBox) e.getSource()).isSelected()) {
                        sudokufield.setShowConflicts(true);
                    } else {
                        sudokufield.setShowConflicts(false);
                    }

                    System.out.println("Checked? " + sudokufield.showConflicts);
                }
            });

        infoPanel.add(showConflicts);

        JLabel timer3 = new JLabel();
        JLabel timer = new JLabel(String.valueOf(puzzle.getTimeUsed()));
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        Timer timer2 = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameTime = String.valueOf(puzzle.getTimeUsed());
                String gameTime2 = dateFormat.format(new Date());
                timer.setText(gameTime);
                timer3.setText(gameTime2);

            }
        });

        timer2.start();
        infoPanel.add(timer);
        infoPanel.add(timer3);

        container.add(infoPanel, BorderLayout.SOUTH);



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
