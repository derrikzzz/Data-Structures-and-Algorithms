// LeetCode 2089. Find Target Indices After Sorting Array
//
// Given nums and target, return every index i where nums[i] == target once nums is
// sorted in non-decreasing order. Return the indices in increasing order, or an
// empty list if target doesn't appear.
//
// Constraints: 1 <= nums.length <= 100; 1 <= nums[i], target <= 100.
//
// Approach: Counting (no sort needed)
// Time: O(n) — one counting pass, plus equalCount adds to the list
// Space: O(1) extra — just two counters (the output list doesn't count)
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessThan = 0;
        int equalCount = 0;

        for (int num : nums) {
            if (num < target) {
                lessThan++;
            } else if (num == target) {
                equalCount++;
            }
        }

        List<Integer> result = new ArrayList<>();
        // The target block starts right after the smaller values: indices lessThan .. lessThan + equalCount - 1
        for (int i = 0; i < equalCount; i++) {
            result.add(lessThan + i);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {
            {1, 2, 5, 2, 3},     // LeetCode example 1
            {1, 2, 5, 2, 3},     // LeetCode example 2
            {1, 2, 5, 2, 3},     // LeetCode example 3: target is the largest
            {1, 2, 5, 2, 3},     // target missing
            {7, 7, 7},           // every element is the target
            {4, 5, 6},           // target smaller than everything
            {4, 5, 6},           // target larger than everything
            {9},                 // single element, found
            {3, 1, 3, 1, 3},     // unsorted input with repeats on both sides
        };
        int[] targets = {2, 3, 5, 4, 7, 1, 9, 9, 3};
        List<List<Integer>> expected = List.of(
            List.of(1, 2), List.of(3), List.of(4), List.of(), List.of(0, 1, 2),
            List.of(), List.of(), List.of(0), List.of(2, 3, 4)
        );

        int passed = 0;
        for (int t = 0; t < inputs.length; t++) {
            List<Integer> got = s.targetIndices(inputs[t], targets[t]);
            boolean ok = got.equals(expected.get(t));
            if (ok) passed++;
            System.out.printf("%-4s %s target=%d -> %s (expected %s)%n", ok ? "ok" : "FAIL",
                    Arrays.toString(inputs[t]), targets[t], got, expected.get(t));
        }
        System.out.printf("%n%d/%d passed%n", passed, inputs.length);
    }
}
