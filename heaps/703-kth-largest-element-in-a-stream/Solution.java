package heaps.703-kth-largest-element-in-a-stream;

import java.util.PriorityQueue;

// Time: O(log k) per add — heap bounded by size k
// Space: O(k) — only k elements stored
class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int n : nums) {
            add(n);
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }
}
