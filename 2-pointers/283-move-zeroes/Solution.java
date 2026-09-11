public class Solution {
    public void moveZeroes(int[] nums) {
        int nextNonZeroPointer = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, nextNonZeroPointer, i);
                nextNonZeroPointer++;
            }
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
