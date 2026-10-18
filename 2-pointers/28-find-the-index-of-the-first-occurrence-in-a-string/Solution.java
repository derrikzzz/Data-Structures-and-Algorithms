public class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        if (m > n) {
            return 0;
        }

        int[] lps = buildlps(needle);

        int j = 0; // number of needle chars currently matched

        for (int i = 0; i < n; i++) {
            char c = haystack.charAt(i);
            while (j > 0 && c != needle.charAt(j)) {
                j = lps[j - 1]; // reuse what we currently matched
            }
            if (c == needle.charAt(j)) {
                j++;
            }

            if (j == m) {
                return i - m + 1;
            }

        }
        return -1;
    }

    private int[] buildlps(String p) {
        int m = p.length(); // length of needle
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
}
