Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.



Java:
public class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) 
                maxProfit += prices[i] - prices[i-1];
        }
        return maxProfit;
    }
}






from code ganker:

这道题跟Best Time to Buy and Sell Stock类似，求最大利润。区别是这里可以交易无限多次（当然我们知道交易不会超过n-1次，也就是每天都进行先卖然后买）。

既然交易次数没有限定，可以看出我们只要对于每次两天差价大于0的都进行交易，就可以得到最大利润。因此算法其实就是累加所有大于0的差价既可以了，非常简单。

如此只需要一次扫描就可以了，时间复杂度是O(n)，空间上只需要O(1)存一个累加结果即可。代码如下： 

public int maxProfit(int[] prices) {

    if(prices == null || prices.length==0)
        return 0;
    int res = 0;
    for(int i=0;i<prices.length-1;i++)
    {
        int diff = prices[i+1]-prices[i];
        if(diff>0)
            res += diff;
    }
    return res;
}

这道题其实比Best Time to Buy and Sell Stock更加简单，只需要看透背后的模型就很OK了哈