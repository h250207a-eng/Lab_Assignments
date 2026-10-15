/** Lab 6, Exercise 2: unrelated classes share a capability through an interface. */
public class FlyableDemo {
    interface Flyable {
        void fly();
    }

    static class Bird implements Flyable {
        @Override public void fly() { System.out.println("Bird flies using wings"); }
    }

    static class Aeroplane implements Flyable {
        @Override public void fly() { System.out.println("Aeroplane flies using engines and wings"); }
    }

    public static void main(String[] args) {
        // Both ultimately extend Object, but have no shared domain superclass.
        Flyable[] flyers = {new Bird(), new Aeroplane()};
        for (Flyable flyer : flyers) flyer.fly();
    }
}
