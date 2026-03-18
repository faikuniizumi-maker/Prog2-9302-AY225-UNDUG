/**
 * =====================================================
 * Student Name    : UNDUG, MOHAMMAD FAISSAL A.
 * Course          : Programming 2
 * Assignment      : MP07 - Sort Records Alphabetically
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 18, 2026
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-undug-mohammadfaissal
 *
 * Description:
 *   This program reads a CSV dataset file whose path is entered by the user.
 *   It loads all candidate records, sorts them alphabetically by the candidate's
 *   last name (first column), and prints the sorted table to the console with
 *   their exam name, score, and result clearly displayed.
 * =====================================================
 */

import java.io.*;
import java.util.*;

public class MP07 {

    // -- SECTION 1: Entry Point --------------------------------------------------
    // Prompts the user for the CSV path, then calls the sort-and-display method.
    public static void main(String[] args) throws IOException {

        // Scanner captures whatever path the user types into the console
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the CSV file: ");
        String filePath = scanner.nextLine().trim();

        sortAndDisplayRecords(filePath);
        scanner.close();
    }

    // -- SECTION 2: Record Loader and Sorter -------------------------------------
    // Reads all valid data rows from the CSV into a list of string arrays.
    // Each array holds the full set of fields for one candidate record.
    // The list is then sorted by comparing the candidate name in column 0.
    static void sortAndDisplayRecords(String filePath) throws IOException {

        // List to hold all parsed data rows before sorting
        List<String[]> records = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int lineNumber = 0;

        // Read through every line of the file
        while ((line = reader.readLine()) != null) {
            lineNumber++;

            // Lines 1-7 are metadata and headers, not candidate data
            if (lineNumber <= 7) continue;

            // Ignore blank lines at the bottom of the file
            if (line.trim().isEmpty()) continue;

            // Parse the line into individual fields
            String[] fields = parseCSVLine(line);

            // Only store rows that have enough columns to be valid data rows
            if (fields.length > 7) {
                records.add(fields);
            }
        }

        reader.close();

        // Sort the list alphabetically by the candidate name at index 0
        // Comparator.comparing extracts the name field and compares as strings
        records.sort(Comparator.comparing(r -> r[0].trim().toLowerCase()));

        // -- SECTION 3: Output ---------------------------------------------------
        // Print the sorted records in a formatted table layout
        System.out.println();
        System.out.println("=".repeat(75));
        System.out.println("  MP07 - RECORDS SORTED ALPHABETICALLY BY CANDIDATE NAME");
        System.out.println("  Student: UNDUG, MOHAMMAD FAISSAL A.");
        System.out.println("=".repeat(75));
        System.out.printf("  %-25s %-12s %-35s %-6s %-6s%n",
                          "Candidate", "Type", "Exam", "Score", "Result");
        System.out.println("  " + "-".repeat(73));

        // Loop through sorted records and print each row
        for (String[] r : records) {
            // Safely read each field, defaulting to empty string if missing
            String name   = r.length > 0 ? r[0].trim() : "";
            String type   = r.length > 1 ? r[1].trim() : "";
            String exam   = r.length > 3 ? r[3].trim() : "";
            String score  = r.length > 6 ? r[6].trim() : "";
            String result = r.length > 7 ? r[7].trim() : "";

            // Truncate long exam names so the table stays aligned
            if (exam.length() > 33) exam = exam.substring(0, 30) + "...";

            System.out.printf("  %-25s %-12s %-35s %-6s %-6s%n",
                              name, type, exam, score, result);
        }

        System.out.println("=".repeat(75));
        System.out.printf("  Total records: %d%n", records.size());
        System.out.println("=".repeat(75));
    }

    // -- SECTION 4: CSV Line Parser ----------------------------------------------
    // Handles quoted fields that may contain commas inside them.
    // Builds each field character by character, only splitting on commas
    // that are outside of double-quote pairs.
    static String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean insideQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                insideQuotes = !insideQuotes;
            } else if (c == ',' && !insideQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        fields.add(current.toString());
        return fields.toArray(new String[0]);
    }
}
