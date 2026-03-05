// main.js
// Entry point - Programming 2 Machine Problem
// University of Perpetual Help System DALTA - Molino Campus
// BSIT - Game Development
// Dataset: Video Game Sales 2024 (Kaggle)

const readline          = require('readline');
const { promptForFile, loadGames } = require('./DataLoader');
const { analyze, displayResults }  = require('./CustomerSegmentation');

const rl = readline.createInterface({
    input:  process.stdin,
    output: process.stdout
});

function printBanner() {
    console.log();
    console.log('  +----------------------------------------------------------+');
    console.log('  |       PROGRAMMING 2 - MACHINE PROBLEM                   |');
    console.log('  |  University of Perpetual Help System DALTA              |');
    console.log('  |  Molino Campus  |  BSIT - Game Development              |');
    console.log('  |  Customer Segmentation - Video Game Sales 2024          |');
    console.log('  +----------------------------------------------------------+');
    console.log();
}

async function main() {
    printBanner();

    try {
        // STEP 1: Prompt user for file path and validate
        const filePath = await promptForFile(rl);

        // STEP 2: Load dataset into memory
        console.log('Loading dataset...');
        const games = loadGames(filePath);

        if (games.length === 0) {
            console.log('No valid records found in the file.');
            rl.close();
            return;
        }

        // STEP 3: Perform customer segmentation analytics
        console.log('Performing segmentation...');
        const segments = analyze(games);

        // STEP 4: Display formatted results
        displayResults(segments);

    } catch (e) {
        console.log('Error: ' + e.message);
    } finally {
        rl.close();
    }
}

main();