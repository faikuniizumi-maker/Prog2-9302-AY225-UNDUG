// VideoGame.js
// Data record class representing one row from the CSV dataset

class VideoGame {
    constructor(title, console_, genre, publisher, developer, criticScore, totalSales, releaseDate) {
        this.title       = title;
        this.console     = console_;
        this.genre       = genre;
        this.publisher   = publisher;
        this.developer   = developer;
        this.criticScore = criticScore;
        this.totalSales  = totalSales;
        this.releaseDate = releaseDate;
    }

    static fromCSVLine(line) {
        try {
            const fields = splitCSV(line);
            if (fields.length < 7) return null;

            return new VideoGame(
                clean(fields[0]),
                clean(fields[1]),
                clean(fields[2]),
                clean(fields[3]),
                clean(fields[4]),
                parseFloat(fields[5]) || 0,
                parseFloat(fields[6]) || 0,
                fields.length > 11 ? clean(fields[11]) : "N/A"
            );
        } catch (e) {
            return null;
        }
    }
}

function clean(s) {
    s = s.trim();
    if (s.startsWith('"') && s.endsWith('"'))
        s = s.slice(1, -1);
    return s === '' ? 'N/A' : s;
}

function splitCSV(line) {
    const tokens = [];
    let inQuotes = false;
    let current  = '';

    for (let i = 0; i < line.length; i++) {
        const c = line[i];
        if (c === '"') {
            inQuotes = !inQuotes;
        } else if (c === ',' && !inQuotes) {
            tokens.push(current);
            current = '';
        } else {
            current += c;
        }
    }
    tokens.push(current);
    return tokens;
}

module.exports = VideoGame;