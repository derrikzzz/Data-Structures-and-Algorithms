package daily.1614-maximum-nesting-depth-of-parentheses;

// Time: O(n) — single pass through the string
// Space: O(1) — only two integer variables
public class Solution {
    public int maxDepth(String s) {
        int depth = 0;
        int maxDepth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            } else if (c == ')') {
                depth--;
            }
        }

        return maxDepth;
    }
}
