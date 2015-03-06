# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return nothing, do it in place
    def flatten(self, root):
        if root == None:            #递归终止条件
            return
        self.flatten(root.left)     #左右递归
        self.flatten(root.right)
        p = root                    #设一节点p从root开始
        if p.left == None:          #如果p的左节点为空 表示p或p以下的右节点已经flatten为linked list 直接return结束
            return
        p = p.left                  #否则到p，即当前root的左节点 准备连接当前root的左右子树
        while p.right:              #循环走到左子树最右子结点
            p = p.right
        p.right = root.right        #三步连接： 1 将左子树最右子节点连接到root右子树的头节点
        root.right = root.left                 # 2 将左子树插入到根节点与右子树之间
        root.left = None                       # 3 将根节点的左指针设为空





由上面可以看出：这道题的意思是将一颗二叉树平化（flatten）为一条链表，而链表的顺序为二叉树的先序遍历。

解题思路：首先将左右子树分别平化为链表，这两条链表的顺序分别为左子树的先序遍历和右子树的先序遍历。然后将左子树链表插入到根节点和右子树链表之间，就可以了。左右子树的平化则使用递归实现。

代码：

复制代码
# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return nothing, do it in place
    def flatten(self, root):
        if root == None:
            return
        self.flatten(root.left)
        self.flatten(root.right)
        p = root
        if p.left == None:
            return
        p = p.left
        while p.right:
            p = p.right
        p.right = root.right
        root.right = root.left
        root.left = None