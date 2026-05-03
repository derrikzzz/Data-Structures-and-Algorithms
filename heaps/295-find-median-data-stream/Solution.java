import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public List<Double> medianFromDataStream(int[] nums, int[] adds) {
        PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> upper = new PriorityQueue<>();

        for (int num : nums) {
            addNum(lower, upper, num);
        }

        List<Double> result = new ArrayList<>();
        for (int val : adds) {
            addNum(lower, upper, val);
            result.add(findMedian(lower, upper));
        }

        return result;
    }

    private void addNum(PriorityQueue<Integer> lower, PriorityQueue<Integer> upper, int num) {
        if (lower.isEmpty() || num <= lower.peek()) {
            lower.offer(num);
        } else {
            upper.offer(num);
        }

        // Balance the heaps
        if (lower.size() > upper.size() + 1) {
            upper.offer(lower.poll());
        } else if (upper.size() > lower.size()) {
            lower.offer(upper.poll());
        }
    }

    private double findMedian(PriorityQueue<Integer> lower, PriorityQueue<Integer> upper) {
        if (lower.size() > upper.size()) {
            return (double) lower.peek();
        } else {
            return (lower.peek() + upper.peek()) / 2.0;
        }
    }


}
