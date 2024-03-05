package ui;

import model.CalendarEntry;
import model.CalendarNotebook;
import model.Date;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// CalendarNotebook application
public class NotebookApp {
    private static final String JSON_STORE = "./data/notebook.json";
    private CalendarNotebook notebook;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: Run the Calendar NotebookApp
    public NotebookApp() throws FileNotFoundException {
        notebook = new CalendarNotebook();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runNotebook();
    }

    // MODIFIES: this
    // EFFECTS: processes user Console input menu
    private void runNotebook() {
        boolean keepRunning = true;
        String input = null;

        while (keepRunning) {
            displayMenu();
            input = this.input.next();
            input = input.toLowerCase();

            if (input.equals("q")) {
                keepRunning = false;
            } else {
                processInput(input);
            }
        }
        System.out.println("\nThank you for using Calendar Notebook and we look forward to working with you again!");
    }

    // MODIFIES: this
    // EFFECTS: processes user input command
    private void processInput(String command) {
        if (command.equals("a")) {
            addEntry();
        } else if (command.equals("v")) {
            viewEntriesForDate();
        } else if (command.equals("d")) {
            deleteEntry();
        } else if (command.equals("s")) {
            saveNotebook();
        } else if (command.equals("l")) {
            loadNotebook();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    // EFFECTS: displays list menu of options to user
    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("\ta -> Add Entry");
        System.out.println("\tv -> View Entries for Date");
        System.out.println("\td -> Delete Entry");
        System.out.println("\ts -> save Notebook to file");
        System.out.println("\tl -> load save Notebook from file");
        System.out.println("\tq -> quit");
        System.out.print("Enter your choice below: ");
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to enter date and content for a new entry, then adds it to the notebook
    private void addEntry() {

        System.out.println("Enter date (day month year): ");
        int day = input.nextInt();
        int month = input.nextInt();
        int year = input.nextInt();
        Date date = new Date(day, month, year);

        System.out.println("Enter content for the entry: ");
        input.nextLine();
        String content = input.nextLine();

        CalendarEntry entry = new CalendarEntry(date, content);
        notebook.addEntry(entry);
        System.out.println("Entry added successfully!");

        System.out.println("Entries in notebook:");
        for (CalendarEntry e : notebook.getEntriesForDate(date)) {
            System.out.println(e.getDate() + " - " + e.getContent());
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to enter a date, then displays entries for that date
    private void viewEntriesForDate() {
        System.out.println("Enter date to view entries (day month year): ");
        int day = input.nextInt();
        int month = input.nextInt();
        int year = input.nextInt();
        input.nextLine();

        Date date = new Date(day, month, year);

        List<CalendarEntry> entries = notebook.getEntriesForDate(date);
        if (entries.isEmpty()) {
            System.out.println("No entries found for the specified date.");
        } else {
            System.out.println("Entries for " + date + ":");
            for (CalendarEntry entry : entries) {
                System.out.println(entry.getContent());
            }
        }
    }

    //MODIFIES: this
    // EFFECTS: prompts the user to enter a date and content of entry to delete, then deletes it from the notebook
    private void deleteEntry() {
        System.out.println("Enter date of entry to delete (day month year): ");
        int day = input.nextInt();
        int month = input.nextInt();
        int year = input.nextInt();
        Date date = new Date(day, month, year);

        System.out.println("Enter content of entry to delete: ");
        input.nextLine();  // Consume newline character
        String content = input.nextLine();

        List<CalendarEntry> entries = notebook.getEntriesForDate(date);
        for (CalendarEntry entry : entries) {
            if (entry.getContent().equals(content)) {
                notebook.deleteEntry(entry);
                System.out.println("Entry deleted successfully!");
                return;
            }
        }
        System.out.println("No matching entry found for deletion.");
    }

    // EFFECTS: saves the workroom to file
    private void saveNotebook() {
        try {
            jsonWriter.open();
            jsonWriter.write(notebook);
            jsonWriter.close();
            System.out.println("Saved file from to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadNotebook() {
        try {
            notebook = jsonReader.read();
            System.out.println("Loaded file from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
