class Solution:
    # @param prices, a list of integer
    # @return an integer
    def maxProfit(self, prices):
        if len(prices) == 0:
            return 0
        f1 = [0 for i in range(len(prices))]    #初始化两个数组f1,f2 f1记录0到i区间的最大利润 f2记录i到n－1区间的最大利润
        f2 = [0 for i in range(len(prices))]
        Min = prices[0]         #Min初始值为prices[0]开始
        for i in range(1, len(prices)):         #循环一遍数组 如果有更小的值 更新Min  每次计算利润如果当前值大于上一个元素的值 就取当前值 否则延用上一元素值
            if prices[i] < Min:             #因每个元素值都是从起点到这个元素为止这个区间买卖一次股票所能得到的最大利润 这一部分可以参考best time part i 只是这里用数组记录每一个元素的最大利润
                Min = prices[i]
            f1[i] = max(f1[i-1], prices[i] - Min)
            
        Max = prices[len(prices)-1]         #求i到n－1区间的最大利润
        for i in range(len(prices)-2, -1, -1):      #用逆序求 方法类似上面 往前遍历时如果有更大的值Max更新
            if prices[i] > Max:
                Max = prices[i]
            f2[i] = max(f2[i+1], Max - prices[i])
            
        res = 0    
        for i in range(len(prices)):        #最后再循环一遍 将两个区间的最大值相加 其中的最大值计委所求
            if f1[i] + f2[i] > res:
                res = f1[i] + f2[i]
        return res

Note: 这题有两种解法 O(n^2)方法 设i从0到n-1，那么针对每一个i，看看在prices的子序列[0,...,i][i,...,n-1]上分别取得的最大利润（第一题）即可。
这样初步一算，时间复杂度是O(n2)。

改进的方法就是动态规划了，那就是第一步扫描，先计算出子序列[0,...,i]中的最大利润，用一个数组保存下来，那么时间是O(n)。
第二步是逆向扫描，计算子序列[i,...,n-1]上的最大利润，这一步同时就能结合上一步的结果计算最终的最大利润了，这一步也是O(n)。
所以最后算法的复杂度就是O(n)的。这里动态规划提高效率实际就是拿空间换时间 用一个数组保存结果这样每次直接查上一次的值做比较即可
但要多开辟空间存数组









from code ganker:

这道题是Best Time to Buy and Sell Stock的扩展，现在我们最多可以进行两次交易。我们仍然使用动态规划来完成，事实上可以解决非常通用的情况，

也就是最多进行k次交易的情况。

这里我们先解释最多可以进行k次交易的算法，然后最多进行两次我们只需要把k取成2即可。我们还是使用“局部最优和全局最优解法”。我们维护两种量，

一个是当前到达第i天可以最多进行j次交易，最好的利润是多少（global[i][j]），另一个是当前到达第i天，最多可进行j次交易，

并且最后一次交易在当天卖出的最好的利润是多少（local[i][j]）。下面我们来看递推式，全局的比较简单，

global[i][j]=max(local[i][j],global[i-1][j])，

也就是去当前局部最好的，和过往全局最好的中大的那个（因为最后一次交易如果包含当前天一定在局部最好的里面，否则一定在过往全局最优的里面）。

对于局部变量的维护，递推式是

local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)，

也就是看两个量，第一个是全局到i-1天进行j-1次交易，然后加上今天的交易，如果今天是赚钱的话（也就是前面只要j-1次交易，最后一次交易取当前天），

第二个量则是取local第i-1天j次交易，然后加上今天的差值（这里因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出，

并不会增加交易次数，而且这里无论diff是不是大于0都一定要加上，因为否则就不满足local[i][j]必须在最后一天卖出的条件了）。

上面的算法中对于天数需要一次扫描，而每次要对交易次数进行递推式求解，所以时间复杂度是O(n*k)，如果是最多进行两次交易，那么复杂度还是O(n)。

空间上只需要维护当天数据皆可以，所以是O(k)，当k=2，则是O(1)。代码如下： 

public int maxProfit(int[] prices) {
    if(prices==null || prices.length==0)
        return 0;
    int[] local = new int[3];
    int[] global = new int[3];
    for(int i=0;i<prices.length-1;i++)
    {
        int diff = prices[i+1]-prices[i];
        for(int j=2;j>=1;j--)
        {
            local[j] = Math.max(global[j-1]+(diff>0?diff:0), local[j]+diff);
            global[j] = Math.max(local[j],global[j]);
        }
    }
    return global[2];
}

可以看到，这里的模型是比较复杂的，主要是在递推式中，local和global是交替求解的。不过理清思路之后，代码是非常简练的，

不禁感叹算法真是牛逼哈，这么个复杂生活问题几行代码就解决了。

