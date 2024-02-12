package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalendarEntryTest {

    @Test
    public void testGetDate() {
        Date date = new Date(10, 2, 2024);
        CalendarEntry entry = new CalendarEntry(date, "Meeting");
        assertEquals(date, entry.getDate());
    }

    @Test
    public void testGetContent() {
        CalendarEntry entry = new CalendarEntry(new Date(10, 2, 2024), "Meeting");
        assertEquals("Meeting", entry.getContent());
    }
}
