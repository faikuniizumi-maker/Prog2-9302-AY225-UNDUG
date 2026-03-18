# Programming Assignment 2 — CSV Dataset Processing

## Student Information

- **Name:** UNDUG, MOHAMMAD FAISSAL A.
- **Section:** BSIT-GD1 9302-AY225
- **Course:** Programming 2
- **School:** University of Perpetual Help System DALTA, Molino Campus

---

## Assignment Overview

This assignment processes the provided Pearson VUE exam results dataset (`Sample_Data-Prog-2-csv.csv`) using three machine problems, each implemented in both Java and JavaScript.

---

## Dataset

**File:** `Sample_Data-Prog-2-csv.csv`  
**Source:** Pearson VUE — University of Perpetual Help System, Molino Campus  
**Contents:** 264 exam records containing candidate name, type (Student/Faculty/NTE), exam taken, score, result (PASS/FAIL), and time used.

---

## Machine Problems

### MP06 — Display Unique Values

**Program Logic:**  
The program reads the CSV file line by line, skipping the first 7 metadata and header rows. It extracts the `Exam` column (index 3) from each data row and stores every value in a Set/TreeSet, which automatically removes duplicates. The resulting unique exam names are then sorted alphabetically and printed as a numbered list with a total count at the end.

| File | Language |
|------|----------|
| `MP06.java` | Java |
| `mp06.js` | JavaScript |

**How to Run:**
```
# Java
javac MP06.java
java MP06

# JavaScript
node mp06.js
```

**Sample Output:**
```
=======================================================
  MP06 - UNIQUE EXAM TYPES IN DATASET
  Student: UNDUG, MOHAMMAD FAISSAL A.
=======================================================

   1. Artificial Intelligence
   2. Cloud Computing
   3. Cybersecurity
   4. Data Analytics
   5. Databases
   6. Device Configuration and Management (Windows 11)
   7. HTML and CSS
   8. HTML5 Application Development
   9. Java
  10. JavaScript
  11. Network Security
  12. Python
  13. Python - Next Generation
  14. Software Development
  15. Information Technology Specialist in Networking

=======================================================
  Total unique exam types found: 15
=======================================================
```

---

### MP07 — Sort Records Alphabetically

**Program Logic:**  
The program reads all data rows from the CSV and stores them as arrays in a list. Each array holds the full set of fields for one candidate. The list is then sorted using a comparator that compares the candidate name field (index 0) in a case-insensitive manner. The sorted records are printed as a formatted table showing candidate name, type, exam, score, and result.

| File | Language |
|------|----------|
| `MP07.java` | Java |
| `mp07.js` | JavaScript |

**How to Run:**
```
# Java
javac MP07.java
java MP07

# JavaScript
node mp07.js
```

**Sample Output:**
```
===========================================================================
  MP07 - RECORDS SORTED ALPHABETICALLY BY CANDIDATE NAME
  Student: UNDUG, MOHAMMAD FAISSAL A.
===========================================================================
  Candidate                 Type         Exam                                Score  Result
  -------------------------------------------------------------------------
  Adoree Arbor              Student      HTML and CSS                        859    PASS
  Alexandre Doles           Student      HTML and CSS                        841    PASS
  ...
===========================================================================
  Total records: 264
===========================================================================
```

---

### MP08 — Filter Records by Keyword

**Program Logic:**  
The program first asks the user for a CSV file path, then prompts for a search keyword. It reads every data row and checks each field for a case-insensitive match against the keyword. Any row where at least one field contains the keyword is added to a results list. The matching records are then displayed in a formatted table along with a count of how many records matched. If no records match, a "not found" message is shown instead.

| File | Language |
|------|----------|
| `MP08.java` | Java |
| `mp08.js` | JavaScript |

**How to Run:**
```
# Java
javac MP08.java
java MP08

# JavaScript
node mp08.js
```

**Sample Output (keyword: "Python"):**
```
===========================================================================
  MP08 - RECORDS MATCHING KEYWORD: "Python"
  Student: UNDUG, MOHAMMAD FAISSAL A.
===========================================================================
  Candidate                 Type         Exam                                Score  Result
  -------------------------------------------------------------------------
  Alfi Tefft                Student      Python                              860    PASS
  Barby Pail                Student      Python                              920    PASS
  ...
===========================================================================
  Records found: 12
===========================================================================
```

---

## How to Use the Programs

1. Open a terminal and navigate to this folder
2. Compile and run the Java or JavaScript program
3. When prompted, enter the path to the CSV file:
   - If you are already in this folder, just type: `Sample_Data-Prog-2-csv.csv`
   - Otherwise use the full path: `C:\path\to\Sample_Data-Prog-2-csv.csv`
4. For MP08, also enter a keyword when prompted (e.g. `PASS`, `Python`, `Student`, `HTML`)
