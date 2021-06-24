package app;

import model.*;

import java.io.*;
import java.util.ArrayList;

public class Loader {

    private static File[] files;
    private static ArrayList<Puzzle> storedPuzzles;

    // -------------------------------------------
    // -- setup game for first use --------------
    // ------------------------------------------

    public static void setup() {
        //creates filesave dir if not present
        //creates four puzzles if not present
        File appdir = new File(System.getenv("APPDATA")+"\\SudokuGR04");
        if (!appdir.exists()) {
            appdir.mkdir();
            try {
                File presaved00 = new File(appdir + "\\supereasy.txt");
                presaved00.createNewFile();
                FileWriter presaved00wr = new FileWriter(presaved00);
                presaved00wr.write("EASY;CLASSIC;false;000000;1U0-1U0-1O3-2O4-2O5-2O6-3O7-3U0-3O9-1O4-1O5-1O6-2O7-2O8-2U0-3O1-3O2-3O3-1O7-1O8-1O9-2U0-2O2-2O3-3O4-3O5-3O6-4O2-4U0-4O4-5O5-5O6-5O7-6O8-6O9-6U0-4O5-4O6-4O7-5O8-5O9-5O1-6U0-6O3-6O4-4O8-4O9-4O1-5O2-5U0-5O4-6O5-6O6-6O7-7O3-7O4-7U0-8O6-8O7-8O8-9O9-9O1-9O2-7U0-7O7-7O8-8O9-8O1-8O2-9O3-9U0-9O5-7O9-7O1-7O2-8O3-8O4-8U0-9O6-9O7-9O8-;");
                presaved00wr.close();
                File presaved01 = new File(appdir + "\\medium01.txt");
                presaved01.createNewFile();
                FileWriter presaved01wr = new FileWriter(presaved01);
                presaved01wr.write("NORMAL;CLASSIC;false;000000;1U0-1U0-1O4-2U0-2O8-2O6-3U0-3O1-3U0-1U0-1O1-1U0-2U0-2O3-2U0-3U0-3U0-3O4-1U0-1U0-1O3-2O2-2U0-2O4-3O6-3O9-3U0-4O1-4U0-4O5-5O6-5O7-5U0-6U0-6U0-6O3-4O8-4U0-4U0-5U0-5U0-5U0-6U0-6O5-6U0-4U0-4U0-4O2-5U0-5O5-5U0-6O4-6U0-6O9-7U0-7U0-7U0-8U0-8U0-8U0-9O9-9U0-9U0-7O3-7U0-7O6-8U0-8O9-8O1-9O7-9U0-9U0-7U0-7O8-7U0-8U0-8U0-8U0-9U0-9U0-9U0-;");
                presaved01wr.close();
                File presaved02 = new File(appdir + "\\samurai01.txt");
                presaved02.createNewFile();
                FileWriter presaved02wr = new FileWriter(presaved02);
                presaved02wr.write("HARD;SAMURAI;false;000000;1O2-1U0-1O5-2U0-2U0-2U0-3O9-3U0-3O4-1U0-1O7-1U0-2U0-2U0-2U0-3U0-3O5-3U0-1O8-1U0-1U0-2U0-2O6-2U0-3U0-3U0-3O1-4U0-4U0-4U0-5O4-5U0-5O9-6U0-6U0-6U0-4U0-4U0-4O6-5U0-5U0-5U0-6O2-6U0-6U0-4U0-4U0-4U0-5O6-5U0-5O2-6U0-6U0-6U0-7O4-7U0-7U0-8U0-8O8-8U0-9U0-9U0-9O9-7U0-7O3-7U0-8U0-8U0-8U0-9U0-9O1-9U0-7O1-7U0-7O8-8U0-8U0-8U0-9O4-9U0-9O7-x1O6-1U0-1O7-2U0-2U0-2U0-3O3-3U0-3O1-1U0-1O9-1U0-2U0-2U0-2U0-3U0-3O7-3U0-1O1-1U0-1U0-2U0-2O2-2U0-3U0-3U0-3O9-4U0-4U0-4U0-5O1-5U0-5O4-6U0-6U0-6U0-4U0-4U0-4O3-5U0-5U0-5U0-6O5-6U0-6U0-4U0-4U0-4U0-5O2-5U0-5O8-6U0-6U0-6U0-7O5-7U0-7U0-8U0-8O6-8U0-9U0-9U0-9O4-7U0-7O4-7U0-8U0-8U0-8U0-9U0-9O8-9U0-7O8-7U0-7O6-8U0-8U0-8U0-9O1-9U0-9O5-x1U0-1U0-1O9-2U0-2U0-2U0-3O5-3U0-3U0-1U0-1O1-1U0-2U0-2U0-2U0-3U0-3O4-3U0-1O4-1U0-1O7-2U0-2U0-2U0-3O8-3U0-3O6-4U0-4U0-4U0-5O4-5U0-5O3-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5O7-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5O2-5U0-5O9-6U0-6U0-6U0-7O1-7U0-7O4-8U0-8U0-8U0-9O2-9U0-9O5-7U0-7O9-7U0-8U0-8U0-8U0-9U0-9O1-9U0-7U0-7U0-7O8-8U0-8U0-8U0-9O4-9U0-9U0-x1O8-1U0-1O9-2U0-2U0-2U0-3O1-3U0-3O4-1U0-1O4-1U0-2U0-2U0-2U0-3U0-3O9-3U0-1O2-1U0-1U0-2U0-2O7-2U0-3U0-3U0-3O8-4U0-4U0-4U0-5O7-5U0-5O8-6U0-6U0-6U0-4U0-4U0-4O2-5U0-5U0-5U0-6O6-6U0-6U0-4U0-4U0-4U0-5O1-5U0-5O2-6U0-6U0-6U0-7O9-7U0-7U0-8U0-8O2-8U0-9U0-9U0-9O5-7U0-7O2-7U0-8U0-8U0-8U0-9U0-9O4-9U0-7O1-7U0-7O4-8U0-8U0-8U0-9O9-9U0-9O7-x1O2-1U0-1O5-2U0-2U0-2U0-3O3-3U0-3O7-1U0-1O1-1U0-2U0-2U0-2U0-3U0-3O6-3U0-1O4-1U0-1U0-2U0-2O1-2U0-3U0-3U0-3O8-4U0-4U0-4U0-5O2-5U0-5O9-6U0-6U0-6U0-4U0-4U0-4O8-5U0-5U0-5U0-6O6-6U0-6U0-4U0-4U0-4U0-5O1-5U0-5O4-6U0-6U0-6U0-7O7-7U0-7U0-8U0-8O9-8U0-9U0-9U0-9O6-7U0-7O3-7U0-8U0-8U0-8U0-9U0-9O9-9U0-7O6-7U0-7O4-8U0-8U0-8U0-9O5-9U0-9O3-;");
                presaved02wr.close();
                File presaved03 = new File(appdir + "\\freeform01.txt");
                presaved03.createNewFile();
                FileWriter presaved03wr = new FileWriter(presaved03);
                presaved03wr.write("HARD;FREEFORM;false;000000;1O5-2U0-2O3-2U0-3U0-3O1-3O7-3U0-3U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-4O3-1O8-1U0-1O4-2O6-2U0-2O7-3U0-4U0-4U0-1O1-1U0-8U0-9U0-9O6-9U0-4U0-4U0-4U0-1U0-8U0-8U0-9O3-9U0-9O9-4U0-4U0-5U0-8U0-8U0-8U0-9U0-9O8-9U0-4U0-5U0-5O9-8U0-8U0-7U0-6O5-6U0-6O3-5O4-5U0-5O1-8O3-7U0-7U0-7U0-6U0-6U0-6U0-5U0-5U0-7U0-7U0-7O5-7O8-7U0-6U0-6O1-6U0-5O7-;");
                presaved03wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //---------------------------------------------------
    // -- get saved files as list or a single puzzle ----
    // --------------------------------------------------

    public static File[] getFiles() /*throws IOException*/ {

        String OS = (System.getProperty("os.name")).toUpperCase();
        File path;
        if (OS.contains("WIN")) {
            path = new File(System.getenv("APPDATA")+"\\SudokuGR04");
        }
        else
        {
            path = new File(System.getProperty("user.home" + "/Library/Application Support/SudokuGR04" ));
        }

        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File dir) {
                if (dir.isFile()) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        //in the files-array there is a list of all files in the dir
        files = path.listFiles(fileFilter);
        for (File i : files) {
            System.out.println(i);
        }
        return files;
    }

    public static ArrayList<Puzzle> getStoredPuzzles() {
        files = getFiles();
        storedPuzzles = new ArrayList<Puzzle>();
        System.out.println();
        for (int j = 0; j < files.length; j++) {
            try {
                storedPuzzles.add(loadPuzzle(j));
            } catch(Exception e) {
                System.out.println("Error in getting Puzzles");}
        }
        return storedPuzzles;
    }


    public static Puzzle loadPuzzle(int nrOfFile) throws Exception {
        files = getFiles();
        FileReader fileReader = new FileReader(files[nrOfFile]);

        String name = files[nrOfFile].toString();
        name = name.substring(name.indexOf("4")+1, name.indexOf("."));
        Difficulty difficulty = null;
        Type type = null;
        boolean solved = true;
        int timeUsed = 0;
        String contentString = "";

        int i;
        StringBuilder sb = new StringBuilder();
        int stage = 0;
        // String parser
        while ((i = fileReader.read()) != -1) {
            if ((char) i == ';') {
                switch (stage) {
                    case 0: // first block: difficulty
                        difficulty = Difficulty.valueOf(sb.toString());
                        System.out.println(difficulty);
                        break;
                    case 1: // second: model.Type of model.Puzzle
                        type = Type.valueOf(sb.toString());
                        System.out.println(type);
                        break;
                    case 2: // third: solved
                        if (sb.toString().equals("true"))
                            solved = true;
                        else
                            solved = false;
                        System.out.println(solved);
                        break;
                    case 3: //fourth: timeUsed
                        timeUsed = Integer.parseInt(sb.toString());
                        System.out.println(timeUsed);
                        break;
                    case 4: // fifth: ContentString
                        contentString = sb.toString();
                        System.out.println(contentString);
                        break;
                    default:
                        throw new Exception();
                }
                sb = new StringBuilder();
                stage++;
            } else {
                sb.append((char) i);
            }
        }

        if (contentString.length() == 324) {
            return new Ninesquare(name, difficulty, type, solved, timeUsed, contentString);
        } else if (contentString.length() == 1624) {
            return new Samurai(name, difficulty, type, solved, timeUsed, contentString);
        } else {
            System.out.println(contentString.length());
            throw new Exception();
        }

    }

    // ---------------------------------------------------------
    // -- save a model.Puzzle to disc after playing ------------------
    // ---------------------------------------------------------

    public static void saveGame(Puzzle puzzle, String name){
        try {
            File file = new File(System.getenv("APPDATA")+"\\SudokuGR04\\" + name + ".txt");
            FileWriter wr = new FileWriter(file);
            wr.write(puzzle.export());
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // -- save a model.Puzzle Template -------------------------
    // ---------------------------------------------------------

    public static void saveTemplate(Puzzle puzzle, String name) {
        for (int fieldnr=0; fieldnr<5; fieldnr++){
            if(puzzle.getType() != Type.SAMURAI) {fieldnr=5;}

            for (int r=0;r<9;r++){
                for (int c=0;c<9;c++){
                    if(puzzle.getTile(fieldnr,r,c).getEntry() !=0) {
                        puzzle.getTile(fieldnr,r,c).setChangeable(false);
                    }
                }
            }
        }
        puzzle.setName(name);
        saveGame(puzzle, name);
    }

}