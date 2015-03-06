class Solution:
    # @param grid, a list of lists of integers
    # @return an integer
    def minPathSum(self, grid):
        row = len(grid); col = len(grid[0])
        dp = [[0 for i in range(col)] for j in range(row)]      #初始化dp为0
        dp[0][0] = grid[0][0]           #dp最左上点为grid[0][0]
        for i in range(1, row):         #初始化dp第一行 每一点为上一点值加这个grid的值 后面状态转移方程要以此为基础
            dp[i][0] = dp[i-1][0] + grid[i][0]
        for j in range(1, col):             #初始化dp第一列 每一点为上一点值加这个grid的值 后面状态转移方程要以此为基础
            dp[0][j] = dp[0][j-1] + grid[0][j]
        for i in range(1, row):             #从［1］［1］开始， 状态转移方程为当前值dp取上一个dp点与左一个dp点的最小值加上当前grid的值
            for j in range(1, col):
                dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
        return dp[row-1][col-1]





题意：

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

解题思路：这道题也是比较简单的动态规划，注意矩阵下标问题就行了。

代码：

复制代码
class Solution:
    # @param grid, a list of lists of integers
    # @return an integer
    def minPathSum(self, grid):
        m = len(grid); n = len(grid[0])
        dp = [[0 for i in range(n)] for j in range(m)]
        dp[0][0] = grid[0][0]
        for i in range(1, n):
            dp[0][i] = dp[0][i-1] + grid[0][i]
        for i in range(1, m):
            dp[i][0] = dp[i-1][0] + grid[i][0]
        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
        return dp[m-1][n-1]




public class Solution {
    public int minPathSum(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0)
            return 0;
        int[] res = new int[grid[0].length];
        res[0] = grid[0][0];
        for(int i=1; i<grid[0].length; i++)
            res[i] = res[i-1] + grid[0][i];     //注意
        for(int i=1; i<grid.length; i++)            //注意
        {
            for(int j=0; j<grid[0].length; j++)
            {
                if(j==0)
                    res[j] += grid[i][j];
                else
                    res[j] = Math.min(res[j], res[j-1]) + grid[i][j];
            }
        }
        return res[grid[0].length-1];
    }
}

Note: 这道题也是动态规划 和unique path i ii差不多 理解了就很简单






from code ganker:

这道题跟Unique Paths，Unique Paths II是相同类型的。事实上，这道题是上面两道题的general版本，是寻找带权重的path。在Unique Paths中，

我们假设每个格子权重都是1，而在Unique Paths II中我们假设障碍格子的权重是无穷大，所以永远不会选择。剩下的区别就是这道题寻找这些路径中权重最小的，

而不是总路径数。其实方法是一样的，还是使用动态规划，只是现在维护的不是路径数，而是到达某一个格子的最短路径（也就是权重之和最小）。

而这个当前最短路径可以取前面一格和上面一格中小的最短路径长度得到，因此递推式是res[i][j]=min(res[i-1][j], res[i][j-1])+grid[i][j]。总共进行两层循环，

时间复杂度是O(m*n)。而空间复杂度仍是使用Unique Paths中的方法来省去一维，是O(m)，不了解的朋友可以看看哈。代码如下：

public int minPathSum(int[][] grid) {
    if(grid == null || grid.length==0 || grid[0].length==0)
        return 0;
    int[] res = new int[grid[0].length];
    res[0] = grid[0][0];
    for(int i=1;i<grid[0].length;i++)
    {
        res[i] = res[i-1]+grid[0][i];
    }
    for(int i=1;i<grid.length;i++)
    {
        for(int j=0;j<grid[0].length;j++)
        {
            if(j==0)
                res[j] += grid[i][j];
            else
                res[j] = Math.min(res[j-1], res[j])+grid[i][j];
        }
    }
    return res[grid[0].length-1];
}

这道题是动态规划的经典题型，模型足够简单，所以可能在简单的面试（比如电面）中出现。总体来说还是比较容易的，递推式比较直观。



