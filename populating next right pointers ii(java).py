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
解题思路：和"Populating Next Right Pointers in Each Node"这道题不同的一点是，这道题的二叉树不是满的二叉树，有些节点是没有的。

但是也可以按照递归的思路来完成。在编写递归的基准情况时需要将细节都考虑清楚：

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
            






/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root==null)
            return;
        if(root.left!=null && root.right!=null)
        {
            root.left.next = root.right;
            TreeLinkNode tmp = root.next;
            while(tmp!=null)
            {
                if(tmp.left!=null)
                {
                    root.right.next = tmp.left;
                    break;
                }
                if(tmp.right!=null)
                {
                    root.right.next = tmp.right;
                    break;
                }
                tmp = tmp.next;
            }
        }
        else if(root.left!=null)
        {
            TreeLinkNode tmp = root.next;
            while(tmp!=null)
            {
                if(tmp.left!=null)
                {
                    root.left.next = tmp.left;
                    break;
                }
                if(tmp.right!=null)
                {
                    root.left.next = tmp.right;
                    break;
                }
                tmp = tmp.next;
            }
        }
        else if(root.right!=null)
        {
            TreeLinkNode tmp = root.next;
            while(tmp!=null)
            {
                if(tmp.left!=null)
                {
                    root.right.next = tmp.left;
                    break;
                }
                if(tmp.right!=null)
                {
                    root.right.next = tmp.right;
                    break;
                }
                tmp = tmp.next;
            }
        }
        connect(root.right);
        connect(root.left);
    }
}

Note： 根据python版改的 如果要求常数空间复杂度 可能递归不能用 看code ganker解法








/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root==null)
            return;
        TreeLinkNode prevHead = root;
        TreeLinkNode currPtr = null;
        TreeLinkNode currLeftMost = null;
        while(prevHead!=null)
        {
            TreeLinkNode prevLevel = prevHead;
            while(prevLevel!=null)
            {
                if(prevLevel.left!=null)
                {
                    if(currLeftMost==null)
                    {
                        currLeftMost = prevLevel.left;
                        currPtr = currLeftMost;
                    }
                    else
                    {
                        currPtr.next = prevLevel.left;
                        currPtr = currPtr.next;
                    }
                }
                if(prevLevel.right!=null)
                {
                    if(currLeftMost==null)
                    {
                        currLeftMost = prevLevel.right;
                        currPtr = currLeftMost;
                    }
                    else
                    {
                        currPtr.next = prevLevel.right;
                        currPtr = currPtr.next;
                    }
                }
                prevLevel = prevLevel.next;
            }
            prevHead = currLeftMost;
            currLeftMost = null;
        }
    }
}

Note: 这题解法跟上一题一模一样！！ 这里面已经有判断左右节点是否存在  记住这个就可以了










from code ganker:

这道题目的要求和Populating Next Right Pointers in Each Node是一样的，

只是这里的二叉树没要求是完全二叉树。其实在实现Populating Next Right Pointers in Each Node的时候我们已经兼容了不是完全二叉树的情况，

其实也比较好实现，就是在判断队列结点时判断一下他的左右结点是否存在就可以了。具体算法就不再分析了，

不熟悉的朋友可以看看Populating Next Right Pointers in Each Node。时间复杂度和空间复杂度不变，还是O(n)和O(1)。代码如下：

public void connect(TreeLinkNode root) {
    if(root == null)
        return;
    TreeLinkNode lastHead = root;
    TreeLinkNode pre = null;
    TreeLinkNode curHead = null;
    while(lastHead!=null)
    {
        TreeLinkNode lastCur = lastHead;
        while(lastCur != null)
        {
            if(lastCur.left!=null)
            {
                if(curHead == null)
                {
                    curHead = lastCur.left;
                    pre = curHead;
                }
                else
                {
                    pre.next = lastCur.left;
                    pre = pre.next;
                }
            }
            if(lastCur.right!=null)
            {
                if(curHead == null)
                {
                    curHead = lastCur.right;
                    pre = curHead;
                }
                else
                {
                    pre.next = lastCur.right;
                    pre = pre.next;
                }
            }                
            lastCur = lastCur.next;

        }
        lastHead = curHead;
        curHead = null;
    }
}

这道题本质是树的层序遍历，只是队列改成用结点自带的链表结点来维护。

