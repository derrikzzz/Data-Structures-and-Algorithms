// LeetCode 53. Maximum Subarray
//
// Given an integer array nums, return the largest sum of any non-empty
// contiguous subarray.
//
// Constraints: 1 <= nums.length <= 10^5; -10^4 <= nums[i] <= 10^4.
//
// Approach: Kadane's algorithm
// Time: O(?)
// Space: O(?)
import java.util.Arrays;

public class Solution {
    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int n = nums.length;

        // Max sum of subarray ending at current position
        int maxEnding = nums[0];

        for (int i = 1; i < n; i++) {
            maxEnding = Math.max(maxEnding + nums[i], nums[i]);
            result = Math.max(result, maxEnding);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {
            {-2, 1, -3, 4, -1, 2, 1, -5, 4},
            {1},
            {5, 4, -1, 7, 8},
            {-3, -1, -2},
            {-1},
        };
        int[] expected = {6, 1, 23, -1, -1};

        for (int k = 0; k < inputs.length; k++) {
            System.out.printf("%s -> %d (expected %d)%n",
                    Arrays.toString(inputs[k]), s.maxSubArray(inputs[k]), expected[k]);
        }
    }
}
