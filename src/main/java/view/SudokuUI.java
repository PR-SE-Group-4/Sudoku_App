package view;

import app.Loader;
import app.Solver;
import model.Type;
import model.Puzzle;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.plaf.ColorUIResource;
import java.util.TimerTask;


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


    public SudokuField getSudokufield() {
        return sudokufield;
    }

    public SudokuUI(Puzzle puzzle) throws IOException {


        this.frame = new JFrame("Sudoku");
        this.puzzle=puzzle;
        color = new Color (175,210,245);

        container = this.frame.getContentPane();

        sudokuPanel = new JPanel(new BorderLayout(10,10));
        infoPanel = new JPanel(new FlowLayout());

        JLabel startImage = new JLabel(new ImageIcon("src/main/java/ressources/logo.png"));

        UIManager UI=new UIManager();
        UI.put("OptionPane.background",new ColorUIResource(175,210,245));
        UI.put("Panel.background",new ColorUIResource(175,210,245));

        sudokuPanel.add(startImage);
        sudokuPanel.setBackground(color);

        container.add(sudokuPanel);

        timerHeader = new JLabel("Time");
        timerField = new JLabel("0");


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

        if (sudokufield != null) {
            this.sudokufield = null;
            sudokufield.removeMouseListener(mouseListener);
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

        JPanel menupanel = new JPanel();
        menupanel.setPreferredSize(new Dimension(100, 600));
        menupanel.setMinimumSize(new Dimension(100, 500));
        menupanel.setLayout(new GridLayout(10,1, 0, 0));
        menupanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menupanel.setBorder(BorderFactory.createMatteBorder(0,0,0,2, Color.BLACK));
        menupanel.setBackground(color);


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
        help.setToolTipText("Helo");
        help.setHorizontalAlignment(SwingConstants.CENTER);
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
        addIcon(exit);


        timerField.setFont(new Font("Verdana", Font.BOLD, 30));
        timerHeader.setFont(new Font("Verdana", Font.BOLD, 26));
        timerHeader.setHorizontalAlignment(SwingConstants.CENTER);
        timerField.setVerticalAlignment(SwingConstants.NORTH);
        timerField.setHorizontalAlignment(SwingConstants.CENTER);

        menupanel.add(timerHeader);
        menupanel.add(timerField);
        menupanel.add(load);
        menupanel.add(create);
        menupanel.add(save);
        menupanel.add(solve);
        menupanel.add(hint);
        menupanel.add(help);
        menupanel.add(exit);



        load.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
                Solver.solve(puzzle);
                sudokufield.repaint();
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
                sudokufield.repaint();
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

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createHover(exit);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addIcon(exit);
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

                    System.out.println("Checked? " + sudokufield.showConflicts);
                }
            });

            startTimer();

        infoPanel.add(showConflicts);
     //   infoPanel.add(timerField);
        container.add(infoPanel, BorderLayout.SOUTH);

    }


    public void startTimer() {


        timeUsed = puzzle.getTimeUsed();
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeUsed++;
                timerField.setText(String.valueOf(timeUsed));

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
