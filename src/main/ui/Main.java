package ui;

import java.io.FileNotFoundException;

//Represent a Starter of Calendar Notebook app
public class Main {
    public static void main(String[] args) {
        try {
            new CalendarNotebookGUI();
            new NotebookApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
