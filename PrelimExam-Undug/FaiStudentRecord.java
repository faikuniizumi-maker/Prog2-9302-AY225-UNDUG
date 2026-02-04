// Programmer: [YOUR FULL NAME] - [YOUR STUDENT ID]
// Student Record System - Java Swing Implementation

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FaiStudentRecord extends JFrame {
    // UI Components
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtStudentID, txtFirstName, txtLastName;
    private JButton btnAdd, btnDelete, btnClear;
    private JLabel lblRecordCount;
    
    // Color Theme - Green Vibe
    private final Color PRIMARY_GREEN = new Color(34, 139, 34);
    private final Color LIGHT_GREEN = new Color(144, 238, 144);
    private final Color DARK_GREEN = new Color(0, 100, 0);
    private final Color PALE_GREEN = new Color(245, 255, 245);
    private final Color WHITE = Color.WHITE;
    
    public FaiStudentRecord() {
        setTitle("Student Record System - [YOUR FULL NAME] [YOUR STUDENT ID]");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 750);
        setLocationRelativeTo(null);
        getContentPane().setBackground(WHITE);
        
        initializeComponents();
        loadDataFromCSV();
        
        setVisible(true);
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout(0, 0));
        
        // Header
        add(createHeaderPanel(), BorderLayout.NORTH);
        
        // Center (Input + Table)
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        centerPanel.add(createInputPanel(), BorderLayout.NORTH);
        centerPanel.add(createTablePanel(), BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // Footer
        add(createFooterPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(PRIMARY_GREEN);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("STUDENT RECORD MANAGEMENT SYSTEM", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(WHITE);
        
        panel.add(title);
        return panel;
    }
    
    private JPanel createInputPanel() {
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBackground(WHITE);
        outerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PRIMARY_GREEN, 2, true),
                "  Student Information Entry  ",
                0, 0,
                new Font("Arial", Font.BOLD, 14),
                DARK_GREEN
            ),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        // Fields Panel
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        fieldsPanel.setBackground(WHITE);
        
        // Student ID
        fieldsPanel.add(createLabel("Student ID:"));
        txtStudentID = createTextField(18);
        fieldsPanel.add(txtStudentID);
        
        // First Name
        fieldsPanel.add(createLabel("First Name:"));
        txtFirstName = createTextField(18);
        fieldsPanel.add(txtFirstName);
        
        // Last Name
        fieldsPanel.add(createLabel("Last Name:"));
        txtLastName = createTextField(18);
        fieldsPanel.add(txtLastName);
        
        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonsPanel.setBackground(WHITE);
        
        btnAdd = createButton("Add Student", PRIMARY_GREEN);
        btnDelete = createButton("Delete Selected", new Color(220, 53, 69));
        btnClear = createButton("Clear Fields", new Color(255, 193, 7));
        
        btnAdd.addActionListener(e -> addStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearFields());
        
        buttonsPanel.add(btnAdd);
        buttonsPanel.add(btnDelete);
        buttonsPanel.add(btnClear);
        
        // Combine
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(WHITE);
        contentPanel.add(fieldsPanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        outerPanel.add(contentPanel);
        return outerPanel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PRIMARY_GREEN, 2, true),
                "  Student Records Database  ",
                0, 0,
                new Font("Arial", Font.BOLD, 14),
                DARK_GREEN
            ),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        // Create table
        String[] columns = {
            "Student ID", "First Name", "Last Name", 
            "Lab 1", "Lab 2", "Lab 3", "Prelim", "Attendance"
        };
        
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setSelectionBackground(LIGHT_GREEN);
        table.setSelectionForeground(DARK_GREEN);
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1, 1));
        
        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(PRIMARY_GREEN);
        header.setForeground(WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(0, 40));
        header.setReorderingAllowed(false);
        
        // Column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(120);
        
        // Cell renderer for alternating colors
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, 
                    isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(WHITE);
                    } else {
                        c.setBackground(PALE_GREEN);
                    }
                }
                
                // Center numeric columns
                if (column >= 3) {
                    setHorizontalAlignment(JLabel.CENTER);
                } else {
                    setHorizontalAlignment(JLabel.LEFT);
                }
                
                return c;
            }
        };
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createFooterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PRIMARY_GREEN);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        
        lblRecordCount = new JLabel("Total Records: 0", JLabel.LEFT);
        lblRecordCount.setFont(new Font("Arial", Font.BOLD, 14));
        lblRecordCount.setForeground(WHITE);
        
        JLabel lblDev = new JLabel("Developed by: [YOUR FULL NAME] - [YOUR STUDENT ID]", JLabel.RIGHT);
        lblDev.setFont(new Font("Arial", Font.ITALIC, 12));
        lblDev.setForeground(LIGHT_GREEN);
        
        panel.add(lblRecordCount, BorderLayout.WEST);
        panel.add(lblDev, BorderLayout.EAST);
        
        return panel;
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(DARK_GREEN);
        return label;
    }
    
    private JTextField createTextField(int width) {
        JTextField field = new JTextField(width);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_GREEN, 2, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }
    
    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        
        btn.addMouseListener(new MouseAdapter() {
            Color original = bg;
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(original.brighter());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(original);
            }
        });
        
        return btn;
    }
    
    private void loadDataFromCSV() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MOCK_DATA.csv"));
            String line;
            boolean firstLine = true;
            int count = 0;
            
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                
                String[] data = line.split(",");
                if (data.length >= 8) {
                    tableModel.addRow(data);
                    count++;
                }
            }
            
            reader.close();
            updateRecordCount();
            
            JOptionPane.showMessageDialog(this,
                "Successfully loaded " + count + " student records!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                "MOCK_DATA.csv file not found!\nPlease ensure it's in the same directory.",
                "File Not Found",
                JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error reading CSV file: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addStudent() {
        String id = txtStudentID.getText().trim();
        String first = txtFirstName.getText().trim();
        String last = txtLastName.getText().trim();
        
        if (id.isEmpty() || first.isEmpty() || last.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please fill in all fields:\n- Student ID\n- First Name\n- Last Name",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Object[] row = {id, first, last, "0", "0", "0", "0", "0"};
        tableModel.addRow(row);
        updateRecordCount();
        clearFields();
        
        JOptionPane.showMessageDialog(this,
            "Student added successfully!\n\nName: " + first + " " + last + "\nID: " + id,
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void deleteStudent() {
        int row = table.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a row to delete!",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String id = tableModel.getValueAt(row, 0).toString();
        String name = tableModel.getValueAt(row, 1) + " " + tableModel.getValueAt(row, 2);
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Delete this record?\n\nStudent: " + name + "\nID: " + id,
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(row);
            updateRecordCount();
            JOptionPane.showMessageDialog(this,
                "Record deleted successfully!",
                "Deleted",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void clearFields() {
        txtStudentID.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtStudentID.requestFocus();
    }
    
    private void updateRecordCount() {
        lblRecordCount.setText("Total Records: " + tableModel.getRowCount());
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new FaiStudentRecord());
    }
}