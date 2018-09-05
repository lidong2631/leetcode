Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]



Java:
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(root, 0, res);
        Collections.reverse(res);
        return res;
    }
    
    private void helper(TreeNode root, int level, List<List<Integer>> res) {
        if (root != null) {
            if (res.size() < level + 1) res.add(new ArrayList<Integer>());
            res.get(level).add(root.val);
            helper(root.left, level+1, res);
            helper(root.right, level+1, res);
        }
    }
}






/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null)
            return res;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int currLevel = 1, nextLevel = 0;
        List<Integer> tmpList = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            currLevel--;
            TreeNode node = queue.poll();
            tmpList.add(node.val);
            if (node.left != null) {
                nextLevel++;
                queue.offer(node.left);
            } 
            if (node.right != null) {
                nextLevel++;
                queue.offer(node.right);
            }
            if (currLevel == 0) {
                currLevel = nextLevel;
                nextLevel = 0;
                res.add(tmpList);
                tmpList = new ArrayList<Integer>();
            }
        }
        Collections.reverse(res);
        return res;
    }
}



