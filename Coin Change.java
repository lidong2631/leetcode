public class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount<0 || coins==null || coins.length==0)
            return -1;
        int[] dp = new int[amount+1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    if (dp[i-coins[j]] != Integer.MAX_VALUE)        // careful here
                        dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                }
            }
        }
        return (dp[amount] == Integer.MAX_VALUE) ? -1 : dp[amount];
    }
}



You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need 
to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.

O(mn)


Another question asking Given a value N, how many ways can we make change if we have infinite supply of S valued coins.

http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/

int count(int[] cents, int m, int n)
    long[][] res = new long[n+1][m];
    for(int i = 0; i< m; i++)
        res[0][i] = 1;

    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < m; j++) {
            long x = (i >= cents[j]) ? res[i-cents[j]][j] : 0;      // including cents[j] at least once
            long y = (j >= 1) ? res[i][j-1] : 0;                    // exclude cents[j]
            res[i][j] = x + y;                                      // res[i][j] would be sum of both cases
        }
    }
    return res[n][m-1];
}

O(mn)