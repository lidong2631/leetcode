class Solution:
    # @param s, a string
    # @return an integer
    def numDecodings(self, s):
        if s == '' or s[0] == '0':      #排除s是空字符串， 0, 01, 02...09情况
            return 0
        dp = [1,1]                      #初始化dp dp表示字符串s[0...i-1]一共有dp[i]种解码方法
        for i in range(2, len(s)+1):        #循环遍历所有字符
            if 10 <= int(s[i-2:i]) <= 26 and int(s[i-1]) != 0:      #如果得到的字符串为10到26之间的数字且不包括10，20 则有两种解码方式dp[i-1]+dp[i-2]
                dp.append(dp[i-2] + dp[i-1])
            elif int(s[i-2:i]) == 10 or int(s[i-2:i]) == 20:        #如果字符串是10或20 只有一种方式dp[i] = dp[i-2]
                dp.append(dp[i-2])
            elif int(s[i-1]) != 0:          #如果字符串是01...09或大于26且第二位不为0的数字如31 只有一种方式 dp[i-1]
                dp.append(dp[i-1])
            else:               #如果是00或30，40。。。90这种情况 没有解码方式work 返回0
                return 0
        return dp[len(s)]

Note: 这一题的解题思路跟数台阶类似 动态规划 也是要么加一位要么加两位 不过本题多了很多限制条件要注意

题意解析：给你一串数字，解码成英文字母。
类似爬楼梯问题，但要加很多限制条件。
定义数组number，number[i]意味着：字符串s[0..i-1]可以有number[i]种解码方法。
回想爬楼梯问题一样，number[i] = number[i-1] + number[i-2]
但不同的是本题有多种限制：
第一： s[i-1]不能是0，如果s[i-1]是0的话，number[i]就只能等于number[i-2]
第二，s[i-2,i-1]中的第一个字符不能是0，而且Integer.parseInt(s.substring(i-2,i))获得的整数必须在0到26之间。

1010，生成的number数组为：[1,1,1,1,1]
10000，生成的number数组为：[1,1,1,0,0,0,0,0,0]



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
        return dp[len(s)]







public class Solution {
    public int numDecodings(String s) {
        if(s==null || s.length()==0 || s.charAt(0)=='0')
            return 0;
        int dp_1 = 1;                   //这里用三个变量就可以了 不用像python版用一个数组 空间复杂度为O(1)
        int dp_2 = 1;
        int dp = 1;
        for(int i=2; i<s.length()+1; i++)
        {
            int s1 = Integer.parseInt(s.substring(i-2,i));
            char s2 = s.charAt(i-1);
            if(s1>=10 && s1<=26 && s2!='0')
                dp = dp_1 + dp_2;
            else if(s1==10 || s1==20)
                dp = dp_2;
            else if(s2!='0')
                dp = dp_1;
            else
                return 0;
            dp_2 = dp_1;
            dp_1 = dp;
        }
        return dp_1;
    }
}

Note： 这题由python版改编 code ganker版没看懂






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
