package ui;

import model.CalendarEntry;
import model.CalendarNotebook;
import model.Date;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class CalendarNotebookGUI {
    protected JFrame frame = new JFrame("Calendar Notebook");
    private JPanel entryPanel = new JPanel();
    private JPanel controlPanel = new JPanel();
    private JPanel searchResultPanel = new JPanel();
    private JPanel searchPanel = new JPanel();
    private JScrollPane searchScrollPanel = new JScrollPane(searchResultPanel);
    private JScrollPane entryScrollPane = new JScrollPane(entryPanel);
    private JTextField dateField = new JTextField();
    private JTextField contentField = new JTextField();
    private JTextField searchField = new JTextField();
    private CalendarNotebook notebook = new CalendarNotebook();
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private static final String JSON_STORE = "./data/notebook.json";
    private JLabel searchLabel = new JLabel("Search by Date (dd/mm/yyyy) or Content:");
    private JLabel contentLabel = new JLabel("Enter content:");
    private JLabel dateLabel = new JLabel("Enter date for new entry (dd/mm/yyyy):");
    private JButton searchButton = new JButton("Search");
    private JButton addButton = new JButton("Add Entry");
    private JButton clearButton = new JButton("Clear");
    private JButton loadButton = new JButton("Load");
    private JButton quitButton = new JButton("Quit");
    private JButton saveButton = new JButton("Save");
    private ImageIcon image1 = new ImageIcon("./data/image1.jpg");
    private ImageIcon image2 = new ImageIcon("./data/image2.jpg");
    private ImageIcon image3 = new ImageIcon("./data/image3.jpg");
    private JLabel imageLabel1 = new JLabel(image1);
    private JLabel imageLabel2 = new JLabel(image2);
    private JLabel imageLabel3 = new JLabel(image3);

    public CalendarNotebookGUI() {
        initializeUI();
    }

    private void initializeUI() {
        setupFrameAndPanels();
        setupControlPanel();
        setupEntryPanel();
        setupSearchPanel();
        addWindowClosingListener();
        frame.setVisible(true);
    }

    private void setupFrameAndPanels() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("欢迎使用日记记事本");
        topPanel.add(titleLabel);
        topPanel.add(imageLabel1);
        topPanel.add(imageLabel2);
        topPanel.add(imageLabel3);
        frame.add(topPanel, BorderLayout.NORTH);

        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));
        entryScrollPane.setBorder(BorderFactory.createTitledBorder("左侧面板 - 全部条目"));
        frame.add(entryScrollPane, BorderLayout.WEST);

        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBorder(BorderFactory.createTitledBorder("中间面板 - 搜索"));
        frame.add(searchPanel, BorderLayout.CENTER);

        controlPanel.setLayout(new GridLayout(7, 2));
        controlPanel.setBorder(BorderFactory.createTitledBorder("右侧面板 - 控制"));
        frame.add(controlPanel, BorderLayout.EAST);
    }

    private void setupControlPanel() {
        controlPanel.add(dateLabel);
        controlPanel.add(dateField);
        controlPanel.add(contentLabel);
        controlPanel.add(contentField);
        addButton.addActionListener(e -> addEntry());
        controlPanel.add(addButton);
        clearButton.addActionListener(e -> clearFields());
        controlPanel.add(clearButton);
        setupSaveLoadButtons();
        setupQuitButton();
    }

    private void setupEntryPanel() {
        // Method placeholder for future enhancements
    }

    private void setupSearchPanel() {
        searchPanel.add(searchLabel);
        setupSearchFieldAndButton();
    }

    private void setupSearchFieldAndButton() {
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchField.getPreferredSize().height));
        searchPanel.add(searchField);
        searchButton.addActionListener(e -> searchEntries());
        searchPanel.add(searchButton);
        searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.Y_AXIS));
        searchPanel.add(searchScrollPanel);
    }

    private void setupSaveLoadButtons() {
        saveButton.addActionListener(e -> saveNotebook());
        controlPanel.add(saveButton);
        loadButton.addActionListener(e -> loadNotebook());
        controlPanel.add(loadButton);
    }

    private void setupQuitButton() {
        quitButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(frame, "Do you want to save the notebook before quitting?", "Quit", JOptionPane.YES_NO_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                saveNotebook();
                System.exit(0);
            } else if (option == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        });
        controlPanel.add(quitButton);
    }

    private void addWindowClosingListener() {
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int option = JOptionPane.showConfirmDialog(frame, "Do you want to save the notebook before quitting?", "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    saveNotebook();
                }
                System.exit(0);
            }
        });
    }

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
            addEntryToPanel(entry, entryPanel);
            JOptionPane.showMessageDialog(frame, "Entry added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid date format or content is empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        dateField.setText("");
        contentField.setText("");
    }

    private boolean validateDate(String dateStr) {
        String[] parts = dateStr.split("/");
        if (        parts.length != 3) {
            return false;
        }
        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return (day > 0 && day <= 31 && month > 0 && month <= 12 && year > 1900 && year < 2100);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void searchEntries() {
        String query = searchField.getText();
        List<CalendarEntry> entries = notebook.searchEntries(query);
        searchResultPanel.removeAll();
        for (CalendarEntry entry : entries) {
            addEntryToPanel(entry, searchResultPanel);
        }
        searchResultPanel.revalidate();
        searchResultPanel.repaint();

        // Show dialog if no entries found
        if (entries.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No entries found for the given query.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveNotebook() {
        try {
            jsonWriter.open();
            jsonWriter.write(notebook);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Notebook saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Unable to write to file: " + JSON_STORE, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void loadNotebook() {
        try {
            notebook = jsonReader.read();
            entryPanel.removeAll();
            for (CalendarEntry entry : notebook.getEntries()) {
                addEntryToPanel(entry, entryPanel);
            }
            entryPanel.revalidate();
            entryPanel.repaint();
            JOptionPane.showMessageDialog(frame, "Notebook loaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Unable to read from file: " + JSON_STORE, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addEntryToPanel(CalendarEntry entry, JPanel panel) {
        JPanel entryDisplayPanel = new JPanel();
        entryDisplayPanel.add(new JLabel(entry.toString()));
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            notebook.deleteEntry(entry);
            panel.remove(entryDisplayPanel);
            panel.revalidate();
            panel.repaint();
            JOptionPane.showMessageDialog(frame, "Entry deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
        entryDisplayPanel.add(deleteButton);
        panel.add(entryDisplayPanel);
        panel.revalidate();
        panel.repaint();
    }


}

