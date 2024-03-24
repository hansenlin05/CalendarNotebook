package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Parts of the code in this file referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//Represents a calendar notebook containing entries for various dates.
public class CalendarNotebook implements Writable {
    private List<CalendarEntry> entries;

    //Effects: Construct an Arraylist of Notebook entries
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

    // Effects: Returns the list of all entries in the notebook.
    public List<CalendarEntry> getEntries() {
        return entries;
    }

    //REQUIRES: searchString is not null
    // MODIFIES: none
    // EFFECTS: Returns a list of CalendarEntry objects whose content contains the searchString.
    // If no such entries exist, returns an empty list.
    public List<CalendarEntry> searchEntries(String query) {
        List<CalendarEntry> results = new ArrayList<>();
        for (CalendarEntry entry : entries) {
            if (entry.getDate().toString().contains(query) || entry.getContent().contains(query)) {
                results.add(entry);
            }
        }
        return results;
    }

    // REQUIRES: none
//      MODIFIES: none
//      EFFECTS: Returns a JSONObject that represents this CalendarNotebook. The structure
//              of the JSONObject would depend on the specific implementation of the
//               CalendarNotebook class. For example, it could contain a single key "entries"
//                      associated with a JSONArray, where each element of the JSONArray is a
//JSONObject representing a CalendarEntry in this CalendarNotebook.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray entriesArray = new JSONArray();

        for (CalendarEntry entry : entries) {
            entriesArray.put(entry.toJson());
        }
        json.put("entries", entriesArray);
        return json;

    }
}
