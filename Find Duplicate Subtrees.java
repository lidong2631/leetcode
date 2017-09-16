Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.

'

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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        postorder(root, new HashMap<>(), res);
        return res;
    }
    
    private String postorder(TreeNode curr, Map<String, Integer> map, List<TreeNode> res) {
        if (curr == null) 
            return "#";
        String serial = curr.val + "," + postorder(curr.left, map, res) + "," + postorder(curr.right, map, res);
        if (map.getOrDefault(serial, 0) == 1)
            res.add(curr);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }
}