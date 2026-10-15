import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Lab 10, Exercise 1: save five user-entered lines, then read them back. */
public class FileLines {
    public static void main(String[] args) {
        Path file = Path.of(args.length > 0 ? args[0] : "exercise1.txt");
        try (Scanner scanner = new Scanner(System.in)) {
            List<String> lines = new ArrayList<>();
            for (int number = 1; number <= 5; number++) {
                System.out.print("Line " + number + ": ");
                if (!scanner.hasNextLine()) {
                    System.out.println("Input ended; five lines are required. No file written.");
                    return;
                }
                lines.add(scanner.nextLine());
            }
            Files.write(file, lines, StandardCharsets.UTF_8);
            System.out.println("Read back from " + file + ":");
            try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
                String line;
                while ((line = reader.readLine()) != null) System.out.println(line);
            }
        } catch (IOException exception) {
            System.err.println("File operation failed: " + exception.getMessage());
        }
    }
}
