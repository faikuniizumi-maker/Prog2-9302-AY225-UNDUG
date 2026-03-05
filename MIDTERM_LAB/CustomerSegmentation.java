import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSegmentation {

    // Segment thresholds (in thousands of units = total sales)
    private static final double PLATINUM_MIN = 100000;
    private static final double GOLD_MIN     = 50000;
    private static final double SILVER_MIN   = 10000;

    private List<VideoGame> games;

    // Segment buckets: publisher name -> total sales
    private Map<String, Double> publisherSales;

    private List<String> platinum;
    private List<String> gold;
    private List<String> silver;
    private List<String> bronze;

    public CustomerSegmentation(List<VideoGame> games) {
        this.games          = games;
        this.publisherSales = new HashMap<>();
        this.platinum       = new ArrayList<>();
        this.gold           = new ArrayList<>();
        this.silver         = new ArrayList<>();
        this.bronze         = new ArrayList<>();
    }

    public void analyze() {
        // Step 1: Aggregate total sales per publisher
        for (VideoGame g : games) {
            String publisher = g.getPublisher();
            double sales     = g.getTotalSales();
            if (!publisher.equals("N/A") && sales > 0) {
                if (publisherSales.containsKey(publisher)) {
                    publisherSales.put(publisher, publisherSales.get(publisher) + sales);
                } else {
                    publisherSales.put(publisher, sales);
                }
            }
        }

        // Step 2: Segment each publisher
        for (Map.Entry<String, Double> entry : publisherSales.entrySet()) {
            String publisher = entry.getKey();
            double sales     = entry.getValue();

            if (sales > PLATINUM_MIN) {
                platinum.add(publisher);
            } else if (sales >= GOLD_MIN) {
                gold.add(publisher);
            } else if (sales >= SILVER_MIN) {
                silver.add(publisher);
            } else {
                bronze.add(publisher);
            }
        }

        // Step 3: Sort each segment alphabetically
        Collections.sort(platinum);
        Collections.sort(gold);
        Collections.sort(silver);
        Collections.sort(bronze);
    }

    public void displayResults() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("  CUSTOMER SEGMENTATION RESULTS");
        System.out.println("  Based on: Publisher Total Sales (in thousands of units)");
        System.out.println("=".repeat(70));

        System.out.println();
        System.out.println("  Segmentation Criteria:");
        System.out.println("  Platinum  ->  > 100,000 total sales");
        System.out.println("  Gold      ->  50,000 - 100,000 total sales");
        System.out.println("  Silver    ->  10,000 - 49,999 total sales");
        System.out.println("  Bronze    ->  < 10,000 total sales");

        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("  SEGMENT SUMMARY");
        System.out.println("=".repeat(70));
        System.out.printf("  %-12s : %d publishers%n", "Platinum", platinum.size());
        System.out.printf("  %-12s : %d publishers%n", "Gold",     gold.size());
        System.out.printf("  %-12s : %d publishers%n", "Silver",   silver.size());
        System.out.printf("  %-12s : %d publishers%n", "Bronze",   bronze.size());
        System.out.println("  " + "-".repeat(35));
        System.out.printf("  %-12s : %d publishers%n", "TOTAL",
                          platinum.size() + gold.size() + silver.size() + bronze.size());

        printSegment("PLATINUM", platinum, "> 100,000");
        printSegment("GOLD",     gold,     "50,000 - 100,000");
        printSegment("SILVER",   silver,   "10,000 - 49,999");
        printSegment("BRONZE",   bronze,   "< 10,000");

        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("  END OF REPORT");
        System.out.println("=".repeat(70));
    }

    private void printSegment(String label, List<String> publishers, String range) {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("  [" + label + "] - Sales Range: " + range);
        System.out.println("  Total Publishers: " + publishers.size());
        System.out.println("=".repeat(70));

        if (publishers.isEmpty()) {
            System.out.println("  No publishers in this segment.");
            return;
        }

        int count = 1;
        for (String publisher : publishers) {
            double sales = publisherSales.get(publisher);
            System.out.printf("  %3d. %-40s  Sales: %,.2f%n",
                              count++, publisher, sales);
        }
    }
}