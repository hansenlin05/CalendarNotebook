package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

//Represent a Starter of Calendar Notebook app
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarNotebookGUI gui = new CalendarNotebookGUI();
            int option = JOptionPane.showConfirmDialog(gui.frame, "Do you want to load the notebook from the local file?", "Load Notebook", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                gui.loadNotebook();
            }
        });
    }
}

