// LeetCode 647. Palindromic Substrings (Blind 75)
//
// Two approaches, both O(n^2) time:
//   1. countSubstringsDp     — 2D table, O(n^2) space
//   2. countSubstringsExpand — expand around every center, O(1) space (preferred)
public class Solution {

    // ---------------------------------------------------------------------
    // Approach 1: Bottom-up DP
    // Time: O(n^2) — fills every (i, j) cell once
    // Space: O(n^2) — the dp table
    //
    // dp[i][j] = true iff s[i..j] is a palindrome. It is a palindrome when the ends
    // match AND the inside is already a palindrome. We fill i from high to low so
    // that dp[i+1][j-1] (the inside) is computed before dp[i][j].
    // ---------------------------------------------------------------------
    public int countSubstringsDp(String s) {
        int n = s.length();
        int count = 0;

        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    // Length 1: a single character is always a palindrome.
                    dp[i][j] = true;
                } else if (j == i + 1) {
                    // Length 2: palindrome iff both characters match.
                    /*
                    Length 2 exists because the recurrence breaks there.
                    If you tried the generic formula on len == 2, you'd evaluate dp[i+1][j-1] = dp[i+1][i], where the row index exceeds the column index. (invalid)
                    In Java that reads as false (default array value), so "aa" would be reported as not a palindrome. Silently wrong, no exception.
                    */
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    // Length >= 3: ends match AND the inside is a palindrome.
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }

                if (dp[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    // ---------------------------------------------------------------------
    // Approach 2: Expand around center
    // Time: O(n^2) — 2n - 1 centers, each expands up to O(n)
    // Space: O(1)
    //
    // Every palindrome has a center: a single char (odd length) or the gap
    // between two chars (even length). Grow outward from each center; every
    // successful step is one more palindrome. Same idea as the DP (ends match +
    // inside is a palindrome), but the "inside" is the previous step of the
    // expansion, so no table is needed.
    // ---------------------------------------------------------------------
    public int countSubstringsExpand(String s) {
        if (s == null || s.length() == 0) return 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            count += expandFromCenter(s, i, i);     // Odd length palindromes (e.g., "aba")
            count += expandFromCenter(s, i, i + 1); // Even length palindromes (e.g., "abba")
        }

        return count;
    }

    private int expandFromCenter(String s, int left, int right) {
        int count = 0;
        // Expand outwards as long as pointers are in bounds and characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] inputs = {"abc", "aaa", "aba"};
        int[] expected = {3, 6, 4}; // abc: a,b,c | aaa: a,a,a,aa,aa,aaa | aba: a,b,a,aba

        for (int k = 0; k < inputs.length; k++) {
            System.out.printf("%s -> dp=%d expand=%d (expected %d)%n",
                    inputs[k], s.countSubstringsDp(inputs[k]),
                    s.countSubstringsExpand(inputs[k]), expected[k]);
        }
    }
}
