package deque;
import java.util.Iterator;

//get must use iteration,not recursion

//add and remove operations must not involve looping or recursion.A single such operation
//must take "constant time", execution time should not depend on the size of the deque.
//This means that you cannot use loop that go over all/most elements of the deque.

//Iterating over the LinkedListDeque using a for-each loop should take time proportional to the number of items
public class LinkedListDeque<T> implements Deque<T>{
    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        size++;
        this.sentinel.next.prev = new Node(item, this.sentinel, this.sentinel.next);
        this.sentinel.next = this.sentinel.next.prev;
    }

    @Override
    public void addLast(T item) {
        size++;
        this.sentinel.prev.next = new Node(item, this.sentinel.prev, this.sentinel);
        this.sentinel.prev = this.sentinel.prev.next;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for(Node<T> temp = this.sentinel.next; temp != this.sentinel; temp = temp.next) {
            System.out.print(String.valueOf(temp.item) + " ");
        }

        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T sb = this.sentinel.next.item;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        size--;
        return sb;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T sb = this.sentinel.prev.item;
        this.sentinel.prev.prev.next = this.sentinel;
        this.sentinel.prev = this.sentinel.prev.prev;
        size--;
        return sb;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            Node<T> temp = this.sentinel.next;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.item;
        } else {
            return null;
        }
    }

    public T getRecursive(int index) {
        return (T) (index >=0 && index < size ? this.getRecursivehelper(this.sentinel.next, index) : null);
    }

    private T getRecursivehelper(Node<T> currentNode, int remainingIndex) {
        return (T) (remainingIndex == 0 ? currentNode.item : getRecursivehelper(currentNode.next, remainingIndex - 1));
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedDequeIterator();
    }

    private class LinkedDequeIterator implements Iterator<T> {
        private Node<T> current;

        public LinkedDequeIterator() {
            this.current = sentinel.next;
        }

        public boolean hasNext() {
            return current != sentinel;
        }

        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null) {
            return false;
        } else if ( !(other instanceof LinkedListDeque)) {
            return false;
        } else {
            LinkedListDeque<?> otherDeque = (LinkedListDeque)other;
            if (otherDeque.size != this.size()) {
                return false;
            } else {
                Iterator<?> otherIterator = otherDeque.iterator();
                Iterator<T> thisIterator = this.iterator();
                while (otherIterator.hasNext() && thisIterator.hasNext()) {
                    T thisItem = thisIterator.next();
                    Object otherItem = otherIterator.next();
                    if (thisItem == null ) {
                        if (otherItem == null) {
                            continue;
                        }
                        return false;
                    } else if (!thisItem.equals(otherItem)) {
                        return false;
                    }
                }
                return true;
            }

        }

    }

    public static class Node<T> {
        T item;
        Node <T> prev;
        Node <T> next;
        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }


}
