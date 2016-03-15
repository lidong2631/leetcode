class Solution:
    # @param prices, a list of integer
    # @return an integer
    def maxProfit(self, prices):
        if len(prices) <= 1:				#如果只有一个值 利润为0
            return 0
        low = prices[0]
        maxProfit = 0
        for i in range(len(prices)):		#遍历prices 用low标记最低估价 如果有更小的 置换low
            if prices[i] < low:
                low = prices[i]
            maxProfit = max(maxProfit, prices[i] - low) #最大利润取当前最大利润和prices[i] - low两者中较大的值
        return maxProfit





解题思路：扫描一遍数组，使用low来标记最低价位，如果有更低的价位，置换掉。

代码：

复制代码
class Solution:
    # @param prices, a list of integer
    # @return an integer
    def maxProfit(self, prices):
        if len(prices) <= 1: return 0
        low = prices[0]
        maxprofit = 0
        for i in range(len(prices)):
            if prices[i] < low: low = prices[i]
            maxprofit = max(maxprofit, prices[i] - low)
        return maxprofit



不同的是要求输出最终的buy price，sell price和profit。写完之后要求你给出一些特殊的test cases
public class Solution {
    
    public static void main(String[] args) {
        int[] A = {10, 8, 15, 12, 24};

        int local = 0, global = 0;
        int buy = A[0], sell = A[0];
        for(int i=1; i<A.length; i++) {
            local = Math.max(0, local+A[i]-A[i-1]);
            
            if(local>global) {
                sell = A[i];
                buy = Math.min(buy, A[i-1]);
            }

            global = Math.max(global, local);
        }
        System.out.println("buy = " + buy + " sell = " + sell + " profit = " + global);
    }
}



public class Solution {
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length == 0)
            return 0;
        int local = 0;
        int global = 0;
        for(int i=0; i<prices.length-1; i++)
        {
            local = Math.max(0, local+prices[i+1]-prices[i]);
            global = Math.max(global, local);
        }
        return global;
    }
}

Note: 动态规划 弄清楚两个公式 

局部 local[i+1] = max(local[i] + prices[i+1] - prices[i]， 0） 

这个可以这么想 local[i]表示以第i天股票价格可以得到的最大利润 知道它求它下一天的最大利润 一种可能是下一天不做交易利润就是0

或者做交易 知道i天的最大利润是这么多(local[i]) 那么它下一天的股票价格跟它比相差prices[i+1]-prices[i] 利润也就差了i天的最大利润加上这个差值

当然如果赔钱就还是0

全局 global[i+1] = max(global[i], local[i+1]) 这个好理解








from code ganker:

这道题求进行一次交易能得到的最大利润。如果用brute force的解法就是对每组交易都看一下利润，取其中最大的，总用有n*(n-1)/2个可能交易，

所以复杂度是O(n^2)。

很容易感觉出来这是动态规划的题目，其实跟Maximum Subarray非常类似，用“局部最优和全局最优解法”。思路是维护两个变量，

一个是到目前为止最好的交易，另一个是在当前一天卖出的最佳交易（也就是局部最优）。递推式是local[i+1]=max(local[i]+prices[i+1]-price[i],0), 

global[i+1]=max(local[i+1],global[i])。这样一次扫描就可以得到结果，时间复杂度是O(n)。而空间只需要两个变量，即O(1)。代码如下： 

public int maxProfit(int[] prices) {
    if(prices==null || prices.length==0)
        return 0;
    int local = 0;
    int global = 0;
    for(int i=0;i<prices.length-1;i++)
    {
        local = Math.max(local+prices[i+1]-prices[i],0);
        global = Math.max(local, global);
    }
    return global;
}

这种题目的解法非常经典，不过是比较简单的动态规划。这道题目还有两个变体，

Best Time to Buy and Sell Stock II和Best Time to Buy and Sell Stock III，虽然题目要求比较像，但是思路却变化比较大，

Best Time to Buy and Sell Stock II可以交易无穷多次，思路还是比较不一样，而Best Time to Buy and Sell Stock III则限定这道题交易两次，

其实还可以general到限定交易k次，会在这道题目中仔细讲解，有兴趣的朋友可以看看哈。

