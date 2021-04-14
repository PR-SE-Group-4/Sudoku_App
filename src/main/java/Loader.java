import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.FileReader;

public class Loader {

    static File[] files;

    static File[] getFiles() /*throws IOException*/ {
        File path = new File(System.getenv("APPDATA")+"\\SudokuGR04");
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
        int stage=0;
        // String parser
        while ((i = fileReader.read()) != -1)  {
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
        return new Ninesquare(name, difficulty, type, solved, contentString);

    }

}