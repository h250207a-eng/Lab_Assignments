import java.util.ArrayList;
import java.util.EmptyStackException;

/** Lab 4, Exercise 5: composition restricts the public API to stack operations. */
public class ComposedStack {
    static class Stack<T> {
        private final ArrayList<T> items = new ArrayList<>();

        public void push(T item) {
            items.add(item);
        }

        public T pop() {
            if (isEmpty()) throw new EmptyStackException();
            return items.remove(items.size() - 1);
        }

        public T peek() {
            if (isEmpty()) throw new EmptyStackException();
            return items.get(items.size() - 1);
        }

        public boolean isEmpty() {
            return items.isEmpty();
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        System.out.println("Pop: " + stack.pop());
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Empty? " + stack.isEmpty());
        System.out.println("Extending ArrayList exposes add(index, value), set and remove(index),");
        System.out.println("allowing changes in the middle and breaking last-in, first-out discipline.");
    }
}
