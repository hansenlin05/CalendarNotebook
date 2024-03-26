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

    @Test
    public void testCompareTo() {
        Date date1 = new Date(1, 1, 2022);
        Date date2 = new Date(2, 1, 2022);
        Date date3 = new Date(1, 2, 2022);
        Date date4 = new Date(1, 1, 2023);

        // Test when the years are different
        assertTrue(date1.compareTo(date4) < 0);
        assertTrue(date4.compareTo(date1) > 0);

        // Test when the years are the same but the months are different
        assertTrue(date1.compareTo(date3) < 0);
        assertTrue(date3.compareTo(date1) > 0);

        // Test when the years and months are the same but the days are different
        assertTrue(date1.compareTo(date2) < 0);
        assertTrue(date2.compareTo(date1) > 0);

        // Test when the dates are the same
        assertEquals(0, date1.compareTo(new Date(1, 1, 2022)));
    }

}
