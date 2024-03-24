package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents an entry in the calendar notebook.
public class CalendarEntry implements Writable {
    private Date date;
    private String content;

    //Effects: Constructs a CalendarEntry with the given date and content.
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

    //EFFECTS: returns a string representation of content
    public String toString() {
        return date + " - " + content;
    }

    //      REQUIRES: none
    //      MODIFIES: none
    //      EFFECTS: Returns a JSONObject that represents this CalendarEntry. The JSONObject
    //               contains four keys: "day", "month", "year", and "content". The values
    //               associated with these keys represent the day, month, year, and content
    //               of this CalendarEntry, respectively.
    //
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", date.getDay());
        json.put("month", date.getMonth());
        json.put("year", date.getYear());
        json.put("content", content);
        return json;
    }
}

