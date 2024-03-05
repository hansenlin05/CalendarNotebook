package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CalendarNotebookTest {

    @BeforeEach
    public void setUp() {

    }

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

    @Test
    public void testGetEntries() {
        CalendarNotebook notebook = new CalendarNotebook();
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(2, 1, 2022);
        CalendarEntry entry1 = new CalendarEntry(date1, "Test content 1");
        CalendarEntry entry2 = new CalendarEntry(date2, "Test content 2");
        notebook.addEntry(entry1);
        notebook.addEntry(entry2);

        List<CalendarEntry> entries = notebook.getEntries();
        assertEquals(2, entries.size());
        assertTrue(entries.contains(entry1));
        assertTrue(entries.contains(entry2));
    }

    @Test
    public void testCalendarNotebookToJson() {
            CalendarNotebook notebook = new CalendarNotebook();
            Date date1 = new Date(1, 1, 2022);
            Date date2 = new Date(2, 1, 2022);
            CalendarEntry entry1 = new CalendarEntry(date1, "Test content 1");
            CalendarEntry entry2 = new CalendarEntry(date2, "Test content 2");
            notebook.addEntry(entry1);
            notebook.addEntry(entry2);

            JSONObject expectedJson = new JSONObject();
            JSONArray entriesArray = new JSONArray();
            JSONObject entryJson1 = new JSONObject();
            entryJson1.put("day", 1);
            entryJson1.put("month", 1);
            entryJson1.put("year", 2022);
            entryJson1.put("content", "Test content 1");
            JSONObject entryJson2 = new JSONObject();
            entryJson2.put("day", 2);
            entryJson2.put("month", 1);
            entryJson2.put("year", 2022);
            entryJson2.put("content", "Test content 2");
            entriesArray.put(entryJson1);
            entriesArray.put(entryJson2);
            expectedJson.put("entries", entriesArray);

            assertEquals(expectedJson.toString(), notebook.toJson().toString());
        }
    }
