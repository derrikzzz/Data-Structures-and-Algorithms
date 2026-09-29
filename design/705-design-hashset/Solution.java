package design.705-design-hashset;

import java.util.LinkedList;

// Time: O(n/k) average — n keys spread across k buckets; O(1) amortized with low load factor
// Space: O(n + k) — k buckets + n stored keys
class MyHashSet {
    private static final int SIZE = 1000;
    private final LinkedList<Integer>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = new LinkedList[SIZE];
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void add(int key) {
        int h = hash(key);
        if (buckets[h] == null) buckets[h] = new LinkedList<>();
        if (!buckets[h].contains(key)) buckets[h].add(key);
    }

    public void remove(int key) {
        int h = hash(key);
        if (buckets[h] != null) buckets[h].remove((Integer) key);
    }

    public boolean contains(int key) {
        int h = hash(key);
        return buckets[h] != null && buckets[h].contains(key);
    }
}
