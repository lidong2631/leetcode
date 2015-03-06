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
        if root == None:                        #如果root为None return
            return
        if root.left and root.right:            #如果左右节点都存在 首先将左右结点连接
            root.left.next = root.right
            tmp = root.next
            while tmp:                          #找到下一个能与root右节点连接的节点 while循环 一旦找到就break出循环
                if tmp.left:
                    root.right.next = tmp.left
                    break
                if tmp.right:
                    root.right.next = tmp.right
                    break
                tmp = tmp.next
        elif root.left:                         #如果只有左节点 步骤大致如上 只是用root左节点做连接
            tmp = root.next
            while tmp:
                if tmp.left:
                    root.left.next = tmp.left
                    break
                if tmp.right:
                    root.left.next = tmp.right
                    break
                tmp = tmp.next
        elif root.right:                        #如果只有右节点
            tmp = root.next
            while tmp:
                if tmp.left:
                    root.right.next = tmp.left
                    break
                if tmp.right;
                    root.right.next = tmp.right
                    break
                tmp = tmp.next
        self.connect(root.right)                #一定要先调用self.connect(root.right) 这样做连接时左边的节点才有点可以连接
        self.connect(root.left)






题意：

Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
 

For example,
Given the following binary tree,

         1
       /  \
      2    3
     / \    \
    4   5    7
 

After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
解题思路：和"Populating Next Right Pointers in Each Node"这道题不同的一点是，这道题的二叉树不是满的二叉树，有些节点是没有的。但是也可以按照递归的思路来完成。在编写递归的基准情况时需要将细节都考虑清楚：

代码一：

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
        if root:
            if root.left and root.right:
                root.left.next = root.right
                tmp = root.next
                while tmp:
                    if tmp.left: root.right.next = tmp.left; break
                    if tmp.right: root.right.next = tmp.right; break
                    tmp = tmp.next
            elif root.left:
                tmp = root.next
                while tmp:
                    if tmp.left: root.left.next = tmp.left; break
                    if tmp.right: root.left.next = tmp.right; break
                    tmp = tmp.next
            elif root.right:
                tmp = root.next
                while tmp:
                    if tmp.left: root.right.next = tmp.left; break
                    if tmp.right: root.right.next = tmp.right; break
                    tmp = tmp.next
            self.connect(root.right)
            self.connect(root.left)
            # @connect(root.right)should be the first!!!
            