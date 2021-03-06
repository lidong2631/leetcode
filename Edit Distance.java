Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')



Python:
class Solution:
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """
        m = len(word1) + 1; n = len(word2) + 1
        dp = [[0 for i in range(n)] for j in range(m)]
        for i in range(n):
            dp[0][i] = i
        for i in range(m):
            dp[i][0] = i
        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = min(dp[i-1][j]+1, dp[i][j-1]+1, dp[i-1][j-1]+(0 if word1[i-1] == word2[j-1] else 1))
        return dp[m-1][n-1]



Java:
public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i < word1.length() + 1; i++)
            dp[i][0] = i;
        for (int i = 0; i < word2.length() + 1; i++)
            dp[0][i] = i;
        for (int i = 1; i < word1.length() + 1; i++) {    
            for (int j = 1; j < word2.length() + 1; j++) {    
                if (word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i-1][j]+1, dp[i][j-1]+1), dp[i-1][j-1]+1);
            }
        }
        return dp[word1.length()][word2.length()];
    }
}

similar to distinct subsequence



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

