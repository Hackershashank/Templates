// Coin Change Permutations — 2D DP

// Ways to form sum i using j coins (ordered ways)
public static int coinChange2DPermutations(int amount, int[] coins) {
    int[][] dp = new int[amount + 1][amount + 1];
    dp[0][0] = 1;

    for (int i = 1; i <= amount; i++) {
        for (int j = 1; j <= amount; j++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i][j] += dp[i - coin][j - 1];
                }
            }
        }
    }

    // Sum over all lengths
    int total = 0;
    for (int j = 1; j <= amount; j++) {
        total += dp[amount][j];
    }
    return total;
}

// dp[i][j] = ways to make i with exactly j coins in sequence.
// Loop over coins for each sum & length.
// Final answer: sum dp[amount][j] for all j
