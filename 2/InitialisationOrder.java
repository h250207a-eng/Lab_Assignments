/** Lab 2, Exercise 4: static blocks run once; instance blocks run per object. */
public class InitialisationOrder {
    static class Initialisation {
        static {
            System.out.println("static block 1");
        }

        static {
            System.out.println("static block 2");
        }

        {
            System.out.println("instance block 1");
        }

        {
            System.out.println("instance block 2");
        }

        Initialisation() {
            System.out.println("constructor");
        }
    }

    public static void main(String[] args) {
        System.out.println("Prediction: static 1, static 2, instance 1, instance 2, constructor,");
        System.out.println("then instance 1, instance 2, constructor for the second object.");
        System.out.println("--- actual output ---");
        new Initialisation();
        new Initialisation();
    }
}
