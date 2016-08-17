class Solution:
    # @return a boolean
    def isInterleave(self, s1, s2, s3):
        if len(s1) + len(s2) != len(s3):            #如果长度不等直接返回False
            return False
        dp = [[False for i in range(len(s2)+1)] for j in range(len(s1)+1)]  #初始化dp二维数组都为False
        dp[0][0] = True     #dp[0][0]为True
        for i in range(1, len(s1)+1):                   #初始化第一行第一列 单独判断s1,s2是否为s3
            dp[i][0] = dp[i-1][0] and s1[i-1] == s3[i-1]
        for j in range(1, len(s2)+1):
            dp[0][j] = dp[0][j-1] and s2[j-1] == s3[j-1]
        for i in range(1, len(s1)+1):               #二维循环 两种情况下dp[i][j]为真 1 dp[i-1][j]为真且下一个字符s1[i-1]跟对应的s3[i+j-1]相同
            for j in range(1, len(s2)+1):               # 2 dp[i][j-1]为真且下一个字符s2[j-1]跟对应的s3[i+j-1]相同
                dp[i][j] = (dp[i-1][j] and s1[i-1] == s3[i+j-1]) or (dp[i][j-1] and s2[j-1] == s3[i+j-1])
        return dp[len(s1)][len(s2)]


Note: 看照片 有图比较好理解

解题思路：二维动态规划。dp[i][j]表示s1[0...i-1]和s2[0...j-1]是否可以拼接为s3[0...i+j-1]，可以拼接为true，不可以拼接为false。




dp[i][j]表示s1取前i位，s2取前j位，是否能组成s3的前i+j位
举个列子，注意左上角那一对箭头指向的格子dp[1][1], 表示s1取第1位a, s2取第1位d，是否能组成s3的前两位aa

从dp[0][1] 往下的箭头表示，s1目前取了0位，s2目前取了1位，我们添加s1的第1位，看看它是不是等于s3的第2位，( i + j 位）

从dp[1][0] 往右的箭头表示，s1目前取了1位，s2目前取了0位，我们添加s2的第1位，看看它是不是等于s3的第2位，( i + j 位)




那什么时候取True，什么时候取False呢？

False很直观，如果不等就是False了嘛。

那True呢？首先第一个条件，新添加的字符，要等于s3里面对应的位( i + j 位)，第二个条件，之前那个格子也要等于True

举个简单的例子s1 = ab, s2 = c, s3 = bbc ，假设s1已经取了2位，c还没取，此时是False（ab!=bb），我们取s2的新的一位c，即便和s3中的c相等，但是之前是False，所以这一位也是False

同理，如果s1 = ab, s2 = c, s3=abc ，同样的假设，s1取了2位，c还没取，此时是True（ab==ab），我们取s2的新的一位c,和s3中的c相等，且之前这一位就是True，此时我们可以放心置True （abc==abc）



还有一点需要注意的是，String 的index是0 base的, 我们以dp[m+1][n+1] 正序遍历字符创造的矩阵是1 base