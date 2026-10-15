/** Lab 5, Exercise 4: field hiding differs from method overriding. */
public class FieldMethodDispatch {
    static class Parent {
        String label = "parent";
        String getLabel() { return label; }
    }

    static class Child extends Parent {
        String label = "child";

        @Override
        String getLabel() { return label; }
    }

    public static void main(String[] args) {
        Parent parent = new Child();
        System.out.println("Field through Parent: " + parent.label);
        System.out.println("Method through Parent: " + parent.getLabel());
        System.out.println("Fields follow the declared reference type (Parent).");
        System.out.println("Overridden methods follow the runtime object type (Child).");
    }
}
