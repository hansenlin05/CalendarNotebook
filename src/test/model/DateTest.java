package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    @Test
    public void testSetDay() {
        Date date = new Date(1, 1, 2024);
        date.setDay(10);
        assertEquals(10, date.getDay());
    }

    @Test
    public void testSetMonth() {
        Date date = new Date(1, 1, 2024);
        date.setMonth(5);
        assertEquals(5, date.getMonth());
    }

    @Test
    public void testSetYear() {
        Date date = new Date(1, 1, 2024);
        date.setYear(2025);
        assertEquals(2025, date.getYear());
    }

    @Test
    public void testEquals() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(1, 1, 2024);
        Date date3 = new Date(2, 1, 2024);

        assertEquals(date1, date2); // Should be equal
        assertNotEquals(date1, date3); // Should not be equal
    }
}
