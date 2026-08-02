public class Solution {
    public int memoizedCutRod(int[] prices, int n) {
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return cutRod(prices, n, memo);
    }

    private int cutRod(int[] prices, int n, int[] memo) {
        if (memo[n] >= 0) {
            return memo[n];
        }
        int maxRevenue;
        if (n == 0) {
            maxRevenue = 0;
        } else {
            maxRevenue = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                maxRevenue = Math.max(maxRevenue, prices[i - 1] + cutRod(prices, n - i, memo));
            }
        }
        memo[n] = maxRevenue;
        return maxRevenue;
    }
}
