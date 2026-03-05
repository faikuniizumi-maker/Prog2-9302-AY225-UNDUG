// CustomerSegmentation.js
// Segments publishers by total sales into Platinum, Gold, Silver, Bronze

const PLATINUM_MIN = 100000;
const GOLD_MIN     = 50000;
const SILVER_MIN   = 10000;

function analyze(games) {
    // Step 1: Aggregate total sales per publisher
    const publisherSales = {};

    for (const game of games) {
        const publisher = game.publisher;
        const sales     = game.totalSales;

        if (publisher !== 'N/A' && sales > 0) {
            if (publisherSales[publisher]) {
                publisherSales[publisher] += sales;
            } else {
                publisherSales[publisher] = sales;
            }
        }
    }

    // Step 2: Segment each publisher
    const platinum = [];
    const gold     = [];
    const silver   = [];
    const bronze   = [];

    for (const publisher in publisherSales) {
        const sales = publisherSales[publisher];

        if (sales > PLATINUM_MIN) {
            platinum.push({ name: publisher, sales });
        } else if (sales >= GOLD_MIN) {
            gold.push({ name: publisher, sales });
        } else if (sales >= SILVER_MIN) {
            silver.push({ name: publisher, sales });
        } else {
            bronze.push({ name: publisher, sales });
        }
    }

    // Step 3: Sort each segment by sales descending
    const sortBySales = (a, b) => b.sales - a.sales;
    platinum.sort(sortBySales);
    gold.sort(sortBySales);
    silver.sort(sortBySales);
    bronze.sort(sortBySales);

    return { platinum, gold, silver, bronze };
}

function displayResults(segments) {
    const { platinum, gold, silver, bronze } = segments;

    console.log();
    console.log('='.repeat(70));
    console.log('  CUSTOMER SEGMENTATION RESULTS');
    console.log('  Based on: Publisher Total Sales (in thousands of units)');
    console.log('='.repeat(70));

    console.log();
    console.log('  Segmentation Criteria:');
    console.log('  Platinum  ->  > 100,000 total sales');
    console.log('  Gold      ->  50,000 - 100,000 total sales');
    console.log('  Silver    ->  10,000 - 49,999 total sales');
    console.log('  Bronze    ->  < 10,000 total sales');

    console.log();
    console.log('='.repeat(70));
    console.log('  SEGMENT SUMMARY');
    console.log('='.repeat(70));
    console.log('  Platinum  : ' + platinum.length + ' publishers');
    console.log('  Gold      : ' + gold.length     + ' publishers');
    console.log('  Silver    : ' + silver.length   + ' publishers');
    console.log('  Bronze    : ' + bronze.length   + ' publishers');
    console.log('  ' + '-'.repeat(35));
    console.log('  TOTAL     : ' + (platinum.length + gold.length + silver.length + bronze.length) + ' publishers');

    printSegment('PLATINUM', platinum, '> 100,000');
    printSegment('GOLD',     gold,     '50,000 - 100,000');
    printSegment('SILVER',   silver,   '10,000 - 49,999');
    printSegment('BRONZE',   bronze,   '< 10,000');

    console.log();
    console.log('='.repeat(70));
    console.log('  END OF REPORT');
    console.log('='.repeat(70));
}

function printSegment(label, publishers, range) {
    console.log();
    console.log('='.repeat(70));
    console.log('  [' + label + '] - Sales Range: ' + range);
    console.log('  Total Publishers: ' + publishers.length);
    console.log('='.repeat(70));

    if (publishers.length === 0) {
        console.log('  No publishers in this segment.');
        return;
    }

    publishers.forEach((p, i) => {
        const num   = String(i + 1).padStart(3, ' ');
        const name  = p.name.padEnd(40, ' ');
        const sales = p.sales.toFixed(2);
        console.log('  ' + num + '. ' + name + '  Sales: ' + sales);
    });
}

module.exports = { analyze, displayResults };