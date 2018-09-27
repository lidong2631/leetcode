Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4



Java:
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
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while (root != null) {
            if (root.val == target) return root.val;
            closest = Math.abs(target - root.val) < Math.abs(target - closest) ? root.val : closest;
            if (root.val < target) root = root.right;
            else root = root.left;
        }
        return closest;
    }
}

O(logn) O(logn)

https://leetcode.com/discuss/55460/simple-iterative-java-solution-with-explaination