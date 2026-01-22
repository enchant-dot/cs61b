package deque;

public class AList<Item> implements List<Item>{
    private Item[] items;
    private int size;

    public AList() {
        items = (Item[])new Object[100];
        size = 0;
    }

    @Override
    public void insert(Item x, int position) {
        Item[] newItems = (Item[]) new Object[items.length+1];
        System.arraycopy(items, 0, newItems, 0, position);
        newItems[position] = x;
        System.arraycopy(items, position, newItems, position+1, items.length-position);
        items = newItems;
    }

    @Override
    public void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    @Override
    public void addFirst(Item x) {
        insert(x, 0);
    }

    @Override
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size*2);
        }
        insert(x, items.length);
    }

    @Override
    public Item getFirst() {
        return items[0];
    }

    @Override
    public Item getLast() {
        return items[items.length - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Item removeLast() {
        Item a = getLast();
        items[items.length-1] = null;
        return a;
    }

}
