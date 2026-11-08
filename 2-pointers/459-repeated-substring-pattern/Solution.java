// LeetCode 459. Repeated Substring Pattern
//
// Return true if s can be built by repeating one of its substrings 2+ times.
//
// Approach: KMP lps table (same buildLps as 28. Find the Index of the First Occurrence)
// lps[n-1] = longest proper prefix of s that is also a suffix. If s is a repeated
// unit, prefix and suffix overlap by everything except one unit, so the leftover
// k = n - lps[n-1] is the unit length. It must divide n evenly, and lps[n-1] > 0
// rules out k == n (the "unit" being the whole string).
//
// Time: O(n) — one pass to build lps
// Space: O(n) — the lps array
public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] lps = buildLps(s);
        int k = n - lps[n - 1]; // length of the smallest candidate repeating unit
        return lps[n - 1] > 0 && n % k == 0;
    }

    private int[] buildLps(String p) {
        int m = p.length();
        int[] lps = new int[m];
        int length = 0;
        int i = 1;

        while (i < m) {
            if (p.charAt(i) == p.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else if (length > 0) {
                length = lps[length - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        return lps;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] inputs = {"abab", "aba", "abcabcabcabc", "aabaaba", "a", "aaaa"};
        boolean[] expected = {true, false, true, false, false, true};

        for (int k = 0; k < inputs.length; k++) {
            System.out.printf("%s -> %b (expected %b)%n",
                    inputs[k], s.repeatedSubstringPattern(inputs[k]), expected[k]);
        }
    }
}
