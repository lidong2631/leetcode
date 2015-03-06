class Solution:
    # @param A, a list of integers
    # @return a boolean
    def canJump(self, A):
        step = A[0]
        for i in range(1, len(A)):  #循环 每次step取上一次step-1及当前A[i]的最大值 如果step<0 返回false 否则如果都遍历完就返回true
            if step > 0:                #如果step>0 则表示至少可以到达下一个点 将step设为step-1
                step-=1
                step = max(step, A[i])  #更新step取这个step和下一个点A[i]的最大值
            else:                   #如果step<0 则代表有点无法跨越 返回false
                return False
        return True         #如果都遍历完则可以走完




public class Solution {
    public boolean canJump(int[] A) {
        if(A==null || A.length==0)
            return false;
        int reach = 0;
        for(int i=0; i<=reach&&i<A.length; i++)
            reach = Math.max(reach, A[i]+i);
        if(reach>=A.length-1)       //这里要注意 是大于等于A.length-1 因为如果最后一个是0 reach最终可能只等于A.length-1 但同样算到终点了
            return true;
        return false;
    }
}

Note: 经典动态规划题 from code ganker 维护一个能跳到的最远距离 一步步递推到底 思路是全局局部最优 类似题目如best time buy stocks





from code ganker:

这道题是动态规划的题目，所用到的方法跟是在Maximum Subarray中介绍的套路，用“局部最优和全局最优解法”。我们维护一个到目前为止能跳到的最远距离，以及从当前一步出发能跳到的最远距离。

局部最优local=A[i]+i，而全局最优则是global=Math.max(global, local)。递推式出来了，代码就比较容易实现了。因为只需要一次遍历时间复杂度是O(n)，而空间上是O(1)。代码如下： 

public boolean canJump(int[] A) {
    if(A==null || A.length==0)
        return false;
    int reach = 0;
    for(int i=0;i<=reach&&i<A.length;i++)
    {
        reach = Math.max(A[i]+i,reach);
    }
    if(reach<A.length-1)
        return false;
    return true;
}

这也是一道比较经典的动态规划的题目，不过不同的切入点可能会得到不同复杂度的算法，比如如果维护的历史信息是某一步是否能够到达，

那么每一次需要维护当前变量的时候就需要遍历前面的所有元素，那么总的时间复杂度就会是O(n^2)。所以同样是动态规划，有时候也会有不同的角度，不同效率的解法。

这道题目还有一个扩展Jump Game II，有兴趣的朋友可以看看。


