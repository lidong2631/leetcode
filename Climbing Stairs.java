You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step



Python:
class Solution:
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n == 1:
            return 1
        dp, dp1, dp2 = 0, 1, 2
        for i in range(3, n+1):
            dp = dp1 + dp2
            dp1, dp2 = dp2, dp
        return dp2



Java:
public class Solution {
    public int climbStairs(int n) {
        if (n == 1) 
            return 1;
        int dp1 = 1, dp2 = 2;
        for (int i = 3; i <= n; i++) {
            int dp = dp1 + dp2;
            dp1 = dp2;
            dp2 = dp;
        }
        return dp2;
    }
}



from code ganker:

这道题目是求跑楼梯的可行解法数量。每一步可以爬一格或者两个楼梯，可以发现，递推式是f(n)=f(n-1)+f(n-2)，也就是等于前一格的可行数量加上前两格的可行数量。

熟悉的朋友可能发现了，这个递归式正是斐波那契数列的定义，不熟悉的朋友可以看看Wiki - 斐波那契数列。根据这个定义，其实很容易实现，可以用递归或者递推都是比较简单的，

下面列举一下递推的代码： 

public int climbStairs(int n) {
    int f1 = 1;
    int f2 = 2;
    if(n==1)
        return f1;
    if(n==2)
        return f2;
    for(int i=3;i<=n;i++)
    {
        int f3 = f1+f2;
        f1 = f2;
        f2 = f3;
    }
    return f2;
}

可以很容易判断，上面代码的时间复杂度是O(n)，面试一般都会实现一下，不过还没完，面试官会接着问一下，有没有更好的解法？还真有，斐波那契数列其实是有O(logn)的解法的。

根据wiki我们知道，斐波那契数列是有通项公式的，如下：



所以如果我们用Pow(x, n)中介绍的分治法来求解这个n次幂的话可以完成O(logn)的求解。还有另一种理解方法就是斐波那契数列的线性代数解法（参见Wiki - 斐波那契数列），

可以看到迭代是一个二乘二的简单矩阵，数列的第n个数就是求解这个矩阵的n-2次幂，同样用分治法就可以完成O(logn)的求解。

这是对于斐波那契数列问题的一般面试过程，先实现一下通常的O(n)的解法，然后再了解一下是否知道有O(logn)的解法，一般不要求实现，知道就行，不过其实实现也不是很难，

有兴趣的朋友可以练习一下哈。