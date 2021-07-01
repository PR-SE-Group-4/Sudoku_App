package view;

import app.Loader;
import app.Solver;
import model.*;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.plaf.ColorUIResource;

/**
 * The SudokuUI class draws the frame and the menu for the application and contains the sudokufield
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */
public class SudokuUI {

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

    /**
     * Constructor for the class SudokuUI, sets settings for the window
     * @throws IOException
     */
    public SudokuUI() throws IOException {

        this.frame = new JFrame("Sudoku");
        color = new Color (175,210,245);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation(dim.width/2-this.frame.getWidth(), 100);

        container = this.frame.getContentPane();

        sudokuPanel = new JPanel(new BorderLayout(10,10));
        infoPanel = new JPanel(new FlowLayout());

        //Load Startimage
        ImageIcon logo = new ImageIcon("src/main/java/ressources/logo.png");
        JLabel startImage = new JLabel(logo);
        this.frame.setIconImage(logo.getImage());

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

    /**
     * Creates the gamefield (sudokufield) for the game and adds listener
     */
    public void createGame() {

        //before creating a new field, delete the existing one
        sudokuPanel.removeAll();

        if (sudokufield != null) {
            this.sudokufield = null;
            this.frame.removeKeyListener(listener);

        }

        if (puzzle.getType() == Type.SAMURAI) {
            this.sudokufield = new SamuraiField(puzzle);
        } else {
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

    /**
     * Creates the Menu for the window and adds listeners to the buttons
     * @throws IOException
     */
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
                    String dropdowntext =  name + " - " + difficulty + " - " + type;
                    namedPuzzles.add(dropdowntext);
                }
                Object[] selectionValues = namedPuzzles.toArray();

                Object selected = JOptionPane.showInputDialog(sudokufield, "Choose a puzzle?",
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

                int selected = JOptionPane.showOptionDialog(sudokuPanel,
                        "Create a new Game?",
                        "Create",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);

                if (selected == 0) {
                    try {
                        puzzle = new Ninesquare();

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

                if (puzzle != null) {
                    saveGame();
                }
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
                    if (candidate == null) {
                        JOptionPane.showMessageDialog(sudokuPanel,
                                "No more hints found!",
                                "Information!",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        puzzle.setEntry(candidate.getNsqFieldNr(), candidate.getRow(), candidate.getCol(), candidate.getEntry());
                        puzzle.getTile(candidate.getNsqFieldNr(), candidate.getRow(), candidate.getCol()).setHint(true);
                        sudokufield.repaint();
                    }

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

                String filename = "src/main/java/ressources/btn";


                Object inputHelp[] = {
                        new ImageIcon(new ImageIcon(filename  + "load.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)), new JLabel("Here you can open a saved game!", JLabel.CENTER),
                        new ImageIcon(new ImageIcon(filename  + "create.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)), new JLabel("Here you can create a new empty game!", JLabel.CENTER),
                        new ImageIcon(new ImageIcon(filename  + "save.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)), new JLabel("Here you can save your game!", JLabel.CENTER),
                        new ImageIcon(new ImageIcon(filename  + "solve.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)), new JLabel("Here you can solve your game!", JLabel.CENTER),
                        new ImageIcon(new ImageIcon(filename  + "hint.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)), new JLabel("Here you can ask for a hint!", JLabel.CENTER),
                        new ImageIcon(new ImageIcon(filename  + "help.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)), new JLabel("Here you can find help!", JLabel.CENTER),
                        new ImageIcon(new ImageIcon(filename  + "reset.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)), new JLabel("Here you can reset the game!", JLabel.CENTER),
                        new ImageIcon(new ImageIcon(filename  + "exit.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)), new JLabel("Here you can exit the game!", JLabel.CENTER)};


                JOptionPane helpPanel = new JOptionPane();
                helpPanel.setMessage(inputHelp);
                helpPanel.setAlignmentX(JLabel.CENTER);
                JDialog dialog = helpPanel.createDialog(sudokuPanel, "Do you need our help?");
                dialog.setVisible(true);
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

                if (puzzle != null) {
                    Solver.deleteGuess(puzzle);
                    sudokufield.repaint();
                }
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

                int selected = JOptionPane.showOptionDialog(sudokuPanel,
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

    /**
     * Creates a hover for a menu-buttons
     * @param label label to create a hover for
     */
    public void createHover (JLabel label) {
        label.setIcon(new ImageIcon(new ImageIcon("src/main/java/ressources/btn" + label.getName() + ".png").getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT)));
    }

    /**
     * Sets an icon for a menu-button
     * @param label label to add the icon to
     */
    public void addIcon (JLabel label) {
        label.setIcon(new ImageIcon(new ImageIcon("src/main/java/ressources/btn" + label.getName() + ".png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
    }


    /**
     * Creates a JLabel for saving a game
     */
    public void saveGame() {

        JLabel labelName = new JLabel("Choose a name for this savegame:");
        JTextField sudokuName = new JTextField();
        if (puzzle.getName() != null) {sudokuName.setText(puzzle.getName());}
        JLabel labelDiff = new JLabel("Choose a difficulty for this savegame!");
        JComboBox sudokuDifficulty = new JComboBox(Difficulty.values());

        final JComponent[] inputs = new JComponent[4];
        inputs[0] = labelName;
        inputs[1] = sudokuName;

        if (puzzle.getDifficulty() == null) {

            inputs[2] = labelDiff;
            inputs[3] = sudokuDifficulty;
        }


        int result = JOptionPane.showConfirmDialog(sudokuPanel,inputs, "Save the game!", JOptionPane.OK_CANCEL_OPTION );

        if (result == JOptionPane.OK_OPTION && !sudokuName.getText().isEmpty()) {

            if (puzzle.getName().equals("new Classic") || puzzle.getName().equals("new Samurai")) {
                puzzle.setDifficulty(Difficulty.valueOf(sudokuDifficulty.getSelectedItem().toString()));
                Loader.saveTemplate(puzzle, sudokuName.getText());
            } else {
                puzzle.setTimeUsed(timeUsed);
                try {
                    Loader.saveGame(puzzle, sudokuName.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            JOptionPane.showMessageDialog(sudokuPanel,"Successful! ", "Game saved!!", JOptionPane.INFORMATION_MESSAGE );
        }

        else if (result == JOptionPane.OK_OPTION && sudokuName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(sudokuPanel,"Missing name! Can't save your game! ", "Missing Name!!", JOptionPane.OK_OPTION );
        }


    }

    /**
     * Creates an info-panel and calls startTimer
     */
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

    /**
     * Starts the timer
     */
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
                if(puzzle.isSolved()) {gameFinished();}
            }
        });

        timer.start();
    }

    /**
     * Stops the timer
     */
    public void stopTimer() {

        timer.stop();
    }

    /**
     * Creates a pane when game is finished
     */
    public void gameFinished(){

        stopTimer();

        JOptionPane.showMessageDialog(sudokuPanel,
                "You have finished your game in " + String.valueOf(timeUsed) + " seconds!",
                "Congratulations",1);

    }

}
