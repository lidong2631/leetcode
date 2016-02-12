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
    public int largestBSTSubtree(TreeNode root) {
        if(root==null)
            return 0;
        if(helper(root, null, null))
            return nodeCount(root);
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }
    
    private int nodeCount(TreeNode p) {
        if(p==null)
            return 0;
        return 1+nodeCount(p.left)+nodeCount(p.right);
    }
    
    private boolean helper(TreeNode p, Integer left, Integer right) {
        if(p==null)
            return true;
        return (left==null || p.val>left) && (right==null || p.val<right) &&
            helper(p.left, left, p.val) && helper(p.right, p.val, right);
    }
}

O(nlogn)

use Validate BST as a subroutine