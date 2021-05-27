package view;

import app.Loader;
import app.Solver;
import model.Type;
import model.Puzzle;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Timer;
import java.util.TimerTask;


public class SudokuUI implements ActionListener {

    private JFrame frame;
    private SudokuField sudokufield;
    private JPanel sudokuPanel;
    private JPanel infoPanel;
    private JLabel timerField;
    final Container container;
    private Puzzle puzzle;
    private Color color;
    private Timer timer;
    private int timeUsed;
    private Font buttonFont;


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
        } else if (puzzle.getType() == Type.CLASSIC || puzzle.getType() == Type.FREEFORM) {
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
        menupanel.setPreferredSize(new Dimension(100, 600));
        menupanel.setLayout(new GridLayout(7,1, 0, 0));
        menupanel.setBackground(color);

        JButton load = new JButton("Load");
        JButton create = new JButton("Create");
        JButton save = new JButton("Save");
        JButton solve = new JButton("Solve");
        JButton hint = new JButton("Hint");
        JButton help = new JButton("Help");
        JButton exit = new JButton("Exit");

        buttonFont = new Font("Verdana", Font.BOLD, 18);

        load.setFont(buttonFont);
        create.setFont(buttonFont);
        save.setFont(buttonFont);
        solve.setFont(buttonFont);
        hint.setFont(buttonFont);
        help.setFont(buttonFont);
        exit.setFont(buttonFont);


        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Puzzle> loadedPuzzles = Loader.getStoredPuzzles();
                ArrayList<String> namedPuzzles = new ArrayList<>();


                for (Puzzle p : loadedPuzzles) {
                    String type = p.getType().toString();
                    String difficulty = p.getDifficulty().toString();
                    String dropdowntext = "- Schwierigkeit: " + difficulty + " | " + " Typ: " + type;
                    namedPuzzles.add(dropdowntext);
                }
                Object[] selectionValues = namedPuzzles.toArray();

                Object selected = JOptionPane.showInputDialog(null, "Choose a puzzle?",
                        "Load a Game", JOptionPane.QUESTION_MESSAGE, null, selectionValues, 1);

                int index = namedPuzzles.indexOf(selected);

                System.out.println(index);
                if ( index >= 0){


                    try {

                        puzzle = Loader.loadPuzzle(namedPuzzles.indexOf(selected));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                   createGame();
                }
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
                        createGame();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else if (selected == 1) {
                    try {
                        puzzle = Loader.loadPuzzle(2);
                        createGame();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else if (selected == 2) {
                    try {
                        puzzle = Loader.loadPuzzle(0);
                        createGame();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }


            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Object[] options = {"Save", "Save & Exit", "Cancel"};

                int selected = JOptionPane.showOptionDialog(null,
                        "Save the game?",
                        "Save",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);

                if (selected == 0) {
                    try {
                        //Save the Game
                        // Send playing time to puzzle
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else if (selected == 1) {
                    try {
                        // Send playing time to puzzle
                        System.exit(0);

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else if (selected == 2) {
                    try {

                        // Do nothing
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }


            }
        });
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Solver.solve(puzzle);
                sudokufield.repaint();

            }
        });

        hint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.Candidate candidate = Solver.getHint(puzzle);
                System.out.println("HINT: " + candidate.getEntry() + " " + candidate.getRow() + " " + candidate.getCol());
                puzzle.setEntry(candidate.getNsqFieldNr(), candidate.getRow(), candidate.getCol(), candidate.getEntry());
                puzzle.getTile(candidate.getNsqFieldNr(), candidate.getRow(), candidate.getCol()).setHint(true);
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
        menupanel.add(save);
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
        showConflicts.setBackground(color);

            showConflicts.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (((JCheckBox) e.getSource()).isSelected()) {
                        sudokufield.setShowConflicts(true);
                    } else {
                        sudokufield.setShowConflicts(false);
                    }
                    sudokufield.repaint();

                    System.out.println("Checked? " + sudokufield.showConflicts);
                }
            });

            startTimer();

        infoPanel.add(showConflicts);
        infoPanel.add(timerField);
        container.add(infoPanel, BorderLayout.SOUTH);

    }


    public void startTimer() {

        timerField = new JLabel();
        timeUsed = puzzle.getTimeUsed();
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeUsed++;
                timerField.setText("Playing Time: " + String.valueOf(timeUsed));

            }
        });

        timer.start();
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
