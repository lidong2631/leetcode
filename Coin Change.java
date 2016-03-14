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