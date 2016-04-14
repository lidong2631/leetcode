public class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins==null || coins.length==0 || amount<0)
            return -1;
        int[] dp = new int[amount+1];
        
        for(int i=1; i<amount+1; i++)
            dp[i] = Integer.MAX_VALUE;
        
        for(int i=1; i<amount+1; i++) {
            for(int j=0; j<coins.length; j++) {
                if(coins[j]<=i) {
                    int tmp = dp[i-coins[j]];
                    if(tmp!=Integer.MAX_VALUE)
                        dp[i] = Math.min(tmp+1, dp[i]);
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }
}

O(mn)


Another question asking Given a value N, how many ways can we make change if we have infinite supply of S valued coins.

http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/

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
System.out.println(res[n][m-1]);

O(mn)