// LeetCode 146. LRU Cache
//
// LRUCache(capacity)
//   - Initialize the cache with a positive capacity.
//   - get(key): return the value of the key if it exists, otherwise -1.
//     Accessing a key makes it the most recently used.
//   - put(key, value): update the value if the key exists, otherwise insert it.
//     If the insert makes the size exceed capacity, evict the least recently used key.
//   - get and put must each run in O(1) average time.
//
// Constraints: 1 <= capacity <= 3000; 0 <= key <= 10^4; 0 <= value <= 10^5;
//              at most 2 * 10^5 calls to get and put.
//
// Time: O(1) average per get / put — HashMap ops are expected O(1); list relinking is O(1)
// Space: O(capacity) — map + doubly linked list hold at most capacity + 1 entries

import java.util.Map;
import java.util.HashMap;

class LRUCache {
    // Inner Node class for the doubly linked list
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node() {
            this(0, 0);
        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        // Initialize dummy head and tail nodes
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        
        Node node = cache.get(key);
        // Move to head (mark as recently used)
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
       if (cache.containsKey(key)) {
            // Update existing node and mark as recently used
            Node node = cache.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
       } else {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            if (cache.size() > capacity) {
                Node lru = removeTail();
                cache.remove(lru.key);
            }
       }
    }

     private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private Node removeTail() {
        Node lruNode = tail.prev;
        removeNode(lruNode);
        return lruNode;
    }


}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
