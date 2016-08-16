public class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) maxProfit += prices[i] - prices[i-1];
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