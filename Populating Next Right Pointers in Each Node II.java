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
        TreeLinkNode curr = null, currL = null, prevHead = root;
        while (prevHead != null) {
            TreeLinkNode prev = prevHead;
            while (prev != null) {
                if (prev.left != null) {
                    if (currL == null) {
                        currL = prev.left; 
                        curr = prev.left;
                    }
                    else {
                        curr.next = prev.left;
                        curr = curr.next;
                    }
                }
                if (prev.right != null) {
                    if (currL == null) {
                        currL = prev.right;
                        curr = prev.right;
                    }
                    else {
                        curr.next = prev.right;
                        curr = curr.next;
                    }
                }
                prev = prev.next;
            }
            prevHead = currL;
            currL = null;
        }
    }
}






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

