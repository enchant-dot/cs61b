package bstmap;
import java.util.Set;
import java.util.Iterator;
import java.util.Stack;


public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private BSTNode root;
    private int size;

    private class BSTNode {
        private K Key;
        private V Value;
        private BSTNode left,right;
        public BSTNode(K key, V value) {
            this.Key = key;
            this.Value = value;
        }

    }

    public BSTMap() {
        size = 0;
        root = null;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean containsKey(K key) {
        return findHelper(root, key) != null;
    }

    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }

    private BSTNode putHelper(BSTNode root, K key, V value) {
        if (root == null) {
            size++;
            return new BSTNode(key, value);
        } else if (root.Key.compareTo(key) < 0) {
            root.right = putHelper(root.right, key, value);
        } else if (root.Key.compareTo(key) > 0) {
            root.left = putHelper(root.left, key, value);
        } else {
            root.Value = value;
        }
        return root;
    }

    public V get(K key) {
        BSTNode node = findHelper(root, key);
        if (node == null) {
            return null;
        }
        return node.Value;
    }

    private BSTNode findHelper(BSTNode n, K key) {
        if (n == null ) {
            return null;
        }
        int cmp = key.compareTo(n.Key);
        if (cmp == 0) {
            return n;
        } else if (cmp < 0) {
            return findHelper(n.left, key);
        } else {
            return findHelper(n.right, key);
        }
    }

    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public V remove(K key) {
        V targetValue = get(key);
        if (targetValue != null) {
            root = removeHelper(root, key);
            size--;
        }
        return targetValue;
    }

    public V remove(K key, V value) {
        V targetValue = get(key);
        if (targetValue != null && targetValue.equals(value)) {
            root = removeHelper(root, key);
            size --;
            return targetValue;
        }
        return null;
    }

    private BSTNode removeHelper(BSTNode n, K key) {
        if (n == null) return null;
        int cmp = key.compareTo(n.Key);
        if (cmp < 0) {
            n.left = removeHelper(n.left, key);
        } else if (cmp > 0) {
            n.right = removeHelper(n.right, key);
        } else {
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;

            BSTNode t = n;
            n = findMin(n.right);
            n.right = deleteMin(t.right);
            n.left = t.left;
        }
        return n;
    }

    private BSTNode findMin(BSTNode n) {
        if (n.left == null) return n;
        return findMin(n.left);
    }

    private BSTNode deleteMin(BSTNode n) {
        if (n.left == null) return n.right;
        n.left = deleteMin(n.left);
        return n;
    }


    public Iterator<K> iterator() {
        return new BSTMapIterator(root);
    }

    private class BSTMapIterator implements Iterator<K> {
        private Stack<BSTNode> stack = new Stack<>();

        public BSTMapIterator(BSTNode root) {
            pushLeftCell(root);
        }

        private void pushLeftCell(BSTNode n) {
            while (n != null) {
                stack.push(n);
                n = n.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public K next() {
            if (!hasNext()) {
                return null;
            }
            BSTNode curr = stack.pop();
            if (curr.right != null) {
                pushLeftCell(curr.right);
            }
            return curr.Key;
        }

    }

    public void printInOrder() {
        printInOrderhelper(root);
    }

    private void printInOrderhelper(BSTNode root) {
        if (root == null) return;
        printInOrderhelper(root.left);
        System.out.print(root.Key + ":" + root.Value);
        printInOrderhelper(root.right);
    }




}
