package daily.3903-smallest-stable-index;

public class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }

         // DP table to store the minimum value from index i to n-1
        int[] suffixMin = new int[n];

        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]); 
        }

        int currentMax = nums[0];

        // Scan from left to right to find the smallest stable index
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            int instabilityScore = currentMax - suffixMin[i];

            if (instabilityScore <= k) {
                return i;
            }
        }

        return -1;
    }
}
