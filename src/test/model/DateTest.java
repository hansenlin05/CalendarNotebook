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
        // Test for equality with itself
        Date date = new Date(10, 2, 2024);
        assertTrue(date.equals(date));

        // Test for inequality with null
        assertFalse(date.equals(null));

        // Test for inequality with different type
        assertFalse(date.equals("2024-02-10"));

        // Test for inequality with different date
        Date differentDate = new Date(15, 2, 2024);
        assertFalse(date.equals(differentDate));

        // Test for equality with same date
        Date sameDate = new Date(10, 2, 2024);
        assertTrue(date.equals(sameDate));

        // Test for inequality with different day
        Date differentDay = new Date(11, 2, 2024);
        assertFalse(date.equals(differentDay));

        // Test for inequality with different month
        Date differentMonth = new Date(10, 3, 2024);
        assertFalse(date.equals(differentMonth));

        // Test for inequality with different year
        Date differentYear = new Date(10, 2, 2025);
        assertFalse(date.equals(differentYear));
    }
}
