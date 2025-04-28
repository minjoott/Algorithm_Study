package leetcode;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                int v = i - coin;
                if (v < 0 || dp[v] == Integer.MAX_VALUE) continue;

                if (dp[v] + 1 < dp[i]) {
                    dp[i] = dp[v] + 1;
                }
            }
        }

        if (dp[amount] != Integer.MAX_VALUE)
            return dp[amount];
        else
            return -1;
    }
}
