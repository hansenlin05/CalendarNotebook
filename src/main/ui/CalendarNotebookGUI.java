package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.CalendarEntry;
import model.CalendarNotebook;
import model.Date;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarNotebookGUI {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField dateField;
    private JTextField contentField;
    private CalendarNotebook notebook;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public CalendarNotebookGUI() {
        notebook = new CalendarNotebook();
        frame = new JFrame("Calendar Notebook");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        dateField = new JTextField("Enter date (dd/mm/yyyy)");
        contentField = new JTextField("Enter content");

        JButton addButton = new JButton("Add Entry");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 这里添加代码来处理添加条目的操作
            }
        });

        JButton viewButton = new JButton("View All Entries");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 这里添加代码来处理查看所有条目的操作
            }
        });

        JButton deleteButton = new JButton("Delete Entry");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 这里添加代码来处理删除条目的操作
            }
        });

        JPanel panel = new JPanel();
        panel.add(dateField);
        panel.add(contentField);
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(deleteButton);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

}
