package deque;

public class SLList<Item> implements List<Item> {

    private class Node {
        public Item items;
        public Node next;

        public Node(Item x, Node n) {
            items = x;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public SLList() {
        sentinel = new Node(null, null);
        size = 0;
    }

    public SLList(Item x) {
        sentinel = new Node(null, null);
        sentinel.next = new Node(x, null);
        size = 1;
    }

    @Override
    public void insert(Item x, int position) {
        Node p = sentinel;
        while (p.next != null && position > 1) {
            p = p.next;
            position--;
        }
        Node newNode = new Node(x, p.next);
        p.next = newNode;
    }

    @Override
    public void addFirst(Item x) {
        size++;
        sentinel.next = new Node(x, sentinel.next);
    }

    @Override
    public void addLast(Item x) {
        Node p = sentinel;
        size++;
        while (p.next != null) {
            p = p.next;
        }

        p.next = new Node(x, null);
    }

    @Override
    public Item getFirst() {
        return sentinel.next.items;
    }

    public Node getLastNode() {
        Node p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    @Override
    public Item getLast() {
        Node back = getLastNode();
        return back.items;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void resize(int capacity) {
        return;
    }

    @Override
    public Item removeLast() {
        Node p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        return p.items;
    }

}
