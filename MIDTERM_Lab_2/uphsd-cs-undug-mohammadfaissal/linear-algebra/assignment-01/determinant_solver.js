/**
 * =====================================================
 * Student Name    : UNDUG, MOHAMMAD FAISSAL A.
 * Course          : Math 101 - Linear Algebra
 * Assignment      : Programming Assignment 1 - 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 18, 2026
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-undug-mohammadfaissal
 * Runtime         : Node.js (run with: node determinant_solver.js)
 *
 * Description:
 *   JavaScript equivalent of DeterminantSolver.java. This script computes
 *   the determinant of the same hardcoded 3x3 matrix using cofactor expansion
 *   along the first row. All intermediate steps are logged to the console
 *   using console.log() for complete transparency of the solution process.
 * =====================================================
 */

// -- SECTION 1: Matrix Declaration -------------------------------------------
// The assigned 3x3 matrix is declared as a 2D JavaScript array.
// These values match the roster entry for UNDUG, MOHAMMAD FAISSAL A.
// Outer array = rows, inner arrays = individual row values.
const matrix = [
    [4, 2, 3],   // Row 1
    [5, 6, 1],   // Row 2
    [3, 4, 2]    // Row 3
];

// -- SECTION 2: Matrix Printer -----------------------------------------------
// Accepts a 3x3 array and prints it in a formatted table-like style.
// Uses template literals for clean string interpolation.
function printMatrix(m) {
    console.log("  +------------------+");
    m.forEach(row => {
        const fmt = row.map(v => v.toString().padStart(3)).join("  ");
        console.log(`  | ${fmt}   |`);
    });
    console.log("  +------------------+");
}

// -- SECTION 3: 2x2 Determinant Helper ---------------------------------------
// Computes the determinant of a 2x2 matrix given four scalar values.
// Called three times during the cofactor expansion step.
// Parameters: a, b = first row; c, d = second row of the 2x2 sub-matrix.
function computeMinor(a, b, c, d) {
    // 2x2 determinant formula: ad - bc
    return (a * d) - (b * c);
}

// -- SECTION 4: Step-by-Step Determinant Solver ------------------------------
// Main solving function. Accepts the 3x3 matrix and:
//   1. Prints the matrix clearly
//   2. Computes each 2x2 minor (M11, M12, M13)
//   3. Prints the computation of each minor with its arithmetic
//   4. Computes and prints each signed cofactor term
//   5. Sums the cofactors and prints the final determinant
//   6. Checks for a singular matrix (det = 0)
function solveDeterminant(m) {
    const line = "=".repeat(52);

    // Print problem header
    console.log(line);
    console.log("  3x3 MATRIX DETERMINANT SOLVER");
    console.log("  Student: UNDUG, MOHAMMAD FAISSAL A.");
    console.log("  Assigned Matrix:");
    console.log(line);
    printMatrix(m);
    console.log(line);
    console.log();
    console.log("  Expanding along Row 1 (cofactor expansion):");
    console.log();

    // -- Step 1: Minor M11 --
    // Delete row 0 and column 0 -> remaining elements: [1][1],[1][2],[2][1],[2][2]
    const minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
    console.log(
        `  Step 1 - Minor M11: det([${m[1][1]},${m[1][2]}],[${m[2][1]},${m[2][2]}])` +
        ` = (${m[1][1]}x${m[2][2]}) - (${m[1][2]}x${m[2][1]}) = ${m[1][1]*m[2][2]} - ${m[1][2]*m[2][1]} = ${minor11}`
    );

    // -- Step 2: Minor M12 --
    // Delete row 0 and column 1 -> remaining: [1][0],[1][2],[2][0],[2][2]
    const minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
    console.log(
        `  Step 2 - Minor M12: det([${m[1][0]},${m[1][2]}],[${m[2][0]},${m[2][2]}])` +
        ` = (${m[1][0]}x${m[2][2]}) - (${m[1][2]}x${m[2][0]}) = ${m[1][0]*m[2][2]} - ${m[1][2]*m[2][0]} = ${minor12}`
    );

    // -- Step 3: Minor M13 --
    // Delete row 0 and column 2 -> remaining: [1][0],[1][1],[2][0],[2][1]
    const minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
    console.log(
        `  Step 3 - Minor M13: det([${m[1][0]},${m[1][1]}],[${m[2][0]},${m[2][1]}])` +
        ` = (${m[1][0]}x${m[2][1]}) - (${m[1][1]}x${m[2][0]}) = ${m[1][0]*m[2][1]} - ${m[1][1]*m[2][0]} = ${minor13}`
    );

    // -- Cofactor Terms --
    // C11 gets +sign, C12 gets -sign, C13 gets +sign (alternating sign rule)
    const c11 =  m[0][0] * minor11;
    const c12 = -m[0][1] * minor12;
    const c13 =  m[0][2] * minor13;

    console.log();
    console.log(`  Cofactor C11 = (+1) x ${m[0][0]} x ${minor11} = ${c11}`);
    console.log(`  Cofactor C12 = (-1) x ${m[0][1]} x ${minor12} = ${c12}`);
    console.log(`  Cofactor C13 = (+1) x ${m[0][2]} x ${minor13} = ${c13}`);

    // -- Final Determinant --
    // Add all three cofactor terms together for the determinant
    const det = c11 + c12 + c13;
    console.log();
    console.log(`  det(M) = ${c11} + (${c12}) + ${c13}`);
    console.log(line);
    console.log(`  DETERMINANT = ${det}`);

    // -- Singular Matrix Check --
    // If det = 0, print a warning that the matrix cannot be inverted
    if (det === 0) {
        console.log("  The matrix is SINGULAR -- it has no inverse.");
    }
    console.log(line);
}

// -- SECTION 5: Program Entry Point ------------------------------------------
// Call the main solver function with the student's assigned matrix.
solveDeterminant(matrix);
