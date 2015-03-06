class Solution:
    # @param triangle, a list of lists of integers
    # @return an integer
    def minimumTotal(self, triangle):
        DP = [0 for i in range(len(triangle))]
        for row in triangle:            #遍历triangle的每一行
            prevDP = DP[:]              #prevDP总是复制上一次DP的结果 即当前DP的上一行
            for i in range(len(row)):       #对每一行的元素逐个遍历
                if i == 0:                      #如果是第一个元素 它的和只有一种情况 即所有行第一个元素相加
                    DP[i] = prevDP[i] + row[i]
                elif i == len(row) - 1:             #如果是最后一个元素 它也只有一种情况 即所有行最后一个元素相加
                    DP[i] = prevDP[i-1] + row[i]
                else:                                   #正常情况 当前元素最小路径和 = 当前元素值 + 上一行与当前元素相邻的两个元素的路径和较小的那个
                    DP[i] = min(prevDP[i-1], prevDP[i]) + row[i]
        return min(DP)      #最后返回最后一行中最小的值即为所求

Note: 动态规划, 使用两个数组, DP记录当前行信息, prevDP记录上一行信息。DP[i]的含义是从顶端走到当前行DP[i]这个元素的最小路径和。

看照片

