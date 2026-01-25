package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 4;
        this.nextLast = 5;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int firstIndex = (nextFirst + 1) % this.items.length;

        if (firstIndex + this.size <= this.items.length) {
            System.arraycopy(this.items, firstIndex, temp, 0, this.size);
        } else {
            int lenPart1 = this.items.length - firstIndex;
            System.arraycopy(this.items, firstIndex, temp, 0, lenPart1);
            System.arraycopy(this.items, 0, temp, lenPart1, this.size - lenPart1);
        }
        this.items = temp;
        this.nextFirst = this.items.length - 1;
        this.nextLast = this.size;
    }

    public void addFirst(T item) {
        if (this.size == this.items.length) {
            resize(this.items.length * 2);
        }
        this.items[nextFirst] = item;
        size++;

        nextFirst = (nextFirst - 1 + this.items.length) % this.items.length;
    }

    public void addLast(T item) {
        if (this.size == this.items.length) {
            resize(this.items.length * 2);
        }
        this.items[nextLast] = item;
        size++;
        nextLast = (nextLast + 1) % this.items.length;
    }

    public void printDeque() {
        for (int i = 0; i < this.size; ++i) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        int FirstIndex = (this.nextFirst + 1) % this.items.length;
        T item = this.items[FirstIndex];
        items[FirstIndex] = null;
        size--;
        nextFirst = FirstIndex;
        if (this.items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        int LastIndex = (this.nextLast - 1 + this.items.length) % this.items.length;
        T item = this.items[LastIndex];
        items[LastIndex] = null;
        size--;
        nextLast = LastIndex;
        if (this.items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (nextFirst + 1 + index) % this.items.length;
        return items[actualIndex];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (this == other) {
            return true;
        } else if (!(other instanceof ArrayDeque)) {

            return false;
        } else {
            ArrayDeque<?> o = (ArrayDeque) other;
            if (o.size != this.size) {
                return false;
            } else {
                for (int i = 0; i < this.size; i++) {
                    T thisitem = (T) this.get(i);
                    Object otheritem = (T) o.get(i);
                    if (otheritem == null) {
                        if (thisitem != null) {
                            return false;
                        }
                    } else if (!thisitem.equals(otheritem)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int start = 0;

        public ArrayDequeIterator() {
        }

        public boolean hasNext() {
            return this.start < ArrayDeque.this.size;
        }

        public T next() {

            T returnItem = ArrayDeque.this.get(this.start);
            ++this.start;
            return returnItem;
        }
    }

}