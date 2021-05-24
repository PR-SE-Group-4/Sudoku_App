import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TileTest {
    Tile t1;
    Tile t2;
    String s1;
    String s2;

    @BeforeEach
    void init(){
        t1 = new Tile(0);
        t2 = new Tile (1, 5, false);
    }

   /* @Test
    void fill() {
        assertFalse(t2.fill(2));
        assertTrue(t1.fill(1));
        assertEquals(t1.getEntry(), 1);
    }*/

    @Test
    void empty() {
        assertFalse(t2.empty());
        assertEquals(5, t2.getEntry());

        t1.setEntry(3);
        assertTrue(t1.isFilled());
        assertEquals(3, t1.getEntry());
        assertTrue(t1.empty());
        assertFalse(t1.isFilled());
        assertEquals( 0, t1.getEntry());
    }

    @Test
    void setEntry() {
        assertEquals(0, t1.getEntry());
        assertFalse(t1.isFilled());
        t1.setEntry(2);
        assertEquals(2, t1.getEntry());
        assertTrue(t1.isFilled());

        assertEquals(5, t2.getEntry());
        t2.setEntry(1);
        assertEquals(5, t2.getEntry());
    }

    @Test
    void testToString() {
        s1 = "\u001B[0m   ";
        s2 = "\u001B[30m[5]";

        assertEquals(s1, t1.toString());
        assertEquals(s2, t2.toString());

        t1.setEntry(3);
        s1 = "\u001B[0m 30";
        assertEquals(s1, t1.toString());
    }

    @Test
    void export() {
        s1 = "0U0";
        s2 = "1O5";

        assertEquals(s1, t1.export());
        assertEquals(s2, t2.export());
    }
}