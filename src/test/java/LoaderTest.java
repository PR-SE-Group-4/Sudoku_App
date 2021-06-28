import app.Loader;
import model.Difficulty;
import model.Ninesquare;
import model.Puzzle;
import model.Type;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoaderTest {
    File folder;
    File presaved00;
    File presaved01;
    File presaved02;
    File presaved03;
    File [] files;
    ArrayList<Puzzle> storedPuzzles;
    Puzzle freeform;
    Puzzle samurai;
    Puzzle newPuzzle;
    Puzzle newTemplate;
    File testPuzzle;
    File testTemplate;

    @BeforeAll
    void init() {
        folder = new File(System.getenv("APPDATA")+"\\SudokuGR04");
        presaved00 = new File(folder + "\\01freeform.txt");
        presaved01 = new File(folder + "\\01medium.txt");
        presaved02 = new File(folder + "\\01samurai.txt");
        presaved03 = new File(folder + "\\01supereasy.txt");
        storedPuzzles = new ArrayList<>();
        newPuzzle = new Ninesquare("Test", Difficulty.NORMAL, Type.FREEFORM, false, 0, "1O5-2U0-2O3-2U0-3U0-3O1-3O7-3U0-3U0-1U0-1U0-2U0-2U0-2U0-3U0-3U0-3U0-4O3-1O8-1U0-1O4-2O6-2U0-2O7-3U0-4U0-4U0-1O1-1U0-8U0-9U0-9O6-9U0-4U0-4U0-4U0-1U0-8U0-8U0-9O3-9U0-9O9-4U0-4U0-5U0-8U0-8U0-8U0-9U0-9O8-9U0-4U0-5U0-5O9-8U0-8U0-7U0-6O5-6U0-6O3-5O4-5U0-5O1-8O3-7U0-7U0-7U0-6U0-6U0-6U0-5U0-5U0-7U0-7U0-7O5-7O8-7U0-6U0-6O1-6U0-5O7-");
        newTemplate = new Ninesquare();
        newTemplate.setEntry(99,0,0,5);
        newTemplate.setEntry(99, 3,3,6);
        newTemplate.setEntry(99, 7,7,9);
        testPuzzle = new File(folder + "\\testPuzzle.txt");
        testTemplate = new File(folder + "\\testTemplate.txt");
    }

    @Test
    @Order(1)
    void setup() {
        Loader.setup();

        assertTrue(folder.exists());
        assertTrue(folder.isDirectory());
        assertTrue(presaved00.exists());
        assertTrue(presaved01.exists());
        assertTrue(presaved02.exists());
        assertTrue(presaved03.exists());
    }

    @Test
    @Order(2)
    void getFiles() {
        files = Loader.getFiles();
        assertEquals(files[0], presaved00);
        assertEquals(files[1], presaved01);
        assertEquals(files[2], presaved02);
        assertEquals(files[3], presaved03);
    }

    @Test
    @Order(3)
    void getStoredPuzzles() {
        storedPuzzles = Loader.getStoredPuzzles();
        assertFalse(storedPuzzles.isEmpty());
        assertEquals(storedPuzzles.get(0).getName(), "01freeform");
        assertEquals(storedPuzzles.get(1).getName(), "01medium");
        assertEquals(storedPuzzles.get(2).getName(), "01samurai");
        assertEquals(storedPuzzles.get(3).getName(), "01supereasy");
    }

    @Test
    @Order(4)
    void loadPuzzle() throws Exception {
        freeform = Loader.loadPuzzle(0);
        samurai = Loader.loadPuzzle(2);

        assertNotNull(freeform);
        assertNotNull(samurai);
        assertEquals("01freeform", freeform.getName());
        assertEquals(Type.FREEFORM, freeform.getType());
        assertEquals("01samurai", samurai.getName());
        assertEquals(Type.SAMURAI, samurai.getType());

    }

    @Test
    @Order(5)
    void saveGame() throws IOException {
        try {
            Loader.saveGame(newPuzzle, "testPuzzle");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(testPuzzle.exists());
    }

    @Test
    @Order(6)
    void saveTemplate() {
        Loader.saveTemplate(newTemplate, "testTemplate");;
        assertTrue(testTemplate.exists());
        assertEquals(5, newTemplate.getTile(99,0,0).getEntry());
        assertFalse(newTemplate.getTile(99, 0,0).isChangeable());
    }
}