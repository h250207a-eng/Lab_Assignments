/** Lab 4, Exercise 4: supply arguments when the parent has no Animal(). */
public class AnimalConstructor {
    static class Animal {
        private final String name;

        Animal(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }

    /* This would fail to compile (kept as a comment so this solution runs):
       static class Dog extends Animal {
           Dog() { }  // implicit super(), but Animal() does not exist
       }
    */
    static class Dog extends Animal {
        Dog(String name) {
            super(name); // EXPLAIN: explicitly select the existing Animal(String).
        }
    }

    public static void main(String[] args) {
        System.out.println(new Dog("Bingo").getName());
        System.out.println("An empty Dog constructor implies super(), but Animal has only Animal(String).");
        System.out.println("Fix: call super(name) as the first constructor statement.");
    }
}
