// LeetCode 2243. Calculate Digit Sum of a String
//
// s is a string of digits. While s.length() > k, do one round:
//   1. Split s into consecutive groups of size k (the last group may be shorter).
//   2. Replace each group with the sum of its digits, written as a string ("346" -> "13").
//   3. Join the results into the new s.
// Return s once its length is <= k.
//
// Constraints: 1 <= s.length <= 100; 2 <= k <= 100; s contains digits only.
//
// Time: O(n) — each round is linear; length shrinks geometrically (within 2 rounds when k = 2)
// Space: O(n) — StringBuilder for the next round
public class Solution {
    public String digitSum(String s, int k) {
        // Continue performing rounds while the string length exceeds k
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            // Step through the string in chunks of size k
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;

                // Sum the digits in the current group, stopping at i + k or end of string
                for (int j = i; j < i + k && j < s.length(); j++) {
                    sum += s.charAt(j) - '0';
                }

                // Append the sum of the current group to build the next string
                nextRound.append(sum);
            }

            // Update s for the next round
            s = nextRound.toString();
            

        }
        return s;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] inputs = {
            "11111222223",   // LeetCode example 1: two rounds
            "00000000",      // LeetCode example 2: zeros stay "0", not ""
            "123",           // length == k: no rounds at all
            "1",             // shorter than k: no rounds
            "1234",          // last group shorter than k
            "99999",         // a sum can be 2 digits, so s can stay long for several rounds
            "99999",         // one round; 36 is two characters
            "1000000000",    // zeros inside a group add nothing
        };
        int[] ks = {3, 3, 3, 2, 3, 2, 4, 5};
        String[] expected = {"135", "000", "123", "1", "64", "99", "369", "10"};

        int passed = 0;
        for (int t = 0; t < inputs.length; t++) {
            String got = sol.digitSum(inputs[t], ks[t]);
            boolean ok = got.equals(expected[t]);
            if (ok) passed++;
            System.out.printf("%-4s s=\"%s\" k=%d -> \"%s\" (expected \"%s\")%n",
                    ok ? "ok" : "FAIL", inputs[t], ks[t], got, expected[t]);
        }
        System.out.printf("%n%d/%d passed%n", passed, inputs.length);
    }
}
