package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node nextN, Node prevN) {
            item = i;
            next = nextN;
            prev = prevN;
        }
    }

    // The first item (if it exists) is at sentinel.next.
    // The last item (if it exists) is at sentinel.prev
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    // circular sentinel topology
    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new Node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            // previous first node is sentinel.next
            sentinel.next = new Node(item, sentinel.next, sentinel);
            sentinel.next.next.prev = sentinel.next;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == 0) {
            sentinel.prev = new Node(item, sentinel, sentinel);
            sentinel.next = sentinel.prev;
        } else {
            // previous last node is sentinel.prev
            sentinel.prev = new Node(item, sentinel, sentinel.prev);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        System.out.println("************ Print Deque ************");
        Node pointer = sentinel;
        if (size != 0) {
            System.out.println("sentinel next -> " + sentinel.next.item);
            System.out.println("sentinel prev -> " + sentinel.prev.item);

            while (pointer.next != sentinel) {
                pointer = pointer.next;
                System.out.println(pointer.item + " next -> " + pointer.next);
                System.out.println(pointer.item + " prev -> " + pointer.prev);
            }
        }
        System.out.println("************ End Print ************");
    }

    public T removeFirst() {
        if (sentinel.next == null) {
            return null;
        }
        T currentFirstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;

        return currentFirstItem;
    }

    public T removeLast() {
        if (sentinel.prev == null) {
            return null;
        }
        T currentLastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;

        return currentLastItem;
    }

    public T get(int index) {
        if (size == 0) {
            return null;
        }

        Node pointer = sentinel;
        int count = 0;
        while (pointer.next != sentinel) {
            pointer = pointer.next;
            if (count == index) {
                return pointer.item;
            }
            count++;
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node pointer = sentinel;

        @Override
        public boolean hasNext() {
            return size != 0 && pointer.next != sentinel;
        }

        @Override
        public T next() {
            if (hasNext()) {
                pointer = pointer.next;
                return pointer.item;
            }
            return  null;
        }
    }

    private T getRecursiveHelper(int index, int count, Node pointer) {
        if (index == count) {
            return pointer.item;
        }
        return getRecursiveHelper(index, count+1, pointer.next);
    }

    public T getRecursive(int index) {
        if (size == 0) {
            return null;
        }
        if (index >= size || index < 0) {
            return null;
        }

        int count = 0;
        Node pointer = sentinel.next;
        return getRecursiveHelper(index, count, pointer);
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<?> castedO = (LinkedListDeque<?>) o;
        if (castedO.size != size) {
            return false;
        }

        Iterator<?> iterator = iterator();
        Iterator<?> oIterator = castedO.iterator();
        while (iterator.hasNext() && oIterator.hasNext()) {
            if (iterator.next() != oIterator.next()) {
                return false;
            }
        }
        return true;
    }
}
