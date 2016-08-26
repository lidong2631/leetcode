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
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        List<Integer> res = new ArrayList<>();
        res.add(0);
        helper(root, res);
        return res.get(0);
    }
    
    private boolean helper(TreeNode root, List<Integer> res) {
        if (root == null) return true;
        boolean l = helper(root.left, res);
        boolean r = helper(root.right, res);
        if (l & r) {
            if (root.left != null && root.left.val != root.val) return false;
            if (root.right != null && root.right.val != root.val) return false;
            res.set(0, res.get(0)+1);
            return true;
        }
        return false;
    }
}

递归解 思路类似Binary Tree Max Sum

https://leetcode.com/discuss/50357/my-concise-java-solution