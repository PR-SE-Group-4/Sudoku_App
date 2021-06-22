import model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SamuraiTest {

    Samurai samurai1;
    Samurai samurai2;
    Tile[] tiles;

    @BeforeAll
    void init() {
        //not solved samurai
        samurai1 = new Samurai("test samurai1", Difficulty.HARD, Type.SAMURAI,false,0,"1O2-1U0-1O5-2U0-2U0-2U0-3O9-3U0-3O4-1U0-1O7-1U0-2U0-2U0-2U0-3U0-3O5-3U0-1O8-1U0-1U0-2U0-2O6-2U0-3U0-3U0-3O1-4U0-4U0-4U0-5O4-5U0-5O9-6U0-6U0-6U0-4U0-4U0-4O6-5U0-5U0-5U0-6O2-6U0-6U0-4U0-4U0-4U0-5O6-5U0-5O2-6U0-6U0-6U0-7O4-7U0-7U0-8U0-8O8-8U0-9U0-9U0-9O9-7U0-7O3-7U0-8U0-8U0-8U0-9U0-9O1-9U0-7O1-7U0-7O8-8U0-8U0-8U0-9O4-9U0-9O7-x1O6-1U0-1O7-2U0-2U0-2U0-3O3-3U0-3O1-1U0-1O9-1U0-2U0-2U0-2U0-3U0-3O7-3U0-1O1-1U0-1U0-2U0-2O2-2U0-3U0-3U0-3O9-4U0-4U0-4U0-5O1-5U0-5O4-6U0-6U0-6U0-4U0-4U0-4O3-5U0-5U0-5U0-6O5-6U0-6U0-4U0-4U0-4U0-5O2-5U0-5O8-6U0-6U0-6U0-7O5-7U0-7U0-8U0-8O6-8U0-9U0-9U0-9O4-7U0-7O4-7U0-8U0-8U0-8U0-9U0-9O8-9U0-7O8-7U0-7O6-8U0-8U0-8U0-9O1-9U0-9O5-x1U0-1U0-1O9-2U0-2U0-2U0-3O5-3U0-3U0-1U0-1O1-1U0-2U0-2U0-2U0-3U0-3O4-3U0-1O4-1U0-1O7-2U0-2U0-2U0-3O8-3U0-3O6-4U0-4U0-4U0-5O4-5U0-5O3-6U0-6U0-6U0-4U0-4U0-4U0-5U0-5O7-5U0-6U0-6U0-6U0-4U0-4U0-4U0-5O2-5U0-5O9-6U0-6U0-6U0-7O1-7U0-7O4-8U0-8U0-8U0-9O2-9U0-9O5-7U0-7O9-7U0-8U0-8U0-8U0-9U0-9O1-9U0-7U0-7U0-7O8-8U0-8U0-8U0-9O4-9U0-9U0-x1O8-1U0-1O9-2U0-2U0-2U0-3O1-3U0-3O4-1U0-1O4-1U0-2U0-2U0-2U0-3U0-3O9-3U0-1O2-1U0-1U0-2U0-2O7-2U0-3U0-3U0-3O8-4U0-4U0-4U0-5O7-5U0-5O8-6U0-6U0-6U0-4U0-4U0-4O2-5U0-5U0-5U0-6O6-6U0-6U0-4U0-4U0-4U0-5O1-5U0-5O2-6U0-6U0-6U0-7O9-7U0-7U0-8U0-8O2-8U0-9U0-9U0-9O5-7U0-7O2-7U0-8U0-8U0-8U0-9U0-9O4-9U0-7O1-7U0-7O4-8U0-8U0-8U0-9O9-9U0-9O7-x1O2-1U0-1O5-2U0-2U0-2U0-3O3-3U0-3O7-1U0-1O1-1U0-2U0-2U0-2U0-3U0-3O6-3U0-1O4-1U0-1U0-2U0-2O1-2U0-3U0-3U0-3O8-4U0-4U0-4U0-5O2-5U0-5O9-6U0-6U0-6U0-4U0-4U0-4O8-5U0-5U0-5U0-6O6-6U0-6U0-4U0-4U0-4U0-5O1-5U0-5O4-6U0-6U0-6U0-7O7-7U0-7U0-8U0-8O9-8U0-9U0-9U0-9O6-7U0-7O3-7U0-8U0-8U0-8U0-9U0-9O9-9U0-7O6-7U0-7O4-8U0-8U0-8U0-9O5-9U0-9O3-");
        //sovled samurai
        samurai2 = new Samurai("test samurai1", Difficulty.HARD, Type.SAMURAI,true,1000,"1O2-1U6-1O5-2U8-2U6-2U6-3O9-3U2-3O4-1U2-1O7-1U5-2U4-2U6-2U8-3U1-3O5-3U2-1O8-1U5-1U2-2U7-2O6-2U3-3U6-3U1-3O1-4U4-4U2-4U7-5O4-5U7-5O9-6U3-6U1-6U1-4U3-4U7-4O6-5U7-5U8-5U9-6O2-6U6-6U2-4U3-4U3-4U8-5O6-5U9-5O2-6U9-6U6-6U3-7O4-7U6-7U7-8U7-8O8-8U6-9U9-9U7-9O9-7U3-7O3-7U5-8U4-8U7-8U2-9U2-9O1-9U1-7O1-7U2-7O8-8U7-8U5-8U5-9O4-9U5-9O7-x1O6-1U8-1O7-2U9-2U9-2U4-3O3-3U9-3O1-1U2-1O9-1U3-2U2-2U4-2U1-3U8-3O7-3U5-1O1-1U2-1U4-2U1-2O2-2U9-3U7-3U6-3O9-4U4-4U7-4U8-5O1-5U2-5O4-6U9-6U2-6U2-4U8-4U3-4O3-5U5-5U8-5U7-6O5-6U4-6U5-4U4-4U3-4U7-5O2-5U6-5O8-6U9-6U2-6U9-7O5-7U7-7U4-8U9-8O6-8U4-9U8-9U2-9O4-7U2-7O4-7U4-8U3-8U5-8U8-9U2-9O8-9U5-7O8-7U9-7O6-8U3-8U1-8U6-9O1-9U4-9O5-x1U9-1U4-1O9-2U1-2U2-2U2-3O5-3U7-3U4-1U7-1O1-1U5-2U1-2U4-2U7-3U2-3O4-3U4-1O4-1U8-1O7-2U8-2U7-2U4-3O8-3U9-3O6-4U8-4U8-4U5-5O4-5U6-5O3-6U1-6U9-6U2-4U8-4U7-4U5-5U9-5O7-5U9-6U5-6U1-6U9-4U1-4U1-4U4-5O2-5U2-5O9-6U4-6U1-6U1-7O1-7U9-7O4-8U7-8U9-8U4-9O2-9U5-9O5-7U9-7O9-7U5-8U4-8U7-8U2-9U5-9O1-9U3-7U5-7U6-7O8-8U3-8U7-8U2-9O4-9U6-9U2-x1O8-1U3-1O9-2U1-2U1-2U4-3O1-3U9-3O4-1U6-1O4-1U6-2U7-2U4-2U1-3U9-3O9-3U5-1O2-1U5-1U2-2U2-2O7-2U6-3U5-3U6-3O8-4U8-4U7-4U1-5O7-5U6-5O8-6U9-6U6-6U2-4U2-4U8-4O2-5U8-5U3-5U2-6O6-6U6-6U8-4U1-4U5-4U7-5O1-5U4-5O2-6U4-6U5-6U4-7O9-7U5-7U1-8U1-8O2-8U2-9U7-9U6-9O5-7U8-7O2-7U7-8U8-8U7-8U1-9U4-9O4-9U7-7O1-7U9-7O4-8U4-8U5-8U5-9O9-9U4-9O7-x1O2-1U7-1O5-2U1-2U8-2U5-3O3-3U5-3O7-1U2-1O1-1U1-2U8-2U4-2U2-3U8-3O6-3U9-1O4-1U5-1U5-2U9-2O1-2U9-3U3-3U8-3O8-4U6-4U8-4U8-5O2-5U3-5O9-6U4-6U5-6U6-4U6-4U9-4O8-5U4-5U9-5U1-6O6-6U8-6U3-4U1-4U8-4U4-5O1-5U6-5O4-6U2-6U8-6U3-7O7-7U4-7U5-8U8-8O9-8U8-9U6-9U6-9O6-7U7-7O3-7U7-8U7-8U4-8U9-9U1-9O9-9U7-7O6-7U3-7O4-8U1-8U6-8U4-9O5-9U1-9O3-");
    }

    @Test
    void getTile() {
        assertEquals(2, samurai1.getTile(0,0,0).getEntry());
        assertFalse(samurai1.getTile(0,0,0).isChangeable());
        assertTrue(samurai1.getTile(0, 0,0).isFilled());
    }

    @Test
    void getColor() {
        assertEquals(1, samurai1.getColor(0,0,0));
        assertEquals(2, samurai1.getColor(2,0,3));
    }

    @Test
    void setEntry() {
        //set entry without overlap
        assertEquals(0, samurai1.getTile(0,0,3).getEntry());
        samurai1.setEntry(0,0,3, 5);
        assertEquals(5, samurai1.getTile(0,0,3).getEntry());

        //set entry with overlap
        samurai1.setEntry(0,6,6,1);
        assertEquals(1, samurai1.getTile(0,6,6).getEntry());
        assertEquals(1, samurai1.getTile(2,0,0).getEntry());

        samurai1.setEntry(1, 6,1, 3);
        assertEquals(3, samurai1.getTile(1, 6, 1).getEntry());
        assertEquals(3, samurai1.getTile(2,0,7).getEntry());

        samurai1.setEntry(2,0,0,5);
        assertEquals(5, samurai1.getTile(2,0,0).getEntry());
        assertEquals(5, samurai1.getTile(0,6,6).getEntry());

        samurai1.setEntry(2, 0,7,5);
        assertEquals(5, samurai1.getTile(2,0,7).getEntry());
        assertEquals(5, samurai1.getTile(1, 6, 1).getEntry());

        samurai1.setEntry(2, 8, 0, 1);
        assertEquals(1, samurai1.getTile(2, 8, 0).getEntry());
        assertEquals(1, samurai1.getTile(3, 2, 6).getEntry());

        samurai1.setEntry(2, 8, 8, 6);
        assertEquals(6, samurai1.getTile(2, 8,8).getEntry());
        assertEquals(6, samurai1.getTile(4,2,2).getEntry());

        samurai1.setEntry(3, 2, 6, 9);
        assertEquals(9, samurai1.getTile(3, 2, 6).getEntry());
        assertEquals(9, samurai1.getTile(2, 8, 0).getEntry());

        samurai1.setEntry(4, 2,2,4);
        assertEquals(4, samurai1.getTile(4, 2,2).getEntry());
        assertEquals(4, samurai1.getTile(2,8,8).getEntry());

    }

    @Test
    void deleteEntry() {
        samurai1.setEntry(0,0,3, 5);
        samurai1.setEntry(2, 2,1, 3);

        samurai1.deleteEntry(0,0,3);
        assertEquals(0,samurai1.getTile(0,0,3).getEntry());

        samurai1.deleteEntry(0,8,7);
        assertEquals(0, samurai1.getTile(0,8,7).getEntry());
        assertEquals(0, samurai1.getTile(2, 2, 1).getEntry());
    }

    @Test
    void getRow() {
        tiles = samurai1.getRow(1, 0);
        for (int c = 0; c < 9; c++) {
            assertEquals(samurai1.getTile(1, 0, c), tiles[c]);
        }
    }

    @Test
    void getCol() {
        tiles = samurai1.getCol(1, 0);
        for (int r = 0; r < 9; r++) {
            assertEquals(samurai1.getTile(1, r, 0), tiles[r]);
        }
    }

    @Test
    void getArea() {
        tiles = samurai1.getArea(2, 3,3);
        assertEquals(4, tiles[0].getEntry());
        int tileColor = samurai1.getTile(2,3,3).getBelongsToArea();
        for (int i = 0; i<tiles.length; i++) {
            assertEquals(tileColor,tiles[i].getBelongsToArea());
        }
    }

    @Test
    void export() {
        System.out.println("Samurai - Export: " + "\n" + samurai1.export());
    }

    @Test
    void isSolved() {
        assertFalse(samurai1.isSolved());
        assertTrue(samurai2.isSolved());
    }

    @Test
    void testToString() {
        System.out.println("Samurai - toString: " + "\n" + samurai1.toString());
    }
}