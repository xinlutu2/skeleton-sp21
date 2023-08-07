package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        assertTrue(arrayDeque.isEmpty());

        arrayDeque.addFirst("front");
        assertEquals(1, arrayDeque.size());
        assertFalse(arrayDeque.isEmpty());
        arrayDeque.addLast("middle");
        assertEquals(2, arrayDeque.size());
        arrayDeque.printDeque();
    }

    @Test
    public void getFirstLastGetWithoutResizeTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst("6");
        arrayDeque.addFirst("5");
        arrayDeque.addFirst("4");
        arrayDeque.addFirst("3");
        arrayDeque.addFirst("2");
        arrayDeque.addFirst("1");
        arrayDeque.addLast("7");

        assertEquals(7, arrayDeque.size());
        assertEquals("3", arrayDeque.get(2));
        arrayDeque.printDeque();
    }

    @Test
    public void getFirstLastGetWithResizeTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst("6");
        arrayDeque.addFirst("5");
        arrayDeque.addFirst("4");
        arrayDeque.addFirst("3");
        arrayDeque.addFirst("2");
        arrayDeque.addFirst("1");
        arrayDeque.addLast("7");
        arrayDeque.addLast("8");
        arrayDeque.addLast("9");

        assertEquals(9, arrayDeque.size());
        assertEquals("9", arrayDeque.get(8));
        arrayDeque.printDeque();
    }

    @Test
    public void removeFirstLastTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst("6");
        arrayDeque.addFirst("5");
        arrayDeque.addFirst("4");
        arrayDeque.addFirst("3");
        arrayDeque.addFirst("2");
        arrayDeque.addFirst("1");
        arrayDeque.addLast("7");
        arrayDeque.addLast("8");
        arrayDeque.addLast("9");

        assertEquals("1", arrayDeque.removeFirst());
        assertEquals("9", arrayDeque.removeLast());
        assertEquals(7, arrayDeque.size());
        arrayDeque.printDeque();
    }

    @Test
    public void equalsTest() {
        ArrayDeque<String> deque1 = new ArrayDeque<>();
        ArrayDeque<String> deque2 = new ArrayDeque<>();
        ArrayDeque<Integer> deque3 = new ArrayDeque<>();

        deque1.addFirst("front");
        deque1.addLast("end");
        deque2.addFirst("front");
        deque2.addLast("end");
        deque3.addFirst(1);
        deque3.addLast(3);

        assertTrue(deque1.equals(deque2));
        assertFalse(deque1.equals(deque3));
    }
}
