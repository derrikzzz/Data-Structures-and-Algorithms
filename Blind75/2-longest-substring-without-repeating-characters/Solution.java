import java.util.HashMap;

public class Solution {

    // Sliding window over [start, end]. Invariant: the window holds no repeated
    // character. Expand end each step; if the char just added now appears twice,
    // shrink from the left until the window is valid again. Each index enters and
    // leaves the window at most once -> O(n).
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> seen = new HashMap<>(); // char -> count inside the window
        int start = 0;
        int maxLength = 0;

        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            seen.put(ch, seen.getOrDefault(ch, 0) + 1);

            while (seen.get(ch) > 1) {
                char startChar = s.charAt(start);
                seen.put(startChar, seen.get(startChar) - 1);
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb")); // 3 ("abc")
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));    // 1 ("b")
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));   // 3 ("wke")
        System.out.println(solution.lengthOfLongestSubstring(""));         // 0
    }
}
