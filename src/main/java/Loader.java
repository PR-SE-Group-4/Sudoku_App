import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class Loader {

    private static File[] files;
    private ArrayList<Puzzle> storedPuzzles;

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

    public ArrayList<Puzzle> getStoredPuzzles() {
        files = getFiles();
        storedPuzzles = new ArrayList<Puzzle>();
        System.out.println();
        for (int j = 0; j <= files.length; j++) {
            try {
                storedPuzzles.add(loadPuzzle(j));
            } catch(Exception e) {
                System.out.println("EXPECTICOTIDON");}
        }
        return storedPuzzles;
    }


    public static Ninesquare loadPuzzle(int nrOfFile) throws Exception {
        files = getFiles();
        FileReader fileReader = new FileReader(files[nrOfFile]);

        String name = files[0].toString();
        Difficulty difficulty = null;
        Type type = null;
        boolean solved = true;
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
                        break;
                    case 1: // second: Type of Puzzle
                        type = Type.valueOf(sb.toString());
                        break;
                    case 2: // third: solved
                        if (sb.toString() == "true")
                            solved = true;
                        else
                            solved = false;
                        break;
                    case 3: //fourth: contentstring
                        contentString = sb.toString();
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

        // TODO: die Samurai Rückgabe;
        if (contentString.length() == 324) {
            return new Ninesquare(name, difficulty, type, solved, contentString);
        } else {
            throw new Exception();
        }

    }

}