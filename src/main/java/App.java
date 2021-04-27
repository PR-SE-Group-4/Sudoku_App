import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class App {

    public static void main(String[] args) throws Exception {

        setup();
        Puzzle puzzle = Loader.loadPuzzle(1);
 //       SudokuUI sudokuUI = new SudokuUI(puzzle);
        System.out.println(puzzle);
    }

    private static void setup() {
        //creates filesave dir if not present
        //creates two puzzles if not present
        File appdir = new File(System.getenv("APPDATA")+"\\SudokuGR04");
        appdir.mkdir();
        try {
            File presaved01 = new File(appdir + "\\supereasy.txt");
            presaved01.createNewFile();
            FileWriter presaved01wr = new FileWriter(presaved01);
            presaved01wr.write("EASY;CLASSIC;false;000000;1U0-1O0-1O3-2O4-2O5-2O6-3O7-3U0-3O9-1O4-1O5-1O6-2O7-2O8-2U0-3O1-3O2-3O3-1O7-1O8-1O9-2U0-2O2-2O3-3O4-3O5-3O6-4O2-4U0-4O4-5O5-5O6-5O7-6O8-6O9-6U0-4O5-4O6-4O7-5O8-5O9-5O1-6U0-6O3-6O4-4O8-4O9-4O1-5O2-5U0-5O4-6O5-6O6-6O7-7O3-7O4-7U0-8O6-8O7-8O8-9O9-9O1-9O2-7U0-7O7-7O8-8O9-8O1-8O2-9O3-9U0-9O5-7O9-7O1-7O2-8O3-8O4-8U0-9O6-9O7-9O8-;");
            presaved01wr.close();
            File presaved02 = new File(appdir + "\\medium01.txt");
            presaved02.createNewFile();
            FileWriter presaved02wr = new FileWriter(presaved02);
            presaved02wr.write("NORMAL;CLASSIC;false;000000;1U0-1U0-1O4-2U0-2O8-2O6-3U0-3O1-3U0-1U0-1O1-1U0-2U0-2O3-2U0-3U0-3U0-3O4-1U0-1U0-1O3-2O2-2U0-2O4-3O6-3O9-3U0-4O1-4U0-4O5-5O6-5O7-5U0-6U0-6U0-6O3-4O8-4U0-4U0-5U0-5U0-5U0-6U0-6O5-6U0-4U0-4U0-4O2-5U0-5O5-5U0-6O4-6U0-6O9-7U0-7U0-7U0-8U0-8U0-8U0-9O9-9U0-9U0-7O3-7U0-7O6-8U0-8O9-8O1-9O7-9U0-9U0-7U0-7O8-7U0-8U0-8U0-8U0-9U0-9U0-9U0-;");
            presaved02wr.close();
            File presaved03 = new File(appdir + "\\samurai01.txt");
            presaved03.createNewFile();
            FileWriter presaved03wr = new FileWriter(presaved03);
            presaved03wr.write("HARD;SAMURAI;false;000000;1U0-1U0-1O4-2U0-2O8-2O6-3U0-3O1-3U0-1U0-1O1-1U0-2U0-2O3-2U0-3U0-3U0-3O4-1U0-1U0-1O3-2O2-2U0-2O4-3O6-3O9-3U0-4O1-4U0-4O5-5O6-5O7-5U0-6U0-6U0-6O3-4O8-4U0-4U0-5U0-5U0-5U0-6U0-6O5-6U0-4U0-4U0-4O2-5U0-5O5-5U0-6O4-6U0-6O9-7U0-7U0-7U0-8U0-8U0-8U0-9O9-9U0-9U0-7O3-7U0-7O6-8U0-8O9-8O1-9O7-9U0-9U0-7U0-7O8-7U0-8U0-8U0-8U0-9U0-9U0-9U0-x1U0-1U0-1O4-2U0-2O8-2O6-3U0-3O1-3U0-1U0-1O1-1U0-2U0-2O3-2U0-3U0-3U0-3O4-1U0-1U0-1O3-2O2-2U0-2O4-3O6-3O9-3U0-4O1-4U0-4O5-5O6-5O7-5U0-6U0-6U0-6O3-4O8-4U0-4U0-5U0-5U0-5U0-6U0-6O5-6U0-4U0-4U0-4O2-5U0-5O5-5U0-6O4-6U0-6O9-7U0-7U0-7U0-8U0-8U0-8U0-9O9-9U0-9U0-7O3-7U0-7O6-8U0-8O9-8O1-9O7-9U0-9U0-7U0-7O8-7U0-8U0-8U0-8U0-9U0-9U0-9U0-x1U0-1U0-1O4-2U0-2O8-2O6-3U0-3O1-3U0-1U0-1O1-1U0-2U0-2O3-2U0-3U0-3U0-3O4-1U0-1U0-1O3-2O2-2U0-2O4-3O6-3O9-3U0-4O1-4U0-4O5-5O6-5O7-5U0-6U0-6U0-6O3-4O8-4U0-4U0-5U0-5U0-5U0-6U0-6O5-6U0-4U0-4U0-4O2-5U0-5O5-5U0-6O4-6U0-6O9-7U0-7U0-7U0-8U0-8U0-8U0-9O9-9U0-9U0-7O3-7U0-7O6-8U0-8O9-8O1-9O7-9U0-9U0-7U0-7O8-7U0-8U0-8U0-8U0-9U0-9U0-9U0-x1U0-1U0-1O4-2U0-2O8-2O6-3U0-3O1-3U0-1U0-1O1-1U0-2U0-2O3-2U0-3U0-3U0-3O4-1U0-1U0-1O3-2O2-2U0-2O4-3O6-3O9-3U0-4O1-4U0-4O5-5O6-5O7-5U0-6U0-6U0-6O3-4O8-4U0-4U0-5U0-5U0-5U0-6U0-6O5-6U0-4U0-4U0-4O2-5U0-5O5-5U0-6O4-6U0-6O9-7U0-7U0-7U0-8U0-8U0-8U0-9O9-9U0-9U0-7O3-7U0-7O6-8U0-8O9-8O1-9O7-9U0-9U0-7U0-7O8-7U0-8U0-8U0-8U0-9U0-9U0-9U0-x1U0-1U0-1O4-2U0-2O8-2O6-3U0-3O1-3U0-1U0-1O1-1U0-2U0-2O3-2U0-3U0-3U0-3O4-1U0-1U0-1O3-2O2-2U0-2O4-3O6-3O9-3U0-4O1-4U0-4O5-5O6-5O7-5U0-6U0-6U0-6O3-4O8-4U0-4U0-5U0-5U0-5U0-6U0-6O5-6U0-4U0-4U0-4O2-5U0-5O5-5U0-6O4-6U0-6O9-7U0-7U0-7U0-8U0-8U0-8U0-9O9-9U0-9U0-7O3-7U0-7O6-8U0-8O9-8O1-9O7-9U0-9U0-7U0-7O8-7U0-8U0-8U0-8U0-9U0-9U0-9U0-;");
            presaved03wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

