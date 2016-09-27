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
    int maxLen = 0;
    
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return maxLen;
    }
    
    private int helper(TreeNode root) {
        if (root == null) return 0;
        int curr = 1;               // careful
        int left = helper(root.left);
        int right = helper(root.right);
        if (root.left != null && root.left.val == root.val + 1)
            curr = left + 1;
        if (root.right != null && root.right.val == root.val + 1)
            curr = Math.max(curr, right + 1);
        maxLen = Math.max(maxLen, curr);
        return curr;
    }
}


Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.



We can solve this problem using postorder traversal, we use a helper function to perform the traversal and in the meantime let it return the max 

length of the consecutive sequence including the current node.

For leaf nodes and nodes that cannot form a consecutive sequence with their left or right child, the helper function will return 1, 

otherwise return the max of the left or right consecutive sequence plus one.

O(n) O(logn)

https://leetcode.com/discuss/66482/share-my-java-postorder-solution