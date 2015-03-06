# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def dfs(self, start, end):
        if start > end:         #如果ｓｔａｒｔ比ｅｎｄ大说明这棵树的所有节点都已经被遍历过　返回[None]表示下面没有节点了
            return [None]
        tmp = []               #每次用一个ｌｉｓｔ　ｔｍｐ来存放当前树的根节点
        for rootval in range(start, end+1):         #循环对每一个节点都执行一次操作
            leftSub = self.dfs(start, rootval-1)    #递归建立当前根节点的左子树　ｂｉｎａｒｙ　ｓｅａｒｃｈ　ｔｒｅｅ的左小右大的逻辑在此建立(start, rootval-1), (rootval+1, end)
            rightSub = self.dfs(rootval+1, end)     #递归建立当前根节点的右子树
            for i in leftSub:           #建立根节点　添加左右子树　并将根节点ａｐｐｅｎｄ到ｔｍｐ　ｌｉｓｔ中去
                for j in rightSub:
                    root = TreeNode(rootval)
                    root.left = i
                    root.right = j
                    tmp.append(root)
        return tmp      #最后返回ｔｍｐ
    
    # @return a list of tree node
    def generateTrees(self, n):
        return self.dfs(1, n)

Note: 这题也要看草稿图　以ｎ=2为例　看下执行过程　比较好理解





题意：接上一题，这题要求返回的是所有符合条件的二叉查找树，而上一题要求的是符合条件的二叉查找树的棵数，我们上一题提过，求个数一般思路是动态规划，而枚举的话，我们就考虑dfs了。dfs(start, end)函数返回以start，start+1，...，end为根的二叉查找树。

代码：


# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @return a list of tree node
    def dfs(self, start, end):
        if start > end: return [None]
        res = []
        for rootval in range(start, end+1):　　　　　　　　#rootval为根节点的值，从start遍历到end
            LeftTree = self.dfs(start, rootval-1)
            RightTree = self.dfs(rootval+1, end)
            for i in LeftTree:　　　　　　　　　　　　　　　　#i遍历符合条件的左子树
                for j in RightTree:　　　　　　　　　　　　  #j遍历符合条件的右子树
                    root = TreeNode(rootval)
                    root.left = i
                    root.right = j
                    res.append(root)
        return res
    def generateTrees(self, n):
        return self.dfs(1, n)