Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3





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




Java:
public class Solution {
    public int numTrees(int n) {
        if (n <= 0) return 0;
        int[] res = new int[n+1];
        res[0] = res[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                res[i] += res[j] * res[i-j-1];
            }
        }
        return res[n];
    }
}

选取一个结点为根，就把结点切成左右子树，以这个结点为根的可行二叉树数量就是左右子树可行二叉树数量的乘积
时间O(n^2) 空间O(n)


   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


http://blog.csdn.net/fightforyourdream/article/details/14514649 解释的不错




from code ganker:

这道题要求可行的二叉查找树的数量，其实二叉查找树可以任意取根，只要满足中序遍历有序的要求就可以。从处理子问题的角度来看，选取一个结点为根，

就把结点切成左右子树，以这个结点为根的可行二叉树数量就是左右子树可行二叉树数量的乘积，所以总的数量是将以所有结点为根的可行结果累加起来。

写成表达式如下：

Count[i] = ∑ Count[i] * [ n-1-i]     0<=i<=n-1

熟悉卡特兰数的朋友可能已经发现了，这正是卡特兰数的一种定义方式，是一个典型的动态规划的定义方式（根据其实条件和递推式求解结果）。所以思路也很明确了，

维护量res[i]表示含有i个结点的二叉查找树的数量。根据上述递推式依次求出1到n的的结果即可。

时间上每次求解i个结点的二叉查找树数量的需要一个i步的循环，总体要求n次，所以总时间复杂度是O(1+2+...+n)=O(n^2)。空间上需要一个数组来维护，

并且需要前i个的所有信息，所以是O(n)。

public int numTrees(int n) {
    if(n<=0)
        return 0;
    int[] res = new int[n+1];
    res[0] = 1;
    res[1] = 1;
    for(int i=2;i<=n;i++)
    {
        for(int j=0;j<i;j++)
        {
            res[i] += res[j]*res[i-j-1];
        }
    }
    return res[n];
}

这种求数量的题目一般都容易想到用动态规划的解法，这道题的模型正好是卡特兰数的定义。当然这道题还可以用卡特兰数的通项公式来求解，

这样时间复杂度就可以降低到O(n)。因为比较直接，这里就不列举代码了。

如果是求解所有满足要求的二叉树（而不仅仅是数量）那么时间复杂度是就取决于结果的数量了，不再是一个多项式的解法了，

有兴趣的朋友可以看看Unique Binary Search Trees II。
