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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null)
                res += root.left.val;
            res += sumOfLeftLeaves(root.left);
        }
        if (root.right != null) {
            res += sumOfLeftLeaves(root.right);
        }
        return res;
    }
}

for left subtree check if we can add its value to res for right subtree just recursively go to child node

O(n)

https://discuss.leetcode.com/topic/60403/java-iterative-and-recursive-solutions