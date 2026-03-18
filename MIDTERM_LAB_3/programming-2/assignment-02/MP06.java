/**
 * =====================================================
 * Student Name    : UNDUG, MOHAMMAD FAISSAL A.
 * Course          : Programming 2
 * Assignment      : MP06 - Display Unique Values
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 18, 2026
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-undug-mohammadfaissal
 *
 * Description:
 *   This program reads a CSV dataset file whose path is entered by the user.
 *   It parses each row and collects all unique values found in the "Exam"
 *   column, then prints them in a numbered list with a total count at the end.
 * =====================================================
 */

import java.io.*;
import java.util.*;

public class MP06 {

    // -- SECTION 1: Entry Point --------------------------------------------------
    // Main method prompts the user for the CSV file path, then calls the
    // processing method to find and display all unique exam types.
    public static void main(String[] args) throws IOException {

        // Ask the user to type in the path to the CSV file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the CSV file: ");
        String filePath = scanner.nextLine().trim();

        // Pass the file path to the method that handles the unique value logic
        displayUniqueExams(filePath);

        scanner.close();
    }

    // -- SECTION 2: CSV Reader and Unique Value Collector ------------------------
    // Opens the CSV file, skips the header rows, then reads each data row.
    // For every row, it extracts the "Exam" column (index 3) and adds it to
    // a TreeSet which automatically removes duplicates and sorts alphabetically.
    static void displayUniqueExams(String filePath) throws IOException {

        // TreeSet keeps values sorted and automatically ignores duplicates
        Set<String> uniqueExams = new TreeSet<>();

        // Open the file using a BufferedReader for efficient line-by-line reading
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        int lineNumber = 0;

        // Read through every line in the file
        while ((line = reader.readLine()) != null) {
            lineNumber++;

            // Skip the first 6 lines which are metadata/header rows, not data
            if (lineNumber <= 6) continue;

            // Skip the column header row (line 7)
            if (lineNumber == 7) continue;

            // Skip blank lines to avoid empty entries
            if (line.trim().isEmpty()) continue;

            // Parse the CSV row into individual fields, handling quoted commas
            String[] fields = parseCSVLine(line);

            // The Exam column is at index 3 (0-based)
            // Make sure the row has enough columns before accessing index 3
            if (fields.length > 3) {
                String exam = fields[3].trim();
                if (!exam.isEmpty()) {
                    uniqueExams.add(exam);
                }
            }
        }

        reader.close();

        // -- SECTION 3: Output ---------------------------------------------------
        // Print all unique exam types in a numbered, readable format
        System.out.println();
        System.out.println("=".repeat(55));
        System.out.println("  MP06 - UNIQUE EXAM TYPES IN DATASET");
        System.out.println("  Student: UNDUG, MOHAMMAD FAISSAL A.");
        System.out.println("=".repeat(55));
        System.out.println();

        int count = 1;
        // Loop through the sorted set and print each unique exam name
        for (String exam : uniqueExams) {
            System.out.printf("  %2d. %s%n", count++, exam);
        }

        System.out.println();
        System.out.println("=".repeat(55));
        System.out.printf("  Total unique exam types found: %d%n", uniqueExams.size());
        System.out.println("=".repeat(55));
    }

    // -- SECTION 4: CSV Line Parser ----------------------------------------------
    // Splits a single CSV line into fields while correctly handling values
    // that are wrapped in double quotes and may contain commas inside them.
    // For example: "Undug,Mohammad" should be treated as ONE field, not two.
    static String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean insideQuotes = false;

        // Go through each character one by one
        for (char c : line.toCharArray()) {
            if (c == '"') {
                // Toggle the insideQuotes flag when we encounter a quote mark
                insideQuotes = !insideQuotes;
            } else if (c == ',' && !insideQuotes) {
                // A comma outside quotes signals the end of a field
                fields.add(current.toString());
                current.setLength(0); // Reset the buffer for the next field
            } else {
                // Any other character just gets added to the current field
                current.append(c);
            }
        }

        // Add the last field after the loop ends
        fields.add(current.toString());
        return fields.toArray(new String[0]);
    }
}
