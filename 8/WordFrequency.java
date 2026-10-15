import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Lab 8, Exercise 4: read a paragraph and rank case-insensitive word frequencies. */
public class WordFrequency {
    static Map<String, Integer> countWords(String paragraph) {
        Map<String, Integer> frequencies = new HashMap<>();
        // Words consist of letters/digits and may contain an internal apostrophe.
        Matcher matcher = Pattern.compile("[\\p{L}\\p{N}]+(?:'[\\p{L}\\p{N}]+)*")
                .matcher(paragraph.toLowerCase(Locale.ROOT));
        while (matcher.find()) frequencies.merge(matcher.group(), 1, Integer::sum);
        return frequencies;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter a paragraph on one line:");
            if (!scanner.hasNextLine()) {
                System.out.println("No paragraph supplied.");
                return;
            }
            Map<String, Integer> frequencies = countWords(scanner.nextLine());
            if (frequencies.isEmpty()) System.out.println("No words found.");
            frequencies.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                            .thenComparing(Map.Entry.comparingByKey()))
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        }
    }
}
