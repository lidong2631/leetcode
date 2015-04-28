<<<<<<< HEAD
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        if(len1+len2!=s3.length())      //不需要判断null的情况 只需要初始检查这个条件即可
            return false;
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        for(int i=0; i<len1; i++)
            dp[i+1][0] = dp[i][0] && s3.charAt(i)==s1.charAt(i);
        for(int j=0; j<len2; j++)
            dp[0][j+1] = dp[0][j] && s3.charAt(j)==s2.charAt(j);
        for(int i=0; i<len1; i++)
            for(int j=0; j<len2; j++)
                dp[i+1][j+1] = (dp[i][j+1] && s3.charAt(i+j+1)==s1.charAt(i)) || (dp[i+1][j] && s3.charAt(i+j+1)==s2.charAt(j));
        return dp[len1][len2];
    }
}

Note: 这个版本跟pyhton版一样 只是下标是按code ganker的程序取得 code ganker程序跟python版实际一样也是二位dp 

只是它空间只用了一维数组 不太清楚为什么可以 以后再说 先理解思路







from code ganker:

这是一道关于字符串操作的题目，要求是判断一个字符串能不能由两个字符串按照他们自己的顺序，每次挑取两个串中的一个字符来构造出来。

像这种判断能否按照某种规则来完成求是否或者某个量的题目，很容易会想到用动态规划来实现。

先说说维护量，res[i][j]表示用s1的前i个字符和s2的前j个字符能不能按照规则表示出s3的前i+j个字符，

如此最后结果就是res[s1.length()][s2.length()]，判断是否为真即可。接下来就是递推式了，假设知道res[i][j]之前的所有历史信息，

我们怎么得到res[i][j]。可以看出，其实只有两种方式来递推，一种是选取s1的字符作为s3新加进来的字符，另一种是选s2的字符作为新进字符。

而要看看能不能选取，就是判断s1(s2)的第i(j)个字符是否与s3的i+j个字符相等。如果可以选取并且对应的res[i-1][j](res[i][j-1])也为真，

就说明s3的i+j个字符可以被表示。这两种情况只要有一种成立，就说明res[i][j]为真，是一个或的关系。所以递推式可以表示成

res[i][j] = res[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1) || res[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1)

时间上因为是一个二维动态规划，所以复杂度是O(m*n)，m和n分别是s1和s2的长度。最后就是空间花费，可以看出递推式中只需要用到上一行的信息，

所以我们只需要一个一维数组就可以完成历史信息的维护，为了更加优化，我们把短的字符串放在内层循环，这样就可以只需要短字符串的长度即可，

所以复杂度是O(min(m,n))。代码如下：

public boolean isInterleave(String s1, String s2, String s3) {
    if(s1.length()+s2.length()!=s3.length())
        return false;
    String minWord = s1.length()>s2.length()?s2:s1;
    String maxWord = s1.length()>s2.length()?s1:s2;
    boolean[] res = new boolean[minWord.length()+1];
    res[0] = true;
    for(int i=0;i<minWord.length();i++)
    {
        res[i+1] = res[i] && minWord.charAt(i)==s3.charAt(i);
    }
    for(int i=0;i<maxWord.length();i++)
    {
        res[0] = res[0] && maxWord.charAt(i)==s3.charAt(i);
        for(int j=0;j<minWord.length();j++)
        {
            res[j+1] = res[j+1]&&maxWord.charAt(i)==s3.charAt(i+j+1) || res[j]&&minWord.charAt(j)==s3.charAt(i+j+1);
        }
    }
    return res[minWord.length()];
}

动态规划其实还是有套路的，无非就是找到维护量，然后得到递推式，接下来看看历史信息对于空间的需求，尽量优化，会在后面对于动态规划做一个比较通用的总结哈。
