public class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        if (m == 0 || n == 0) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public String printLcs(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return "";
        }

        int m = text1.length();
        int n = text2.length();

        if (m == 0 || n == 0) {
            return "";
        }

        // Same DP table as longestCommonSubsequence: dp[i][j] = LCS length of
        // text1[0..i) and text2[0..j).
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtrack from dp[m][n] to the origin, rebuilding the LCS.
        // At each cell we retrace the choice the forward pass made:
        //   - chars matched  -> that char is in the LCS; go diagonally.
        //   - otherwise      -> move toward the larger neighbour we came from.
        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                sb.append(text1.charAt(i - 1)); // matched char (collected in reverse)
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--; // the value came from above
            } else {
                j--; // the value came from the left
            }
        }

        return sb.reverse().toString(); // we built it back-to-front
    }
    
}