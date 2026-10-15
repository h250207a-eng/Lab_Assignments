import java.util.ArrayList;
import java.util.EmptyStackException;

/** Lab 8, Exercise 3: a type-safe last-in, first-out collection. */
public class GenericStack {
    static class Stack<T> {
        private final ArrayList<T> items = new ArrayList<>();

        public void push(T item) { items.add(item); }

        public T pop() {
            if (isEmpty()) throw new EmptyStackException();
            return items.remove(items.size() - 1);
        }

        public T peek() {
            if (isEmpty()) throw new EmptyStackException();
            return items.get(items.size() - 1);
        }

        public boolean isEmpty() { return items.isEmpty(); }
    }

    public static void main(String[] args) {
        Stack<String> words = new Stack<>();
        words.push("First");
        words.push("Second");
        System.out.println("Peek: " + words.peek());
        System.out.println("Pop: " + words.pop());
        System.out.println("Pop: " + words.pop());
        System.out.println("Empty? " + words.isEmpty());
        try {
            words.pop();
        } catch (EmptyStackException exception) {
            System.out.println("Cannot pop an empty stack.");
        }
        Stack<Integer> numbers = new Stack<>();
        numbers.push(42);
        System.out.println("Integer stack: " + numbers.pop());
    }
}
