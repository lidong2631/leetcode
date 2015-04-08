/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; 
 * }
 */
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root==null)
            return res;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int currLevel = 1;
        int nextLevel = 0;
        while(!queue.isEmpty()) {
            currLevel--;
            TreeNode node = queue.poll();
            if(node.left!=null) {
                nextLevel++;
                queue.offer(node.left);
            }
            if(node.right!=null) {
                nextLevel++;
                queue.offer(node.right);
            }
            if(currLevel==0) {
                currLevel = nextLevel;
                nextLevel = 0;
                res.add(node.val);
            }
        }
        return res;
    }
}

树的层序遍历 跟Binary Tree Level Order Traversal基本一样 时间O(n) 空间O(n)