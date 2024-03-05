package persistence;

import static org.junit.jupiter.api.Assertions.*;

import model.CalendarEntry;
import model.CalendarNotebook;
import org.junit.jupiter.api.Test;
import java.io.IOException;


class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CalendarNotebook notebook = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyNotebook() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyNotebook.json");
        try {
            CalendarNotebook notebook = reader.read();
            assertEquals(0, notebook.getEntries().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReader() {
        JsonReader reader = new JsonReader("./data/testReader.json");
        try {
            CalendarNotebook notebook = reader.read();
            assertNotNull(notebook);

            // Validate entries
            assertEquals(2, notebook.getEntries().size());

            // Validate first entry
            CalendarEntry entry1 = notebook.getEntries().get(0);
            assertEquals(1, entry1.getDate().getDay());
            assertEquals(1, entry1.getDate().getMonth());
            assertEquals(2022, entry1.getDate().getYear());
            assertEquals("Entry 1", entry1.getContent());

            // Validate second entry
            CalendarEntry entry2 = notebook.getEntries().get(1);
            assertEquals(2, entry2.getDate().getDay());
            assertEquals(1, entry2.getDate().getMonth());
            assertEquals(2022, entry2.getDate().getYear());
            assertEquals("Entry 2", entry2.getContent());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}

