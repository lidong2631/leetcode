class Solution:
    # @return an integer
    def numDistinct(self, S, T):
        lenS = len(S); lenT = len(T)
        dp = [[0 for i in range(lenT+1)] for j in range(lenS+1)]    #先初始化dp全为0 注意i，j全都要取到lenS+1, lenT+1
        for i in range(lenS+1):             #将每行第一个元素设为0 因为空值是所有字符串的字串
            dp[i][0] = 1
        for i in range(1, lenS+1):                  #从第一位开始双循环比较
            for j in range(1, min(i+1, lenT+1)):        #注意j最多取到lenT+1 不能超过其自身长度
                if S[i-1] == T[j-1]:                        #比较当前S和T的字符 如果它们相等 当前dp值 = 把这个字符从S，T中分别去掉，剩余T中的字符串在S[0..i-1]字符串中出现的次数(即不考虑这个字符因为它在S,T中都出现了　单纯看去掉这个字符有多少可能　即是新增加的数目) +　当前Ｔ中的字符串在S[0..i-2]中的数目(因为当前字符不相同，　所以只能看加上这个字符的Ｔ字符串在去掉这个字符的S[0..i-2]字符串中可能的次数)
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
                else:                                           #如果不相等　则只看　当前Ｔ中的字符串在S[0..i-2]中的数目
                    dp[i][j] = dp[i-1][j]
        return dp[lenS][lenT]           #最后返回最终值dp[lenS][lenT]

Note: 看照片





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
    public int numDistinct(String S, String T) {
        if(S.length()==0)
            return 0;
        if(T.length()==0)
            return 1;
        int[][] dp = new int[S.length()+1][T.length()+1];
        for(int i=0; i<S.length()+1; i++)
            dp[i][0] = 1;
        for(int i=1; i<S.length()+1; i++)
        {
            for(int j=1; j<Math.min(i+1, T.length()+1); j++)
                dp[i][j] = dp[i-1][j] + (S.charAt(i-1)==T.charAt(j-1)?dp[i-1][j-1]:0);
        }
        return dp[S.length()][T.length()];
    }
}

Note：  python版版改编 空间复杂度比code ganker的解法多一点 用了二维数组







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

可以看到代码跟上面推导的递推式下标有点不同，因为下标从0开始，这种细节在实现的时候比较能想清楚，这里res[j+1]相当于T的前j个字符对应的串，少看一个。而res[0]表示一个字符都没有时的结果，最后结果返回res[T.length()]，对应于整个字符串的可行序列的数量。

