package deque;

public interface MinPQ<Item> {
    void add(Item item);
    Item getSmallest();
    Item removeSmallest();
    int size();
}
