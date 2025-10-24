package deque;

import org.apache.commons.collections.iterators.ArrayListIterator;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{

    private T[] items;
    private int size;
    private int capacity;


    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        size = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void resize(int newcapacity) {
        T[] a = (T[]) new Object[newcapacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
        capacity = newcapacity;
    }
    public void addFirst(T x) {
        if (size == capacity) {
            if (capacity == 0) {
                resize(items.length + 1);
            } else {
                resize(items.length * 2);
            }
        }
        if (isEmpty()) {
            items[size++] = x;
        }
        else {
            for (int i = size; i > 0; i--) {
                items[i] = items[i - 1];
            }
            items[0] = x;
            size++;
        }
    }
    public void addLast(T x) {
        if (size == capacity) {
            if (capacity == 0) {
                resize(items.length + 1);
            } else {
                resize(items.length * 2);
            }
        }
        items[size++] = x;
    }
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            items[i] = items[i + 1];
        }
        size--;
        return items[0];
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T temp = items[size - 1];
        items[size - 1] = null;
        size--;
        return temp;
    }
    public T get(int index) {
        if (index >= size  || index < 0) {
            return null;
        }
        return items[index];
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int start;
        public ArrayDequeIterator() {
            start = 0;
        }
        @Override
        public boolean hasNext() {
            return start < size;
        }
        @Override
        public T next() {
            T returnItem = items[start];
            start++;
            return returnItem;
        }
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof ArrayDeque)) return false;
        ArrayDeque<?> o = (ArrayDeque<?>) other;
        if (o.size != this.size) return false;
        for (int i = 0; i < size; i++) {
            T thisItem = this.get(i);
            Object otherItem = o.get(i);
            if (thisItem == null) {
                if (otherItem != null) return false;
            } else if (!(thisItem.equals(otherItem))) return false;
        }
        return true;
    }
}
