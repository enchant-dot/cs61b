package deque;

import afu.org.checkerframework.checker.oigj.qual.O;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    private static class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<T>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    @Override
    public void addFirst(T item) {
        size += 1;
        sentinel.next.prev = new Node<T> (item, sentinel, sentinel.next);
        sentinel.next = sentinel.next.prev;

    }
    @Override
    public void addLast(T item) {
        size += 1;
        sentinel.prev.next = new Node<T> (item, sentinel.prev, sentinel);
        sentinel.prev =  sentinel.prev.next;
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
        Node<T> temp;
        temp = sentinel.next;
        while (temp != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item =  sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return item;
    }
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item =  sentinel.prev.item;
        sentinel.prev.prev.next =  sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return item;
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> temp = sentinel.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursivehelper(sentinel.next, index);
    }
    private T getRecursivehelper(Node<T> currentNode, int remainingIndex) {
        if (remainingIndex == 0) {
            return currentNode.item;
        }
        return getRecursivehelper(currentNode.next, remainingIndex - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node<T> current;

        public LinkedListDequeIterator() {
            current = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }

    }
//    public boolean contains(T x) {
//        for (int i = 0; i < size; i++) {
//            T item = get(i);
//            if (x == null) {
//                if (item == null) {
//                    return true;
//                }
//            } else {
//                if (x.equals(item)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof LinkedListDeque<?>)) {
            return false;
        }
        LinkedListDeque<?> o = (LinkedListDeque<?>) other;
        if (o.size() != this.size()) {
            return false;
        }

        Iterator<T> thisIter = this.iterator();
        Iterator<?> otherIter = o.iterator();
        while (thisIter.hasNext() && otherIter.hasNext()) {
            T thisItem = thisIter.next();
            Object otherItem = otherIter.next();
            if (thisItem == null) {
                if (otherItem == null) {
                    return false;
                }
            } else if((!thisItem.equals(otherItem))) {
                return false;
            }
        }
        return true;
    }
}

