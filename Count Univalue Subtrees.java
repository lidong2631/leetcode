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
    
    int res = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        helper(root);
        return res;
    }
    
    private boolean helper(TreeNode root) {
        if (root == null) return true;
        boolean l = helper(root.left);
        boolean r = helper(root.right);
        if (l & r) {
            if (root.left != null && root.left.val != root.val)
                return false;
            if (root.right != null && root.right.val != root.val)
                return false;
            res += 1;
            return true;
        }
        return false;
    }
}

递归解 思路类似Binary Tree Max Sum

https://leetcode.com/discuss/50357/my-concise-java-solution



Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.