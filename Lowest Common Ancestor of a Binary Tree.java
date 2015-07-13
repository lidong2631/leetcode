The idea is to traverse the tree starting from root. If any of the given keys (n1 and n2) matches with root, 

then root is LCA (assuming that both keys are present). If root doesn’t match with any of the keys, 

we recur for left and right subtree. The node which has one key present in its left subtree and the other 

key present in right subtree is the LCA. If both keys lie in left subtree, then left subtree has LCA also, 

otherwise LCA lies in right subtree.

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left!=null && right!=null)
            return root;
        return (left!=null)?left:right;
    }
}

递归解　O(n) O(logn)

http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/





非递归解

cc150原题解

https://leetcode.com/discuss/45503/dfs-java-solution