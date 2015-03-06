class Solution:
    # @param A, a list of integers
    # @return an integer
    def jump(self, A):
        prev = 0; curr = 0; minStep = 0 #prev表示用最少步数minStep可以走的最大距离 curr表示用最少步数加一步可以走得最大距离
        for i in range(len(A)):     #循环遍历一遍数组
            if i > prev:                            #如果i大于prev 代表现在走过的距离已经大于最少步数可走得最远距离 则
                if prev == curr and prev < len(A)-1:    #先判断
                    return -1
                prev = curr                             #更新最少步数能走最大距离prev 为最少步数加一步能走最大距离curr 并将minStep+1
                minStep+=1
            curr = max(curr, i + A[i])      #更新curr为局部最优解i+A[i](从当前位置出发能走得最大距离)和全局最优解curr(到目前为止能走得最大距离)的较大值
        return minStep

Note: We use "prev" to keep track of the maximum distance that has been reached by using the minimum steps "minStep",

    whereas "curr" is the maximum distance that can be reached by using "minStep+1" steps. 

    Thus, curr = max(i+A[i]) where 0 <= i <= last.






public class Solution {
    public int jump(int[] A) {
        if(A==null || A.length==0)
            return 0;
        int last = 0; 
        int reach = 0;
        int step = 0;
        for(int i=0; i<=reach&&i<A.length; i++)
        {
            if(i>last)      //这里如果i大于last 说明last不能走到i的位置 需要再多走一步到reach的位置
            {
                step++;
                last = reach;
            }
            reach = Math.max(reach, i+A[i]);
        }
        if(reach>=A.length-1)
            return step;
        return 0;
    }
}

Note: 根据code ganker写的 同i差不多 也是动态规划 局部全局最优 只是这里要维护两个step-1和step步的最远距离





from code ganker:

这道题是Jump Game的扩展，区别是这道题不仅要看能不能到达终点，而且要求到达终点的最少步数。其实思路和Jump Game还是类似的，

只是原来的全局最优现在要分成step步最优和step-1步最优（假设当前步数是step）。当走到超过step-1步最远的位置时，说明step-1不能到达当前一步，我们就可以更新步数，

将step+1。时间复杂度仍然是O(n)，空间复杂度也是O(1)。代码如下：

public int jump(int[] A) {
    if(A==null || A.length==0)
        return 0;
    int lastReach = 0;
    int reach = 0;
    int step = 0;
    for(int i=0;i<=reach&&i<A.length;i++)
    {
        if(i>lastReach)
        {
            step++;
            lastReach = reach;
        }
        reach = Math.max(reach,A[i]+i);
    }
    if(reach<A.length-1)
        return 0;
    return step;
}

动态规划是面试中特别是onsite中非常重要的类型，一般面试中模型不会过于复杂，所以大家可以熟悉一下比较经典的几个题，比如Jump Game，Maximum Subarray等。









贪心法（ Greedy algorithm），又称貪心演算法，是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，

从而希望导致结果是最好或最优的算法。[1]比如在旅行推销员问题中，如果旅行员每次都选择最近的城市，那这就是一种贪心算法。

贪心算法在有最优子结构的问题中尤为有效。最优子结构的意思是局部最优解能决定全局最优解。简单地说，问题能够分解成子问题来解决，

子问题的最优解能递推到最终问题的最优解。

贪心算法与动态规划的不同在于它每对每个子问题的解决方案都做出选择，不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，

有回退功能。

贪心法可以解决一些最优化问题，如：求图中的最小生成树、求哈夫曼编码……对于其他问题，贪心法一般不能得到我们所要求的答案。

一旦一个问题可以通过贪心法来解决，那么贪心法一般是解决这个问题的最好办法。由于贪心法的高效性以及其所求得的答案比较接近最优结果，

贪心法也可以用作辅助算法或者直接解决一些要求结果不特别精确的问题。

