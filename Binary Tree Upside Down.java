From cleanCode

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
    public TreeNode UpsideDownBinaryTree(TreeNode root) {
        TreeNode p = root, parent = null, parentRight = null;
        while(p!=null) {
            TreeNode left = p.left; //先保存左节点 以便后面更改它的值还可以跳到下一层
            p.left = parentRight;   //左节点设为父节点的右子树
            parentRight = p.right;  //更新父右节点为当前节点右节点
            p.right = parent;       //当前右节点设为父节点
            parent = p;     //保存当前节点为下一层的父节点
            p = left;       //跳到下一层
        }
        return parent;
    }
}

迭代解法 很像reverse linkedlist 每次跳到左节点 过程维护parent parentRight两个变量 并更改p的左右节点值 p.left = p.parent.right; p.right = parent


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
    public TreeNode UpsideDownBinaryTree(TreeNode root) {
        return upsideDown(root, null);
    }
    
    private TreeNode upsideDown(TreeNode p, TreeNode parent) {
        if(p==null)
            return parent;
        TreeNode root = upsideDown(p.left, p);
        p.left = (parent==null)?parent:parent.right;
        p.right = parent;
        return root;
    }
}


    1
   / \
  2   3
 / \
4   5


   4
  / \
 5   2
    / \
   3   1  