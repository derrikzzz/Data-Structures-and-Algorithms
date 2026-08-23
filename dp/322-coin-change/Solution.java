package dp.322-coin-change;
import java.util.Arrays;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        // dp[i] = minimum coins needed to make amount i
        int[] dp = new int[amount + 1];

        dp[0] = 0; // Base case: 0 coins needed to make amount 0
        
        Arrays.fill(dp, 1, amount + 1, Integer.MAX_VALUE); // Initialize other amounts to "infinity"

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

         return dp[amount] > amount ? -1 : dp[amount];
    }
}
