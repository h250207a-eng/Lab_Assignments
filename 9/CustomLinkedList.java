import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Lab 9, Exercise 4: a static Node and an inner Iterator support for-each. */
public class CustomLinkedList {
    static class LinkedList<T> implements Iterable<T> {
        // EXPLAIN: static nodes do not retain a reference to the enclosing list.
        private static class Node<T> {
            private final T value;
            private Node<T> next;
            Node(T value) { this.value = value; }
        }

        private Node<T> head;
        private Node<T> tail;
        private int modificationCount;

        public void add(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) head = node;
            else tail.next = node;
            tail = node;
            modificationCount++;
        }

        @Override
        public Iterator<T> iterator() {
            return new ListIterator();
        }

        // EXPLAIN: the non-static inner iterator can access this list's head.
        private class ListIterator implements Iterator<T> {
            private Node<T> current = head;
            private final int expectedModificationCount = modificationCount;

            @Override
            public boolean hasNext() {
                checkModification();
                return current != null;
            }

            @Override
            public T next() {
                checkModification();
                if (current == null) throw new NoSuchElementException("Iterator exhausted");
                T value = current.value;
                current = current.next;
                return value;
            }

            private void checkModification() {
                if (expectedModificationCount != modificationCount) {
                    throw new ConcurrentModificationException("List changed during iteration");
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<String> words = new LinkedList<>();
        words.add("First");
        words.add("Second");
        for (String word : words) System.out.println(word);
        Iterator<String> iterator = words.iterator();
        while (iterator.hasNext()) iterator.next();
        try {
            iterator.next();
        } catch (NoSuchElementException exception) {
            System.out.println("Exhausted iterator correctly rejects next().");
        }
    }
}
