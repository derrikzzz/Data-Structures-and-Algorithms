package dp.treasure-hunter;

/*
You're a treasure hunter in a neighborhood where houses are arranged in a row, and each house contains a different amount of treasure. Your goal is to collect as much treasure as possible, but there's a catch: if you collect treasure from two adjacent houses, it triggers a neighborhood-wide alert, ending your hunt immediately.
Given an array treasure of non-negative integers, where each integer represents the amount of treasure in a house, write a function to return the maximum amount of treasure you can collect without triggering any alarms.
Example 1:
Input: treasure = : [3, 1, 4, 1, 5]
Best Haul: 12
Explanation: Collect from houses 0, 2, and 4 for a total of 3 + 4 + 5 = 12.
*/

public class Solution {
    public int maxTreasure(int[] treasure) {
        int[] dp = new int[treasure.length];
        if (treasure.length == 0) {
            return 0;
        }

        int n = treasure.length;

        if (n == 1) {
            return treasure[0];
        }

        dp[0] = treasure[0];
        dp[1] = Math.max(treasure[0], treasure[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + treasure[i]);
        }

        return dp[n - 1];
    }
}
