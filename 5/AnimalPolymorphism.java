/** Lab 5, Exercise 2: overridden methods dispatch on the actual object type. */
public class AnimalPolymorphism {
    static class Animal {
        void sound() { System.out.println("Animal sound"); }
    }

    static class Cat extends Animal {
        @Override
        void sound() { System.out.println("Meow"); }
    }

    static class Cow extends Animal {
        @Override
        void sound() { System.out.println("Moo"); }
    }

    static class Duck extends Animal {
        @Override
        void sound() { System.out.println("Quack"); }
    }

    public static void main(String[] args) {
        Animal[] animals = {new Cat(), new Cow(), new Duck()};
        for (Animal animal : animals) {
            // EXPLAIN: one call selects a different implementation at run time.
            animal.sound();
        }
    }
}
