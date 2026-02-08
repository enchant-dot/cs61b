package hashmap;

import java.util.LinkedList;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashSet;
/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }



    /* Instance Variables */
    private Collection<Node>[] buckets;
    private static final int default_capacity = 16;
    private static final double default_loadfactor = 0.75;

    // You should probably define some more!

    private int size;
    private double loadfactor;

    /** Constructors */
    public MyHashMap() {
        this(default_capacity, default_loadfactor);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, default_loadfactor);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.loadfactor = maxLoad;
        this.size = 0;
        this.buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    private int hash(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = (Collection<Node>[]) new Collection[tableSize];

        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    public void clear() {
        buckets = createTable(default_capacity);
        size = 0;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        int thiskey = hash(key);
        for (Node node : buckets[thiskey]) {
            if (node.key.equals(key))  return node.value;
        }

        return null;
    }

    public int size() {
        return this.size;
    }

    public void put(K key, V value) {
        int index = hash(key);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        size++;
        buckets[index].add(new Node(key, value));

        if ((double) this.size / this.buckets.length >= this.loadfactor  ) {
            resize(this.buckets.length * 2);
        }
    }

    public void resize(int newCapacity) {
        Collection<Node>[] newTable = createTable(newCapacity);

        for (int i = 0; i < buckets.length; i++) {
            for (Node node : buckets[i]) {
                int newIndex = Math.floorMod(node.key.hashCode() , newCapacity);
                newTable[newIndex].add(node);
            }
        }
        this.buckets = newTable;

    }

    public Set<K> keySet() {
        HashSet<K> Set = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            for (Node node : buckets[i]) {
                Set.add(node.key);
            }
        }
        return Set;
    }

    public V remove(K key) {
        int hashkey = hash(key);
        for (Node node: buckets[hashkey]) {
            if (node.key.equals(key)) {
                V Value = node.value;
                this.buckets[hashkey].remove(node);
                size--;
                return Value;
            }
        }
        return null;
    }

    public V remove(K key, V value) {
        return remove(key);
    }

    public Iterator<K> iterator() {
        return keySet().iterator();
    }


}
