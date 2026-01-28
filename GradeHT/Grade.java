import javax.swing.*;
import java.awt.*;

public class Grade extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // UI Components
    private JTextField attendanceField, lab1Field, lab2Field, lab3Field;
    private JTextArea resultArea;
    private JButton calculateButton, clearButton;
    
    // Custom Blue Gradient Panel
    class GradientPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(13, 71, 161); // Dark Blue
            Color color2 = new Color(66, 165, 245); // Light Blue
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }
    
    public Grade() {
        setTitle("Prelim Grading System Calculator");
        setSize(700, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main Panel with Gradient
        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(null);
        
        // Title Label
        JLabel titleLabel = new JLabel("PRELIM GRADING SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 20, 600, 40);
        mainPanel.add(titleLabel);
        
        // Subtitle Label
        JLabel subtitleLabel = new JLabel("Calculate Your Required Exam Score", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        subtitleLabel.setForeground(new Color(200, 230, 255));
        subtitleLabel.setBounds(50, 55, 600, 25);
        mainPanel.add(subtitleLabel);
        
        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBackground(new Color(255, 255, 255, 240));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(13, 71, 161), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        inputPanel.setBounds(50, 100, 600, 240);
        
        // Input Labels and Fields
        int yPos = 20;
        int labelWidth = 200;
        int fieldWidth = 350;
        int rowHeight = 50;
        
        // Attendance
        JLabel attendanceLabel = new JLabel("Attendance Score (0-100):");
        attendanceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        attendanceLabel.setBounds(20, yPos, labelWidth, 30);
        inputPanel.add(attendanceLabel);
        
        attendanceField = new JTextField();
        attendanceField.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(66, 165, 245), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        attendanceField.setBounds(230, yPos, fieldWidth, 35);
        inputPanel.add(attendanceField);
        
        // Lab Work 1
        yPos += rowHeight;
        JLabel lab1Label = new JLabel("Lab Work 1 Grade:");
        lab1Label.setFont(new Font("Arial", Font.BOLD, 14));
        lab1Label.setBounds(20, yPos, labelWidth, 30);
        inputPanel.add(lab1Label);
        
        lab1Field = new JTextField();
        lab1Field.setFont(new Font("Arial", Font.PLAIN, 14));
        lab1Field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(66, 165, 245), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        lab1Field.setBounds(230, yPos, fieldWidth, 35);
        inputPanel.add(lab1Field);
        
        // Lab Work 2
        yPos += rowHeight;
        JLabel lab2Label = new JLabel("Lab Work 2 Grade:");
        lab2Label.setFont(new Font("Arial", Font.BOLD, 14));
        lab2Label.setBounds(20, yPos, labelWidth, 30);
        inputPanel.add(lab2Label);
        
        lab2Field = new JTextField();
        lab2Field.setFont(new Font("Arial", Font.PLAIN, 14));
        lab2Field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(66, 165, 245), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        lab2Field.setBounds(230, yPos, fieldWidth, 35);
        inputPanel.add(lab2Field);
        
        // Lab Work 3
        yPos += rowHeight;
        JLabel lab3Label = new JLabel("Lab Work 3 Grade:");
        lab3Label.setFont(new Font("Arial", Font.BOLD, 14));
        lab3Label.setBounds(20, yPos, labelWidth, 30);
        inputPanel.add(lab3Label);
        
        lab3Field = new JTextField();
        lab3Field.setFont(new Font("Arial", Font.PLAIN, 14));
        lab3Field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(66, 165, 245), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        lab3Field.setBounds(230, yPos, fieldWidth, 35);
        inputPanel.add(lab3Field);
        
        mainPanel.add(inputPanel);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(50, 360, 600, 50);
        
        calculateButton = new JButton("CALCULATE");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.setPreferredSize(new Dimension(180, 40));
        calculateButton.setBackground(new Color(13, 71, 161));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setBorder(BorderFactory.createEmptyBorder());
        calculateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        calculateButton.addActionListener(e -> calculateGrades());
        buttonPanel.add(calculateButton);
        
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setPreferredSize(new Dimension(180, 40));
        clearButton.setBackground(new Color(66, 165, 245));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setBorder(BorderFactory.createEmptyBorder());
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(e -> clearFields());
        buttonPanel.add(clearButton);
        
        mainPanel.add(buttonPanel);
        
        // Result Area
        JLabel resultLabel = new JLabel("RESULTS:", SwingConstants.LEFT);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBounds(50, 420, 600, 25);
        mainPanel.add(resultLabel);
        
        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(255, 255, 255, 250));
        resultArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(13, 71, 161), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(50, 450, 600, 230);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(13, 71, 161), 2));
        mainPanel.add(scrollPane);
        
        add(mainPanel);
    }
    
    private void calculateGrades() {
        try {
            // Get input values
            double attendance = Double.parseDouble(attendanceField.getText().trim());
            double lab1 = Double.parseDouble(lab1Field.getText().trim());
            double lab2 = Double.parseDouble(lab2Field.getText().trim());
            double lab3 = Double.parseDouble(lab3Field.getText().trim());
            
            // Validate inputs
            if (attendance < 0 || attendance > 100 || lab1 < 0 || lab1 > 100 || lab2 < 0 || lab2 > 100 || lab3 < 0 || lab3 > 100) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter valid values:\n- Attendance: 0-100\n- Lab Work Grades: 0-100", 
                    "Invalid Input", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Calculate Lab Work Average
            // Formula: Lab Work Average = (Lab 1 + Lab 2 + Lab 3) / 3
            double labAverage = (lab1 + lab2 + lab3) / 3.0;
            
            // Attendance is treated as a direct score (0-100 scale)
            double attendanceScore = attendance;
            if (attendanceScore > 100) attendanceScore = 100;
            
            // Calculate Class Standing
            // Formula: Class Standing = (Attendance × 0.40) + (Lab Work Average × 0.60)
            double classStanding = (attendanceScore * 0.40) + (labAverage * 0.60);
            
            // Calculate Required Prelim Exam for Passing (75)
            // Formula: Prelim Grade = (Prelim Exam × 0.30) + (Class Standing × 0.70)
            // Solving for Prelim Exam: 75 = (Prelim Exam × 0.30) + (Class Standing × 0.70)
            // Prelim Exam = (75 - (Class Standing × 0.70)) / 0.30
            double requiredForPassing = (75 - (classStanding * 0.70)) / 0.30;
            
            // Calculate Required Prelim Exam for Excellent (100)
            // Formula: Prelim Grade = (Prelim Exam × 0.30) + (Class Standing × 0.70)
            // Solving for Prelim Exam: 100 = (Prelim Exam × 0.30) + (Class Standing × 0.70)
            // Prelim Exam = (100 - (Class Standing × 0.70)) / 0.30
            double requiredForExcellent = (100 - (classStanding * 0.70)) / 0.30;
            
            // Build result text
            StringBuilder result = new StringBuilder();
            result.append("===================================================================\n");
            result.append("              PRELIM GRADING COMPUTATION RESULTS\n");
            result.append("===================================================================\n\n");
            
            result.append("INPUT VALUES:\n");
            result.append("-------------------------------------------------------------------\n");
            result.append(String.format("  Attendance Score:        %.2f\n", attendance));
            result.append(String.format("  Lab Work 1 Grade:        %.2f\n", lab1));
            result.append(String.format("  Lab Work 2 Grade:        %.2f\n", lab2));
            result.append(String.format("  Lab Work 3 Grade:        %.2f\n\n", lab3));
            
            result.append("COMPUTED VALUES:\n");
            result.append("-------------------------------------------------------------------\n");
            result.append(String.format("  Lab Work Average:        %.2f\n", labAverage));
            result.append(String.format("  Class Standing (70%%):    %.2f\n\n", classStanding));
            
            result.append("REQUIRED PRELIM EXAM SCORES:\n");
            result.append("-------------------------------------------------------------------\n");
            
            // For Passing Grade
            if (requiredForPassing <= 0) {
                result.append(String.format("  To Pass (75):            Already Passed! (Current: %.2f)\n", 
                    0.30 * 0 + 0.70 * classStanding));
            } else if (requiredForPassing > 100) {
                result.append("  To Pass (75):            IMPOSSIBLE (even with 100)\n");
            } else {
                result.append(String.format("  To Pass (75):            %.2f\n", requiredForPassing));
            }
            
            // For Excellent Grade
            if (requiredForExcellent <= 0) {
                result.append(String.format("  For Excellent (100):     Already Excellent! (Current: %.2f)\n\n", 
                    0.30 * 0 + 0.70 * classStanding));
            } else if (requiredForExcellent > 100) {
                double maxPrelimGrade = 0.30 * 100 + 0.70 * classStanding;
                result.append(String.format("  For Excellent (100):     IMPOSSIBLE (Max: %.2f with 100)\n\n", maxPrelimGrade));
            } else {
                result.append(String.format("  For Excellent (100):     %.2f\n\n", requiredForExcellent));
            }
            
            result.append("REMARKS:\n");
            result.append("-------------------------------------------------------------------\n");
            
            // Evaluation remarks
            if (requiredForPassing <= 0) {
                result.append("  * Congratulations! You have already PASSED the Prelim period!\n");
                result.append("    Your current class standing guarantees a passing grade of 75+.\n");
            } else if (requiredForPassing > 100) {
                result.append("  X Unfortunately, it is mathematically IMPOSSIBLE to pass.\n");
                result.append("    Even with a perfect score of 100 on the Prelim Exam,\n");
                result.append("    you cannot reach the passing grade of 75.\n");
                double maxPrelimGrade = 0.30 * 100 + 0.70 * classStanding;
                result.append(String.format("    Your maximum possible Prelim Grade: %.2f\n", maxPrelimGrade));
            } else {
                result.append(String.format("  - You need %.2f%% on the Prelim Exam to achieve\n", requiredForPassing));
                result.append("    a passing Prelim Grade of 75.\n");
            }
            
            result.append("\n");
            
            if (requiredForExcellent <= 0) {
                result.append("  * Outstanding! You already have an EXCELLENT standing!\n");
                result.append("    Your class standing ensures an excellent grade of 100.\n");
            } else if (requiredForExcellent > 100) {
                result.append("  - An excellent Prelim Grade of 100 is not achievable.\n");
                double maxPrelimGrade = 0.30 * 100 + 0.70 * classStanding;
                result.append(String.format("    Maximum possible Prelim Grade: %.2f (with 100 on exam)\n", maxPrelimGrade));
            } else {
                result.append(String.format("  - You need %.2f%% on the Prelim Exam to achieve\n", requiredForExcellent));
                result.append("    an excellent Prelim Grade of 100.\n");
            }
            
            result.append("\n===================================================================\n");
            
            resultArea.setText(result.toString());
            resultArea.setCaretPosition(0);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numeric values in all fields.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearFields() {
        attendanceField.setText("");
        lab1Field.setText("");
        lab2Field.setText("");
        lab3Field.setText("");
        resultArea.setText("");
        attendanceField.requestFocus();
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            Grade frame = new Grade();
            frame.setVisible(true);
        });
    }
}