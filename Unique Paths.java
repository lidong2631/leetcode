A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28



Python:
class Solution:
    def uniquePaths(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """
        res = [0 for x in range(n)]
        res[0] = 1
        for i in range(m):
            for j in range(1, n):
                res[j] += res[j-1]
        return res[n-1]



Java:
public class Solution {
    public int uniquePaths(int m, int n) {
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[j] += res[j-1];
            }
        }
        return res[n-1];
    }
}
0 0 0
0 0 0
0 0 0



Golang:
func uniquePaths(m int, n int) int {
    res := make([]int, n)
    res[0] = 1
    for i := 0; i < m; i++ {
        for j := 1; j < n; j++ {
            res[j] += res[j-1]
        }
    }
    return res[n-1]
}

create n elems array: make([]int, n)




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




