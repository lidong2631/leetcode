import java.util.*;
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class InPostTree {
    public static void main(String[] args) {
        InPostTree ipt = new InPostTree();
        int[] inorder = {4,2,5,1,6,8,3,7};
        int[] postorder = {4,5,2,8,6,7,3,1};
        ipt.buildTree(inorder, postorder);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<inorder.length; i++)
            map.put(inorder[i], i);
        return helper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, map);
    }
    
    private TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, HashMap<Integer, Integer> map) {
        if(inStart>inEnd || postStart>postEnd)
            return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int index = map.get(postorder[postEnd]);
        root.left = helper(inorder, inStart, index-1, postorder, postStart, postStart+(index-inStart)-1, map);
        root.right = helper(inorder, index+1, inEnd, postorder, postEnd-(inEnd-index), postEnd-1, map);
        return root;
    }
}