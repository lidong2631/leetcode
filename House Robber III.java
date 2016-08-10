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
    public int rob(TreeNode root) {
        return dfs(root)[1];
    }
    
    private int[] dfs(TreeNode node) {
        int[] res = new int[2];
        if(node==null)
            return res;
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        res[0] = left[1] + right[1];
        res[1] = Math.max(res[0], left[0]+right[0]+node.val);
        return res;
    }
}

res[0]: subtree without current root
res[1]: maximum of (subtree of node.left and node.right with current root.val, res[0])


                3
               / \
     (3,3)    2   3     (1,3)
               \   \
     (0,3)      3   1   (0,1)


https://leetcode.com/discuss/91652/c-java-python-%26-explanation