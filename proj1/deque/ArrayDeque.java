package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T> {
    private T[] items;
    private int size;
    private int front;
    private int back;
    private static final int FACTOR = 2;
    private static final int STARTING_INDEX = 2;
    private int CAPACITY = 8;

    public ArrayDeque() {
        items = (T[]) new Object[CAPACITY];
        size = 0;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * FACTOR);
        }

        if (size == 0) {
            items[STARTING_INDEX] = item;
            front = STARTING_INDEX;
            back = STARTING_INDEX;
        } else {
            int newFront = (CAPACITY + front - 1) % CAPACITY;
            items[newFront] = item;
            front = newFront;
        }

        size = size + 1;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            int currFront = (front + i) % CAPACITY;
            newArray[i] = items[currFront];
        }

        front = 0;
        back = size-1;
        CAPACITY = capacity;
        items = newArray;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * FACTOR);
        }

        if (size == 0) {
            items[STARTING_INDEX] = item;
            front = STARTING_INDEX;
            back = STARTING_INDEX;
        } else {
            int newBack = (back + 1) % CAPACITY;
            items[newBack] = item;
            back = newBack;
        }

        size = size + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        System.out.println("************ Print Deque ************");
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                System.out.print(get(i) + "->");
            }
        }
        System.out.println(" ");
        System.out.println("************ End Print ************");
    }

    public T removeFirst() {
        T x = items[front];
        items[front] = null;
        front = (front + 1) % CAPACITY;
        size = size - 1;
        return x;
    }

    public T get(int i) {
        return items[(front + i) % CAPACITY];
    }

    public T removeLast() {
        T x = items[back];
        items[back] = null;
        back = (CAPACITY + back - 1) % CAPACITY;
        size = size - 1;
        return x;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int counter;

        public ArrayDequeIterator() {
            counter = 0;
        }

        @Override
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        public T next() {
            T returnItem = get(counter);
            counter += 1;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<?> castedO = (ArrayDeque<?>) o;
        if (castedO.size != this.size) {
            return false;
        }
        Iterator<?> iterator = iterator();
        Iterator<?> oIterator = castedO.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() != oIterator.next()) {
                return false;
            }
        }
        return true;
    }
}
