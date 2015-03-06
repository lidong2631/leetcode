class Solution:
    # @param prices, a list of integer
    # @return an integer
    def maxProfit(self, prices):
        if prices <= 1:
            return 0
        maxProfit = 0
        for i in range(1, len(prices)):             #因为是不限次数 只要是递增序列就可以一直累加利润
            if prices[i] > prices[i-1]:
                maxProfit += prices[i] - prices[i-1]
        return maxProfit





题意：

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

解题思路：由于可以进行无限次的交易，那么只要是递增序列，就可以进行利润的累加。

代码：

复制代码
class Solution:
    # @param prices, a list of integer
    # @return an integer
    def maxProfit(self, prices):
        maxprofit = 0
        for i in range(1, len(prices)):
            if prices[i] >= prices[i-1]:
                maxprofit += prices[i] - prices[i-1]
        return maxprofit







public class Solution {
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length==0)
            return 0;
        int maxProfit = 0;
        for(int i=0; i<prices.length-1; i++)
        {
            int diff = prices[i+1] - prices[i];
            if(diff>0)
                maxProfit+=diff;
        }
        return maxProfit;
    }
}

Note: 这题其实比上一题还简单 可以交易无限次 所以只要两只相邻股票价格是递增就可以一买一卖的挣钱 这样累加利润知道遍历所有prices





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