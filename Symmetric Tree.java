Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.




# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a boolean
    def help(self, p, q):
        if p == None and q == None: return True
        if p and q and p.val == q.val:
            return self.help(p.right, q.left) and self.help(p.left, q.right)
        return False
    
    def isSymmetric(self, root):
        if root:
            return self.help(root.left, root.right)
        return True





Java:
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return helper(root.left, root.right);
    }
    
    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null) 
            return false;
        if (left.val == right.val)
            return helper(left.left, right.right) && helper(left.right, right.left);
        return false;
    }
}

Note: 上下分别是递归和非递归解法 递归的python版比code ganker写的简练 都不难 看看就好 递归地比较简练


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
    public boolean isSymmetric(TreeNode root) {
        if(root==null)
            return true;
        if(root.left==null && root.right==null)
            return true;
        if(root.left==null || root.right==null)
            return false;
        LinkedList<TreeNode> queue1 = new LinkedList();
        LinkedList<TreeNode> queue2 = new LinkedList();
        queue1.offer(root.left);
        queue2.offer(root.right);
        while(!queue1.isEmpty() && !queue2.isEmpty())
        {
            TreeNode left = queue1.poll();
            TreeNode right = queue2.poll();
            
            if(left.val!=right.val)
                return false;
            if((left.left!=null && right.right==null) || (left.left==null && right.right!=null))
                return false;
            if((left.right!=null && right.left==null) || (left.right==null && right.left!=null))
                return false;
            if(left.left!=null && right.right!=null)
            {
                queue1.offer(left.left);
                queue2.offer(right.right);
            }
            if(left.right!=null && right.left!=null)
            {
                queue1.offer(left.right);
                queue2.offer(right.left);
            }
        }
        return true;
    }
}



from code ganker:

这道题是树的题目，本质上还是树的遍历。这里无所谓哪种遍历方式，只需要对相应结点进行比较即可。一颗树对称其实就是看左右子树是否对称，一句话就是左同右，右同左，

结点是对称的相等。题目中也要求了递归和非递归的解法，关于这个我们已经介绍过很多次了，不了解的朋友可以看看Binary Tree Inorder Traversal，里面介绍了几种树的遍历方式。

这道题目也就是里面的程序框架加上对称性质的判断即可。遍历这里就不多说了，我们主要说说结束条件，假设到了某一结点，不对称的条件有以下三个：

（1）左边为空而右边不为空；（2）左边不为空而右边为空；（3）左边值不等于右边值。根据这几个条件在遍历时进行判断即可。算法的时间复杂度是树的遍历O(n)，

空间复杂度同样与树遍历相同是O(logn)。递归方法的代码如下：

public boolean isSymmetric(TreeNode root) {
    if(root == null)
        return true;
    return helper(root.left, root.right);
}
public boolean helper(TreeNode root1, TreeNode root2)
{
    if(root1 == null && root2 == null)
        return true;
    if(root1 == null || root2 == null)
        return false;
    if(root1.val != root2.val)
        return false;
    return helper(root1.left,root2.right) && helper(root1.right,root2.left);
}

非递归方法仍然是用栈来模拟递归过程，代码如下：

public boolean isSymmetric(TreeNode root) {
    if(root == null)
        return true;
    if(root.left == null && root.right == null)
        return true;
    if(root.left == null || root.right == null)
        return false;
    LinkedList<TreeNode> q1 = new LinkedList<TreeNode>();
    LinkedList<TreeNode> q2 = new LinkedList<TreeNode>();
    q1.add(root.left);
    q2.add(root.right);
    while(!q1.isEmpty() && !q2.isEmpty())
    {
        TreeNode n1 = q1.poll();
        TreeNode n2 = q2.poll();
        
        if(n1.val != n2.val)
            return false;
        if(n1.left == null && n2.right != null || n1.left != null && n2.right == null)
            return false;
        if(n1.right == null && n2.left != null || n1.right != null && n2.left == null)
            return false;
        if(n1.left != null && n2.right != null)
        {
            q1.add(n1.left);
            q2.add(n2.right);
        }
        if(n1.right != null && n2.left != null)
        {
            q1.add(n1.right);
            q2.add(n2.left);
        }            
    }
    return true;
}

从上面可以看出非递归方法比起递归方法要繁琐一些，因为递归可以根据当前状态（比如两个都为空）直接放回true，而非递归则需要对false的情况一一判断，不能如递归那样简练。