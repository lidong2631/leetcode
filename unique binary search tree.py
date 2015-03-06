class Solution:
    # @return an integer
    def numTrees(self, n):
        bst = [1, 1, 2]             #初始条件 当n＝0，1，2时对应值是1，1，2
        if n <= 2:
            return bst[n]           #若n小于等于2 直接返回对应值
        else:
            bst += [0 for i in range(n-2)]          #否则先插入n－2个0到list里准备存放对应的值
            for i in range(3, n+1):                 #从3开始循环到第n次
                for j in range(1, i+1):             #内循环对应每一个既定i 从1到i循环
                    bst[i] += bst[j-1] * bst[i-j]   #套用公式计算dp[n]=dp[0]*dp[n-1]+dp[1]*dp[n-2]+......+dp[n-1]*dp[0] 解释见下面
        return bst[n]




题意：

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
解题思路：这题从数学上讲，其实是卡特兰数。不过我们显然不从数学上来解决这个问题。这题是求二叉树的棵数。这里有个解题技巧：一般来说求数量，要首先想到使用动态规划（dp），

而如果是像下一题的要求，不只是数量，还要把所有的树都枚举出来，就要使用dfs（深度优先搜索）来遍历决策树了。

　　　　　那么这道题是使用动态规划来解决的。那么如何去求这个问题的状态转移方程呢？其实大部分动态规划的难点都是求状态转移方程。n=0时，为空树，

那么dp[0]=1; n=1时，显然也是1，dp[1]=1；n=2时，dp[2]=2; 对于n>2时，dp[n]=dp[0]*dp[n-1]+dp[1]*dp[n-2]+......+dp[n-1]*dp[0]；

这不就是卡特兰数的定义吗？编程很容易实现。

代码：

复制代码
class Solution:
    # @return an integer
    def numTrees(self, n):
        dp = [1, 1, 2]
        if n <= 2:
            return dp[n]
        else:
            dp += [0 for i in range(n-2)]
            for i in range(3, n + 1):
                for j in range(1, i+1):
                    dp[i] += dp[j-1] * dp[i-j]
            return dp[n]





这题想了好久才想清楚。其实如果把上例的顺序改一下，就可以看出规律了。
 1                  1                      2                       3              3
    \                 \                 /      \                  /              / 
      3                2              1         3                2              1
    /                   \                                       /                  \
 2                       3                                     1                    2

比如，以1为根的树有几个，完全取决于有二个元素的子树有几种。同理，2为根的子树取决于一个元素的子树有几个。以3为根的情况，则与1相同。

定义Count[i] 为以[1,i]能产生的Unique Binary Tree的数目，

如果数组为空，毫无疑问，只有一种BST，即空树，
Count[0] =1

如果数组仅有一个元素{1}，只有一种BST，单个节点
Count[1] = 1 [1]

如果数组有两个元素{1,2}， 那么有如下两种可能
1                       2
  \                    /
    2                1
Count[2] = Count[0] * Count[1]   (1为根的情况)   --> [1,2]  左边为空 count[0],右边只有一个元素count[1]
                  + Count[1] * Count[0]  (2为根的情况。  -->[1,2]  左边只有一个元素count[1]，右边为空count[0]

再看一遍三个元素的数组，可以发现BST的取值方式如下：
Count[3] = Count[0]*Count[2]  (1为根的情况)  [1,2,3]
               + Count[1]*Count[1]  (2为根的情况)[1,2,3]
               + Count[2]*Count[0]  (3为根的情况)[1,2,3]

所以，由此观察，可以得出Count的递推公式为
Count[i] = ∑ Count[i] * [ n-1-i]     0<=i<=n-1
问题至此划归为一维动态规划。