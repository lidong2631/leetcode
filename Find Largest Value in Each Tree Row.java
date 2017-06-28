You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
Hide Company Tags LinkedIn
Hide Tags Tree Depth-first Search Breadth-first Search






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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        res.add(root.val);
        int max = Integer.MIN_VALUE;
        int curr = 1, next = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            curr--;
            if (cur.left != null) {
                queue.offer(cur.left);
                max = Math.max(max, cur.left.val);
                next++;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                max = Math.max(max, cur.right.val);
                next++;
            }
            if (curr == 0) {
                curr = next;
                next = 0;
                res.add(max);
                max = Integer.MIN_VALUE;
            }
        }
        res.remove(res.size()-1);
        return res;
    }
}