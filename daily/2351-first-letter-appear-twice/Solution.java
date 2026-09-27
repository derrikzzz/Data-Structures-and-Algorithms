package daily.2351-first-letter-appear-twice;

import java.util.HashSet;

// Time: O(n) — single pass through the string
// Space: O(1) — at most 26 lowercase letters in the set
public class Solution {
    public char repeatedCharacter(String s) {
        HashSet<Character> seen = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (!seen.add(c)) {
                return c;
            }
        }

        return '0';
    }
}
