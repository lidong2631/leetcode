# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def height(self, root):                 #height函数用来计算对某一节点 其到其叶子节点的距离
        if root == None:
            return 0
        return max(self.height(root.left), self.height(root.right)) + 1     #取左右子树大的值 并加1
    
    # @param root, a tree node
    # @return a boolean
    def isBalanced(self, root):             #判断二叉树是否balanced
        if root == None:                    #空树是平衡二叉树
            return True
        if abs(self.height(root.left) - self.height(root.right)) <= 1:      #如果对于当前节点其左右子树高度差不大于1 则递归检查其左右子节点是否符合题意 直到所有节点都被检查完毕
            return self.isBalanced(root.left) and self.isBalanced(root.right)       #如果都检查完均符合条件返回True
        else:                               #否则一旦有高度差大于1 返回False
            return False

Note: 熟悉abs()函数




题意：Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

判断一颗二叉树是否是平衡二叉树。

解题思路：在这道题里，平衡二叉树的定义是二叉树的任意节点的两颗子树之间的高度差小于等于1。这实际上是AVL树的定义。首先要写一个计算二叉树高度的函数，二叉树的高度定义为：树为空时，高度为0。然后递归求解：树的高度 = max(左子树高度，右子树高度)+1(根节点要算上)。高度计算函数实现后，递归求解每个节点的左右子树的高度差，如果有大于1的，则return False。如果高度差小于等于1，则继续递归求解。

代码：

复制代码
# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a boolean
    def Height(self, root):
        if root == None:
            return 0
        return max( self.Height( root.left ), self.Height( root.right ) ) + 1
    
    def isBalanced(self, root):
        if root  == None:
            return True
        if abs( self.Height( root.left ) - self.Height( root.right ) ) <= 1:
            return self.isBalanced( root.left ) and self.isBalanced( root.right )
        else:
            return False







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
    public boolean isBalanced(TreeNode root) {
        if(root==null)
            return true;
        if(Math.abs(height(root.left) - height(root.right)) <= 1)
            return isBalanced(root.left) && isBalanced(root.right);
        else
            return false;
    }
    
    private int height(TreeNode root) {
        if(root==null)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}

Note: 这个解法根据python版改的 下面的是code ganker的版本 python版自己写了个height函数计算高度 code ganker调用递归函数helper




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
    public boolean isBalanced(TreeNode root) {
        return helper(root)>=0;             //大于0代表返回的是数的最大高度 否则-1代表not balanced
    }
    
    private int helper(TreeNode root) {
        if(root==null)
            return 0;
        int left = helper(root.left);   //递归左右子树 算高度
        int right = helper(root.right);
        if(left<0 || right<0)           //如果左右的高度任何一个为负数 说明当前节点下面的子树已经不balanced 一直返回-1直到递归头
            return -1;
        if(Math.abs(left-right)>=2)     //否则计算下当前节点左右子树高度差 判断是否>=2 即是否平衡
            return -1;
        return Math.max(left, right) + 1;   //如果平衡 返回当前节点的最大高度 递归的当前节点的父节点
    }
}



from code ganker:

这道题是树操作的题目，还是老套路用递归。这道题是求解树是否平衡，还是有一些小技巧的。要判断树是否平衡，根据题目的定义，

深度是比需的信息，所以我们必须维护深度，另一方面我们又要返回是否为平衡树，那么对于左右子树深度差的判断也是必要的。

这里我们用一个整数来做返回值，而0或者正数用来表示树的深度，而-1则用来比较此树已经不平衡了，如果已经不平衡，则递归一直返回-1即可，

也没有继续比较的必要了，否则就利用返回的深度信息看看左右子树是不是违反平衡条件，如果违反返回-1，否则返回左右子树深度大的加一作为自己的深度即可。

算法的时间是一次树的遍历O(n)，空间是栈高度O(logn)。代码如下： 

public boolean isBalanced(TreeNode root)
{
    return helper(root)>=0;
}
private int helper(TreeNode root)
{
    if(root == null)
        return 0;
    int left = helper(root.left);
    int right = helper(root.right);
    if(left<0 || right<0)
        return -1;
    if(Math.abs(left-right)>=2)
        return -1;
    return Math.max(left,right)+1;
}

可以看出树的题目万变不离其宗，都是递归遍历，只是处理上保存量，递归条件和结束条件会有一些变化。

