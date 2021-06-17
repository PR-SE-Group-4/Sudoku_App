package app;

import model.Ninesquare;
import model.Puzzle;
import model.Samurai;
import model.Tile;

import java.util.LinkedList;
import java.util.Random;


public class Solver {

    //------
    public static class Candidate {
        int nsqFieldNr;
        int row;
        int col;
        int entry;

        Candidate(int nsqFieldNr, int row, int col, int entry) {
            this.nsqFieldNr = nsqFieldNr;
            this.row = row;
            this.col = col;
            this.entry = entry;
        }

        public int getNsqFieldNr() {
            return nsqFieldNr;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getEntry() {
            return entry;
        }

        @Override
        public String toString() {
            return row + ", " + col + ", " + entry;
        }
    }




    static LinkedList<Candidate> getCandidates(Ninesquare toSolve, int numberOfOptions) {

        // at NumberOfOptions == 1 the result is definitive.
        // at a higher NumberOfOptions, the result is less and less probable
        //boolean[row][col][pillar / entry]
        boolean[][][] candidateCube = new boolean[9][9][9]; // mit false initialisiert: false bedeutet "ist Kandidat"

        for (int row = 0; row < 9; row++) {                       // alle Zeilen des zu lösenden Ninesquare durchlaufen
            for (int col =0; col < 9; col++) {                    // alle Spalten
                Tile currTile = toSolve.getTile(99, row, col);    // das aktuell für Elimination vorgesehene Tile
                if (currTile.isFilled()) {                        // das Tile in row, col ist filled, Elimination beginnt
                    int eliminationValue = currTile.getEntry() - 1;   // der Wert im aktuellen Tile, umgerechnet auf Arrayindex (1 = 0)

                    // clearFilledPillar: diese row, col ist befült und braucht keine Candidates mehr
                    for (int i = 0; i <9; i++) {
                      candidateCube[row][col][i] = true;
                    }

                    for (int i = 0; i < 9; i++) {
                      // clearNumberinRow: in dieser row gibt es die Zahl eliminationValue schon und kann nicht mehr vorkommen
                      candidateCube[row][i][eliminationValue] = true;
                      // clearNumberinCol: in dieser col gibt es die Zahl eliminationValue schon und kann nicht mehr vorkommen
                      candidateCube[i][col][eliminationValue] = true;
                    }

                    // clearNumberInArea: in dieser area gibt es die Zahl eliminationValue schon und kann nicht mehr vorkommen
                    // 1. Area/Color des aktuellen Tile evaluieren
                    int currentArea = toSolve.getColor(99,row, col);
                    // 2. toSolve durchiterieren und bei getColor-Hit dann eliminieren
                    for (int arearow = 0; arearow < 9; arearow++) {
                        for (int areacol = 0; areacol < 9; areacol++) {
                            if (toSolve.getColor(99,arearow, areacol) == currentArea) {
                              candidateCube[arearow][areacol][eliminationValue] = true;
                            }
                        }
                    }
                }
            }
        }

        // make a list of candidates
        LinkedList<Candidate> candidates = new LinkedList<>();
        int falseCounter;
        int trigger = 0;

        // Single-Pillars: there are less than numberOfOptions candidates in:
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                falseCounter = 0;
                for (int p = 0; p < 9; p++) {
                    if (!candidateCube[r][c][p]) {
                        falseCounter++;
                        trigger = p;
                    }
                }
                if (falseCounter > 0 && falseCounter <= numberOfOptions) {
                    candidates.add(new Candidate(99, r, c, trigger+1));
                }
            }
        }

        // Single-Rows: im Row folgender c/p ist genau 1 false:
        for (int c = 0; c < 9; c++) {
            for (int p = 0; p < 9; p++) {
                falseCounter = 0;
                for (int r = 0; r < 9; r++) {
                    if (!candidateCube[r][c][p]){
                        falseCounter++;
                        trigger = r;
                    }
                }
                if (falseCounter > 0 && falseCounter <=  numberOfOptions) {
                    candidates.add(new Candidate(99, trigger, c, p+1));
                }
            }
        }

        // Single-Col: im Col folgender p/r ist genau 1 false:
        for (int p = 0; p < 9; p++) {
            for (int r = 0; r < 9; r++) {
                falseCounter = 0;
                for (int c = 0; c < 9; c++) {
                    if (!candidateCube[r][c][p]){
                        falseCounter++;
                        trigger = c;
                    }
                }
                if (falseCounter > 0 && falseCounter <= numberOfOptions) {
                    candidates.add(new Candidate(99, r, trigger, p+1));
                }
            }
        }
    return candidates;
    }



    // --------------------------------------------
    public static int solveNinesquareUnambiguously(Ninesquare toSolve) {
        LinkedList<Candidate> candidates = new LinkedList<>();
        candidates.add(new Candidate(99, 0,0,0));
        int filledFields = 0;
        while (!toSolve.isSolved() && !candidates.isEmpty()){
           candidates = getCandidates(toSolve, 1);
            for (Candidate c : candidates) {
                filledFields++;
                toSolve.setEntry(99, c.row, c.col, c.entry);

            }
        }
        return filledFields;
    }

    public static void solveSamuraiUnambiguously(Samurai toSolve) {
        int filledInThisRound = 1;
        while (filledInThisRound > 0) {
            filledInThisRound = 0;
            for (int nsqFieldNr = 0; nsqFieldNr < 5; nsqFieldNr++) {
                filledInThisRound += solveNinesquareUnambiguously(toSolve.getNinesquare(nsqFieldNr));
            }
        }
    }

    public static void solve(Puzzle toSolve) {
        if (Ninesquare.class.isInstance(toSolve)) {
            Solver.solve9sqWithEducatedGuesses((Ninesquare) toSolve);

        } else if (Samurai.class.isInstance(toSolve)) {
            Solver.solveSamuraiWithEducatedGuesses((Samurai) toSolve);
        }
        System.out.println(toSolve);
    }



    public static Candidate getHint(Puzzle toSolve) {
        if (Ninesquare.class.isInstance(toSolve)) {                 // Puzzle is Ninesquare
            Ninesquare ts = (Ninesquare) toSolve;
            LinkedList<Candidate> candidates = getCandidates(ts, 1);
            return candidates.get(0);

        } else if (Samurai.class.isInstance(toSolve)) {             // TODO Samurai
            return new Candidate(0,0,0,0);
        }
        return new Candidate(0,0,0,0);
    }

    public static void solve9sqWithEducatedGuesses(Ninesquare toSolve) { // solve with a mixture of conclusive solving and educated guessing
         do { // as long as it isn't solved
            solveNinesquareUnambiguously(toSolve); // fill all unambiguous fields
            LinkedList<Candidate> currentEasedCandidates = getCandidates(toSolve, 2); // get slightly ambiguous candidates
            if (!currentEasedCandidates.isEmpty()) {
                Candidate c = currentEasedCandidates.get(new Random().nextInt(currentEasedCandidates.size())); // randomly pick an ambiguous candidate
                toSolve.setEntry(99, c.row, c.col, c.entry);
            } else {
                if (toSolve.isSolved()) {
                    boolean isConflicted = false;
                    for (int r = 0; r < 9; r++) {
                        for (int c = 0; c < 9; c++) {
                            if (toSolve.getTile(99, r, c).isConflicted()) isConflicted = true;
                        }
                    }
                    if (!isConflicted) break; // if no more candidates, is solved and no conflicts: job is done
                    System.out.println(toSolve);
                }
                deleteGuess(toSolve); // if there are no more candidates and it is not (solved and not conflicted) this is a dead end
            }
        } while (!toSolve.isSolved());
    }

    private static void solveSamuraiWithEducatedGuesses(Samurai toSolve) {
        solveSamuraiUnambiguously(toSolve);
        //TODO
    }

    public static void deleteGuess(Ninesquare toSolve) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                toSolve.deleteEntry(99, row, col);
            }
        }
    }
}
