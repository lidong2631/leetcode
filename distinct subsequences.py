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


使用动态规划。初始化的时候注意空串是任意字符串的子串。dp[i][j]表示T的前j位是S的前i位的子串的情况数。递推公式是如果S的第i位等于T的第j位, dp[i][j] = dp[i-1][j-1] + dp[i-1][j], 如果不等, dp[i][j] = dp[i-1][j] 。

题意：

Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.

解题思路：这道题使用动态规划来解决。题的意思是：S的所有子串中，有多少子串是T。下面来看看状态转移方程。dp[i][j]表示S[0...i-1]中有多少子串是T[0...j-1]。

　　　　　当S[i-1]=T[j-1]时：dp[i][j]=dp[i-1][j-1]+dp[i-1][j]；S[0...i-1]中有多少子串是T[0...j-1]包含：{S[0...i-2]中有多少子串是T[0...j-2]}+{S[0...i-2]中有多少子串是T[0...j-1]}

　　　　   当S[i-1]!=T[j-1]时：dp[i][j]=dp[i-1][j]

　　　　   那么初始化状态如何确定呢：dp[i][0]=1；S[0...i-1]只有一个子串是空串。

