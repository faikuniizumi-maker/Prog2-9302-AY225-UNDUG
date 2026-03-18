/**
 * =====================================================
 * Student Name    : UNDUG, MOHAMMAD FAISSAL A.
 * Course          : Programming 2
 * Assignment      : MP07 - Sort Records Alphabetically
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 18, 2026
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-undug-mohammadfaissal
 * Runtime         : Node.js (run with: node mp07.js)
 *
 * Description:
 *   This program reads a CSV dataset file whose path is entered by the user.
 *   It loads all candidate records, sorts them alphabetically by the candidate's
 *   name (first column), and prints the sorted table to the console with
 *   their exam name, score, and result clearly displayed.
 * =====================================================
 */

"use strict";

const fs       = require("fs");
const readline = require("readline");

// -- SECTION 1: CSV Line Parser -----------------------------------------------
// Parses one CSV line into fields, correctly handling quoted values that
// contain commas. Toggles an insideQuotes flag on each double-quote character,
// and only splits on commas found outside of quoted sections.
function parseCSVLine(line) {
    const fields = [];
    let current = "";
    let insideQuotes = false;

    for (const c of line) {
        if (c === '"') {
            insideQuotes = !insideQuotes;
        } else if (c === "," && !insideQuotes) {
            fields.push(current);
            current = "";
        } else {
            current += c;
        }
    }

    fields.push(current);
    return fields;
}

// -- SECTION 2: Record Loader and Sorter --------------------------------------
// Reads all data lines from the CSV (skipping the first 7 header lines),
// parses each into a field array, and stores them in a records list.
// The list is then sorted using localeCompare on the candidate name field
// so that alphabetical ordering is consistent regardless of letter case.
function sortAndDisplayRecords(filePath) {

    const rawText = fs.readFileSync(filePath, "utf8");
    const lines   = rawText.split(/\r?\n/);

    // Collect all valid data rows into this array
    const records = [];

    // Data rows start at index 7 (after 6 metadata lines + 1 header line)
    for (let i = 7; i < lines.length; i++) {
        const line = lines[i].trim();
        if (!line) continue;

        const fields = parseCSVLine(line);

        // Only keep rows that have at least 8 columns of data
        if (fields.length > 7) {
            records.push(fields);
        }
    }

    // Sort alphabetically by candidate name (column 0), case-insensitive
    records.sort((a, b) =>
        a[0].trim().toLowerCase().localeCompare(b[0].trim().toLowerCase())
    );

    // -- SECTION 3: Output ------------------------------------------------------
    // Print the sorted records as a formatted table with column headers
    const line75 = "=".repeat(75);
    console.log();
    console.log(line75);
    console.log("  MP07 - RECORDS SORTED ALPHABETICALLY BY CANDIDATE NAME");
    console.log("  Student: UNDUG, MOHAMMAD FAISSAL A.");
    console.log(line75);

    // Column header row
    console.log(
        "  " +
        "Candidate".padEnd(25) +
        "Type".padEnd(12) +
        "Exam".padEnd(35) +
        "Score".padEnd(6) +
        "Result"
    );
    console.log("  " + "-".repeat(73));

    // Print each sorted record row
    records.forEach((r) => {
        let name   = (r[0] || "").trim();
        let type   = (r[1] || "").trim();
        let exam   = (r[3] || "").trim();
        let score  = (r[6] || "").trim();
        let result = (r[7] || "").trim();

        // Shorten long exam names so table columns stay aligned
        if (exam.length > 33) exam = exam.substring(0, 30) + "...";

        console.log(
            "  " +
            name.padEnd(25) +
            type.padEnd(12) +
            exam.padEnd(35) +
            score.padEnd(6) +
            result
        );
    });

    console.log(line75);
    console.log(`  Total records: ${records.length}`);
    console.log(line75);
}

// -- SECTION 4: Program Entry Point -------------------------------------------
// Prompts the user for the file path via readline, then starts processing.
const rl = readline.createInterface({
    input:  process.stdin,
    output: process.stdout
});

rl.question("Enter the path to the CSV file: ", (filePath) => {
    rl.close();
    sortAndDisplayRecords(filePath.trim());
});
