class Solution:
    # @return a list of lists of integers
    def generate(self, numRows):                        #产生杨辉三角
        if numRows == 0:                                #如果numRows为0 返回[]
            return []
        if numRows == 1:                                #如果numRows为1 返回[[1]]
            return [[1]]
        if numRows == 2:                                #如果numRows为2 返回[[1],[1,1]]
            return [[1],[1,1]]
        if numRows > 2:                                 #如果numRows大于2 首先初始化list pascalTri为[[],[],[]...] 形式
            pascalTri = [[] for i in range(numRows)]
            for i in range(0, numRows):                 #然后初始化所有pascalTri元素为1 pascalTri为[[1],[1,1],[1,1,1],[1,1,1,1]...] 
                pascalTri[i] = [1 for j in range(0, i+1)]
            for i in range(2, numRows):                 #从第三行开始,每一行除两头的元素,其他元素等于上一行相邻两元素之和
                for j in range(1, i):
                    pascalTri[i][j] = pascalTri[i-1][j-1] + pascalTri[i-1][j]
        return pascalTri






题意：

Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
解题思路：杨辉三角的求解。

代码：

复制代码
class Solution:
    # @return a list of lists of integers
    def generate(self, numRows):
        if numRows == 0:
            return []
        if numRows == 1:
            return [[1]]
        if numRows == 2:
            return [[1], [1, 1]]
        if numRows > 2:
            list = [[] for i in range(numRows)]
            for i in range(0, numRows):
                list[i] = [1 for j in range(i + 1)]
            for i in range(2, numRows):
                for j in range(1, i):
                    list[i][j] = list[i - 1][j - 1] + list[i - 1][j]
            return list