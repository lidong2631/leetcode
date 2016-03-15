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

let's take the successor for example, basically we always want to find p's closest node (in inorder traversal) 

and the node's value is larger than p's value, right? That node can either be p's parent or the smallest node in p' right branch.

When the code runs into the else block, that means the current root is either p's parent or a node in p's right branch.

If it's p's parent node, there are two scenarios: 1. p doesn't have right child, in this case, the recursion will eventually 

return null, so p's parent is the successor; 2. p has right child, then the recursion will return the smallest node in the 

right sub tree, and that will be the answer.

If it's p's right child, there are two scenarios: 1. the right child has left sub tree, eventually the smallest node from the 

left sub tree will be the answer; 2. the right child has no left sub tree, the recursion will return null, 

then the right child (root) is our answer.

https://leetcode.com/discuss/59787/share-my-java-recursive-solution







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

