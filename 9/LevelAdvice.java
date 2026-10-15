/** Lab 9, Exercise 1: enum constants form a fixed set of valid levels. */
public class LevelAdvice {
    enum Level { LOW, MEDIUM, HIGH }

    static String advice(Level level) {
        return switch (level) {
            case LOW -> "Proceed normally";
            case MEDIUM -> "Be careful";
            case HIGH -> "Take immediate action";
        };
    }

    public static void main(String[] args) {
        for (Level level : Level.values()) {
            System.out.println(level + ": " + advice(level));
        }
    }
}
