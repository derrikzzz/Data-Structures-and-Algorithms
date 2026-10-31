// LeetCode 62. Unique Paths
//
// A robot starts at the top-left of an m x n grid and can only move right or
// down. Return the number of unique paths to the bottom-right corner.
//
// Constraints: 1 <= m, n <= 100; the answer fits in an int (<= 2 * 10^9).
//
// Time: O(m * n) — fills every cell once
// Space: O(m * n) — the dp table (reducible to O(n) with a single row)
public class Solution {
    public int uniquePaths(int m, int n) {
        
        int[][] dp = new int[m][n];

        // Only 1 way to reach any cell in the 1st column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // Only 1 way to reach any cell in 1st row 
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] inputs = {{3, 7}, {3, 2}, {1, 1}, {1, 5}, {10, 10}};
        int[] expected = {28, 3, 1, 1, 48620};

        for (int k = 0; k < inputs.length; k++) {
            int m = inputs[k][0], n = inputs[k][1];
            System.out.printf("m=%d n=%d -> %d (expected %d)%n",
                    m, n, s.uniquePaths(m, n), expected[k]);
        }
    }
}
