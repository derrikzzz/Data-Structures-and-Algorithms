// LeetCode 278. First Bad Version
//
// You have n versions [1, 2, ..., n]. Every version after a bad version is also bad.
// Given an API isBadVersion(version), find the first bad version while minimising
// the number of API calls.
//
// Constraints: 1 <= bad <= n <= 2^31 - 1.
//
// Approach: Binary search for the lower bound — shrink [left, right] until it closes
//           on the first version where isBadVersion flips to true
// Time: O(log n) — halve the search range each step
// Space: O(1)

// Local stand-in for LeetCode's VersionControl API so this file runs on its own.
class VersionControl {
    protected int firstBad;

    boolean isBadVersion(int version) {
        return version >= firstBad;
    }
}

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2; // avoids overflow when n is near 2^31 - 1
            if (isBadVersion(mid)) {
                right = mid; // 1st bad version is mid or earlier
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] ns   = {5, 1, 2, 2, 10, 10, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] bads = {4, 1, 1, 2, 1, 10, Integer.MAX_VALUE, 1702766719};
        // LeetCode example 1, example 2, small ranges, first/last version bad, overflow-sized n

        Solution s = new Solution();
        int passed = 0;
        for (int k = 0; k < ns.length; k++) {
            s.firstBad = bads[k];
            int got = s.firstBadVersion(ns[k]);
            boolean ok = got == bads[k];
            if (ok) passed++;
            System.out.printf("%-4s n=%d -> %d (expected %d)%n", ok ? "ok" : "FAIL", ns[k], got, bads[k]);
        }
        System.out.printf("%n%d/%d passed%n", passed, ns.length);
    }
}
