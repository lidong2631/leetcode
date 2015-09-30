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
        while(root!=null) {
            closest = (Math.abs(target - root.val) < Math.abs(target - closest))? root.val : closest;
            if(closest==target)
                return closest;
            root = root.val>target?root.left:root.right;
        }
        return closest;
    }
}

O(logn) O(logn)

https://leetcode.com/discuss/55460/simple-iterative-java-solution-with-explaination