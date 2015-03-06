class Solution:
    # @param s, a string
    # @return an integer
    def minCut(self, s):
        dp = [0 for i in range(len(s)+1)]
        p = [[False for i in range(len(s))] for j in range(len(s))]
        for i in range(len(s)+1):
            dp[i] = len(s) - i
        for i in range(len(s)-1, -1, -1):
            for j in range(i, len(s)):
                if s[i] == s[j] and ((j - i < 2) or p[i+1][j-1]):
                    p[i][j] = True
                    dp[i] = min(1 + dp[j+1], dp[i])
        return dp[0] - 1

Note: 这个解法有TLE kitt的解法也有TLE 貌似python有点慢 第二遍过好好看看

题意：

Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

解题思路：由于这次不需要穷举出所有符合条件的回文分割，而是需要找到一个字符串s回文分割的最少分割次数，分割出来的字符串都是回文字符串。

求次数的问题，不需要dfs，用了也会超时，之前的文章说过，求次数要考虑动态规划（dp）。对于程序的说明：p[i][j]表示从字符i到j是否为一个回文字符串。

dp[i]表示从第i个字符到最后一个字符，最少的分割次数下，有多少个回文字符串，即分割次数+1。这道题动态规划的思路比较简单，直接上代码吧。

代码：


class Solution:
    # @param s, a string
    # @return an integer
    # @dfs time out
    # @dp is how many palindromes in the word
    def minCut(self, s):
        dp = [0 for i in range(len(s)+1)]
        p = [[False for i in range(len(s))] for j in range(len(s))]
        for i in range(len(s)+1):
            dp[i] = len(s) - i
        for i in range(len(s)-1, -1, -1):
            for j in range(i, len(s)):
                if s[i] == s[j] and (((j - i) < 2) or p[i+1][j-1]):
                    p[i][j] = True
                    dp[i] = min(1+dp[j+1], dp[i])
        return dp[0]-1





public class Solution {
    public int minCut(String s) {
        if(s==null || s.length()==0)
            return 0;
        boolean[][] isPalin = getDict(s);
        int[] res = new int[s.length()+1];
        res[0] = 0;
        for(int i=0; i<s.length(); i++)
        {
            res[i+1] = i+1;     //这里res[i+1]=i+1 而不是i因为字符串起始位置也要切一刀 相当于"abc"在a左边也切一刀 左边是空串 加上ab之间bc之间一共3刀
            for(int j=0; j<=i; j++)
            {
                if(isPalin[j][i])
                    res[i+1] = Math.min(res[i+1], res[j]+1);    //这里比较去小的数
            }
        }
        return res[s.length()]-1;   //最后再减掉多的那一刀
    }
    
    private boolean[][] getDict(String s) {
        boolean[][] dict = new boolean[s.length()][s.length()];
        for(int i=s.length()-1;i>=0;i--)
        {
            for(int j=i;j<s.length();j++)
            {
                if(s.charAt(i)==s.charAt(j) && (j-i<2 || dict[i+1][j-1]))
                    dict[i][j] = true;
            }
        }
        return dict;
    }
}

Note: 三个地方注意 加注释的   这题跟word break很像 但不好理解





from code ganker:

这道题跟Palindrome Partitioning非常类似，区别就是不需要返回所有满足条件的结果，而只是返回最小的切割数量就可以。

做过Word Break的朋友可能马上就会想到，其实两个问题非常类似，当我们要返回所有结果（Palindrome Partitioning和Word Break II）的时候，

使用动态规划会耗费大量的空间来存储中间结果，所以没有明显的优势。而当题目要求是返回某个简单量（比如Word Break是返回能否切割，

而这道题是返回最小切割数）时，那么动态规划比起brute force就会有明显的优势。这道题先用Palindrome Partitioning中的方法建立字典，

接下来动态规划的方式和Word Break是完全一样的，我们就不再细说了，不熟悉的朋友可以看看Word Break的分析哈。

因为保存历史信息只需要常量时间就能完成，进行两层循环，时间复杂度是O(n^2)。空间上需要一个线性数组来保存信息，所以是O(n)。代码如下：

public int minCut(String s) {
    if(s == null || s.length()==0)
        return 0;
    boolean[][] dict = getDict(s);      //得到s对应的dict
    int[] res = new int[s.length()+1];  //储存结果 相当于python解法中的dp[]
    res[0] = 0;
    for(int i=0;i<s.length();i++)       //两题解法完全一样 只是这里将代码单独提出来写在了getDict里
    {
        res[i+1] = i+1;
        for(int j=0;j<=i;j++)
        {
            if(dict[j][i])
            {
                res[i+1] = Math.min(res[i+1],res[j]+1);
            }
        }
    }
    return res[s.length()]-1;
}
private boolean[][] getDict(String s)       //这里跟python解法相同 根据一个字符串s得到一个数组dict[i][j] 表示从字符i到j是否为一个回文字符串
{
    boolean[][] dict = new boolean[s.length()][s.length()];
    for(int i=s.length()-1;i>=0;i--)
    {
        for(int j=i;j<s.length();j++)
        {
            if(s.charAt(i)==s.charAt(j) && (j-i<2 || dict[i+1][j-1]))
                dict[i][j] = true;
        }
    }
    return dict;
}

这个问题和Word Break可以说是一个题目，这里多了一步求解字典。如果求解所有结果时，他们没有多项式时间的解法，复杂度取决于结果数量，

而当求解某一种统计的特殊量时，用动态规划就会很大的优势，可以降低时间复杂度



