public class VideoGame {

    private String title;
    private String console;
    private String genre;
    private String publisher;
    private String developer;
    private double criticScore;
    private double totalSales;
    private String releaseDate;

    public VideoGame(String title, String console, String genre,
                     String publisher, String developer,
                     double criticScore, double totalSales,
                     String releaseDate) {
        this.title       = title;
        this.console     = console;
        this.genre       = genre;
        this.publisher   = publisher;
        this.developer   = developer;
        this.criticScore = criticScore;
        this.totalSales  = totalSales;
        this.releaseDate = releaseDate;
    }

    public static VideoGame fromCSVLine(String line) {
        try {
            String[] f = splitCSV(line);
            if (f.length < 7) return null;

            String title       = clean(f[0]);
            String console     = clean(f[1]);
            String genre       = clean(f[2]);
            String publisher   = clean(f[3]);
            String developer   = clean(f[4]);
            double criticScore = parseDouble(f[5]);
            double totalSales  = parseDouble(f[6]);
            String releaseDate = f.length > 11 ? clean(f[11]) : "N/A";

            return new VideoGame(title, console, genre, publisher,
                                 developer, criticScore, totalSales, releaseDate);
        } catch (Exception e) {
            return null;
        }
    }

    private static String clean(String s) {
        s = s.trim();
        if (s.startsWith("\"") && s.endsWith("\""))
            s = s.substring(1, s.length() - 1);
        return s.isEmpty() ? "N/A" : s;
    }

    private static double parseDouble(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static String[] splitCSV(String line) {
        java.util.List<String> tokens = new java.util.ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString());
        return tokens.toArray(new String[0]);
    }

    public String getTitle()       { return title; }
    public String getConsole()     { return console; }
    public String getGenre()       { return genre; }
    public String getPublisher()   { return publisher; }
    public String getDeveloper()   { return developer; }
    public double getCriticScore() { return criticScore; }
    public double getTotalSales()  { return totalSales; }
    public String getReleaseDate() { return releaseDate; }
}    

