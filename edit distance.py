class Solution:
    # @return an integer
    def minDistance(self, word1, word2):
        dp = [[0 for j in range(len(word2)+1)] for i in range(len(word1)+1)]
        for i in range(len(word1)+1):
            dp[i][0] = i
        for j in range(len(word2)+1):
            dp[0][j] = j
        for i in range(1, len(word1)+1):
            for j in range(1, len(word2)+1):
                dp[i][j] = min(dp[i-1][j]+1, dp[i][j-1]+1, dp[i-1][j-1]+(0 if word1[i-1] == word2[j-1] else 1))
        return dp[len(word1)][len(word2)]

Note: 这题比较难以理解 结合照片理解会比较容易 二维动态规划






题意：

Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character

解题思路：这道题是很有名的编辑距离问题。用动态规划来解决。状态转移方程是这样的：dp[i][j]表示word1[0...i-1]到word2[0...j-1]的编辑距离。

而dp[i][0]显然等于i，因为只需要做i次删除操作就可以了。同理dp[0][i]也是如此，等于i，因为只需做i次插入操作就可以了。

dp[i-1][j]变到dp[i][j]需要加1，因为word1[0...i-2]到word2[0...j-1]的距离是dp[i-1][j]，而word1[0...i-1]到word1[0...i-2]需要执行一次删除，

所以dp[i][j]=dp[i-1][j]+1；同理dp[i][j]=dp[i][j-1]+1，因为还需要加一次word2的插入操作。

如果word[i-1]==word[j-1]，则dp[i][j]=dp[i-1][j-1]，如果word[i-1]!=word[j-1]，那么需要执行一次替换replace操作，所以dp[i][j]=dp[i-1][j-1]+1，

以上就是状态转移方程的推导。

代码：

复制代码
class Solution:
    # @return an integer
    def minDistance(self, word1, word2):
        m=len(word1)+1; n=len(word2)+1
        dp = [[0 for i in range(n)] for j in range(m)]
        for i in range(n):
            dp[0][i]=i
        for i in range(m):
            dp[i][0]=i
        for i in range(1,m):
            for j in range(1,n):
                dp[i][j]=min(dp[i-1][j]+1, dp[i][j-1]+1, dp[i-1][j-1]+(0 if word1[i-1]==word2[j-1] else 1))
        return dp[m-1][n-1]




public class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.length()==0)
            return word2.length();
        if(word2.length()==0)
            return word1.length();
        String minWord = word1.length()>word2.length()?word2:word1;
        String maxWord = word1.length()>word2.length()?word1:word2;
        int[] res = new int[minWord.length()+1];
        for(int i=0; i<minWord.length(); i++) { //跟空字符串比较
            res[i] = i;
        }
        for(int i=0; i<maxWord.length(); i++) {
            int[] tmp = new int[minWord.length()+1];
            tmp[0] = i+1;       //初始化tmp 意思为空字符串跟有i＋1个字符的序列需要i＋1次编辑
            for(int j=0; j<minWord.length(); j++) {
                if(minWord.charAt(j)==maxWord.charAt(i))
                    tmp[j+1] = res[j];
                else {
                    tmp[j+1] = Math.min(res[j], Math.min(tmp[j], res[j+1]))+1;  //三种情况 res[j]->res[i-1][j-1] tmp[j]->res[i][j-1]
                }
            }
            res = tmp;
        }
        return res[minWord.length()];
    }
}

res[i][j] = min(res[i-1][j], res[i][j-1], res[i-1][j-1])+1

code ganker版 二维降一维 看评论 O(m*n) O(min(m, n))






public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+2];
        for(int i=0; i<word1.length()+1; i++)
            dp[i][0] = i;
        for(int i=0; i<word2.length()+1; i++)
            dp[0][i] = i;
        for(int i=1; i<word1.length()+1; i++)
        {    
            for(int j=1; j<word2.length()+1; j++)
            {    
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i-1][j]+1, dp[i][j-1]+1), dp[i-1][j-1]+1);
            }
        }
        return dp[word1.length()][word2.length()];
    }
}

Note: 根据python版改编 二维dp问题 code ganker思路跟python版一样 只是空间上优化了下






from code ganker:

这道题求一个字符串编辑成为另一个字符串的最少操作数，操作包括添加，删除或者替换一个字符。这道题难度是比较大的，用常规思路出来的方法一般都是brute force，而且还不一定正确。

这其实是一道二维动态规划的题目，模型上确实不容易看出来，下面我们来说说递推式。

我们维护的变量res[i][j]表示的是word1的前i个字符和word2的前j个字符编辑的最少操作数是多少。假设我们拥有res[i][j]前的所有历史信息，

看看如何在常量时间内得到当前的res[i][j]，我们讨论两种情况：

1）如果word1[i-1]=word2[j-1]，也就是当前两个字符相同，也就是不需要编辑，那么很容易得到res[i][j]=res[i-1][j-1]，因为新加入的字符不用编辑；

2）如果word1[i-1]!=word2[j-1]，那么我们就考虑三种操作，如果是插入word1，那么res[i][j]=res[i-1][j]+1，也就是取word1前i-1个字符和word2前j个字符的最好结果，

然后添加一个插入操作；如果是插入word2，那么res[i][j]=res[i][j-1]+1，道理同上面一种操作；如果是替换操作，那么类似于上面第一种情况，

但是要加一个替换操作（因为word1[i-1]和word2[j-1]不相等），所以递推式是res[i][j]=res[i-1][j-1]+1。上面列举的情况包含了所有可能性，有朋友可能会说为什么没有删除操作，

其实这里添加一个插入操作永远能得到与一个删除操作相同的效果，所以删除不会使最少操作数变得更好，因此如果我们是正向考虑，则不需要删除操作。取上面几种情况最小的操作数，

即为第二种情况的结果，即res[i][j] = min(res[i-1][j], res[i][j-1], res[i-1][j-1])+1。

接下来就是分析复杂度，算法时间上就是两层循环，所以时间复杂度是O(m*n)，空间上每一行只需要上一行信息，所以可以只用一维数组即可，我们取m, n中小的放入内层循环，

则复杂度是O(min(m,n))。代码如下：

public int minDistance(String word1, String word2) {
    if(word1.length()==0)
        return word2.length();
    if(word2.length()==0)
        return word1.length();
    String minWord = word1.length()>word2.length()?word2:word1;
    String maxWord = word1.length()>word2.length()?word1:word2;
    int[] res = new int[minWord.length()+1];
    for(int i=0;i<=minWord.length();i++)
    {
        res[i] = i;
    }
    for(int i=0;i<maxWord.length();i++)
    {
        int[] newRes = new int[minWord.length()+1];
        newRes[0] = i+1;
        for(int j=0;j<minWord.length();j++)
        {
            if(minWord.charAt(j)==maxWord.charAt(i))
            {
                newRes[j+1]=res[j];
            }
            else
            {
                newRes[j+1] = Math.min(res[j],Math.min(res[j+1],newRes[j]))+1;
            }
        }
        res = newRes;
    }
    return res[minWord.length()];
}

上面代码用了minWord, maxWord是为了使得空间是min(m,n)，细节做得比较细，面试的时候可能不用刻意这么做，提一下就好。

这道题目算是难度比较大的题目，所以在短时间的面试可能时间太紧了，所以也有变体。我自己在面试Google的时候，问的是如何判断edit distance是不是在1以内，

返回true或false就可以了。这样一改其实就没有必要动态规划了，只需要利用距离只有1这一点进行判断就可以，大概思路就是只要有一个不同，接下来就不能再有不同了，有兴趣的朋友可以想想哈。

