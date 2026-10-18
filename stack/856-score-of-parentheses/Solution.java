import java.util.ArrayDeque;
import java.util.Deque;

// Time: O(n) — single pass
// Space: O(n) — stack depth proportional to nesting depth
public class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // base accumulator for the outermost scope

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); // open a new scope
            } else {
                int inner = stack.pop();
                int score = inner == 0 ? 1 : 2 * inner; // "()" = 1, "(A)" = 2*A
                int outer = stack.pop();
                stack.push(outer + score); // merge score into enclosing scope
            }
        }

        return stack.pop();
    }
}
