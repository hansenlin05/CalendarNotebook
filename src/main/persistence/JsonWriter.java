package persistence;

import model.CalendarEntry;
import model.CalendarNotebook;
import model.Date;
import org.json.JSONObject;

import model.CalendarNotebook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of calendar notebook to file
    public void write(CalendarNotebook notebook) {
        JSONObject json = notebookToJson(notebook);
        saveToFile(json.toString(TAB));
    }

    // EFFECTS: converts calendar notebook to JSON object and returns it
    private JSONObject notebookToJson(CalendarNotebook notebook) {
        JSONObject json = new JSONObject();
        JSONArray entriesArray = new JSONArray();

        for (CalendarEntry entry : notebook.getEntries()) {
            entriesArray.put(entryToJson(entry));
        }

        json.put("entries", entriesArray);
        return json;
    }

    // EFFECTS: converts entry to JSON object and returns it
    private JSONObject entryToJson(CalendarEntry entry) {
        JSONObject json = new JSONObject();
        json.put("day", entry.getDate().getDay());
        json.put("month", entry.getDate().getMonth());
        json.put("year", entry.getDate().getYear());
        json.put("content", entry.getContent());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
