# Programming Assignment 1 — 3x3 Matrix Determinant Solver

## Student Information

- **Name:** UNDUG, MOHAMMAD FAISSAL A.
- **Section:** BSIT-GD1 9302-AY225
- **Course:** Math 101 — Linear Algebra, UPHSD Molino Campus
- **Assignment:** Programming Assignment 1 — 3x3 Matrix Determinant Solver

---

## Assigned Matrix

The following 3x3 matrix was assigned to this student from the Section 4 roster:

```
  +------------------+
  |   4    2    3    |
  |   5    6    1    |
  |   3    4    2    |
  +------------------+
```

---

## How to Run

### Java Program

1. Make sure you have the Java Development Kit (JDK) installed.
2. Open a terminal or command prompt and navigate to this folder.
3. Compile the program:
   ```
   javac DeterminantSolver.java
   ```
4. Run the compiled program:
   ```
   java DeterminantSolver
   ```

### JavaScript Program

1. Make sure you have Node.js installed.
2. Open a terminal or command prompt and navigate to this folder.
3. Run the script:
   ```
   node determinant_solver.js
   ```

---

## Sample Output

Both programs produce identical mathematical results. Below is the expected console output:

```
====================================================
  3x3 MATRIX DETERMINANT SOLVER
  Student: UNDUG, MOHAMMAD FAISSAL A.
  Assigned Matrix:
====================================================
  +------------------+
  |   4    2    3    |
  |   5    6    1    |
  |   3    4    2    |
  +------------------+
====================================================

  Expanding along Row 1 (cofactor expansion):

  Step 1 - Minor M11: det([6,1],[4,2]) = (6x2)-(1x4) = 12 - 4 = 8
  Step 2 - Minor M12: det([5,1],[3,2]) = (5x2)-(1x3) = 10 - 3 = 7
  Step 3 - Minor M13: det([5,6],[3,4]) = (5x4)-(6x3) = 20 - 18 = 2

  Cofactor C11 = (+1) x 4 x  8 = 32
  Cofactor C12 = (-1) x 2 x  7 = -14
  Cofactor C13 = (+1) x 3 x  2 = 6

  det(M) = 32 + (-14) + 6
====================================================
  DETERMINANT = 24
====================================================
```

---

## Final Determinant Value

**det(M) = 24**

The matrix is **non-singular** — it has an inverse.
