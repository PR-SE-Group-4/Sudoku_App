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
                    candidates.add(new Candidate(nsqFieldNr, r, c, trigger+1));
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
                    candidates.add(new Candidate(nsqFieldNr, trigger, c, p+1));
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
                    candidates.add(new Candidate(nsqFieldNr, r, trigger, p+1));
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
        System.out.println("Final form: \n" + toSolve);
    }

    public static Candidate getHint(Puzzle toSolve) {
        LinkedList<Candidate> candidates = new LinkedList<Candidate>();
        if (Ninesquare.class.isInstance(toSolve)) {                 // Puzzle is Ninesquare
            Ninesquare ts = (Ninesquare) toSolve;
            candidates.addAll(getCandidates(99, ts, 1));


        } else if (Samurai.class.isInstance(toSolve)) {             // Puzzle is
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
        LinkedList<Candidate> currentEasedCandidates = getCandidates(99, toSolve, 2); // get slightly ambiguous candidates
        if (currentEasedCandidates.isEmpty()) {
            return false;
        } else {
            Candidate c = currentEasedCandidates.get(new Random().nextInt(currentEasedCandidates.size())); // randomly pick an ambiguous candidate
            toSolve.setEntry(99, c.row, c.col, c.entry);
            return true;
        }
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
