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
    public int rob(TreeNode root) {
        return dfs(root)[1];
    }
    
    private int[] dfs(TreeNode node) {
        int[] res = new int[2];
        if(node==null)
            return res;
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        res[0] = left[1] + right[1];
        res[1] = Math.max(res[0], left[0]+right[0]+node.val);
        return res;
    }
}

Let

res[1](node) be the value of maximum money we can rob from the subtree with node as root ( we can rob node if necessary).

res[0](node) be the value of maximum money we can rob from the subtree with node as root but without robbing node.

Then we have

res[0](node) = res[1](node.left) + res[1](node.right) and

res[1](node) = max( res[0](node.left)+res[0](node.right)+node.value, res[1](node) )


                3 (3+1+3, 3+3)
               / \
     (3,3)    2   3     (1,3)
               \   \
     (0,3)      3   1   (0,1)


https://leetcode.com/discuss/91652/c-java-python-%26-explanation




The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, 

each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". 

It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.