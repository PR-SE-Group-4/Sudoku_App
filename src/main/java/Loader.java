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


    public static Puzzle loadPuzzle(int nrOfFile) throws Exception {
        files = getFiles();
        FileReader fileReader = new FileReader(files[nrOfFile]);

        String name = files[0].toString();
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
                    case 1: // second: Type of Puzzle
                        type = Type.valueOf(sb.toString());
                        System.out.println(type);
                        break;
                    case 2: // third: solved
                        if (sb.toString() == "true")
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

}