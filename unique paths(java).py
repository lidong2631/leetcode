class Solution:
    # @return an integer
    def uniquePaths(self, m, n):
        if m == 1 or n == 1:            #如果只有一个点 返回1
            return 1
        else:
            dp = [[0 for i in range(n)] for j in range(m)]      #初始化dp全为0
            for i in range(m):              #设第一列都为1 此为后面dp的初始条件
                dp[i][0] = 1
            for j in range(n):              #设第一行为1
                dp[0][j] = 1
            for i in range(1, m):           #dp 转移方程 dp[i][j] = dp[i-1][j] + dp[i][j-1]
                for j in range(1, n):
                    dp[i][j] = dp[i-1][j] + dp[i][j-1]
            return dp[m-1][n-1]





题意：

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.

解题思路：这道题和climbing stairs很像，可以用动态规划解决。状态转移方程为dp[i][j]=dp[i-1][j]+dp[i][j-1]。

代码：

复制代码
class Solution:
    # @return an integer
    def uniquePaths(self, m, n):
        if m == 1 and n == 1:
            list = [[1]]
        elif m == 1 and n > 1:
            list = [[1 for i in range(n)]]
        elif m > 1 and n == 1:
            list = [[1] for i in range(m)]
        else:
            list = [[0 for i in range(n)] for i in range(m)]
            for i in range(0, n):
                list[0][i] = 1
            for i in range(0, m):
                list[i][0] = 1
            for i in range(1, m):
                for j in range(1, n):
                    list[i][j] = list[i-1][j] + list[i][j-1]
        return list[m-1][n-1]










public class Solution {
    public int uniquePaths(int m, int n) {
        return helper(1, 1, m, n);
    }
    
    private int helper(int row, int col, int m, int n)
    {
        if(row==m && col==n)
            return 1;
        if(row>m || col>n)
            return 0;
        return helper(row+1, col, m, n) + helper(row, col+1, m, n);
    }
}

Note：brute force解法会TLE 时间复杂度不是多项式




public class Solution {
    public int uniquePaths(int m, int n) {
        int[] res = new int[n];
        res[0] = 1;
        for(int i=0; i<m; i++)
            for(int j=1; j<n; j++)
                res[j] += res[j-1];
        return res[n-1];
    }
}

Note: 利用动态规划 时间复杂度O(m*n), 空间O(n) 这里要注意空间我们只用了一维数组 省掉了一维 是以每一行为单位循环 然后下一行直接用上一行已有的结果

code ganker提到还可以用内循环拿到O(min(m,n))的空间 见下面code 更优解法

public class Solution {
    public int uniquePaths(int m, int n) {
        int min = m>n?n:m;
        int max = m>n?m:n;
        int[] res = new int[min];
        res[0] = 1;
        for(int i=0; i<max; i++) {
            for(int j=1; j<min; j++)
                res[j]+=res[j-1];
        }
        return res[min-1];
    }
}


public class Solution {
    public int uniquePaths(int m, int n) {
        int small = m<n? m-1:n-1;
        int big = m<n? n-1:m-1;
        double A = 1;
        double C = 1;
        for(int i=1; i<=small; i++)
        {
            A*=i;
            C*=big+small+1-i;
        }
        return (int)(C/A);
    }
}

Note: 用的组合公式求得 时间复杂度最低 要记住排列组合的程序写法 公式为A(n,m)=n(n-1)(n-2)……(n-m+1)= n!/(n-m)! 

C(n,m)=A(n,m)/m！；C(n,m)=C(n,n-m）。（n≥m)






from code ganker:

这道题是比较典型的动态规划的题目。模型简单，但是可以考核动态规划的思想。

我们先说说brute force的解法，比较容易想到用递归，到达某一格的路径数量等于它的上面和左边的路径数之和，结束条件是走到行或者列的边缘。

因为每条路径都会重新探索，时间复杂度是结果数量的量级，不是多项式的复杂度。代码如下： 

public int uniquePaths(int m, int n) {
    return helper(1,1,m,n);
}
private int helper(int row, int col, int m, int n)
{
    if(row==m && col==n)
        return 1;
    if(row>m || col>n)
        return 0;
    return helper(row+1,col,m,n)+helper(row,col+1,m,n);
}

上面的代码放到LeetCode中跑会超时，因为不是多项式量级的。其实上一步中递推式已经出来了，就是res[i][j]=res[i-1][j]+res[i][j-1]，

这样我们就可以用一个数组来保存历史信息，也就是在i行j列的路径数，这样每次就不需要重复计算，从而降低复杂度。用动态规划我们只需要对所有格子进行扫描一次，

到了最后一个得到的结果就是总的路径数，所以时间复杂度是O(m*n)。而对于空间可以看出我们每次只需要用到上一行当前列，以及前一列当前行的信息，

我们只需要用一个一维数组存上一行的信息即可，然后扫过来依次更替掉上一行对应列的信息即可（因为所需要用到的信息都还没被更替掉），

所以空间复杂度是O(n)（其实如果要更加严谨，我们可以去行和列中小的那个，然后把小的放在内层循环，这样空间复杂度就是O(min(m,n))，

下面的代码为了避免看起来过于繁杂，就不做这一步了，有兴趣的朋友可以实现一下，比较简单，不过代码有点啰嗦）。实现的代码如下：

public int uniquePaths(int m, int n) {
    if(m<=0 || n<=0)
        return 0;
    int[] res = new int[n];
    res[0] = 1;
    for(int i=0;i<m;i++)
    {
        for(int j=1;j<n;j++)
        {
           res[j] += res[j-1];
        }
    }
    return res[n-1];
}

上面的方法用动态规划来求解，如果我们仔细的看这个问题背后的数学模型，其实就是机器人总共走m+n-2步，其中m-1步往下走，n-1步往右走，

本质上就是一个组合问题，也就是从m+n-2个不同元素中每次取出m-1个元素的组合数。根据组合的计算公式，我们可以写代码来求解即可。代码如下：

public int uniquePaths(int m, int n) {
    double dom = 1;
    double dedom = 1;
    int small = m<n? m-1:n-1;
    int big = m<n? n-1:m-1;
    for(int i=1;i<=small;i++)
    {
        dedom *= i;
        dom *= small+big+1-i;
    }
    return (int)(dom/dedom);
}

上面的代码求解了组合的结果，只需要做一次行或者列的扫描，所以时间复杂度是O(min(m,n))，而空间复杂度是O(1)。比起上面的两种解法更优。

不过这里有个弊端，就是如果代码中的dom和dedom如果不是double，而是用int，那么会很容易越界，因为这是一个阶乘，

所以大家在面试中讨论这种方法要注意和提及越界的问题。

上面介绍了几种方法来求解这个问题，其实都是比较简单的模型，只是提供了不同的思路。Unique Paths II是这道题的扩展，给机器人增加了障碍，

有兴趣的朋友可以联系一下




