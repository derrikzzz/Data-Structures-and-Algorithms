// LeetCode 384. Shuffle an Array
//
// Solution(nums)
//   - Initialize the object with the integer array nums.
//   - reset(): reset the array to its original configuration and return it.
//   - shuffle(): return a random shuffling of the array. All permutations of the
//     array must be equally likely.
//
// Constraints: 1 <= nums.length <= 50; -10^6 <= nums[i] <= 10^6; all elements of nums are unique;
//              at most 10^4 calls in total to reset and shuffle.
//
// Time: O(n) per reset / shuffle — reset copies the array; shuffle is one Fisher–Yates pass
// Space: O(n) — working array + original backup

import java.util.Random;

class Solution {
    private final int[] array;
    private final int[] original;

    private final Random rand;

    public Solution(int[] nums) {
        this.array = nums.clone();
        this.original = nums.clone();

        this.rand = new Random();
    }

    public int[] reset() {
        // Restore the working array from the original backup
        System.arraycopy(this.original, 0, this.array, 0, this.original.length);
        return this.array;
    }

    public int[] shuffle() {
        int n = this.array.length;

        for (int i = 0; i < n; i++) {
            // Pick a random index in the range [i, n - 1]
            int swapIdx = i + rand.nextInt(n - i);
            swap(i, swapIdx);
        }
        return this.array;
        
    }

    private void swap(int i, int j) {
        int temp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
