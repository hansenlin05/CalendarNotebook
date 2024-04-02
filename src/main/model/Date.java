package model;

//Represents a date with day, month, and year components.
public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    //EFFECTS:Constructs a date and Set the date to the given date
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

    //EFFECTS: Return Date in string
    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    //EFFECTS: Return true if date equal to the date of given object
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

    //EFFECTS: Helper function when sorting entries by year/month/day
    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        } else if (this.month != other.month) {
            return this.month - other.month;
        } else {
            return this.day - other.day;
        }
    }
}

