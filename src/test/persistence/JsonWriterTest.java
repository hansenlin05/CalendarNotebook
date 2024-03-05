package persistence;

import model.CalendarNotebook;
import model.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.CalendarEntry;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class JsonWriterTest {

    @Test
    void testWriter() {
        try {
            CalendarNotebook notebook = new CalendarNotebook();
            notebook.addEntry(new CalendarEntry(new Date(1, 1, 2022), "Entry 1"));
            notebook.addEntry(new CalendarEntry(new Date(2, 1, 2022), "Entry 2"));

            JsonWriter writer = new JsonWriter("./data/testWriter.json");
            writer.open();
            writer.write(notebook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriter.json");
            notebook = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
