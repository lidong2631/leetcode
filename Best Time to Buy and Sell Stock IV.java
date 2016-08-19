public class Solution {
    public int maxProfit(int k, int[] prices) {
        if(prices==null || prices.length==0)
            return 0;
        if(k>prices.length)     //这里有个坎 就是如果交易次数大于prices长度 就要用ii的方法做
            return maxProfit2(prices);
        int[] local = new int[k+1];
        int[] global = new int[k+1];
        for(int i=0;i<prices.length-1;i++)
        {
            int diff = prices[i+1]-prices[i];
            for(int j=k;j>=1;j--)
            {
                local[j] = Math.max(global[j-1]+(diff>0?diff:0), local[j]+diff);
                global[j] = Math.max(local[j],global[j]);
            }
        }
        return global[k];
    }
    
    private int maxProfit2(int[] prices) {
        int profit = 0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i]-prices[i-1]>0)
                profit+=prices[i]-prices[i-1];
        }
        return profit;
    }
}

这题是ii和iii的结合

http://www.cnblogs.com/grandyang/p/4295761.html