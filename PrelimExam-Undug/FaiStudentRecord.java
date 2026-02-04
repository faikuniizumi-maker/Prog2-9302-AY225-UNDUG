// Mohammad Faissal A. Undug - 19-0143-442
// StudentRecords.java
// Java Swing Student Record System (Grade = Attendance)

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FaiStudentRecord extends JFrame implements ActionListener {

    private JTable table;
    private DefaultTableModel model;

    private JTextField idField;
    private JTextField nameField;
    private JTextField gradeField;

    private JButton addButton;
    private JButton deleteButton;

    public FaiStudentRecord() {

        setTitle("Records - Mohammad Faissal A. Undug - 19-0143-442");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ONLY 3 COLUMNS
        model = new DefaultTableModel(
            new String[]{"ID", "Name", "Grade"}, 0
        );

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("ID"));
        inputPanel.add(new JLabel("Name"));
        inputPanel.add(new JLabel("Grade"));
        inputPanel.add(new JLabel(""));

        idField = new JTextField();
        nameField = new JTextField();
        gradeField = new JTextField();

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete Selected");

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);

        inputPanel.add(idField);
        inputPanel.add(nameField);
        inputPanel.add(gradeField);
        inputPanel.add(addButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);

        loadCSV("MOCK_DATA.csv");

        setVisible(true);
    }

    // READ CSV — USE ATTENDANCE AS GRADE (LAST COLUMN)
    private void loadCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            br.readLine(); // skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String id = data.length > 0 ? data[0].trim() : "";
                String name = data.length > 1 ? data[1].trim() : "";

                // Attendance is usually the LAST column
                String grade = "";
                if (data.length > 0) {
                    grade = data[data.length - 1].trim();
                }

                model.addRow(new Object[]{id, name, grade});
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                this,
                "Error reading CSV:\n" + e.getMessage(),
                "File Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ADD
        if (e.getSource() == addButton) {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String gradeText = gradeField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || gradeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            // Grade must be numeric
            try {
                int grade = Integer.parseInt(gradeText);
                model.addRow(new Object[]{id, name, grade});

                idField.setText("");
                nameField.setText("");
                gradeField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Grade must be a number.");
            }
        }

        // DELETE
        if (e.getSource() == deleteButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
            } else {
                model.removeRow(selectedRow);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FaiStudentRecord());
    }
}
