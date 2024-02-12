package ui;

import model.CalendarEntry;
import model.CalendarNotebook;
import model.Date;
import model.exceptions.EmptyDateException;

import java.util.List;
import java.util.Scanner;

public class NotebookApp {
    private CalendarNotebook notebook;
    private Scanner scanner;


    public NotebookApp() {
        notebook = new CalendarNotebook();
        scanner = new Scanner(System.in);
        runNotebook();
    }

    private void runNotebook() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = scanner.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you for using Calendar Notebook and we look forward to working with you again!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addEntry();
        } else if (command.equals("v")) {
            viewEntriesForDate();
        } else if (command.equals("d")) {
            deleteEntry();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("\ta -> Add Entry");
        System.out.println("\tv -> View Entries for Date");
        System.out.println("\td -> Delete Entry");
        System.out.println("\tq -> quit");
        System.out.print("Enter your choice below: ");
    }

    // EFFECTS: prompts the user to enter date and content for a new entry, then adds it to the notebook
    private void addEntry() {
        System.out.println("Enter date (day month year): ");
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();
        Date date = new Date(day, month, year);

        System.out.println("Enter content for the entry: ");
        scanner.nextLine();
        String content = scanner.nextLine();

        CalendarEntry entry = new CalendarEntry(date, content);
        notebook.addEntry(entry);
        System.out.println("Entry added successfully!");

        // Debugging: Print out all entries in the notebook
        System.out.println("Entries in notebook:");
        for (CalendarEntry e : notebook.getEntriesForDate(date)) {
            System.out.println(e.getDate() + " - " + e.getContent());
        }
    }

    // EFFECTS: prompts the user to enter a date, then displays entries for that date
    private void viewEntriesForDate() {
        System.out.println("Enter date to view entries (day month year): ");
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

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

    // EFFECTS: prompts the user to enter a date and content of entry to delete, then deletes it from the notebook
    private void deleteEntry() {
        System.out.println("Enter date of entry to delete (day month year): ");
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();
        Date date = new Date(day, month, year);

        System.out.println("Enter content of entry to delete: ");
        scanner.nextLine();  // Consume newline character
        String content = scanner.nextLine();

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
}
