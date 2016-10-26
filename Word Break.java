public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] res = new boolean[s.length()+1];
        res[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (res[j] && wordDict.contains(s.substring(j, i))) res[i] = true;
            }
        }
        return res[s.length()];
    }
}

Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".


public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        if(s==null || s.length()==0)
            return true;
        boolean[] res = new boolean[s.length()+1];
        res[0] = true;
        for(int i=0; i<s.length(); i++) {
            StringBuilder tmp = new StringBuilder(s.substring(0, i+1));
            for(int j=0; j<=i; j++) {
                if(res[j] && wordDict.contains(tmp.toString())) {
                    res[i+1] = true;
                    break;
                }
                tmp.deleteCharAt(0);
            }
        }
        return res[s.length()];
    }
}

O(n^2) O(n)

Time complexity for substring in java
http://stackoverflow.com/questions/4679746/time-complexity-of-javas-substring



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



