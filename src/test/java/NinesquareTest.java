import model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NinesquareTest {
    Ninesquare ninesquare1;
    Ninesquare ninesquare2;
    Tile[] tiles;

    @BeforeAll
    void inti(){
        ninesquare1 =  new Ninesquare("Test",Difficulty.NORMAL, Type.FREEFORM, false, 0, "1O5-2U0-2O3-2U0-3U0-3O1-3O7-3U0-3U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-4O3-1O8-1U0-1O4-2O6-2U0-2O7-3U0-4U0-4U0-1O1-1U0-8U0-9U0-9O6-9U0-4U0-4U0-4U0-1U0-8U0-8U0-9O3-9U0-9O9-4U0-4U0-5U0-8U0-8U0-8U0-9U0-9O8-9U0-4U0-5U0-5O9-8U0-8U0-7U0-6O5-6U0-6O3-5O4-5U0-5O1-8O3-7U0-7U0-7U0-6U0-6U0-6U0-5U0-5U0-7U0-7U0-7O5-7O8-7U0-6U0-6O1-6U0-5O7-");
        ninesquare2 = new Ninesquare("Test", Difficulty.EASY, Type.FREEFORM, true, 10, "1O5-2U2-2O3-2U4-3U5-3O1-3O7-3U6-3U8-1U9-1U2-2U3-2U4-2U5-3U6-3U7-3U1-4O3-1O8-1U2-1O4-2O6-2U3-2O7-3U4-4U5-4U8-1O1-1U9-8U8-9U7-9O6-9U5-4U4-4U3-4U2-1U1-8U7-8U9-9O3-9U8-9O9-4U5-4U9-5U8-8U7-8U6-8U5-9U4-9O8-9U3-4U2-5U1-5O9-8U8-8U7-7U6-6O5-6U4-6O3-5O4-5U1-5O1-8O3-7U2-7U3-7U4-6U5-6U6-6U7-5U8-5U9-7U1-7U3-7O5-7O8-7U4-6U3-6O1-6U2-5O7-");
    }
    @Test
    void getTile() {
        assertEquals(5, ninesquare1.getTile(99, 0,0).getEntry());
        assertFalse(ninesquare1.getTile(99,0,0).isChangeable());
        assertTrue(ninesquare1.getTile(99,0,0).isFilled());
    }

    @Test
    void getColor() {
        assertEquals(1, ninesquare1.getBelongsToArea(99,0,0));
        assertEquals(2, ninesquare1.getBelongsToArea(99,0,1));
    }

    @Test
    void setEntry() {
        assertEquals(0, ninesquare1.getTile(99,1,1).getEntry());
        assertFalse(ninesquare1.getTile(99,1,1).isFilled());
        assertFalse(ninesquare1.getTile(99,1,1).isConflicted());
        ninesquare1.setEntry(99,1,1, 5);
        assertEquals(5, ninesquare1.getTile(99, 1,1 ).getEntry());
        assertTrue(ninesquare1.getTile(99, 1,1).isFilled());
        assertTrue(ninesquare1.getTile(99,1,1).isConflicted());
    }

    @Test
    void deleteEntry() {
        ninesquare1.setEntry(99,1,1,5);
        assertEquals(5, ninesquare1.getTile(99, 1,1 ).getEntry());
        ninesquare1.deleteEntry(99,1,1);
        assertEquals(0, ninesquare1.getTile(99, 1,1 ).getEntry());
        assertFalse(ninesquare1.getTile(99,1,1).isFilled());
        assertFalse(ninesquare1.getTile(99,1,1).isConflicted());
    }

    @Test
    void getRow() {
        tiles = ninesquare1.getRow(99, 0);
        for (int c = 0; c < 9; c++) {
            assertEquals(ninesquare1.getTile(99, 0, c), tiles[c]);
        }
    }

    @Test
    void getCol() {
        tiles = ninesquare1.getCol(99, 0);
        for (int r = 0; r < 9; r++) {
            assertEquals(ninesquare1.getTile(99, r, 0), tiles[r]);
        }
    }

    @Test
    void getArea() {
        tiles = ninesquare1.getArea(99, 0,0);
        assertEquals(5, tiles[0].getEntry());
        int tileColor = ninesquare1.getTile(99,0,0).getBelongsToArea();
        for (int i = 0; i<tiles.length; i++) {
            assertEquals(tileColor,tiles[i].getBelongsToArea());
        }
    }

    @Test
    void getAreaColor() {
        tiles = ninesquare1.getArea(99, 2);
        assertEquals(3, tiles[1].getEntry());
        for (int i = 0; i<tiles.length; i++) {
            assertEquals(2,tiles[i].getBelongsToArea());
        }
    }

    @Test
    void isSolved() {
        assertFalse(ninesquare1.isSolved());
        assertTrue(ninesquare2.isSolved());
    }

    @Test
    void testToString() {
        System.out.println("Ninesquare - toString: " + "\n" + ninesquare1.toString());
    }

    @Test
    void export() {
        System.out.println("Ninesquare - Export: " + "\n" + ninesquare1.export());
    }

    @Test
    void exportContentString() {
        System.out.println("Ninesquare - ExportContentString: " + "\n" + ninesquare1.exportContentString());
    }
}