题意：

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.

解题思路：解码有多少种方法。一般求“多少”我们考虑使用dp。状态方程如下：

　　　　　当s[i-2:i]这两个字符是10~26但不包括10和20这两个数时，比如21，那么可以有两种编码方式（BA，U），所以dp[i]=dp[i-1]+dp[i-2]

　　　　　当s[i-2:i]等于10或者20时，由于10和20只有一种编码方式，所以dp[i]=dp[i-2]

　　　　   当s[i-2:i]不在以上两个范围时，如09这种，编码方式为0，而31这种，dp[i]=dp[i-1]。

　　　　   注意初始化时：dp[0]=1,dp[1]=1

代码：


class Solution:
    # @param s, a string
    # @return an integer
    def numDecodings(self, s):
        if s=="" or s[0]=='0': return 0
        dp=[1,1]
        for i in range(2,len(s)+1):
            if 10 <=int(s[i-2:i]) <=26 and s[i-1]!='0':
                dp.append(dp[i-1]+dp[i-2])
            elif int(s[i-2:i])==10 or int(s[i-2:i])==20:
                dp.append(dp[i-2])
            elif s[i-1]!='0':
                dp.append(dp[i-1])
            else:
                return 0




public class Solution {
    public int numDecodings(String s) {
        if(s==null || s.length()==0 || s.charAt(0)=='0')
            return 0;
        int curr = 0, prev1 = 1, prev2 = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2')
                    curr = prev2;
                else
                    return 0;
            }
            else if (s.charAt(i-1) == '0' || s.charAt(i-1) >= '3') 
                curr = prev1;

            else if (s.charAt(i-1) == '2' && s.charAt(i) >= '7')
                curr = prev1;
                
            else
                curr = prev1 + prev2;
                
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}

O(n) O(1)





这道题要求解一个数字串按照字符串编码方式可解析方式的数量。看到这种求数量的，我们很容易想到动态规划来存储前面信息，然后迭代得到最后结果。

我们维护的量res[i]是表示前i个数字有多少种解析的方式，接下来来想想递归式，有两种方式：第一种新加进来的数字不然就是自己比较表示一个字符，

那么解析的方式有res[i-1]种，第二种就是新加进来的数字和前一个数字凑成一个字符，解析的方式有res[i-2]种（因为上一个字符和自己凑成了一个）。

当然这里要判断前面说的两种情况能否凑成一个字符，也就是范围的判断，如果可以才有对应的解析方式，如果不行，那么就是0。

最终结果就是把这两种情况对应的解析方式相加。这里可以把范围分成几个区间：
（1）00：res[i]=0（无法解析，没有可行解析方式）；
（2）10, 20：res[i]=res[i-2]（只有第二种情况成立）；
（3）11-19, 21-26：res[i]=res[i-1]+res[i-2]（两种情况都可行）；
（4）01-09, 27-99：res[i]=res[i-1]（只有第一种情况可行）；

递推式就是按照上面的规则来得到，接下来我们只要进行一遍扫描，然后依次得到维护量就可以了，算法的时间复杂度是O(n)。

空间上可以看出我们每次只需要前两位的历史信息，所以只需要维护三个变量然后迭代赋值就可以了，所以空间复杂度是O(1)。代码如下：




public int numDecodings(String s) {
    if(s==null || s.length()==0 || s.charAt(0)=='0')
    {
        return 0;
    }
    int num1=1;
    int num2=1;
    int num3=1;
    for(int i=1;i<s.length();i++)
    {
        if(s.charAt(i)=='0')
        {
            if(s.charAt(i-1)=='1' || s.charAt(i-1)=='2')
                num3 = num1;
            else
                return 0;
        }
        else
        {
            if(s.charAt(i-1)=='0' || s.charAt(i-1)>='3')
                num3 = num2;
            else
            {
                if(s.charAt(i-1)=='2' && s.charAt(i)>='7' && s.charAt(i)<='9')
                    num3 = num2;
                else
                    num3 = num1+num2;
            }
        }
        num1 = num2;
        num2 = num3;
    }
    return num2;
}

这道题是一维动态规划的题目，递推式关系来说是比较容易得到的，主要是要对这些两位数进行划分有一些细节，容易出小错误

from code ganker评论

public int numDecodings(String s) {
    if (s == null || s.length() == 0)
    return 0;

    int len = s.length();
    int[] dp = new int[len + 1];

    dp[len] = 1;

    for (int i = len - 1; i >= 0; i--) {
        if (s.charAt(i) != '0') {
            dp[i] = dp[i + 1];
            if (i < len - 1 && Integer.parseInt(s.substring(i, i + 2)) <= 26)
                dp[i] += dp[i + 2];
        }
    }

    return dp[0];
}