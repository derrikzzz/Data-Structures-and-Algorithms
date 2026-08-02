public class Solution {
    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        Solution solution = new Solution();
        System.out.println(solution.maxCoins(nums));
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] extendedNums = new int[n + 2];
        extendedNums[0] = 1;
        extendedNums[n + 1] = 1;
        System.arraycopy(nums, 0, extendedNums, 1, n);

        int[][] dp = new int[n + 2][n + 2];

        for (int length = 1; length <= n; length++) {
            for (int left = 1; left <= n - length + 1; left++) {
                int right = left + length - 1;
                for (int i = left; i <= right; i++) {
                    dp[left][right] = Math.max(dp[left][right],
                            dp[left][i - 1] + dp[i + 1][right] + extendedNums[left - 1] * extendedNums[i] * extendedNums[right + 1]);
                }
            }
        }

        return dp[1][n];
    }
}
