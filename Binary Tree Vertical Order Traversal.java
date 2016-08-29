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
        
        Queue<TreeNode > queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> indexList = new LinkedList<>();
        queue.add(root);
        indexList.add(0);
        
        int left = 0, right = 0;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int pos = indexList.poll();
            
            if (!map.containsKey(pos)) map.put(pos, new ArrayList<Integer>());
            map.get(pos).add(curr.val);
            
            if (curr.left != null) {
                queue.add(curr.left);
                indexList.add(pos-1);
                if (pos <= left) left = pos - 1;
            }
            if (curr.right != null) {
                queue.add(curr.right);
                indexList.add(pos+1);
                if (pos >= right) right = pos + 1;
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
