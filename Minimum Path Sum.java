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



Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.



public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] res = new int[n];
        res[0] = grid[0][0];
        for (int i = 1; i < n; i++)
            res[i] = res[i-1] + grid[0][i];
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) res[j] += grid[i][0];
                else res[j] = grid[i][j] + Math.min(res[j], res[j-1]);
            }
        }
        return res[n-1];
    }
}





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



