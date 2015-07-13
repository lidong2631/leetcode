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
    public int kthSmallest(TreeNode root, int k) {
        int count = countNode(root.left);
        if(k<=count) {
            return kthSmallest(root.left, k);
        } else if(k>count+1) {
            return kthSmallest(root.right, k-1-count);  
        }
        return root.val;
    }
    
    private int countNode(TreeNode curr) {
        if(curr==null)
            return 0;
        return 1+countNode(curr.left)+countNode(curr.right);
    }
}

The best performance is we just have to count the nodes for once (i.e. kth is root), which is O(n); 
the worse/average case when we need count nodes for each subtree traversal, binary search is always log(n), 
and number of traversed subtrees could be n, then as total is O(nlog(n)).