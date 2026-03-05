import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLoader {

    public static File promptForFile(Scanner scanner) {
        File file;
        while (true) {
            System.out.print("Enter dataset file path: ");
            String path = scanner.nextLine().trim();

            if (path.startsWith("\"") && path.endsWith("\""))
                path = path.substring(1, path.length() - 1);

            file = new File(path);

            if (file.exists() && file.isFile()) {
                if (!path.toLowerCase().endsWith(".csv")) {
                    System.out.println("Invalid file path. Please try again.");
                    continue;
                }
                System.out.println("File found. Processing...");
                break;
            } else {
                System.out.println("Invalid file path. Please try again.");
            }
        }
        return file;
    }

    public static List<VideoGame> loadGames(File file) throws IOException {
        List<VideoGame> games = new ArrayList<>();

        BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), "UTF-8"));

        String line = br.readLine(); // skip header
        int skipped = 0;

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            VideoGame g = VideoGame.fromCSVLine(line);
            if (g != null) {
                games.add(g);
            } else {
                skipped++;
            }
        }
        br.close();

        System.out.println("Records loaded : " + games.size());
        if (skipped > 0)
            System.out.println("Rows skipped   : " + skipped);

        return games;
    }
}