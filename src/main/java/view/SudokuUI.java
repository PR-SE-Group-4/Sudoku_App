package view;

import app.Loader;
import app.Solver;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.plaf.ColorUIResource;


public class SudokuUI implements ActionListener {

    private JFrame frame;
    private SudokuField sudokufield;
    private JPanel sudokuPanel;
    private JPanel infoPanel;
    private JLabel timerHeader;
    private JLabel timerField;
    final Container container;
    private Puzzle puzzle;
    private Color color;
    private Timer timer;
    private int timeUsed;
    private SudokuListener listener;

    public SudokuUI() throws IOException {


        this.frame = new JFrame("Sudoku");
        color = new Color (175,210,245);

        container = this.frame.getContentPane();

        sudokuPanel = new JPanel(new BorderLayout(10,10));
        infoPanel = new JPanel(new FlowLayout());

        //Load Startimage
        JLabel startImage = new JLabel(new ImageIcon("src/main/java/ressources/logo.png"));

        // Design PopUp-Fields
        UIManager UI=new UIManager();
        UI.put("OptionPane.background",new ColorUIResource(175,210,245));
        UI.put("Panel.background",new ColorUIResource(175,210,245));

        sudokuPanel.add(startImage);
        sudokuPanel.setBackground(color);

        container.add(sudokuPanel);
        timerHeader = new JLabel("Time");
        timerField = new JLabel("0");


        createMenu();

        // Settings for window
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

    // Create the gamefield
    public void createGame() {

        //before creating a new field, delete the existing one
        sudokuPanel.removeAll();

        if (sudokufield != null) {
            this.sudokufield = null;
            this.frame.removeKeyListener(listener);

        }

        if (puzzle.getType() == Type.SAMURAI) {
            this.sudokufield = new SamuraiField(puzzle);
            System.out.println("Samurai Puzzle");
        } else if (puzzle.getType() == Type.CLASSIC || puzzle.getType() == Type.FREEFORM) {
            this.sudokufield = new NinesquareField(puzzle);
        }

        sudokuPanel.add(sudokufield);

        listener = new SudokuListener(sudokufield);
        sudokufield.addMouseListener(listener);
        this.frame.addKeyListener(listener);

        sudokuPanel.setLayout(new FlowLayout());
        createInfoPanel();
        this.frame.pack();

    }

    public void createMenu() throws IOException {

        // Settings for Menu Panel
        JPanel menupanel = new JPanel();
        menupanel.setPreferredSize(new Dimension(100, 600));
        menupanel.setMinimumSize(new Dimension(100, 500));
        menupanel.setLayout(new GridLayout(10,1, 0, 0));
        menupanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menupanel.setBorder(BorderFactory.createMatteBorder(0,0,0,2, Color.BLACK));
        menupanel.setBackground(color);


        // Create Iconbuttons
        JLabel load = new JLabel();
        load.setName("load");
        load.setToolTipText("Load");
        load.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel create = new JLabel();
        create.setName("create");
        create.setToolTipText("Create");
        create.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel save = new JLabel();
        save.setName("save");
        save.setToolTipText("Save");
        save.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel solve = new JLabel();
        solve.setName("solve");
        solve.setToolTipText("Solve");
        solve.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel hint = new JLabel();
        hint.setName("hint");
        hint.setToolTipText("Hint");
        hint.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel help = new JLabel();
        help.setName("help");
        help.setToolTipText("Help");
        help.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel reset = new JLabel();
        reset.setName("reset");
        reset.setToolTipText("Reset");
        reset.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel exit = new JLabel();
        exit.setName("exit");
        exit.setToolTipText("Exit");
        exit.setHorizontalAlignment(SwingConstants.CENTER);

        addIcon(load);
        addIcon(create);
        addIcon(save);
        addIcon(solve);
        addIcon(hint);
        addIcon(help);
        addIcon(reset);
        addIcon(exit);

        timerHeader.setFont(new Font("Verdana", Font.BOLD, 26));
        timerField.setFont(new Font("Verdana", Font.BOLD, 28));
        timerHeader.setHorizontalAlignment(SwingConstants.CENTER);
        timerField.setHorizontalAlignment(SwingConstants.CENTER);
        timerField.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));

        menupanel.add(timerHeader);
        menupanel.add(timerField);

        load.setFocusable(false);
        create.setFocusable(false);
        solve.setFocusable(false);
        hint.setFocusable(false);
        help.setFocusable(false);
        reset.setFocusable(false);
        exit.setFocusable(false);

        menupanel.add(load);
        menupanel.add(create);
        menupanel.add(save);
        menupanel.add(solve);
        menupanel.add(hint);
        menupanel.add(help);
        menupanel.add(reset);
        menupanel.add(exit);



        load.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                ArrayList<Puzzle> loadedPuzzles = Loader.getStoredPuzzles();
                ArrayList<String> namedPuzzles = new ArrayList<>();

                // Load  Savegames in Dropdownfield
                for (Puzzle p : loadedPuzzles) {
                    String name = p.getName();
                    String type = p.getType().toString();
                    String difficulty = p.getDifficulty().toString();
                    String dropdowntext =  name + ": " + "- Difficulty: " + difficulty + " | " + " Type: " + type;
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


            @Override
            public void mouseEntered(MouseEvent e) {
                createHover(load);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                addIcon(load);
            }
        });

        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Object[] options = {"Classic", "Samurai"};

                int selected = JOptionPane.showOptionDialog(null,
                        "Create a new Game?",
                        "Create",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);

                if (selected == 0) {
                    try {
                        puzzle = new Ninesquare();
                        puzzle.setType(Type.CLASSIC);


                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else if (selected == 1) {
                    try {
                      puzzle = new Samurai();
                      puzzle.setType(Type.SAMURAI);

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                        createGame();

                }


            @Override
            public void mouseEntered(MouseEvent e) {
                createHover(create);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addIcon(create);
            }
        });

        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                saveGame();

              /*  Object[] options = {"Save", "Save & Exit", "Cancel"};

                int selected = JOptionPane.showInputDialog(null,
                        "Save the game?",
                        "Save",
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        options,
                        options[0]);

                if (selected == 0) {
                    try {
                        //Save the Game and send playing time to puzzle
                        saveGame("Test");

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else if (selected == 1) {
                    try {
                        // Send playing time to puzzle
                        Loader.saveGame(puzzle, puzzle.getName());
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
                }*/

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createHover(save);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addIcon(save);
            }
        });

        solve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (puzzle != null) {
                    Solver.solve(puzzle);
                    sudokufield.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createHover(solve);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addIcon(solve);
            }
        });

        hint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (puzzle != null) {
                    Solver.Candidate candidate = Solver.getHint(puzzle);
                    puzzle.setEntry(candidate.getNsqFieldNr(), candidate.getRow(), candidate.getCol(), candidate.getEntry());
                    puzzle.getTile(candidate.getNsqFieldNr(), candidate.getRow(), candidate.getCol()).setHint(true);
                    sudokufield.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createHover(hint);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addIcon(hint);
            }
        });


        help.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JOptionPane.showMessageDialog(null,
                        "Hier kommt der Hilfetext zur Unterstützung für Anfänger",
                        "Sudoku Help",3);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createHover(help);
            }

            @Override
            public void mouseExited(MouseEvent e) {
               addIcon(help);
            }
        });
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Do something
            }
            @Override
            public void mouseEntered (MouseEvent e){
                createHover(reset);
            }

            @Override
            public void mouseExited (MouseEvent e){
                addIcon(reset);
            }

        });

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Object[] options = {"Save & Exit", "Exit without Saving"};

                int selected = JOptionPane.showOptionDialog(null,
                        "You want to leave?",
                        "Exit",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);

                if (selected == 0) {
                    try {
                        //Save the Game
                        saveGame();
                        System.exit(0);


                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else if (selected == 1) {
                    try {
                        // Send playing time to puzzle
                        System.exit(0);

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
                    @Override
                    public void mouseEntered (MouseEvent e){
                        createHover(exit);
                    }

                    @Override
                    public void mouseExited (MouseEvent e){
                        addIcon(exit);
                    }

        });


        this.frame.add(menupanel, BorderLayout.WEST);

    }

    public void createHover (JLabel label) {
        label.setIcon(new ImageIcon(new ImageIcon("src/main/java/ressources/btn" + label.getName() + ".png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT)));
    }

    public void addIcon (JLabel label) {
        label.setIcon(new ImageIcon(new ImageIcon("src/main/java/ressources/btn" + label.getName() + ".png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));

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

        container.add(infoPanel, BorderLayout.SOUTH);

    }

    public void saveGame() {
        String name = JOptionPane.showInputDialog(null, "Choose a name for this savegame!", "Save the game!", JOptionPane.INFORMATION_MESSAGE);


        if (puzzle.getName() == null) {
            puzzle.setDifficulty(Difficulty.NORMAL);
            Loader.saveTemplate(puzzle, name);
        }
        else {
            puzzle.setTimeUsed(timeUsed);
            Loader.saveGame(puzzle, name);
        }


    }


    public void startTimer() {

        timeUsed = puzzle.getTimeUsed();
        if (timer != null) {
           stopTimer();
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeUsed++;
                timerField.setText(String.valueOf(timeUsed));
                if(puzzle.solved) {gameFinished();}
            }
        });


        timer.start();
    }

    public void stopTimer() {

        timer.stop();
    }

    public void gameFinished(){

        stopTimer();

        JOptionPane.showMessageDialog(null,
                "You have finished your game in " + String.valueOf(timeUsed) + " seconds!",
                "Congratulations",1);

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
