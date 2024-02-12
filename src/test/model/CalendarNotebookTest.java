package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CalendarNotebookTest {

    @Test
    public void testAddEntry() {
        CalendarNotebook notebook = new CalendarNotebook();
        CalendarEntry entry = new CalendarEntry(new Date(10, 2, 2024), "Meeting");
        notebook.addEntry(entry);
        assertTrue(notebook.getEntriesForDate(new Date(10, 2, 2024)).contains(entry));
    }

    @Test
    public void testDeleteEntry() {
        CalendarNotebook notebook = new CalendarNotebook();
        CalendarEntry entry = new CalendarEntry(new Date(10, 2, 2024), "Meeting");
        notebook.addEntry(entry);
        notebook.deleteEntry(entry);
        assertFalse(notebook.getEntriesForDate(new Date(10, 2, 2024)).contains(entry));
    }

    @Test
    public void testGetEntriesForDate() {
        CalendarNotebook notebook = new CalendarNotebook();
        Date date1 = new Date(10, 2, 2024);
        Date date2 = new Date(15, 2, 2024);
        CalendarEntry entry1 = new CalendarEntry(date1, "Meeting");
        CalendarEntry entry2 = new CalendarEntry(date2, "Appointment");
        notebook.addEntry(entry1);
        notebook.addEntry(entry2);

        List<CalendarEntry> entriesForDate1 = notebook.getEntriesForDate(date1);
        List<CalendarEntry> entriesForDate2 = notebook.getEntriesForDate(date2);

        assertEquals(1, entriesForDate1.size());
        assertEquals(1, entriesForDate2.size());
        assertEquals("Meeting", entriesForDate1.get(0).getContent()); // Using toString for simplicity
        assertEquals("Appointment", entriesForDate2.get(0).getContent()); // Using toString for simplicity
    }
}
