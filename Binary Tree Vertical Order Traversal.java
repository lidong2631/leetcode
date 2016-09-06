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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> index = new LinkedList<>();
        queue.add(root);
        index.add(0);
        
        int left = 0, right = 0;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int i = index.poll();
            
            if (!map.containsKey(i)) map.put(i, new ArrayList<Integer>());
            map.get(i).add(curr.val);
            
            if (curr.left != null) {
                queue.add(curr.left);
                index.add(i - 1);
                if (left > i - 1) left = i - 1;
            }
            if (curr.right != null) {
                queue.add(curr.right);
                index.add(i + 1);
                if (right < i + 1) right = i + 1;
            }
        }
        for (int i = left; i <= right; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}

Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]

level order套路

O(n) O(n)

https://leetcode.com/discuss/75054/5ms-java-clean-solution
