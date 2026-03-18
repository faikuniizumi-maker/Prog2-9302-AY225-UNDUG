/**
 * =====================================================
 * Student Name    : UNDUG, MOHAMMAD FAISSAL A.
 * Course          : Programming 2
 * Assignment      : MP08 - Filter Records by Keyword
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 18, 2026
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-undug-mohammadfaissal
 * Runtime         : Node.js (run with: node mp08.js)
 *
 * Description:
 *   This program reads a CSV dataset file whose path is entered by the user.
 *   It then asks the user to type a keyword, and searches every field of every
 *   record for a case-insensitive match. All matching rows are printed in a
 *   formatted table along with a count of how many records were found.
 * =====================================================
 */

"use strict";

const fs       = require("fs");
const readline = require("readline");

// -- SECTION 1: CSV Line Parser -----------------------------------------------
// Splits a single CSV line into fields while handling quoted values that
// contain commas. Uses an insideQuotes boolean flag to track whether the
// current character is inside a quoted section or not.
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

// -- SECTION 2: Keyword Filter ------------------------------------------------
// Reads every data row in the CSV and checks whether any of its fields
// contain the search keyword (case-insensitive comparison using toLowerCase).
// Rows where at least one field matches are added to the results array.
// At the end, matching rows are printed as a formatted table.
function filterByKeyword(filePath, keyword) {

    const rawText    = fs.readFileSync(filePath, "utf8");
    const lines      = rawText.split(/\r?\n/);
    const keyLower   = keyword.toLowerCase(); // Pre-lowercase for faster comparisons
    const matches    = []; // Stores only the rows that match the keyword

    // Start reading data rows from index 7 (skip metadata + header lines)
    for (let i = 7; i < lines.length; i++) {
        const line = lines[i].trim();
        if (!line) continue;

        const fields = parseCSVLine(line);
        if (fields.length <= 7) continue;

        // Check if the keyword appears in any field of this row
        const rowMatches = fields.some(
            (field) => field.toLowerCase().includes(keyLower)
        );

        if (rowMatches) {
            matches.push(fields);
        }
    }

    // -- SECTION 3: Output ------------------------------------------------------
    // Display all matching records in a readable table format
    const line75 = "=".repeat(75);
    console.log();
    console.log(line75);
    console.log(`  MP08 - RECORDS MATCHING KEYWORD: "${keyword}"`);
    console.log("  Student: UNDUG, MOHAMMAD FAISSAL A.");
    console.log(line75);

    // If nothing matched, tell the user and stop
    if (matches.length === 0) {
        console.log(`  No records found matching "${keyword}".`);
        console.log(line75);
        return;
    }

    // Print column headers
    console.log(
        "  " +
        "Candidate".padEnd(25) +
        "Type".padEnd(12) +
        "Exam".padEnd(35) +
        "Score".padEnd(6) +
        "Result"
    );
    console.log("  " + "-".repeat(73));

    // Print each matching row
    matches.forEach((r) => {
        let name   = (r[0] || "").trim();
        let type   = (r[1] || "").trim();
        let exam   = (r[3] || "").trim();
        let score  = (r[6] || "").trim();
        let result = (r[7] || "").trim();

        // Truncate exam names that are too long to keep columns tidy
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
    console.log(`  Records found: ${matches.length}`);
    console.log(line75);
}

// -- SECTION 4: Program Entry Point -------------------------------------------
// Uses readline to ask for the file path first, then the search keyword.
// Both inputs are collected before the filter function is called.
const rl = readline.createInterface({
    input:  process.stdin,
    output: process.stdout
});

// Ask for file path first, then ask for keyword inside the callback
rl.question("Enter the path to the CSV file: ", (filePath) => {
    rl.question("Enter keyword to filter by: ", (keyword) => {
        rl.close();
        filterByKeyword(filePath.trim(), keyword.trim());
    });
});
