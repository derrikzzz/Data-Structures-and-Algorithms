package dp.300-longest-increasing-subsequence;

public class Solution {
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        //dp[i] = length of longest increasing subsequence ending at index i
        int[] dp = new int[n];
        int maxLength = 1;

        for (int i = 0; i < n; i++) {
            dp[i] = 1; // Each element is an increasing subsequence of length 1
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) { // if nums[j] < nums[i], then nums[i] can extend the subsequence ending at j, so dp[i] = max(dp[i], dp[j] + 1)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }
}
