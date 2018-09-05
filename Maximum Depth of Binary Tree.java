Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.




# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return an integer
    def maxDepth(self, root):
        if root == None:
            return 0
        return max(self.maxDepth(root.left)+1, self.maxDepth(root.right)+1)

Note: too easy to explain. could think other solutions




Java:
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
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

Note: 上面是递归解法 下面是非递归解法 非递归解法用的层序遍历 都是从code ganker来地 不难看看就好





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
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int currNum = 1, nextNum = 0, level = 1;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            currNum--;
            if (curr.left == null && curr.right == null && queue.isEmpty())
                return level;
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
        return level;
    }
}





from code ganker:

这是一道比较简单的树的题目，可以有递归和非递归的解法，递归思路简单，返回左子树或者右子树中大的深度加1，作为自己的深度即可，代码如下：

public int maxDepth(TreeNode root) {
    if(root == null)
        return 0;
    return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
}

非递归解法一般采用层序遍历(相当于图的BFS），因为如果使用其他遍历方式也需要同样的复杂度O(n). 层序遍历理解上直观一些，维护到最后的level便是树的深度。代码如下：

public int maxDepth(TreeNode root) {
    if(root == null)
        return 0;
    int level = 0;
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    int curNum = 1; //num of nodes left in current level
    int nextNum = 0; //num of nodes in next level
    while(!queue.isEmpty())
    {
        TreeNode n = queue.poll();
        curNum--;
        if(n.left!=null)
        {
            queue.add(n.left);
            nextNum++;
        }
        if(n.right!=null)
        {
            queue.add(n.right);
            nextNum++;
        }
        if(curNum == 0)
        {
            curNum = nextNum;
            nextNum = 0;
            level++;
        }
    }
    return level;
}

总体来说我觉得这道题可以考核树的数据结构，也可以看看对递归和非递归的理解。相关的扩展可以是Minimum Depth of Binary Tree.