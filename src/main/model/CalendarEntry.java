package model;

import java.util.ArrayList;
import java.util.List;

 //Represents an entry in the calendar notebook.

public class CalendarEntry {
    private Date date;
    private String content;

    //Constructs a CalendarEntry with the given date and content.
    //date: The date of the entry.
    //content: The content of the entry.
    public CalendarEntry(Date date, String content) {
        this.date = date;
        this.content = content;
    }

    //Effects: Gets the date of the entry.
    public Date getDate() {
        return date;
    }


    //Effects: Gets the content of the entry
    public String getContent() {
        return content;
    }

}

