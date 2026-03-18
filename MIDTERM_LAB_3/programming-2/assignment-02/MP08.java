/**
 * =====================================================
 * Student Name    : UNDUG, MOHAMMAD FAISSAL A.
 * Course          : Programming 2
 * Assignment      : MP08 - Filter Records by Keyword
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 18, 2026
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-undug-mohammadfaissal
 *
 * Description:
 *   This program reads a CSV dataset file whose path is entered by the user.
 *   It then asks the user to type a keyword, and searches every field of every
 *   record for a case-insensitive match. All matching rows are printed in a
 *   formatted table along with a count of how many records were found.
 * =====================================================
 */

import java.io.*;
import java.util.*;

public class MP08 {

    // -- SECTION 1: Entry Point --------------------------------------------------
    // Collects the file path and search keyword from the user, then calls
    // the filter method to find and display all matching records.
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        // Ask for the CSV file location first
        System.out.print("Enter the path to the CSV file: ");
        String filePath = scanner.nextLine().trim();

        // Ask for the keyword the user wants to search for
        System.out.print("Enter keyword to filter by: ");
        String keyword = scanner.nextLine().trim();

        filterByKeyword(filePath, keyword);
        scanner.close();
    }

    // -- SECTION 2: Filter Logic -------------------------------------------------
    // Reads every data row from the CSV and checks whether any field in that
    // row contains the keyword (case-insensitive). Matching rows are stored
    // in a list and then printed as a formatted table.
    static void filterByKeyword(String filePath, String keyword) throws IOException {

        // List to collect only the rows that match the keyword
        List<String[]> matches = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int lineNumber = 0;

        // Convert keyword to lowercase once so every comparison is case-insensitive
        String keywordLower = keyword.toLowerCase();

        while ((line = reader.readLine()) != null) {
            lineNumber++;

            // Skip the first 7 lines (metadata + column headers)
            if (lineNumber <= 7) continue;

            // Skip empty lines
            if (line.trim().isEmpty()) continue;

            String[] fields = parseCSVLine(line);

            // Only process rows that have enough columns
            if (fields.length <= 7) continue;

            // Check every field in this row for the keyword
            boolean rowMatches = false;
            for (String field : fields) {
                if (field.toLowerCase().contains(keywordLower)) {
                    rowMatches = true;
                    break; // No need to check remaining fields once a match is found
                }
            }

            // If any field matched, add the whole row to the results list
            if (rowMatches) {
                matches.add(fields);
            }
        }

        reader.close();

        // -- SECTION 3: Output ---------------------------------------------------
        // Display the filtered results in a clearly formatted table
        System.out.println();
        System.out.println("=".repeat(75));
        System.out.printf("  MP08 - RECORDS MATCHING KEYWORD: \"%s\"%n", keyword);
        System.out.println("  Student: UNDUG, MOHAMMAD FAISSAL A.");
        System.out.println("=".repeat(75));

        // Show a message and exit early if no records matched
        if (matches.isEmpty()) {
            System.out.printf("  No records found matching \"%s\".%n", keyword);
            System.out.println("=".repeat(75));
            return;
        }

        // Print the table header row
        System.out.printf("  %-25s %-12s %-35s %-6s %-6s%n",
                          "Candidate", "Type", "Exam", "Score", "Result");
        System.out.println("  " + "-".repeat(73));

        // Print each matching record
        for (String[] r : matches) {
            String name   = r.length > 0 ? r[0].trim() : "";
            String type   = r.length > 1 ? r[1].trim() : "";
            String exam   = r.length > 3 ? r[3].trim() : "";
            String score  = r.length > 6 ? r[6].trim() : "";
            String result = r.length > 7 ? r[7].trim() : "";

            // Truncate long exam names to keep table aligned
            if (exam.length() > 33) exam = exam.substring(0, 30) + "...";

            System.out.printf("  %-25s %-12s %-35s %-6s %-6s%n",
                              name, type, exam, score, result);
        }

        System.out.println("=".repeat(75));
        System.out.printf("  Records found: %d%n", matches.size());
        System.out.println("=".repeat(75));
    }

    // -- SECTION 4: CSV Line Parser ----------------------------------------------
    // Custom parser that handles quoted fields containing commas.
    // Iterates character by character, toggling a flag when inside quotes,
    // and only splits on commas that appear outside of quoted sections.
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
