<<<<<<< HEAD
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
=======
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
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
        return dp[len(s)]