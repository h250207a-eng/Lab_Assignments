import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/** Lab 10, Exercise 5: load name,roll,gpa CSV and write reports grouped by grade band. */
public class LearnerReports {
    record Learner(String name, int roll, double gpa) {
        Learner {
            if (name == null || name.isBlank() || roll <= 0 || !Double.isFinite(gpa) || gpa < 0 || gpa > 4) {
                throw new IllegalArgumentException("Expected name, positive roll and GPA from 0 to 4");
            }
        }

        String gradeBand() {
            // Assumption, since the exercise gives no cutoffs: A >= 3.5, B >= 3.0, C below 3.0.
            return gpa >= 3.5 ? "A" : gpa >= 3.0 ? "B" : "C";
        }
    }

    static List<Learner> loadLearners(Path file) throws IOException {
        List<Learner> learners = new ArrayList<>();
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
        for (int index = 0; index < lines.size(); index++) {
            String line = lines.get(index).strip();
            if (line.isEmpty() || index == 0 && line.equalsIgnoreCase("name,roll,gpa")) continue;
            // The manual uses simple unquoted CSV; commas inside names are not supported here.
            String[] parts = line.split(",", -1);
            try {
                if (parts.length != 3) throw new IllegalArgumentException("Expected three columns");
                learners.add(new Learner(parts[0].strip(), Integer.parseInt(parts[1].strip()),
                        Double.parseDouble(parts[2].strip())));
            } catch (IllegalArgumentException exception) {
                throw new IOException("Invalid CSV row " + (index + 1) + ": " + exception.getMessage(), exception);
            }
        }
        return learners;
    }

    public static void main(String[] args) {
        Path csv = Path.of(args.length > 0 ? args[0] : "learners.csv");
        Path outputDirectory = Path.of(args.length > 1 ? args[1] : ".");
        try {
            Map<String, List<Learner>> groups = loadLearners(csv).stream()
                    .collect(Collectors.groupingBy(Learner::gradeBand, TreeMap::new, Collectors.toList()));
            Files.createDirectories(outputDirectory);
            for (String band : List.of("A", "B", "C")) {
                List<String> report = new ArrayList<>();
                report.add("Grade band " + band);
                report.add("name,roll,gpa");
                List<Learner> learners = groups.getOrDefault(band, List.of());
                learners.stream().sorted(java.util.Comparator.comparing(Learner::name))
                        .map(learner -> learner.name() + "," + learner.roll() + "," + learner.gpa())
                        .forEach(report::add);
                Files.write(outputDirectory.resolve("report-" + band + ".txt"), report, StandardCharsets.UTF_8);
                System.out.println("Band " + band + ": " + learners.size() + " learners");
            }
        } catch (IOException exception) {
            System.err.println("Report generation failed: " + exception.getMessage());
        }
    }
}
