Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3



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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) 
            return res;
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        helper(root, list, res);
        return res;
    }
    
    private void helper(TreeNode root, List<Integer> l, List<String> res) {
        if (root.left == null && root.right == null) {
            res.add(pathStr(l));
            return;
        }
        if (root.left != null) {
            l.add(root.left.val);
            helper(root.left, l, res);
            l.remove(l.size()-1);
        }
        if (root.right != null) {
            l.add(root.right.val);
            helper(root.right, l, res);
            l.remove(l.size()-1);
        }
    }
    
    private String pathStr(List<Integer> l) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < l.size(); i++) {
            if (i != l.size()-1) sb.append(l.get(i) + "->");
            else sb.append(l.get(i));
        }
        return sb.toString();
    }
}

根据Path Sum ii改写 O(n) O(logn)

