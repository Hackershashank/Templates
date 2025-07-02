// Coin Change Combinations — 2D DP


// Ways to form sum i using first j coins
public static int coinChange2DCombinations(int amount, int[] coins) {
    int n = coins.length;
    int[][] dp = new int[amount + 1][n + 1];

    // Base case: sum 0 can always be formed in 1 way (use no coins)
    for (int j = 0; j <= n; j++) {
        dp[0][j] = 1;
    }

    for (int i = 1; i <= amount; i++) {
        for (int j = 1; j <= n; j++) {
            int coin = coins[j - 1];
            dp[i][j] = dp[i][j - 1]; // ways without using coin j
            if (i - coin >= 0) {
                dp[i][j] += dp[i - coin][j]; // use coin j at least once
            }
        }
    }

    return dp[amount][n];
}

