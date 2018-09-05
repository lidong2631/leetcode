Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---




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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int last = 1, next = 0;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            last--;
            if (curr.left != null) {
                queue.add(curr.left);
                next++;
            }
            if (curr.right != null) {
                queue.add(curr.right);
                next++;
            }
            if (last == 0) {
                last = next;
                next = 0;
                res.add(curr.val);
            }
        }
        return res;
    }
}

时间O(n) 空间O(n)