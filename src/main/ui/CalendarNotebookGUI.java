package ui;

import model.CalendarEntry;
import model.CalendarNotebook;
import model.Date;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Represents a graphical user interface for interacting with a CalendarNotebook.
public class CalendarNotebookGUI extends JFrame {
    private static final String JSON_STORE = "./data/notebook.json";
    protected final JPanel entryPanel = new JPanel();
    private final JPanel controlPanel = new JPanel();
    private final JPanel searchResultPanel = new JPanel();
    private final JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JScrollPane searchScrollPanel = new JScrollPane(searchResultPanel);
    private final JScrollPane entryScrollPane = new JScrollPane(entryPanel);
    private final JTextField dateField = new JTextField();
    private final JTextField contentField = new JTextField();
    private final JTextField searchField = new JTextField();
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);
    private final JLabel searchLabel = new JLabel("Search by Date (dd/mm/yyyy) or Content:");
    private final JLabel contentLabel = new JLabel("Enter content for new Entry:");
    private final JLabel dateLabel = new JLabel("Enter date for new entry (dd/mm/yyyy):");
    private final JLabel entryLabel = new JLabel(
            "<html><body><p>Oh, my God! Your notebook is empty.</p></body></html>"
                    + "<html><body><p> Is this your first time using it?</p></body></html>");
    private final JButton searchButton = new JButton("Search");
    private final JButton addButton = new JButton("Add Entry");
    private final JButton clearButton = new JButton("Clear");
    private final JButton loadButton = new JButton("Load");
    private final JButton quitButton = new JButton("Quit");
    private final JButton saveButton = new JButton("Save");
    private final ImageIcon image1 = new ImageIcon("./data/Notebook.jpg");
    private final ImageIcon image2 = new ImageIcon("./data/search.jpg");
    private final ImageIcon image3 = new ImageIcon("./data/ok.png");
    private final ImageIcon image4 = new ImageIcon("./data/mock.jpg");
    private final ImageIcon image5 = new ImageIcon("./data/think.jpg");
    private final ImageIcon image6 = new ImageIcon("./data/heart.jpg");
    private final ImageIcon image7 = new ImageIcon("./data/sad.jpg");
    private final JLabel imageLabel1 = new JLabel(image1);
    private final JLabel imageLabel2 = new JLabel(image2);
    protected JFrame frame = new JFrame("Calendar Notebook");
    private CalendarNotebook notebook = new CalendarNotebook();

    // Constructs a new CalendarNotebookGUI and initializes the user interface.
    public CalendarNotebookGUI() {
        initializeUI();
    }

    // MODIFIES: this
    // EFFECTS: Initializes the user interface components.
    private void initializeUI() {
        setupFrameAndPanels();
        setupControlPanel();
        setupEntryPanel();
        setupSearchPanel();
        addWindowClosingListener();
        frame.setVisible(true);
        image6.setImage(image6.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JOptionPane.showMessageDialog(frame, "Welcome to Calendar Notebook!",
                "The App welcomed you", JOptionPane.WARNING_MESSAGE, image6);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the main frame and panels.
    private void setupFrameAndPanels() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        topPanel.setBorder(BorderFactory.createTitledBorder("Welcome to The Calendar notebook App!:"));
        topPanel.add(imageLabel1);
        frame.add(topPanel, BorderLayout.NORTH);

        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));
        entryScrollPane.setBorder(BorderFactory.createTitledBorder("All Entries In The Notebook:"));
        frame.add(entryScrollPane, BorderLayout.WEST);

        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Universal Searcher"));
        frame.add(searchPanel, BorderLayout.CENTER);

        controlPanel.setLayout(new GridLayout(7, 2));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
        frame.add(controlPanel, BorderLayout.EAST);

    }

    // MODIFIES: this
    // EFFECTS: Sets up the control panel.
    private void setupControlPanel() {
        controlPanel.add(dateLabel);
        controlPanel.add(dateField);
        dateField.setText("dd/mm/yyyy");
        controlPanel.add(contentLabel);
        controlPanel.add(contentField);
        addButton.addActionListener(e -> addEntry());
        controlPanel.add(addButton);
        clearButton.addActionListener(e -> clearFields());
        controlPanel.add(clearButton);
        setupSaveLoadButtons();
        setupQuitButton();
        quitButton.setBackground(Color.red);
        quitButton.setForeground(Color.WHITE);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the entry panel.
    private void setupEntryPanel() {
        entryPanel.add(entryLabel);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the search panel.
    private void setupSearchPanel() {
        image2.setImage(image2.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

        searchPanel.add(imageLabel2);
        searchPanel.add(searchLabel);
        setupSearchFieldAndButton();
    }

    // MODIFIES: this
    // EFFECTS: Sets up the search AndButton.
    private void setupSearchFieldAndButton() {
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchField.getPreferredSize().height));
        searchPanel.add(searchField);
        searchButton.addActionListener(e -> searchEntries());
        searchPanel.add(searchButton);
        searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.Y_AXIS));
        searchPanel.add(searchScrollPanel);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the Save and Load Buttons.
    private void setupSaveLoadButtons() {
        saveButton.setBackground(Color.BLUE);
        saveButton.setForeground(Color.WHITE);
        loadButton.setBackground(Color.BLUE);
        loadButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.green);
        addButton.setForeground(Color.WHITE);

        saveButton.addActionListener(e -> saveNotebook());
        controlPanel.add(saveButton);
        loadButton.addActionListener(e -> loadNotebook());
        controlPanel.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the Quit Buttons.
    private void setupQuitButton() {
        quitButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(frame,
                    "Do you want to save the notebook before quitting?", "Quit", JOptionPane.YES_NO_CANCEL_OPTION);
            quitComfirm(option);
        });
        controlPanel.add(quitButton);
    }

    // MODIFIES: this
    // EFFECTS: Adds a window closing listener to the frame
    private void addWindowClosingListener() {
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int option = JOptionPane.showConfirmDialog(frame,

                        "Do you want to save the notebook before quitting?",
                        "Close Window", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                quitComfirm(option);
            }
        });
    }

    private void quitComfirm(int option) {
        if (option == JOptionPane.YES_OPTION) {
            saveNotebook();
            image7.setImage(image7.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(frame,
                    "Bye, I'll be lonely for while. Remember to come and play with me!",
                    "The App Say Good Bye to you", JOptionPane.WARNING_MESSAGE, image7);
            System.exit(0);
        } else if (option == JOptionPane.NO_OPTION) {
            image4.setImage(image4.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(frame, "You Lost all data since Last Save!",
                    "The App mocked you", JOptionPane.WARNING_MESSAGE, image4);
            System.exit(0);
        } else {
            image5.setImage(image5.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(frame, "Think before you do",
                    "The App criticized you", JOptionPane.WARNING_MESSAGE, image5);
        }
    }

    // REQUIRES: The date format is valid and the content is not empty.
    // MODIFIES: this
    // EFFECTS: Adds a new entry to the notebook.
    private void addEntry() {
        if (validateDate(dateField.getText()) && !contentField.getText().trim().isEmpty()) {
            String[] dateParts = dateField.getText().split("/");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            Date date = new Date(day, month, year);
            String content = contentField.getText();
            CalendarEntry entry = new CalendarEntry(date, content);
            notebook.addEntry(entry);

            entryPanel.removeAll();
            for (CalendarEntry e : notebook.getEntries()) {
                addEntryToPanel(e, entryPanel);
            }
            entryPanel.revalidate();
            entryPanel.repaint();
            image3.setImage(image3.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(frame, "Entry added successfully!",
                    "The App praised you", JOptionPane.WARNING_MESSAGE, image3);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid date format or content is empty.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Clears the text fields.
    private void clearFields() {
        dateField.setText("");
        contentField.setText("");
        searchField.setText("");
        searchResultPanel.removeAll();
        searchResultPanel.revalidate();
        searchResultPanel.repaint();
    }

    // REQUIRES: The date string is in the format "dd/mm/yyyy".
    // EFFECTS: Returns true if the date string is valid, false otherwise.
    private boolean validateDate(String dateStr) {
        String[] parts = dateStr.split("/");
        if (parts.length != 3) {
            return false;
        }
        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return (day > 0 && day <= 31 && month > 0 && month <= 12 && year > 1000 && year < 3100);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: Searches for entries that match the query and displays the results.
    private void searchEntries() {
        String query = searchField.getText();
        List<CalendarEntry> entries = notebook.searchEntries(query);
        searchResultPanel.removeAll();


        // Show dialog if no entries found
        if (!entries.isEmpty() && !searchField.getText().trim().isEmpty()) {
            for (CalendarEntry entry : entries) {
                addEntryToPanel(entry, searchResultPanel);
            }
            searchResultPanel.revalidate();
            searchResultPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "No entries found for the given query.",
                    "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Saves the notebook to a file.
    private void saveNotebook() {
        try {
            jsonWriter.open();
            jsonWriter.write(notebook);
            jsonWriter.close();
            image3.setImage(image3.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(frame, "Notebook saved successfully!",
                    "The App praised you", JOptionPane.WARNING_MESSAGE, image3);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads the notebook from a file.
    protected void loadNotebook() {
        try {
            notebook = jsonReader.read();
            entryPanel.removeAll();
            for (CalendarEntry entry : notebook.getEntries()) {
                addEntryToPanel(entry, entryPanel);
            }
            entryPanel.revalidate();
            entryPanel.repaint();
            clearFields();
            image3.setImage(image3.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(frame, "Notebook Loaded successfully!",
                    "The App praised you", JOptionPane.WARNING_MESSAGE, image3);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Unable to read from file: " + JSON_STORE,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds an entry to a panel.
    private void addEntryToPanel(CalendarEntry entry, JPanel panel) {
        JPanel entryDisplayPanel = new JPanel();
        entryDisplayPanel.add(new JLabel(entry.toString()));
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            notebook.deleteEntry(entry);
            entryPanel.removeAll();
            searchResultPanel.removeAll();
            searchEntries();
            for (CalendarEntry entries : notebook.getEntries()) {
                addEntryToPanel(entries, entryPanel);
            }
            panel.revalidate();
            panel.repaint();

            image3.setImage(image3.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            JOptionPane.showMessageDialog(frame, "Entry deleted successfully!",
                    "The App praised you", JOptionPane.WARNING_MESSAGE, image3);
        });
        entryDisplayPanel.add(deleteButton);
        panel.add(entryDisplayPanel);
        panel.revalidate();
        panel.repaint();
    }


}

