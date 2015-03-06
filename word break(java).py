class Solution:
    # @param s, a string
    # @param dict, a set of string
    # @return a boolean
    def wordBreak(self, s, dict):
        dp = [False for i in range(len(s)+1)]   #初始化一个list dp 全赋值为False
        dp[0] = True                            #初始化dp[0]赋值为True 相当于空字符串''默认可以用字典的单词表示
        for i in range(1, len(s)+1):            #外循环遍历所有字母 注意循环从1开始到len(s)+1结束
            for k in range(i):                  #内循环遍历以i为结尾的子串 k循环i次
                if dp[k] and s[k:i] in dict:    #如果k之前的字符串可以用字典中的词表示 且从k开始到结束的这个子串s[k：i]在dict里 则到这个i为止的字符串也可以用字典中的词表示 见下面例子
                    dp[i] = True                #dp[i] = True
        return dp[len(s)]

Note： S = 'leetcode' dict = ['leet', 'code']

对于'leet' k一共循环4次 可以得到 

dp[0] and s[0:4]  ->  (dp['']   True, 'leet'    True)    True
dp[1] and s[1:4]  ->  (dp['l']  False, 'eet'    False)    False
dp[2] and s[2:4]  ->  (dp['le'] False, 'et'     False)    False
dp[3] and s[3:4]  ->  (dp['lee']    False, 't'  False)    False

对于'code'同理





解题思路：这道题考察的显然不是dfs，为什么？因为这道题不需要给出如何分割的答案，只需要判断是否可以分割为字典中的单词即可。

我们考虑使用动态规划，这个思路看代码的话不难，用python写起来也比较清晰。

代码：


class Solution:
    # @param s, a string
    # @param dict, a set of string
    # @return a boolean
    # @good coding!
    def wordBreak(self, s, dict):
        dp = [False for i in range(len(s)+1)]
        dp[0] = True
        for i in range(1, len(s)+1):
            for k in range(i):
                if dp[k] and s[k:i] in dict:
                    dp[i] = True
        return dp[len(s)]






public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if(s==null || s.length()==0) return false;
        int strLen = s.length();
        boolean[] dp = new boolean[strLen+1];       //注意布尔数组 dp长度时strLen＋1 dp[0]默认是true
        dp[0] = true;
        for(int i=1; i<strLen+1; i++)           //这里i代表的第一个字符就是s里的第0个字符
        {
            for(int j=0; j<=i; j++)         //内循环j从0开始到i结束
            {
                if(dp[j] && dict.contains(s.substring(j, i)))       //这里substring从j开始 因为dp[j]到字符串第j个字符 在s里是以0为底开始所以实际在s中到j－1字符
                {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[strLen];
    }
}

Note: 这题动态规划思想不难 不过细节很值得注意 顺便复习 java中的Set类



from code ganker:

这道题仍然是动态规划的题目，我们总结一下动态规划题目的基本思路。首先我们要决定要存储什么历史信息以及用什么数据结构来存储信息。

然后是最重要的递推式，就是如从存储的历史信息中得到当前步的结果。最后我们需要考虑的就是起始条件的值。

接下来我们套用上面的思路来解这道题。首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，

我们需要一个长度为n的布尔数组来存储信息。然后假设我们现在拥有res[0,...,i-1]的结果，我们来获得res[i]的表达式。

思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，如果都成立，那么res[i]为true，写成式子是

http://blog.csdn.net/linhuanmars/article/details/22358863

假设总共有n个字符串，并且字典是用HashSet来维护，那么总共需要n次迭代，每次迭代需要一个取子串的O(i)操作，然后检测i个子串，

而检测是constant操作。所以总的时间复杂度是O(n^2)（i的累加仍然是n^2量级），而空间复杂度则是字符串的数量，即O(n)。

public boolean wordBreak(String s, Set<String> dict) {
    if(s==null || s.length()==0)
        return true;
    boolean[] res = new boolean[s.length()+1];
    res[0] = true;
    for(int i=0;i<s.length();i++)
    {
        StringBuilder str = new StringBuilder(s.substring(0,i+1));
        for(int j=0;j<=i;j++)
        {
            if(res[j] && dict.contains(str.toString()))
            {
                res[i+1] = true;
                break;
            }
            str.deleteCharAt(0);
        }
    }
    return res[s.length()];
}

动态规划的题目在LeetCode中占有相当的比例，不过却没有什么通法，因为每道题会有不同的性质和获取信息的角度。

但是总体来说基本思路就如同我上面介绍的那样，根据步骤出来之后基本上问题也就解决了




