package app;

/**
 * The App class contains the main-method and is responsible for starting the application
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */
public class App {

    /**
     * starts the application
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Loader.setup();

        new view.SudokuUI();

    }




}

