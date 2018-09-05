Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

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




Python:
         



Java:
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
        while (root != null) {
            TreeLinkNode tmp = new TreeLinkNode(0);
            TreeLinkNode curr = tmp;
            while (root != null) {
                if (root.left != null) { 
                    curr.next = root.left; 
                    curr = curr.next;
                }
                if (root.right != null) { 
                    curr.next = root.right; 
                    curr = curr.next;
                }
                root = root.next;
            }
            root = tmp.next;
        }
    }
}

https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/37811/Simple-solution-using-constant-space





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

