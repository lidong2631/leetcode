题意：

Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,

There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.

解题思路：这道题是设置了障碍的，也是用动态规划解决。

代码：

复制代码
class Solution:
    # @param obstacleGrid, a list of lists of integers
    # @return an integer
    def uniquePathsWithObstacles(self, obstacleGrid):
        m = len(obstacleGrid); n = len(obstacleGrid[0])
        res = [[0 for i in range(n)] for j in range(m)]
        for i in range(m):
            if obstacleGrid[i][0] == 0:
                res[i][0] = 1
            else:
                res[i][0] == 0
                break
        for i in range(n):
            if obstacleGrid[0][i] == 0:
                res[0][i] = 1
            else:
                res[0][i] = 0
                break
        for i in range(1, m):
            for j in range(1, n):
                if obstacleGrid[i][j] == 1: res[i][j] = 0
                else:
                    res[i][j] = res[i-1][j] + res[i][j-1]
        return res[m-1][n-1]



public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] res = new int[n];
        res[0] = obstacleGrid[0][0] == '1' ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) res[j] = 0;
                else if (j > 0) res[j] += res[j-1];
            }
        }
        return res[n-1];
    }
}




from code ganker:

这道题跟Unique Paths非常类似，只是这道题给机器人加了障碍，不是每次都有两个选择（向右，向下）了。因为有了这个条件，

所以Unique Paths中最后一个直接求组合的方法就不适用了，这里最好的解法就是用动态规划了。递推式还是跟Unique Paths一样，只是每次我们要判断一下是不是障碍，如果是，

则res[i][j]=0，否则还是res[i][j]=res[i-1][j]+res[i][j-1]。实现中还是只需要一个一维数组，因为更新的时候所需要的信息足够了。

这样空间复杂度是是O(n)（如同Unique Paths中分析的，如果要更加严谨，我们可以去行和列中小的那个，然后把小的放在内层循环，空间复杂度就是O(min(m,n))，

时间复杂度还是O(m*n)。代码如下：

public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if(obstacleGrid == null || obstacleGrid.length==0 || obstacleGrid[0].length==0)
        return 0;
    int[] res = new int[obstacleGrid[0].length];
    res[0] = 1;
    for(int i=0;i<obstacleGrid.length;i++)
    {
        for(int j=0;j<obstacleGrid[0].length;j++)
        {
            if(obstacleGrid[i][j]==1)
            {
                res[j]=0;
            }
            else
            {
                if(j>0)
                    res[j] += res[j-1];
            }
        }
    }
    return res[obstacleGrid[0].length-1];
}

这里就不列出brute force递归方法的代码了，递归式和结束条件跟动态规划很近似，有兴趣的朋友可以写一下哈。

