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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p.right!=null) {
            p = p.right;
            while(p.left!=null)
                p = p.left;
            return p;
        }
        TreeNode ptr = null;
        while(root!=null)
            root = root.val>p.val?(ptr=root).left:root.right;
        return ptr;
    }
}

O(n) O(1)

https://leetcode.com/discuss/59728/10-and-4-lines-o-h-java-c





Successor
public TreeNode successor(TreeNode root, TreeNode p) {
    if (root == null)
        return null;

    if (root.val <= p.val) {
        return successor(root.right, p);
    } else {
        TreeNode left = successor(root.left, p);
        return (left != null) ? left : root;
    }
}

Predecessor
public TreeNode predecessor(TreeNode root, TreeNode p) {
    if (root == null)
        return null;

    if (root.val >= p.val) {
        return predecessor(root.left, p);
    } else {
        TreeNode right = predecessor(root.right, p);
        return (right != null) ? right : root;
    }
}

https://leetcode.com/discuss/59787/share-my-java-recursive-solution