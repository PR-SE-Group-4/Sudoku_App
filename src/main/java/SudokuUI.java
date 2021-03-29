import javax.swing.*;

public class SudokuUI {

    private final JFrame frame = new JFrame("Sudoku");

    public SudokuUI() {


    }

    public void SudokuMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu game = new JMenu("Spiel");
        JMenu help = new JMenu("Hilfe");

        JMenuItem newgame = new JMenuItem("Neues Spiel");
        JMenuItem load = new JMenuItem("Laden");
        JMenuItem export = new JMenuItem("Exportieren");
        JMenuItem classic = new JMenuItem("Classic");
        JMenuItem matrix = new JMenuItem("Matrix");
        JMenuItem close = new JMenuItem("Schließen");

        game.add(newgame);
        game.add(load);
        game.add(export);
        game.add(classic);
        game.add(load);
        game.add(load);

    }
}
