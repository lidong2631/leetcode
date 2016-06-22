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
        if(root==null)
            return res;
        
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        Queue<Integer> col = new LinkedList<Integer>();
        
        q.offer(root);
        col.offer(0);
        
        int left = 0, right = 0;
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            int cur = col.poll();
            
            if(!map.containsKey(cur))
                map.put(cur, new ArrayList<Integer>());
            map.get(cur).add(curr.val);
            
            if(curr.left!=null) {
                q.offer(curr.left);
                col.offer(cur-1);
                left = cur<=left?cur-1:left;
            }
            if(curr.right!=null) {
                q.offer(curr.right); 
                col.offer(cur+1);
                right = cur>=right?cur+1:right;
            }
        }
        for(int i=left; i<=right; i++)
            res.add(map.get(i));
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
