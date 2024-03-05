package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.CalendarEntry;
import model.CalendarNotebook;
import model.Date;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.CalendarNotebook;
import org.json.*;

// Represents a reader that reads Notebooks from JSON data stored in Local file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CalendarNotebook read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCalendarNotebook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws FileNotFoundException {
        StringBuilder contentBuilder = new StringBuilder();
        Scanner scanner = new Scanner(new File(source));
        while (scanner.hasNextLine()) {
            contentBuilder.append(scanner.nextLine());
        }
        scanner.close();
        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private CalendarNotebook parseCalendarNotebook(JSONObject jsonObject) {
        CalendarNotebook notebook = new CalendarNotebook();
        addEntries(notebook, jsonObject);
        return notebook;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to Notebook
    private void addEntries(CalendarNotebook notebook, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(notebook, nextEntry);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addEntry(CalendarNotebook notebook, JSONObject jsonObject) {
        int day = jsonObject.getInt("day");
        int month = jsonObject.getInt("month");
        int year = jsonObject.getInt("year");
        Date date = new Date(day, month, year);
        String content = jsonObject.getString("content");
        CalendarEntry entry = new CalendarEntry(date, content);
        notebook.addEntry(entry);
    }
}

