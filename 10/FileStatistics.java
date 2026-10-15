import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/** Lab 10, Exercise 3: count lines, whitespace-separated words and Unicode characters. */
public class FileStatistics {
    public static void main(String[] args) {
        Path file = Path.of(args.length > 0 ? args[0] : "sample.txt");
        try {
            String text = Files.readString(file, StandardCharsets.UTF_8);
            long lines = text.lines().count();
            long words = text.isBlank() ? 0 : text.strip().split("\\s+").length;
            // Count Unicode code points; CR and LF each count as characters.
            long characters = text.codePoints().count();
            System.out.println("File: " + file);
            System.out.println("Lines: " + lines);
            System.out.println("Words: " + words);
            System.out.println("Characters (including line endings): " + characters);
        } catch (IOException exception) {
            System.err.println("Cannot read file: " + exception.getMessage());
        }
    }
}
