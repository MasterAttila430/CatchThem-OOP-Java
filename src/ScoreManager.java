import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreManager {

    private static final String FILE_NAME = "highscore.txt";

    public static void saveHighScore(int score, int level) {
        int currentBest = loadHighScore();
        if (score > currentBest) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                writer.write(String.valueOf(score));
                writer.newLine();
                writer.write(String.valueOf(level));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int loadHighScore() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return 0;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            reader.close();

            return line != null ? Integer.parseInt(line) : 0;
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error: Cannot load HighScore file" + e.getMessage());
            return 0;
        }
    }

    public static String loadHighScoreString() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return "No reccords";

        try (Stream<String> stream = Files.lines(Paths.get(FILE_NAME))) {
            List<String> lines = stream.collect(Collectors.toList());

            if (!lines.isEmpty()) {
                String result = lines.get(0) + " point";
                if (lines.size() > 1) result += " (" + lines.get(1) + ". level)";
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Hiba";
    }
}