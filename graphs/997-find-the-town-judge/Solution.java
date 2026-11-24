// LeetCode 997. Find the Town Judge
//
// n people labeled 1..n. trust[i] = [a, b] means person a trusts person b.
// The town judge (if one exists) trusts nobody, and is trusted by everyone else.
// Exactly one person fits both rules if a judge exists. Return the judge's
// label, or -1 if there is none.
//
// Constraints: 1 <= n <= 1000; 0 <= trust.length <= 10^4; a != b;
//              all trust pairs are unique.
//
// Approach: one score per person = (people who trust them) - (people they trust);
//           the judge is the only one who can reach n - 1
// Time: O(n + E), where E = trust.length
// Space: O(n)
import java.util.Arrays;

public class Solution {
    public int findJudge(int n, int[][] trust) {
       if (n == 1 && trust.length == 0) {
            return 1;
        }

        int[] trustScores = new int[n + 1];

        for (int[] relation : trust) {
            int truster = relation[0];
            int trusted = relation[1];

            trustScores[truster]--;
            trustScores[trusted]++;
        }

        for (int i = 1; i <= n; i++) {
            if (trustScores[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][][] inputs = {
            {{1, 2}},                                   // LeetCode example 1
            {{1, 3}, {2, 3}},                           // LeetCode example 2
            {{1, 3}, {2, 3}, {3, 1}},                   // LeetCode example 3: candidate trusts someone
            {},                                         // one person, no trust: they are the judge
            {},                                         // two people, no trust: nobody is trusted by all
            {{1, 2}, {2, 3}},                           // chain: 3 trusted by only one person
            {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}},   // 4 is trusted but trusts 3
            {{1, 2}, {2, 3}, {3, 1}},                   // cycle: everyone trusts someone
            {{1, 3}},                                   // 2 and 3 trust nobody, but 2 isn't trusted
            {{1, 2}, {3, 2}, {4, 2}},                   // star into 2
        };
        int[] ns = {2, 3, 3, 1, 2, 3, 4, 3, 3, 4};
        int[] expected = {2, 3, -1, 1, -1, -1, 3, -1, -1, 2};

        int passed = 0;
        for (int t = 0; t < inputs.length; t++) {
            int got = s.findJudge(ns[t], inputs[t]);
            boolean ok = got == expected[t];
            if (ok) passed++;
            System.out.printf("%-4s n=%d trust=%s -> %d (expected %d)%n", ok ? "ok" : "FAIL",
                    ns[t], Arrays.deepToString(inputs[t]), got, expected[t]);
        }
        System.out.printf("%n%d/%d passed%n", passed, inputs.length);
    }
}
