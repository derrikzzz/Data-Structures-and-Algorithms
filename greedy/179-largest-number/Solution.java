import java.util.Arrays;

public class Solution {
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        // Greedy comparator: order a before b if a+b > b+a as strings.
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));

        // Edge case: all zeros (e.g. [0, 0]) -> "0" instead of "00".
        if (strs[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestNumber(new int[]{10, 2}));           // 210
        System.out.println(solution.largestNumber(new int[]{3, 30, 34, 5, 9})); // 9534330
        System.out.println(solution.largestNumber(new int[]{0, 0}));            // 0
    }
}
