/** Lab 6, Exercise 5: resolve conflicting interface default methods explicitly. */
public class DefaultMethodConflict {
    interface FirstGreeting {
        default void hello() { System.out.println("First greeting"); }
    }

    interface SecondGreeting {
        default void hello() { System.out.println("Second greeting"); }
    }

    static class Both implements FirstGreeting, SecondGreeting {
        @Override
        public void hello() {
            FirstGreeting.super.hello();
            SecondGreeting.super.hello();
            System.out.println("Both resolves the conflict");
        }
    }

    public static void main(String[] args) {
        new Both().hello();
        System.out.println("Unrelated interfaces provide the same default signature, so the class must override it.");
        System.out.println("InterfaceName.super.hello() explicitly selects a default implementation.");
    }
}
