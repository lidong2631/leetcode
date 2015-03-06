class Solution:
    # @return a list of integers
    def getRow(self, rowIndex):
        actualRow =[1]                      #杨辉三角第一行[1]
        for row in range(0, rowIndex):      #循环每一行
            previousRow = actualRow         #上一行附值为上一次的actualRow
            actualRow = [1]                 #每一行第一个元素为1
            for i in range(1,len(previousRow)):                     #循环 从第二个元素开始附值 到上一行结尾个数为止
                actualRow.append(previousRow[i-1] + previousRow[i]) #每一个元素为上一行相邻两个元素相加
            actualRow.append(1)             #最后加上一个1为最后元素
        return actualRow



The following solution use O(k^2) space but still accepted by OJ
class Solution:
    # @return a list of integers
    def getRow(self, rowIndex):
        if rowIndex == 0:
            return [1]
        if rowIndex == 1:
            return [1,1]
        pascalTri = [[] for i in range(rowIndex+1)]
        pascalTri[0] = [1]
        pascalTri[1] = [1,1]
        for i in range(2, rowIndex+1):
            pascalTri[i] = [1 for j in range(i+1)]
            for j in range(1, i):
                pascalTri[i][j] = pascalTri[i-1][j-1] + pascalTri[i-1][j]
        return pascalTri[rowIndex]