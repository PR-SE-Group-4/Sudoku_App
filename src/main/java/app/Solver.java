package app;

import model.Ninesquare;
import model.Tile;

import java.util.LinkedList;


public class Solver {

    //------
    static class Candidate {
        int row;
        int col;
        int entry;

        Candidate(int row, int col, int entry) {
            this.row = row;
            this.col = col;
            this.entry = entry;
        }

        @Override
        public String toString() {
            return row + ", " + col + ", " + entry;
        }
    }




    static LinkedList<Candidate> getCandidates(Ninesquare toSolve) {

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

        // Kandidatenliste erstellen
        LinkedList<Candidate> candidates = new LinkedList<Candidate>();
        int falseCounter;
        int trigger = 0;
        // Single-Pillars: im Pillar folgender r/c ist genau 1 false:
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                falseCounter = 0;
                for (int p = 0; p < 9; p++) {
                    if (!candidateCube[r][c][p]) {
                        falseCounter++;
                        trigger = p;
                    }
                }
                if (falseCounter == 1) {
                    System.out.println("Folgender Pillar: " + (trigger + 1) + ", in Row " + r + ", Col " + c);
                    candidates.add(new Candidate(r, c, trigger+1));
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
                if (falseCounter == 1) {
                    System.out.println("Folgende Row: " + trigger + ", in Col " + c + ", Pillar " + (p+1));
                    candidates.add(new Candidate(trigger, c, p+1));
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
                if (falseCounter == 1) {
                    System.out.println("Folgende Col: " + trigger + ", in Pillar " + (p+1) + ", Row " + r);
                    candidates.add(new Candidate(r, trigger, p+1));
                }
            }
        }

        for (Candidate candidate : candidates) {
            System.out.println(candidate);
        }
        return candidates;
    }


    // --------------------------------------------
    public static void solveNinesquare(Ninesquare toSolve) {
        while (!toSolve.isSolved()){
            LinkedList<Candidate> candidates = getCandidates(toSolve);
            for (Candidate c : candidates) {
                toSolve.setEntry(99, c.row, c.col, c.entry);
            }
        }

    }


}
