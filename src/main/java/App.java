public class App {


    public static void main(String[] args) throws Exception {


        new SudokuUI();
        //New
        System.out.println("Test");

        // "Test": Erstes Klassik-sudoku anlegen und ausgeben.

    //    Ninesquare test9sq = new Ninesquare();
    //    System.out.print(test9sq);

        Ninesquare fileOne = Loader.loadFileOne();
        System.out.print(fileOne);
    }

}

