// LeetCode 35. Search Insert Position
//
// Given a sorted array of distinct integers and a target value, return the index if the
// target is found. If not, return the index where it would be if it were inserted in order.
// You must write an algorithm with O(log n) runtime complexity.
//
// Constraints: 1 <= nums.length <= 10^4; -10^4 <= nums[i], target <= 10^4;
//              nums contains distinct values sorted in ascending order.
//
// Approach: Binary search for the lower bound — first index whose value is >= target
// Time: O(log n) — halve the search range each step
// Space: O(1)
import java.util.Arrays;

public class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // allow insertion at the end

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[][] numss = {{1, 3, 5, 6}, {1, 3, 5, 6}, {1, 3, 5, 6}, {1, 3, 5, 6}, {1}, {1}, {1}};
        int[] targets = {5, 2, 7, 0, 1, 0, 2};
        int[] expected = {2, 1, 4, 0, 0, 0, 1};
        // LeetCode examples 1-3, insert at front, single element: found / before / after

        Solution s = new Solution();
        int passed = 0;
        for (int k = 0; k < numss.length; k++) {
            int got = s.searchInsert(numss[k], targets[k]);
            boolean ok = got == expected[k];
            if (ok) passed++;
            System.out.printf("%-4s nums=%s target=%d -> %d (expected %d)%n",
                    ok ? "ok" : "FAIL", Arrays.toString(numss[k]), targets[k], got, expected[k]);
        }
        System.out.printf("%n%d/%d passed%n", passed, numss.length);
    }
}
