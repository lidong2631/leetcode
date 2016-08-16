题意：

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

解题思路：使用递归解决。

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
    # @param sum, an integer
    # @return a boolean
    def hasPathSum(self, root, sum):
        if root == None:
            return False
        if root.left == None and root.right == None:
            return root.val == sum
        return self.hasPathSum(root.left, sum - root.val) or self.hasPathSum(root.right, sum - root.val)




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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return sum == root.val;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}



from code ganker:

这道题是树操作的题目，判断是否从根到叶子的路径和跟给定sum相同的。还是用常规的递归方法来做，递归条件是看左子树或者右子树有没有满足条件的路径，

也就是子树路径和等于当前sum减去当前节点的值。结束条件是如果当前节点是空的，则返回false，如果是叶子，那么如果剩余的sum等于当前叶子的值，

则找到满足条件的路径，返回true。算法的复杂度是输的遍历，时间复杂度是O(n)，空间复杂度是O(logn)。代码如下：

public boolean hasPathSum(TreeNode root, int sum) {
    if(root == null)
        return false;
    if(root.left == null && root.right==null && root.val==sum)
        return true;
    return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
}

树的题目在LeetCode中出现的频率很高，不过方法都很接近，掌握了就都差不多。这类求解树的路径的题目是一种常见题型，

类似的还有Binary Tree Maximum Path Sum，那道题更加复杂一些，路径可以起始和结束于任何结点（把二叉树看成无向图），有兴趣的朋友可以看看哈。
