Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).





/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());
    }
    
    private int dfs(TreeNode root, int level, int order, List<Integer> left, List<Integer> right) {
        if (root == null)
            return 0;
        if (left.size() == level) {
            left.add(order);
            right.add(order);
        }
        else
            right.set(level, order);
        int curr = right.get(level) - left.get(level) + 1;
        int l = dfs(root.left, level+1, 2*order, left, right);
        int r = dfs(root.right, level+1, 2*order+1, left, right);
        return Math.max(curr, Math.max(l, r));
    }
}

O(n)

          1
         / \
        3   2 
       /        
      5   

left: 1         right: 1        width: 1
left: 1,2       right: 1,2      width: 1
left: 1,2,4     right: 1,2,4    width: 1
left: 1,2,4     right: 1,3,4    width: 2



We know that a binary tree can be represented by an array (assume the root begins from the position with index 1 in the array). 

If the index of a node is i, the indices of its two children are 2*i and 2*i + 1. The idea is to use two arrays (start[] and end[]) to record the the indices of the 

leftmost node and rightmost node in each level, respectively. For each level of the tree, the width is end[level] - start[level] + 1. 

Then, we just need to find the maximum width.









