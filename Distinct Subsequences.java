题意：

Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.

解题思路：

这道题使用动态规划来解决。题的意思是：S的所有子串中，有多少子串是T。

下面来看看状态转移方程。dp[i][j]表示S[0...i-1]中有多少子串是T[0...j-1]。

当S[i-1]=T[j-1]时：dp[i][j]=dp[i-1][j-1]+dp[i-1][j]；S[0...i-1]中有多少子串是T[0...j-1]包含：

{S[0...i-2]中有多少子串是T[0...j-2]}+{S[0...i-2]中有多少子串是T[0...j-1]}

当S[i-1]!=T[j-1]时：dp[i][j]=dp[i-1][j-1]

那么初始化状态如何确定呢：dp[i][0]=1；S[0...i-1]只有一个子串是空串。

代码：


class Solution:
    # @return an integer
    # @dp
    # dp[i][j] means how many first j of T is sub of first i of S.
    def numDistinct(self, S, T):
        dp = [[0 for i in range(len(T)+1)] for j in range(len(S)+1)]
        for j in range(len(S)+1):
            dp[j][0] = 1
        for i in range(1, len(S)+1):
            for j in range(1, min(i+1, len(T)+1)):
                if S[i-1] == T[j-1]:
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1]
                else:
                    dp[i][j] = dp[i-1][j]
        return dp[len(S)][len(T)]




public class Solution {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= Math.min(t.length(), i); j++) {    // careful
                dp[i][j] = dp[i-1][j] + ((s.charAt(i-1) == t.charAt(j-1)) ? dp[i-1][j-1] : 0);
            }
        }
        return dp[s.length()][t.length()];
    }
}

S = "rabbbit", T = "rabbit"

Return 3.


from code ganker:

这道题应该很容易感觉到是动态规划的题目。还是老套路，先考虑我们要维护什么量。这里我们维护res[i][j]，

对应的值是S的前i个字符和T的前j个字符有多少个可行的序列（注意这道题是序列，不是子串，也就是只要字符按照顺序出现即可，不需要连续出现）。

下面来看看递推式，假设我们现在拥有之前的历史信息，我们怎么在常量操作时间内得到res[i][j]。假设S的第i个字符和T的第j个字符不相同，

那么就意味着res[i][j]的值跟res[i-1][j]是一样的，前面该是多少还是多少，而第i个字符的加入也不会多出来任何可行结果。

如果S的第i个字符和T的第j个字符相同，那么所有res[i-1][j-1]中满足的结果都会成为新的满足的序列，当然res[i-1][j]的也仍是可行结果，

所以res[i][j]=[i-1][j-1]+res[i-1][j]。所以综合上面两种情况，递推式应该是res[i][j]=(S[i]==T[j]?res[i-1][j-1]:0)+res[i][j]。

算法进行两层循环，时间复杂度是O(m*n)，而空间上只需要维护当前i对应的数据就可以，也就是O(m)。代码如下：

public int numDistinct(String S, String T) {
    if(T.length()==0)
    {
        return 1;
    }
    if(S.length()==0)
        return 0;
    int[] res = new int[T.length()+1];
    res[0] = 1;
    for(int i=0;i<S.length();i++)
    {
        for(int j=T.length()-1;j>=0;j--)
        {
            res[j+1] = (S.charAt(i)==T.charAt(j)?res[j]:0)+res[j+1];
        }
    }
    return res[T.length()];
}

可以看到代码跟上面推导的递推式下标有点不同，因为下标从0开始，这种细节在实现的时候比较能想清楚，
这里res[j+1]相当于T的前j个字符对应的串，少看一个。而res[0]表示一个字符都没有时的结果，最后结果返回res[T.length()]，
对应于整个字符串的可行序列的数量。

