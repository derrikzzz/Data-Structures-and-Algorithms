import java.util.PriorityQueue;

public class Solution {
    public int[][] kClosest(int[][] points, int k) {
        
        // Max-Heap: ordered by descending squared distance from the origin
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) ->
            Integer.compare((b[0] * b[0] + b[1] * b[1]), a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            maxHeap.offer(point);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Extract remaining k closest points from the heap
        int[][] result = new int[k][2];
        while (k > 0) {
            result[k - 1] = maxHeap.poll();
            k--;
        }

        return result;
    }
}
