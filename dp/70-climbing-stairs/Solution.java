package dp.70-climbing-stairs;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int climbStairsTopDown(int n) {
        Map<Integer, Integer> memo = new HashMap<>();

        return climbStairsHelper(n, memo);
    }

    private int climbStairsHelper(int i, Map<Integer, Integer> memo) {
        if (i <= 1) {
            return 1;
        }

        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        memo.put(i, climbStairsHelper(i - 1, memo) + climbStairsHelper(i - 2, memo));
        return memo.get(i);
    }

    public int climbStairsBottomUp(int n) {
        if (n <= 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
        // Space optimization
        /*
        public int stairs(int n) {
            if (n <= 1) {
                return 1;
            }
            int prev2 = 1;
            int prev1 = 1;
            for (int i = 2; i <= n; i++) {
                int curr = prev1 + prev2;
                prev2 = prev1;
                prev1 = curr;
            }
            return prev1;
        }
        */
    }
}
