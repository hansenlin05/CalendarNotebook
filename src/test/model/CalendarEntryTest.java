package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalendarEntryTest {

    @BeforeEach
    public void setUp() {
    }

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
    @Test
    public void testToString() {
        CalendarEntry entry = new CalendarEntry(new Date(10, 2, 2024), "Meeting");
        assertEquals("Meeting", entry.toString());
    }

    @Test
    public void testToJson() {
        Date date = new Date(1, 1, 2022);
        String content = "Test content";
        CalendarEntry entry = new CalendarEntry(date, content);

        JSONObject expectedJson = new JSONObject();
        expectedJson.put("day", 1);
        expectedJson.put("month", 1);
        expectedJson.put("year", 2022);
        expectedJson.put("content", "Test content");

        assertEquals(expectedJson.toString(), entry.toJson().toString());
    }
}
