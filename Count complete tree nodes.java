




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
    public int countNodes(TreeNode root) {
        if(root==null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int count = 1, currLevelNum = 1, nextLevelNum = 0;
        while(!queue.isEmpty()) {
            TreeNode p = queue.poll();
            currLevelNum--;
            if(p.left!=null) {
                count++;
                nextLevelNum++;
                queue.add(p.left);
            }
            if(p.right!=null) {
                count++;
                nextLevelNum++;
                queue.add(p.right);
            }
            if(currLevelNum==0) {
                currLevelNum = nextLevelNum;
                nextLevelNum = 0;
            }
        }
        return count;
    }
}