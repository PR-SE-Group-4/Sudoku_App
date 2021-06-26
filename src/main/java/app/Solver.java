package app;

import model.Ninesquare;
import model.Puzzle;
import model.Samurai;
import model.Tile;

import java.util.LinkedList;
import java.util.Random;

/**
 * The Solver class provides methods for auto-solving sudoku-games, giving hints and resetting game to initial state.
 * @author Clemens Grill, Thomas Hollin, Lisa Köberl
 * @version %I%, %G%
 */

public class Solver {

    /**
     * The Candidate class represents a possible entry with its ninesquarfieldnumber, row, column and value
     *
     */
    public static class Candidate {
        int nsqFieldNr;
        int row;
        int col;
        int entry;

        /**
         * Constructor for the class Candiate
         * @param nsqFieldNr number of ninesquarefield (important for samurai)
         * @param row row of entry
         * @param col column of entry
         * @param entry entry
         */

        public Candidate(int nsqFieldNr, int row, int col, int entry) {
            this.nsqFieldNr = nsqFieldNr;
            this.row = row;
            this.col = col;
            this.entry = entry;
        }

        /**
         * Returns fieldnumber of a Candidate (important for samurai)
         * @return fieldnumber
         */

        public int getNsqFieldNr() {
            return nsqFieldNr;
        }

        /**
         * Returns row of a Candidate
         * @return row
         */
        public int getRow() {
            return row;
        }

        /**
         * Returns column of a Candidate
         * @return column
         */
        public int getCol() {
            return col;
        }

        /**
         * Returns entry of a Candidate
         * @return entry
         */

        public int getEntry() {
            return entry;
        }

        /**
         * Converts the Candidate object to a String
         * @return String of Candidate
         */
        @Override
        public String toString() {
            return row + ", " + col + ", " + entry;
        }
    }



    static LinkedList<Candidate> getCandidates(int nsqFieldNr, Ninesquare toSolve, int numberOfOptions) {

        // at NumberOfOptions == 1 the result is definitive.
        // at a higher NumberOfOptions, the result is less and less probable
        //boolean[row][col][pillar / entry]
        boolean[][][] candidateCube = new boolean[9][9][9]; // mit false initialisiert: false bedeutet "ist Kandidat"

        for (int row = 0; row < 9; row++) {                       // alle Zeilen des zu lösenden Ninesquare durchlaufen
            for (int col =0; col < 9; col++) {                    // alle Spalten
                Tile currTile = toSolve.getTile(99, row, col);    // das aktuell für Elimination vorgesehene Tile
                if (currTile.isFilled()) {                        // das Tile in row, col ist filled, Elimination beginnt
                    int eliminationValue = currTile.getEntry() - 1;   // der Wert im aktuellen Tile, umgerechnet auf Arrayindex (1 = 0)

                    for (int i = 0; i < 9; i++) {
                        // clearFilledPillar: diese row, col ist befült und braucht keine Candidates mehr
                        candidateCube[row][col][i] = true;
                        // clearNumberinRow: in dieser row gibt es die Zahl eliminationValue schon und kann nicht mehr vorkommen
                        candidateCube[row][i][eliminationValue] = true;
                        // clearNumberinCol: in dieser col gibt es die Zahl eliminationValue schon und kann nicht mehr vorkommen
                        candidateCube[i][col][eliminationValue] = true;
                    }

                    // clearNumberInArea: in dieser area gibt es die Zahl eliminationValue schon und kann nicht mehr vorkommen
                    // 1. Area/Color des aktuellen Tile evaluieren
                    int currentArea = toSolve.getBelongsToArea(99,row, col);
                    // 2. toSolve durchiterieren und bei getColor-Hit dann eliminieren
                    for (int arearow = 0; arearow < 9; arearow++) {
                        for (int areacol = 0; areacol < 9; areacol++) {
                            if (toSolve.getBelongsToArea(99,arearow, areacol) == currentArea) {
                                candidateCube[arearow][areacol][eliminationValue] = true;
                            }
                        }
                    }
                }
            }
        }

        // make a list of candidates
        LinkedList<Solver.Candidate> candidates = new LinkedList<>();
        int falseCounterRCP, falseCounterCPR, falseCounterPRC;          // counts how many candidates in a Row, Column, Pillar
        int triggerP = 0, triggerR = 0, triggerC = 0;                   // used when a fitting amount is found

        for (int v1 = 0; v1 < 9; v1++){                                 // v1 can be row, column or pillar
            for (int v2 = 0; v2 < 9; v2++){                             // v2 can be column pillar or row
                falseCounterRCP = 0;
                falseCounterCPR = 0;
                falseCounterPRC = 0;
                for (int v3 = 0; v3 <9; v3++){
                    if(!candidateCube[v1][v2][v3]) {                    // if the arrayfield is false, it is a probable candidate
                        falseCounterRCP++;                              // probable candidates in Pillar are counted
                        triggerP = v3;                                  // stores the current pillar for triggering
                    }
                    if(!candidateCube[v3][v1][v2]) {                    // see above, only pivoted
                        falseCounterCPR++;
                        triggerR = v3;
                    }
                    if(!candidateCube[v2][v3][v1]) {
                        falseCounterPRC++;
                        triggerC = v3;
                    }
                }
                if (falseCounterRCP > 0 && falseCounterRCP <= numberOfOptions) {    // if there is a fitting amount of probable candidates
                    candidates.add(new Solver.Candidate(nsqFieldNr, v1, v2, triggerP+1));   // it is added as a definitive candidate
                }
                if (falseCounterCPR > 0 && falseCounterCPR <= numberOfOptions) {
                    candidates.add(new Solver.Candidate(nsqFieldNr, triggerR, v1, v2+1));
                }
                if (falseCounterPRC > 0 && falseCounterPRC <= numberOfOptions) {
                    candidates.add(new Solver.Candidate(nsqFieldNr, v2, triggerC, v1+1));
                }
            }
        }
    return candidates;
    }

    public static void solve(Puzzle toSolve) {
        if (Ninesquare.class.isInstance(toSolve)) {
            Solver.solve9sq((Ninesquare) toSolve);

        } else if (Samurai.class.isInstance(toSolve)) {
            Solver.solveSam((Samurai) toSolve);
        }
        System.out.println(toSolve);
    }

    @org.jetbrains.annotations.Nullable
    public static Candidate getHint(Puzzle toSolve) {
        LinkedList<Candidate> candidates = new LinkedList<Candidate>();
        if (Ninesquare.class.isInstance(toSolve)) {                 // Puzzle is Ninesquare
            Ninesquare ts = (Ninesquare) toSolve;
            candidates.addAll(getCandidates(99, ts, 1));

        } else if (Samurai.class.isInstance(toSolve)) {             // Puzzle is Samurai
            Ninesquare ts;
            for (int nsqFieldNr = 0; nsqFieldNr<5; nsqFieldNr++) {
                ts = ((Samurai) toSolve).getNinesquare(nsqFieldNr);
                candidates.addAll(getCandidates(nsqFieldNr, ts, 1));
            }
        }
        if (candidates.isEmpty()) {
            return null;
        } else {
            return candidates.get(0);
        }

    }

    public static void solve9sq(Ninesquare toSolve) { // solve with a mixture of conclusive solving and educated guessing
        boolean solvingIsWorking = true;
        do { // as long as it isn't solved
            solve9sqPrecisely(toSolve); // fill all unambiguous fields
            solvingIsWorking = solve9sqUnprecise(toSolve); // guess one probable number
            if (!solvingIsWorking && !toSolve.isSolved()) deleteGuess(toSolve); // this is a dead end
        } while (!toSolve.isSolved());
    }

    public static int solve9sqPrecisely(Ninesquare toSolve) {
        LinkedList<Candidate> candidates = new LinkedList<>();
        candidates.add(new Candidate(99, 0,0,0));
        int filledFields = 0;
        while (!toSolve.isSolved() && !candidates.isEmpty()){
            candidates = getCandidates(99, toSolve, 1);
            for (Candidate c : candidates) {
                filledFields++;
                toSolve.setEntry(99, c.row, c.col, c.entry);
            }
        }
        return filledFields;
    }

    private static boolean solve9sqUnprecise(Ninesquare toSolve) {
        for (int unprecision = 2; unprecision < 10; unprecision++) {
            LinkedList<Candidate> currentEasedCandidates = getCandidates(99, toSolve, unprecision); // get slightly ambiguous candidates
            if (!currentEasedCandidates.isEmpty()) {
                Candidate c = currentEasedCandidates.get(new Random().nextInt(currentEasedCandidates.size())); // randomly pick an ambiguous candidate
                toSolve.setEntry(99, c.row, c.col, c.entry);
                unprecision = 11;
                return true;
            }
        }
        return false;
    }

    public static void solveSam(Samurai toSolve) {
        int preciselyFilled;
        boolean unpreciselyFilled;
        int randomlyChosen9Sq;
        do {
            // fill as precisely as possible
            do {
                preciselyFilled = 0;
                for (int nsqFieldNo = 0; nsqFieldNo < 5; nsqFieldNo++) {
                    preciselyFilled += solve9sqPrecisely(toSolve.getNinesquare(nsqFieldNo));
                }
            } while (preciselyFilled > 0);
            // try to fill one more ambiguous entry
            randomlyChosen9Sq = new Random().nextInt(5);
            unpreciselyFilled = solve9sqUnprecise(toSolve.getNinesquare(randomlyChosen9Sq));
            if (!toSolve.getNinesquare(randomlyChosen9Sq).isSolved() && !unpreciselyFilled) {
                deleteGuess(toSolve);
            }
        } while(!toSolve.isSolved());
    }

    public static void deleteGuess(Puzzle toSolve) {
        if (Ninesquare.class.isInstance(toSolve)) {
            Solver.deleteNinesquare((Ninesquare) toSolve);

        } else if (Samurai.class.isInstance(toSolve)) {
            for (int nsqFieldNr = 0; nsqFieldNr < 5; nsqFieldNr++) {
                Solver.deleteNinesquare(((Samurai) toSolve).getNinesquare(nsqFieldNr));
            }
        }
    }

    public static void deleteNinesquare(Ninesquare toSolve) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                toSolve.deleteEntry(99, row, col);
            }
        }
    }
}
