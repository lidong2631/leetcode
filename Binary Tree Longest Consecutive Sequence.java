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
    int max;
    
    public int longestConsecutive(TreeNode root) {
        max = 0;
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {
        if(root==null)
            return 0;
        int len = 1;
        int left = helper(root.left);
        int right = helper(root.right);
        
        if(root.left!=null && (root.left.val-1)==root.val)
            len = left + 1; // len = Math.max(len, left+1);
        if(root.right!=null && (root.right.val-1)==root.val)
            len = Math.max(len, right+1);
        
        max = Math.max(max, len);
        return len;
    }
}

We can solve this problem using postorder traversal, we use a helper function to perform the traversal and in the meantime let it return the max 

length of the consecutive sequence including the current node.

For leaf nodes and nodes that cannot form a consecutive sequence with their left or right child, the helper function will return 1, 

otherwise return the max of the left or right consecutive sequence plus one.

O(n) O(logn)

https://leetcode.com/discuss/66482/share-my-java-postorder-solution