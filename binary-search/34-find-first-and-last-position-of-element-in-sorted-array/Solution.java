// LeetCode 34. Find First and Last Position of Element in Sorted Array
//
// Given an array of integers nums sorted in non-decreasing order, find the starting
// and ending position of a given target value. If target is not found, return [-1, -1].
// You must write an algorithm with O(log n) runtime complexity.
//
// Constraints: 0 <= nums.length <= 10^5; -10^9 <= nums[i], target <= 10^9.
//
// Approach: Two binary searches — on a match, keep searching left (first) or right (last)
// Time: O(log n) — two binary searches
// Space: O(1)
import java.util.Arrays;

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        return new int[]{ findBound(nums, target, true), findBound(nums, target, false) };
    }

    private int findBound(int[] nums, int target, boolean findFirst) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // avoids overflow of (left + right)
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                result = mid;                      // found one copy; remember it...
                if (findFirst) right = mid - 1;    // ...and keep looking left for an earlier one
                else           left = mid + 1;     // ...or right for a later one
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {
            {5, 7, 7, 8, 8, 10},       // LeetCode example 1
            {5, 7, 7, 8, 8, 10},       // LeetCode example 2: target missing
            {},                        // empty array
            {1},                       // single element, found
            {1},                       // single element, missing
            {2, 2, 2, 2, 2},           // every element is the target
            {1, 2, 3, 4, 5},           // target at the first index
            {1, 2, 3, 4, 5},           // target at the last index
            {1, 3, 5, 7},              // target smaller than everything
            {1, 3, 5, 7},              // target larger than everything
            {1, 3, 5, 7},              // target falls between two elements
        };
        int[] targets = {8, 6, 0, 1, 2, 2, 1, 5, 0, 9, 4};
        int[][] expected = {
            {3, 4}, {-1, -1}, {-1, -1}, {0, 0}, {-1, -1},
            {0, 4}, {0, 0}, {4, 4}, {-1, -1}, {-1, -1}, {-1, -1},
        };

        int passed = 0;
        for (int k = 0; k < inputs.length; k++) {
            int[] got = s.searchRange(inputs[k], targets[k]);
            boolean ok = Arrays.equals(got, expected[k]);
            if (ok) passed++;
            System.out.printf("%-4s %s target=%d -> %s (expected %s)%n", ok ? "ok" : "FAIL",
                    Arrays.toString(inputs[k]), targets[k], Arrays.toString(got), Arrays.toString(expected[k]));
        }
        System.out.printf("%n%d/%d passed%n", passed, inputs.length);
    }
}
