class Solution:
    # @param obstacleGrid, a list of lists of integers
    # @return an integer
    def uniquePathsWithObstacles(self, obstacleGrid):
        row = len(obstacleGrid); col = len(obstacleGrid[0])
        dp = [[0 for i in range(col)] for j in range(row)]      #初始化dp全为0
        for i in range(row):            #遍历第一列 如果obstacleGrid的值是0 则可以通过设对应dp值为1 否则不能通过直接break循环 后面dp值就是默认的0
            if obstacleGrid[i][0] == 0:
                dp[i][0] = 1
            else:
                break
        for j in range(col):            #同理 第一行
            if obstacleGrid[0][j] == 0:
                dp[0][j] = 1
            else:
                break
        for i in range(1, row):         #如果obstacleGrid的值为0 则表示可以通过 进行dp转移方程计算 否则dp对应值为0
            for j in range(1, col):
                if obstacleGrid[i][j] == 0:
                    dp[i][j] = dp[i-1][j] + dp[i][j-1]
                else:
                    dp[i][j] == 0
        return dp[row-1][col-1]





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
        if(obstacleGrid==null || obstacleGrid.length==0 || obstacleGrid[0].length==0)
            return 0;
        int max = obstacleGrid.length>obstacleGrid[0].length ? obstacleGrid.length:obstacleGrid[0].length;
        int min = obstacleGrid.length>obstacleGrid[0].length ? obstacleGrid[0].length:obstacleGrid.length;
        int[] res = new int[min];
        res[0] = 1;
        for(int i=0; i<max; i++) {
            for(int j=0; j<min; j++) {
                if(obstacleGrid.length>=obstacleGrid[0].length && obstacleGrid[i][j]==1)    //判断行列哪个大 然后判断对应的obstacleGrid[i][j](行大于等于列)或obstacleGrid[j][i](列大于行)是否等于1
                    res[j] = 0;
                else if(obstacleGrid[0].length>obstacleGrid.length && obstacleGrid[j][i]==1)
                    res[j] = 0;
                else if(j>0)
                    res[j]+=res[j-1];
            }
        }
        return res[min-1];
    }
}

取行和列中小的那个，然后把小的放在内层循环，空间复杂度就是O(min(m,n))




public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid==null || obstacleGrid.length==0 || obstacleGrid[0].length==0)
            return 0;
        int res[] = new int[obstacleGrid[0].length];
        res[0] = 1;         //这里必须写 不然这个case过不了[[0]]
        for(int i=0; i<obstacleGrid.length; i++) {      //从第一行第一列开始
            for(int j=0; j<obstacleGrid[0].length; j++) {
                if(obstacleGrid[i][j]==1)       //如果是1就设这个res[j]为0 没有路可走了
                    res[j]=0;
                else if(j>0)                //否则只要不是第一个元素 用动态规划转移方程 第一个元素永远不变一直等于res[0]
                    res[j] += res[j-1];
            }
        }
        return res[obstacleGrid[0].length-1];
    }
}

第二遍写的解法








public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid==null | obstacleGrid.length==0 || obstacleGrid[0].length==0)
            return 0;
        int[] res = new int[obstacleGrid[0].length];
        res[0] = 1;
        for(int i=0; i<obstacleGrid.length; i++)
        {
            for(int j=0; j<obstacleGrid[0].length; j++)     //j从0开始 因为每次要判断当前行第一个元素是不是障碍
            {
                if(obstacleGrid[i][j]==1)               //check
                    res[j] = 0;
                else
                {
                    if(j>0)                             //记得j>)才可以用res[j]+=res[j-1]
                        res[j] += res[j-1];
                }
            }
        }
        return res[obstacleGrid[0].length-1];
    }
}

Note: 这题跟上一题思路差不多 只是这里有障碍所以组合公式解法不能用 用动态规划 加个判断条件就可以了






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

