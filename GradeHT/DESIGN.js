/**
 * Prelim Grading System Calculator
 * JavaScript Implementation
 */

function calculateGrades() {
    // Clear previous error messages
    hideError();
    
    // Get input values
    const attendance = parseFloat(document.getElementById('attendance').value);
    const lab1 = parseFloat(document.getElementById('lab1').value);
    const lab2 = parseFloat(document.getElementById('lab2').value);
    const lab3 = parseFloat(document.getElementById('lab3').value);
    
    // Validate inputs
    if (isNaN(attendance) || isNaN(lab1) || isNaN(lab2) || isNaN(lab3)) {
        showError('Please enter valid numeric values in all fields.');
        return;
    }
    
    if (attendance < 0 || attendance > 100 || lab1 < 0 || lab1 > 100 || 
        lab2 < 0 || lab2 > 100 || lab3 < 0 || lab3 > 100) {
        showError('Please enter valid values:\n- Attendance: 0-100\n- Lab Work Grades: 0-100');
        return;
    }
    
    // Calculate Lab Work Average
    // Formula: Lab Work Average = (Lab 1 + Lab 2 + Lab 3) / 3
    const labAverage = (lab1 + lab2 + lab3) / 3.0;
    
    // Attendance is treated as a direct score (0-100 scale)
    const attendanceScore = attendance;
    
    // Calculate Class Standing
    // Formula: Class Standing = (Attendance × 0.40) + (Lab Work Average × 0.60)
    const classStanding = (attendanceScore * 0.40) + (labAverage * 0.60);
    
    // Calculate Required Prelim Exam for Passing (75)
    // Formula: Prelim Grade = (Prelim Exam × 0.30) + (Class Standing × 0.70)
    // Solving for Prelim Exam: 75 = (Prelim Exam × 0.30) + (Class Standing × 0.70)
    // Prelim Exam = (75 - (Class Standing × 0.70)) / 0.30
    const requiredForPassing = (75 - (classStanding * 0.70)) / 0.30;
    
    // Calculate Required Prelim Exam for Excellent (100)
    // Formula: Prelim Grade = (Prelim Exam × 0.30) + (Class Standing × 0.70)
    // Solving for Prelim Exam: 100 = (Prelim Exam × 0.30) + (Class Standing × 0.70)
    // Prelim Exam = (100 - (Class Standing × 0.70)) / 0.30
    const requiredForExcellent = (100 - (classStanding * 0.70)) / 0.30;
    
    // Build result text
    let result = '';
    result += '===================================================================\n';
    result += '              PRELIM GRADING COMPUTATION RESULTS\n';
    result += '===================================================================\n\n';
    
    result += 'INPUT VALUES:\n';
    result += '-------------------------------------------------------------------\n';
    result += `  Attendance Score:        ${attendance.toFixed(2)}\n`;
    result += `  Lab Work 1 Grade:        ${lab1.toFixed(2)}\n`;
    result += `  Lab Work 2 Grade:        ${lab2.toFixed(2)}\n`;
    result += `  Lab Work 3 Grade:        ${lab3.toFixed(2)}\n\n`;
    
    result += 'COMPUTED VALUES:\n';
    result += '-------------------------------------------------------------------\n';
    result += `  Lab Work Average:        ${labAverage.toFixed(2)}\n`;
    result += `  Class Standing (70%):    ${classStanding.toFixed(2)}\n\n`;
    
    result += 'REQUIRED PRELIM EXAM SCORES:\n';
    result += '-------------------------------------------------------------------\n';
    
    // For Passing Grade
    if (requiredForPassing <= 0) {
        const currentGrade = 0.30 * 0 + 0.70 * classStanding;
        result += `  To Pass (75):            Already Passed! (Current: ${currentGrade.toFixed(2)})\n`;
    } else if (requiredForPassing > 100) {
        result += '  To Pass (75):            IMPOSSIBLE (even with 100)\n';
    } else {
        result += `  To Pass (75):            ${requiredForPassing.toFixed(2)}\n`;
    }
    
    // For Excellent Grade
    if (requiredForExcellent <= 0) {
        const currentGrade = 0.30 * 0 + 0.70 * classStanding;
        result += `  For Excellent (100):     Already Excellent! (Current: ${currentGrade.toFixed(2)})\n\n`;
    } else if (requiredForExcellent > 100) {
        const maxPrelimGrade = 0.30 * 100 + 0.70 * classStanding;
        result += `  For Excellent (100):     IMPOSSIBLE (Max: ${maxPrelimGrade.toFixed(2)} with 100)\n\n`;
    } else {
        result += `  For Excellent (100):     ${requiredForExcellent.toFixed(2)}\n\n`;
    }
    
    result += 'REMARKS:\n';
    result += '-------------------------------------------------------------------\n';
    
    // Evaluation remarks
    if (requiredForPassing <= 0) {
        result += '  * Congratulations! You have already PASSED the Prelim period!\n';
        result += '    Your current class standing guarantees a passing grade of 75+.\n';
    } else if (requiredForPassing > 100) {
        result += '  X Unfortunately, it is mathematically IMPOSSIBLE to pass.\n';
        result += '    Even with a perfect score of 100 on the Prelim Exam,\n';
        result += '    you cannot reach the passing grade of 75.\n';
        const maxPrelimGrade = 0.30 * 100 + 0.70 * classStanding;
        result += `    Your maximum possible Prelim Grade: ${maxPrelimGrade.toFixed(2)}\n`;
    } else {
        result += `  - You need ${requiredForPassing.toFixed(2)}% on the Prelim Exam to achieve\n`;
        result += '    a passing Prelim Grade of 75.\n';
    }
    
    result += '\n';
    
    if (requiredForExcellent <= 0) {
        result += '  * Outstanding! You already have an EXCELLENT standing!\n';
        result += '    Your class standing ensures an excellent grade of 100.\n';
    } else if (requiredForExcellent > 100) {
        result += '  - An excellent Prelim Grade of 100 is not achievable.\n';
        const maxPrelimGrade = 0.30 * 100 + 0.70 * classStanding;
        result += `    Maximum possible Prelim Grade: ${maxPrelimGrade.toFixed(2)} (with 100 on exam)\n`;
    } else {
        result += `  - You need ${requiredForExcellent.toFixed(2)}% on the Prelim Exam to achieve\n`;
        result += '    an excellent Prelim Grade of 100.\n';
    }
    
    result += '\n===================================================================\n';
    
    // Display results
    document.getElementById('results').textContent = result;
}

function clearFields() {
    document.getElementById('attendance').value = '';
    document.getElementById('lab1').value = '';
    document.getElementById('lab2').value = '';
    document.getElementById('lab3').value = '';
    document.getElementById('results').textContent = 'Enter your grades and click CALCULATE to see your required exam scores.';
    hideError();
    document.getElementById('attendance').focus();
}

function showError(message) {
    const errorDiv = document.getElementById('errorMessage');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';
}

function hideError() {
    const errorDiv = document.getElementById('errorMessage');
    errorDiv.style.display = 'none';
}

// Allow Enter key to trigger calculation
document.addEventListener('DOMContentLoaded', function() {
    const inputs = document.querySelectorAll('input[type="number"]');
    inputs.forEach(input => {
        input.addEventListener('keypress', function(event) {
            if (event.key === 'Enter') {
                calculateGrades();
            }
        });
    });
});