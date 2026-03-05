import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        printBanner();

        Scanner scanner = new Scanner(System.in);

        try {
            // STEP 1: Prompt user for file path and validate
            File file = DataLoader.promptForFile(scanner);

            // STEP 2: Load dataset into memory
            System.out.println("Loading dataset...");
            List<VideoGame> games = DataLoader.loadGames(file);

            if (games.isEmpty()) {
                System.out.println("No valid records found in the file.");
                return;
            }

            // STEP 3: Perform customer segmentation analytics
            System.out.println("Performing segmentation...");
            CustomerSegmentation segmentation = new CustomerSegmentation(games);
            segmentation.analyze();

            // STEP 4: Display formatted results
            segmentation.displayResults();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void printBanner() {
        System.out.println();
        System.out.println("  +----------------------------------------------------------+");
        System.out.println("  |       PROGRAMMING 2 - MACHINE PROBLEM                   |");
        System.out.println("  |  University of Perpetual Help System DALTA              |");
        System.out.println("  |  Molino Campus  |  BSIT - Game Development              |");
        System.out.println("  |  Customer Segmentation - Video Game Sales 2024          |");
        System.out.println("  +----------------------------------------------------------+");
        System.out.println();
    }
}