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
    
    private List<List<Integer>> res = new ArrayList<List<Integer>>();
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        helper(root);
        return res;
    }
    
    private int helper(TreeNode root) {
        if (root == null) return -1;
        
        int level = 1 + Math.max(helper(root.left), helper(root.right));
        if (res.size() < level + 1)
            res.add(new ArrayList<Integer>());
        res.get(level).add(root.val);
        return level;
    }
}

good solution similar to level order traversal 

O(n)

https://leetcode.com/discuss/110389/12-lines-simple-java-solution-using-recursion