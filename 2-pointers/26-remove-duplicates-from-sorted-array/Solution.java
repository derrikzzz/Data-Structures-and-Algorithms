// Time: O(n) — single pass
// Space: O(1) — in-place, no extra array
public class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                // k tracks the write position, i scans forward
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }
}
