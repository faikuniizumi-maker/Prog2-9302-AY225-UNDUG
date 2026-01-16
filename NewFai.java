import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * NewFai - A Secure Java Swing application for tracking attendance
 * This application includes student ID and password authentication
 */
public class NewFai {
    
    // Main frame for the application
    private JFrame frame;
    
    // Text fields for user input and system-generated data
    private JTextField studentIdField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField courseField;
    private JTextField timeInField;
    private JTextField eSignatureField;
    
    // Simulated student database (In production, this would be a real database)
    private Map<String, StudentInfo> studentDatabase;
    
    // Flag to track if user is authenticated
    private boolean isAuthenticated = false;
    
    /**
     * Inner class to store student information
     */
    private class StudentInfo {
        String name;
        String course;
        String passwordHash;
        
        StudentInfo(String name, String course, String password) {
            this.name = name;
            this.course = course;
            this.passwordHash = hashPassword(password);
        }
    }
    
    /**
     * Constructor - initializes the application
     */
    public NewFai() {
        initializeDatabase();
        initialize();
    }
    
    /**
     * Initialize sample student database
     * In production, this would connect to a real database
     */
    private void initializeDatabase() {
        studentDatabase = new HashMap<>();
        // Your student account
        studentDatabase.put("19-0143-442", new StudentInfo("Mohammad Faissal A. Undug", "BSIT/ 1st Year", "FaiPerp6"));
        // Add more students as needed
    }
    
    /**
     * Hash password using SHA-256 for security
     */
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Initialize the contents of the frame
     */
    private void initialize() {
        // Create the main window
        frame = new JFrame("Secure Attendance Tracker");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window on screen
        
        // Create main panel with padding and blue gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(100, 150, 230); // Light blue
                Color color2 = new Color(50, 100, 180); // Deeper blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        
        // Title label with white text
        JLabel titleLabel = new JLabel(" STUDENT ATTENDANCE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);
        
        // Info label
        JLabel infoLabel = new JLabel("Please authenticate to record attendance", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        infoLabel.setForeground(new Color(230, 240, 255));
        gbc.gridy = 1;
        mainPanel.add(infoLabel, gbc);
        
        // Reset gridwidth for subsequent components
        gbc.gridwidth = 1;
        
        // Student ID field
        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setForeground(Color.WHITE);
        studentIdLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        mainPanel.add(studentIdLabel, gbc);
        
        studentIdField = new JTextField(20);
        studentIdField.setFont(new Font("Arial", Font.PLAIN, 12));
        studentIdField.setBackground(Color.WHITE);
        studentIdField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 200), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        // Press Enter to move to password field
        studentIdField.addActionListener(e -> passwordField.requestFocus());
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        mainPanel.add(studentIdField, gbc);
        
        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        mainPanel.add(passwordLabel, gbc);
        
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 200), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        // Press Enter to trigger login
        passwordField.addActionListener(e -> authenticateStudent());
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        mainPanel.add(passwordField, gbc);
        
        // Attendance Name field (auto-filled after authentication)
        JLabel nameLabel = new JLabel("Attendance Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        mainPanel.add(nameLabel, gbc);
        
        nameField = new JTextField(20);
        nameField.setEditable(false);
        nameField.setFont(new Font("Arial", Font.PLAIN, 12));
        nameField.setBackground(new Color(220, 235, 255));
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 200), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        mainPanel.add(nameField, gbc);
        
        // Course/Year field (auto-filled after authentication)
        JLabel courseLabel = new JLabel("Course/Year:");
        courseLabel.setForeground(Color.WHITE);
        courseLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        mainPanel.add(courseLabel, gbc);
        
        courseField = new JTextField(20);
        courseField.setEditable(false);
        courseField.setFont(new Font("Arial", Font.PLAIN, 12));
        courseField.setBackground(new Color(220, 235, 255));
        courseField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 200), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        mainPanel.add(courseField, gbc);
        
        // Time In field (system-generated)
        JLabel timeInLabel = new JLabel("Time In:");
        timeInLabel.setForeground(Color.WHITE);
        timeInLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        mainPanel.add(timeInLabel, gbc);
        
        timeInField = new JTextField(20);
        timeInField.setEditable(false); // Make it read-only
        timeInField.setBackground(new Color(220, 235, 255));
        timeInField.setFont(new Font("Arial", Font.PLAIN, 12));
        timeInField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 200), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        mainPanel.add(timeInField, gbc);
        
        // E-Signature field (system-generated)
        JLabel eSignatureLabel = new JLabel("E-Signature:");
        eSignatureLabel.setForeground(Color.WHITE);
        eSignatureLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 0.3;
        mainPanel.add(eSignatureLabel, gbc);
        
        eSignatureField = new JTextField(20);
        eSignatureField.setEditable(false); // Make it read-only
        eSignatureField.setBackground(new Color(220, 235, 255));
        eSignatureField.setFont(new Font("Arial", Font.PLAIN, 12));
        eSignatureField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 200), 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        mainPanel.add(eSignatureField, gbc);
        
        // Button panel for Login and Record Attendance
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        
        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 13));
        loginButton.setBackground(new Color(30, 80, 150));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.addActionListener(e -> authenticateStudent());
        buttonPanel.add(loginButton);
        
        // Record Attendance button
        JButton recordButton = new JButton("Record Attendance");
        recordButton.setFont(new Font("Arial", Font.BOLD, 13));
        recordButton.setBackground(new Color(50, 150, 80));
        recordButton.setForeground(Color.WHITE);
        recordButton.setFocusPainted(false);
        recordButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        recordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recordButton.setOpaque(true);
        recordButton.setBorderPainted(false);
        recordButton.addActionListener(e -> generateAttendance());
        buttonPanel.add(recordButton);
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        mainPanel.add(buttonPanel, gbc);
        
        // Add main panel to frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    /**
     * Authenticate student with Student ID and Password
     */
    private void authenticateStudent() {
        String studentId = studentIdField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        // Validate input
        if (studentId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "Please enter both Student ID and Password.", 
                "Authentication Failed", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check if student exists in database
        StudentInfo student = studentDatabase.get(studentId);
        
        if (student == null) {
            JOptionPane.showMessageDialog(frame, 
                "Student ID not found in the system.", 
                "Authentication Failed", 
                JOptionPane.ERROR_MESSAGE);
            passwordField.setText(""); // Clear password field
            return;
        }
        
        // Verify password
        String inputPasswordHash = hashPassword(password);
        if (!inputPasswordHash.equals(student.passwordHash)) {
            JOptionPane.showMessageDialog(frame, 
                "Incorrect password. Please try again.", 
                "Authentication Failed", 
                JOptionPane.ERROR_MESSAGE);
            passwordField.setText(""); // Clear password field
            return;
        }
        
        // Authentication successful
        isAuthenticated = true;
        nameField.setText(student.name);
        courseField.setText(student.course);
        
        // Disable login fields after successful authentication
        studentIdField.setEnabled(false);
        passwordField.setEnabled(false);
        
        JOptionPane.showMessageDialog(frame, 
            "Welcome, " + student.name + "!\nYou are now authenticated.", 
            "Authentication Successful", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Generate attendance data - captures current time and creates e-signature
     * Only works if student is authenticated
     */
    private void generateAttendance() {
        // Check if user is authenticated
        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(frame, 
                "Please login first before recording attendance.", 
                "Not Authenticated", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Check if attendance already recorded
        if (!timeInField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "Attendance already recorded for this session.", 
                "Already Recorded", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Get current system date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeIn = now.format(formatter);
        timeInField.setText(timeIn);
        
        // Generate unique e-signature using UUID + Student ID for extra security
        String eSignature = UUID.randomUUID().toString() + "-" + studentIdField.getText();
        eSignatureField.setText(eSignature);
        
        // Show confirmation message
        JOptionPane.showMessageDialog(frame, 
            "Attendance recorded successfully!\n\n" +
            "Student: " + nameField.getText() + "\n" +
            "Time: " + timeIn + "\n" +
            "Signature: " + eSignature.substring(0, 20) + "...", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Main method - entry point of the application
     */
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety
        SwingUtilities.invokeLater(() -> {
            try {
                // Set the system look and feel for better appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Create and display the application
            NewFai app = new NewFai();
            
            // Display login credentials for reference
            System.out.println("===== Student Login Info =====");
            System.out.println("Student ID: 19-0143-442");
            System.out.println("Password: FaiPerp6");
            System.out.println("==============================");
        });
    }
}