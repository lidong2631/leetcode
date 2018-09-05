Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.





# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return an integer
    def minDepth(self, root):
        if root == None:
            return 0
        if root.left == None and root.right != None:
            return self.minDepth( root.right ) + 1
        if root.left != None and root.right == None:
            return self.minDepth( root.left ) + 1
        return min( self.minDepth( root.left ), self.minDepth( root.right ) ) + 1



Java:
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) 
            return 0;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null) 
            return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}



/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int currNum = 1;
        int nextNum = 0;
        int level = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            currNum--;
            if (curr.left == null && curr.right == null) {
                return level;
            }
            if (curr.left != null) {
                queue.offer(curr.left);
                nextNum++;
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                nextNum++;
            }
            if (currNum == 0) {
                currNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
        return 0; 
    }
}





from code ganker:

这道题是树的题目，其实跟Maximum Depth of Binary Tree非常类似，只是这道题因为是判断最小深度，所以必须增加一个叶子的判断（因为如果一个节点如果只有左子树或者右子树，

我们不能取它左右子树中小的作为深度，因为那样会是0，我们只有在叶子节点才能判断深度，而在求最大深度的时候，因为一定会取大的那个，所以不会有这个问题）。

这道题同样是递归和非递归的解法，递归解法比较常规的思路，比Maximum Depth of Binary Tree多加一个左右子树的判断，代码如下：

public int minDepth(TreeNode root) {
    if(root == null)
        return 0;
    if(root.left == null)
        return minDepth(root.right)+1;
    if(root.right == null)
        return minDepth(root.left)+1;
    return Math.min(minDepth(root.left),minDepth(root.right))+1;
}

非递归解法同样采用层序遍历(相当于图的BFS），只是在检测到第一个叶子的时候就可以返回了，代码如下：

public int minDepth(TreeNode root) {
    if(root == null)
        return 0;
    LinkedList queue = new LinkedList();
    int curNum = 0;
    int lastNum = 1;
    int level = 1;
    queue.offer(root);
    while(!queue.isEmpty())
    {
        TreeNode cur = queue.poll();
        if(cur.left==null && cur.right==null)
            return level;
        lastNum--;
        if(cur.left!=null)
        {
            queue.offer(cur.left);
            curNum++;
        }
        if(cur.right!=null)
        {
            queue.offer(cur.right);
            curNum++;
        }
        if(lastNum==0)
        {
            lastNum = curNum;
            curNum = 0;
            level++;
        }
    }
    return 0;
}


