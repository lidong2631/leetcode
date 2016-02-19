# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return nothing, do it in place
    def flatten(self, root):
        if root == None:            #递归终止条件
            return
        self.flatten(root.left)     #左右递归
        self.flatten(root.right)
        p = root                    #设一节点p从root开始
        if p.left == None:          #如果p的左节点为空 表示p或p以下的右节点已经flatten为linked list 直接return结束
            return
        p = p.left                  #否则到p，即当前root的左节点 准备连接当前root的左右子树
        while p.right:              #循环走到左子树最右子结点
            p = p.right
        p.right = root.right        #三步连接： 1 将左子树最右子节点连接到root右子树的头节点
        root.right = root.left                 # 2 将左子树插入到根节点与右子树之间
        root.left = None                       # 3 将根节点的左指针设为空





由上面可以看出：这道题的意思是将一颗二叉树平化（flatten）为一条链表，而链表的顺序为二叉树的先序遍历。

解题思路：首先将左右子树分别平化为链表，这两条链表的顺序分别为左子树的先序遍历和右子树的先序遍历。

然后将左子树链表插入到根节点和右子树链表之间，就可以了。左右子树的平化则使用递归实现。

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
    # @return nothing, do it in place
    def flatten(self, root):
        if root == None:
            return
        self.flatten(root.left)
        self.flatten(root.right)
        p = root
        if p.left == None:
            return
        p = p.left
        while p.right:
            p = p.right
        p.right = root.right
        root.right = root.left
        root.left = None



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
    
    private TreeNode prev = null;
    
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}

O(n)

https://leetcode.com/discuss/30719/my-short-post-order-traversal-java-solution-for-share





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
    public void flatten(TreeNode root) {
        List<TreeNode> pre = new ArrayList<TreeNode>();
        pre.add(null);
        helper(root, pre);
    }
    
    private void helper(TreeNode root, List<TreeNode> pre) {
        if(root==null)
            return;
        TreeNode right = root.right;
        if(pre.get(0)!=null) {
            pre.get(0).left = null;
            pre.get(0).right = root;
        }
        pre.set(0, root);
        helper(root.left, pre);
        helper(right, pre);     //注意pre在92行会被改变
    }
}

code ganker版




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
    public void flatten(TreeNode root) {
        if(root==null)
            return;
        flatten(root.left);
        flatten(root.right);
        TreeNode p = root;      //这里p要等于root 不可以等于root.left 如果root.left==null p就变成空指针了
        if(p.left==null)        //这里要记得一定要先判断left节点存不存在
            return;
        p = p.left;
        while(p.right!=null)    //一路到最右边底
            p = p.right;
        p.right = root.right;   //做链接
        root.right = root.left;
        root.left = null;
    }
}

Note: python改编的 code ganker的没太看懂 思路一样 以后再说

顺便复习下树的三种遍历 先序 中序 后序 递归和迭代的写法都要会






from code ganker:

这是树的题目，要求把一颗二叉树按照先序遍历顺序展成一个链表，不过这个链表还是用树的结果，就是一直往右走（没有左孩子）来模拟链表。

老套路还是用递归来解决，维护先序遍历的前一个结点pre，然后每次把pre的左结点置空，右结点设为当前结点。

这里需要注意的一个问题就是我们要先把右子结点保存一下，以便等会可以进行递归，否则有可能当前结点的右结点会被覆盖，后面就取不到了。

算法的复杂度时间上还是一次遍历，O(n)。空间上是栈的大小，O(logn)。代码如下： 

public void flatten(TreeNode root) {
    ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
    pre.add(null);
    helper(root, pre);
}
private void helper(TreeNode root, ArrayList<TreeNode> pre)
{
    if(root == null)
        return;
    TreeNode right = root.right;
    if(pre.get(0)!=null)
    {
        pre.get(0).left = null;
        pre.get(0).right = root;
    }
    pre.set(0,root);
    helper(root.left, pre);
    helper(right, pre);
}

树的题目讨论得比较多了，主要思路就是递归，考虑好递归条件和结束条件，有时候如果递归过程会对树结构进行修改的话，要先保存一下结点。


