package model;

//Represents a date with day, month, and year components.
public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    // Effects: Returns the day of the date.
    public int getDay() {
        return day;
    }

    // Modifies: This Date's day.
    // Effects: Sets the day of the date to the specified day.
    public void setDay(int day) {
        this.day = day;
    }


    // Effects: Returns the month of the date.
    public int getMonth() {
        return month;
    }

    // Modifies: This Date's month.
    // Effects: Sets the month of the date to the specified month.
    public void setMonth(int month) {
        this.month = month;
    }

    // Effects: Returns the year of the date.
    public int getYear() {
        return year;
    }

    // Modifies: This Date's year.
    // Effects: Sets the year of the date to the specified year.
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date date = (Date) obj;
        return day == date.day && month == date.month && year == date.year;
    }
}