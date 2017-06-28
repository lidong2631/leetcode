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
    
    int sum = 0;
    
    public TreeNode convertBST(TreeNode root) {
        getSum(root);
        inOrder(root);
        return root;
    }
    
    private void getSum(TreeNode root) {
        if (root != null) {
            getSum(root.left);
            sum += root.val;
            getSum(root.right);
        }
    }
    
    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            sum -= root.val;
            root.val = root.val + sum;
            inOrder(root.right);
        }
    }
}

O(n)



Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
Hide Company Tags Amazon
Hide Tags Tree
