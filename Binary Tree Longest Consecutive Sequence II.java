Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. 

On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int res = 0;
    
    public int longestConsecutive(TreeNode root) {
        longestPath(root);
        return res;
    }
    
    private int[] longestPath(TreeNode root) {
        if (root == null)
            return new int[] {0,0};
        int increase = 1, decrease = 1;
        if (root.left != null) {
            int[] left = longestPath(root.left);
            if (root.val == root.left.val + 1)
                decrease = left[1] + 1;
            else if (root.val == root.left.val - 1)
                increase = left[0] + 1;
        }
        if (root.right != null) {
            int[] right = longestPath(root.right);
            if (root.val == root.right.val + 1)
                decrease = Math.max(decrease, right[1] + 1);
            else if (root.val == root.right.val - 1)
                increase = Math.max(increase, right[0] + 1);
        }
        res = Math.max(res, decrease + increase - 1);
        return new int[] {increase, decrease};
    }
}