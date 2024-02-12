package model;

import java.util.ArrayList;
import java.util.List;

//Represents a calendar notebook containing entries for various dates.
public class CalendarNotebook {
    private List<CalendarEntry> entries;

    public CalendarNotebook() {
        entries = new ArrayList<>();
    }

    // Modifies: This CalendarNotebook's entries.
    // Effects: Adds the specified entry to the notebook.
    public void addEntry(CalendarEntry entry) {
        entries.add(entry);
    }

    // Modifies: This CalendarNotebook's entries.
    // Effects: Removes the specified entry from the notebook.
    public void deleteEntry(CalendarEntry entry) {
        entries.remove(entry);
    }

    // Requires: date != null.
    // Effects: Returns the list of entries for the specified date.
    public List<CalendarEntry> getEntriesForDate(Date date) {
        List<CalendarEntry> entriesForDate = new ArrayList<>();
        for (CalendarEntry entry : entries) {
            if (entry.getDate().equals(date)) {
                entriesForDate.add(entry);
            }
        }
        return entriesForDate;
    }
}
