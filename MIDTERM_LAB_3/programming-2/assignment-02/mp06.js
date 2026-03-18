/**
 * =====================================================
 * Student Name    : UNDUG, MOHAMMAD FAISSAL A.
 * Course          : Programming 2
 * Assignment      : MP06 - Display Unique Values
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 18, 2026
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-undug-mohammadfaissal
 * Runtime         : Node.js (run with: node mp06.js)
 *
 * Description:
 *   This program reads a CSV dataset file whose path is entered by the user.
 *   It parses each row and collects all unique values found in the "Exam"
 *   column, then prints them in a numbered list with a total count at the end.
 * =====================================================
 */

"use strict";

// Built-in Node.js modules for file reading and user input
const fs            = require("fs");
const readline      = require("readline");

// -- SECTION 1: CSV Line Parser -----------------------------------------------
// Splits one CSV line into an array of field strings.
// Handles fields wrapped in double quotes that may contain commas inside them.
// Iterates character by character, toggling insideQuotes when a " is found,
// and only treating a comma as a field separator when outside quotes.
function parseCSVLine(line) {
    const fields = [];
    let current = "";
    let insideQuotes = false;

    for (const c of line) {
        if (c === '"') {
            // Flip the quote flag — we entered or exited a quoted section
            insideQuotes = !insideQuotes;
        } else if (c === "," && !insideQuotes) {
            // Comma outside quotes = end of one field, start of next
            fields.push(current);
            current = "";
        } else {
            current += c;
        }
    }

    // Push the last field that has no trailing comma
    fields.push(current);
    return fields;
}

// -- SECTION 2: Unique Exam Extractor -----------------------------------------
// Reads all lines from the file, skips the first 7 (metadata + header),
// parses each data row, and adds the Exam field (index 3) to a Set.
// A Set automatically discards duplicate values, leaving only unique ones.
// The resulting set is converted to a sorted array for display.
function displayUniqueExams(filePath) {

    // Read the entire file as a UTF-8 string, then split into individual lines
    const rawText = fs.readFileSync(filePath, "utf8");
    const lines   = rawText.split(/\r?\n/); // Handle both Windows and Unix line endings

    // Use a Set to collect exam names — duplicates are ignored automatically
    const uniqueExams = new Set();

    // Lines 0-6 (first 7) are metadata/headers — start reading data from index 7
    for (let i = 7; i < lines.length; i++) {
        const line = lines[i].trim();
        if (!line) continue; // Skip blank lines

        const fields = parseCSVLine(line);

        // Exam is at column index 3; only add if the field has content
        if (fields.length > 3 && fields[3].trim()) {
            uniqueExams.add(fields[3].trim());
        }
    }

    // Convert Set to array and sort alphabetically for clean output
    const sortedExams = [...uniqueExams].sort();

    // -- SECTION 3: Output ------------------------------------------------------
    // Print all unique exam names in a numbered list
    const line55 = "=".repeat(55);
    console.log();
    console.log(line55);
    console.log("  MP06 - UNIQUE EXAM TYPES IN DATASET");
    console.log("  Student: UNDUG, MOHAMMAD FAISSAL A.");
    console.log(line55);
    console.log();

    sortedExams.forEach((exam, index) => {
        // Pad the number so single and double digits align neatly
        console.log(`  ${String(index + 1).padStart(2)}. ${exam}`);
    });

    console.log();
    console.log(line55);
    console.log(`  Total unique exam types found: ${sortedExams.length}`);
    console.log(line55);
}

// -- SECTION 4: Program Entry Point -------------------------------------------
// Uses readline to prompt the user for the CSV file path in the terminal.
// Once the user presses Enter, the path is passed to the main processing function.
const rl = readline.createInterface({
    input:  process.stdin,
    output: process.stdout
});

rl.question("Enter the path to the CSV file: ", (filePath) => {
    rl.close();
    displayUniqueExams(filePath.trim());
});
