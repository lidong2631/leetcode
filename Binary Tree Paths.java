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
        List<String> res = new ArrayList<String>();
        if(root==null)
            return res;
        List<Integer> item = new ArrayList<Integer>();
        item.add(root.val);
        helper(root, res, item);
        return res;
    }
    
    private void helper(TreeNode root, List<String> res, List<Integer> item) {
        if(root==null)
            return;
        if(root.left==null && root.right==null) {
            String tmp = pathStr(item);
            res.add(tmp);
            return;
        }
        if(root.left!=null) {
            item.add(root.left.val);
            helper(root.left, res, item);
            item.remove(item.size()-1);
        }
        if(root.right!=null) {
            item.add(root.right.val);
            helper(root.right, res, item);
            item.remove(item.size()-1);
        }
    }
    
    private String pathStr(List<Integer> item) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<item.size(); i++) {
            if(i!=item.size()-1)
                sb.append(String.valueOf(item.get(i))+"->");
            else
                sb.append(String.valueOf(item.get(i)));
        }
        return sb.toString();
    }
}

根据Path Sum ii改写 O(n) O(logn)