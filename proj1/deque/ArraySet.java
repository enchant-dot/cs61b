package deque;

import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    public boolean contain(T x) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("can't add null");
        }

        if (contain(x)) {
            return;
        }
        items[size++] = x;
    }

    public int size() {
        return size;
    }



    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos++;
            return returnItem;
        }

    }

    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder();
        returnSB.append("{");
        for (int i = 0; i < size; i++) {
            returnSB.append(items[i].toString());
            returnSB.append(",");
        }
        returnSB.append(items[size-1]);
        returnSB.append("}");
        return returnSB.toString();

    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        ArraySet<T> o = (ArraySet<T>) other;

        if (o.size() != this.size()) {
            return false;
        }
        for (T item : this) {
            if (! o.contain(item)) {
                return false;
            }
        }
        return true;
     }

     public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(1);
        aset.add(23);
        aset.add(42);

        for (int i :aset) {
            System.out.println(i);
        }

        System.out.println(aset);

        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(aset.equals(aset2));

     }
}
