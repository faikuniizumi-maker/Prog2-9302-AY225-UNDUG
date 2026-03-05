// DataLoader.js
// Handles file path validation and CSV loading

const fs       = require('fs');
const readline = require('readline');
const VideoGame = require('./VideoGame');

function promptForFile(rl) {
    return new Promise((resolve) => {
        function ask() {
            rl.question('Enter dataset file path: ', function(path) {
                // Remove surrounding quotes if any
                path = path.trim().replace(/^"|"$/g, '');

                if (fs.existsSync(path) && fs.statSync(path).isFile()) {
                    if (!path.toLowerCase().endsWith('.csv')) {
                        console.log('Invalid file path. Please try again.');
                        ask();
                        return;
                    }
                    console.log('File found. Processing...');
                    resolve(path);
                } else {
                    console.log('Invalid file path. Please try again.');
                    ask();
                }
            });
        }
        ask();
    });
}

function loadGames(filePath) {
    try {
        const content = fs.readFileSync(filePath, 'utf8');
        const lines   = content.split('\n');

        const games   = [];
        let skipped   = 0;

        // Skip header (first line)
        for (let i = 1; i < lines.length; i++) {
            const line = lines[i].trim();
            if (!line) continue;

            const game = VideoGame.fromCSVLine(line);
            if (game) {
                games.push(game);
            } else {
                skipped++;
            }
        }

        console.log('Records loaded : ' + games.length);
        if (skipped > 0)
            console.log('Rows skipped   : ' + skipped);

        return games;

    } catch (e) {
        throw new Error('Failed to read file: ' + e.message);
    }
}

module.exports = { promptForFile, loadGames };