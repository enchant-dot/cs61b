package deque;

public interface List<Item> {
    public void insert(Item x, int position);
    public void addFirst(Item x);
    public void addLast(Item x);
    public Item getFirst();
    public Item getLast();
    public int size();
    public void resize(int capacity);
    public Item removeLast();
}