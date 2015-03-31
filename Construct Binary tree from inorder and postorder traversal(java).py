# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param preorder, a list of integers
    # @param inorder, a list of integers
    # @return a tree node
    def buildTree(self, preorder, inorder):
        if len(preorder) == 0:      #如果inorder为空 返回None inorder只有一个值 建立一个节点
            return None
        if len(preorder) == 1:
            return TreeNode(preorder[0])
        root = TreeNode(preorder[0])        #根节点为先序遍历第一个值
        index = inorder.index(preorder[0])      #根据根节点的值找到中序遍历中根节点的位置index
        root.left = self.buildTree(preorder[1:index+1], inorder[0:index])       #递归左右子树
        root.right = self.buildTree(preorder[index+1:len(preorder)], inorder[index+1:len(inorder)])
        return root




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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<inorder.length; i++)
            map.put(inorder[i], i);
        return helper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, map);
    }
    
    private TreeNode helper(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR, HashMap<Integer, Integer> map) {
        if(inL>inR || postL>postR)
            return null;
        TreeNode root = new TreeNode(postorder[postR]);
        int index = map.get(postorder[postR]);
        root.left = helper(inorder, inL, index-1, postorder, postL, postL+index-inL-1, map);
        root.right = helper(inorder, index+1, inR, postorder, postL+index-inL, postR-1, map);
        return root;
    }
}





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
        root.left = helper(inorder, inStart, index-1, postorder, postStart, postStart+(index-inStart)-1, map);  //inorder左子树:inStart, index-1       postorder左子树:postStart, postStart+(index-inStart)-1 看草稿就想明白 6837, 8673
        root.right = helper(inorder, index+1, inEnd, postorder, postEnd-(inEnd-index), postEnd-1, map);     //inorder右子树:index+1, inEnd         postorder右子树:postEnd-(postEnd-index), postEnd-1
        return root;
    }
}

Note: 根据code ganker写的 跟上一题大同小异






这道题和Construct Binary Tree from Preorder and Inorder Traversal思路完全一样，如果有朋友不了解，

请先看看Construct Binary Tree from Preorder and Inorder Traversal哈。这里的区别是要从中序遍历和后序遍历中构造出树，算法还是一样，

只是现在取根是从后面取（因为后序遍历根是遍历的最后一个元素）。思想和代码基本都是差不多的，自然时间复杂度和空间复杂度也还是O(n)。代码如下：

public TreeNode buildTree(int[] inorder, int[] postorder) {
    if(inorder==null || postorder==null || inorder.length==0 || postorder.length==0)
    {
        return null;
    }
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for(int i=0;i<inorder.length;i++)
    {
        map.put(inorder[i],i);
    }
    return helper(inorder,postorder,0,inorder.length-1, 0, postorder.length-1,map);
}
private TreeNode helper(int[] inorder, int[] postorder, int inL, int inR, int postL, int postR, HashMap<Integer, Integer> map)
{
    if(inL>inR || postL>postR)
        return null;
    TreeNode root = new TreeNode(postorder[postR]);
    int index = map.get(root.val);
    root.left = helper(inorder,postorder,inL,index-1,postL,postL+index-inL-1,map);
    root.right = helper(inorder,postorder,index+1,inR,postR-(inR-index),postR-1,map);
    return root;
}

这道题和Construct Binary Tree from Preorder and Inorder Traversal是树中难度比较大的题目了，

有朋友可能会想根据先序遍历和后序遍历能不能重新构造出树来，答案是否定的。只有中序便利可以根据根的位置切开左右子树，其他两种遍历都不能做到，

其实先序遍历和后序遍历是不能唯一确定一棵树的，会有歧义发生，也就是两棵不同的树可以有相同的先序遍历和后序遍历，有兴趣的朋友可以试试举出这种例子哈。

