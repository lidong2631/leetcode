# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
#         self.next = None

class Solution:
    # @param root, a tree node
    # @return nothing
    def connect(self, root):
        if root == None:                #如果root为空 直接返回
            return
        if root and root.left:          #如果root和其左节点都存在 将左节点指向右节点
            root.left.next = root.right
            if root.next:               #如果root存在next指针 将右节点指向root next节点的左节点
                root.right.next = root.next.left
            else:                       #否则设root右孩子next为空
                root.right.next = None
        self.connect(root.left)         #递归调用
        self.connect(root.right)






题意：

         1
       /  \
      2    3
     / \  / \
    4  5  6  7
变为：
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
解题思路：看到二叉树我们就想到需要使用递归的思路了。直接贴代码吧，思路不难。

代码：

复制代码
# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
#         self.next = None

class Solution:
    # @param root, a tree node
    # @return nothing
    def connect(self, root):
        if root and root.left:
            root.left.next = root.right
            if root.next:
                root.right.next = root.next.left
            else:
                root.right.next = None
            self.connect(root.left)
            self.connect(root.right)