public class Solution {
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = countSetBits(i);
        }
        return result;
    }

    private int countSetBits(int num) {
        int count = 0;
        while (num > 0) {
            count += num & 1; // Increment count if the least significant bit is 1
            num >>= 1; // Right shift to check the next bit
        }
        return count;
    }
}
