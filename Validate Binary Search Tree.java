Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
    2
   / \
  1   3
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
             is 5 but its right child's value is 4.





# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a boolean
    def ValidBST(self, root, min, max):
        if root == None:
            return True
        if root.val <= min or root.val >= max:
            return False
        return self.ValidBST(root.left, min, root.val) and self.ValidBST(root.right, root.val, max)
    
    def isValidBST(self, root):
        return self.ValidBST(root, -2147483648, 2147483647)



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
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
    
    private boolean helper(TreeNode root, TreeNode left, TreeNode right) {
        if (root == null)
            return true;
        if ((left == null || root.val > left.val) && (right == null || root.val < right.val))
            return helper(root.left, left, root) && helper(root.right, root, right);
        return false;
    }
}

public class Solution {
    public boolean isValidBST(TreeNode root) {
        return valid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean valid(TreeNode p, int low, int high) {
        if (p == null)
            return true;
        return p.val > low && p.val < high && valid(p.left, low, p.val) && valid(p.right, p.val, high);
    }    
}







From cleanCode:

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
    public boolean isValidBST(TreeNode root) {
        if(root==null)
            return true;
        return isSubtreeLessThan(root.left, root.val)
            && isSubtreeGreaterThan(root.right, root.val)
            && isValidBST(root.left) && isValidBST(root.right);
    }
    
    private boolean isSubtreeLessThan(TreeNode p, int val) {
        if(p==null)
            return true;
        return p.val<val && isSubtreeLessThan(p.left, val) && isSubtreeLessThan(p.right, val);
    }
    
    private boolean isSubtreeGreaterThan(TreeNode p, int val) {
        if(p==null)
            return true;
        return p.val>val && isSubtreeGreaterThan(p.left, val) && isSubtreeGreaterThan(p.right, val);
    }
}

暴力法 很没有效率 worst case O(n^2) O(n) stack space


Top-down recursion
public boolean isValidBST(TreeNode root) {
    return valid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
}

private boolean valid(TreeNode p, int low, int high) {
    if(p==null)
        return true;
    return p.val>low && p.val<high && valid(p.left, low, p.val) && valid(p.right, p.val, high);
}

much better top-down recursion O(n) O(n)

The above method wont work if tree contains the smallest or the largest integer value. We could use null value instead

public boolean isValidBST(TreeNode root) {
    return valid(root, null, null);
}

private boolean valid(TreeNode p, Integer low, Integer high) {
    if(p==null)
        return true;
    return (low==null || p.val>low) && (high==null || p.val<high)
        && valid(p.left, low, p.val) && valid(p.right, p.val, high);
}


In-order traversal

private TreeNode prev;
public boolean isValidBST(TreeNode root) {
    prev = null;
    return helper(root);
}

private boolean helper(TreeNode p) {
    if(p==null)
        return true;
    if(helper(p.left)) {
        if(prev!=null && p.val<=prev.val)
            return false;
        prev = p;
        return helper(p.right);
    }
    return false;
}

O(n) O(n)





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
    public boolean isValidBST(TreeNode root) {
        if(root==null)
            return true;
        ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
        pre.add(null);
        return helper(root, pre);
    }
    
    private boolean helper(TreeNode root, ArrayList<TreeNode> pre) {
        if(root==null)
            return true;
        boolean left = helper(root.left, pre);
        if(pre.get(0)!=null && pre.get(0).val>=root.val)
            return false;
        pre.set(0, root);
        boolean right = helper(root.right, pre);
        return left && right;
    }
}

Note: 这是用中序遍历做检查的解法 跟recover binary tree思路一致 还有一个解法是跟python版一样的 在code ganker第二种解法中



from code ganker:

这道题是检查一颗二分查找树是否合法，二分查找树是非常常见的一种数据结构，因为它可以在O(logn)时间内实现搜索。这里我们介绍两种方法来解决这个问题。

第一种是利用二分查找树的性质，就是它的中序遍历结果是按顺序递增的。根据这一点我们只需要中序遍历这棵树，然后保存前驱结点，每次检测是否满足递增关系即可。

注意以下代码我么用一个一个变量的数组去保存前驱结点，原因是java没有传引用的概念，如果传入一个变量，它是按值传递的，所以是一个备份的变量，改变它的值并不能影响它在函数外部的值，

算是java中的一个小细节。代码如下：

public boolean isValidBST(TreeNode root) {
    ArrayList<Integer> pre = new ArrayList<Integer>();
    pre.add(null);
    return helper(root, pre);
}
private boolean helper(TreeNode root, ArrayList<Integer> pre)
{
    if(root == null)
        return true;
    boolean left = helper(root.left,pre);
    if(pre.get(0)!=null && root.val<=pre.get(0))
        return false;
    pre.set(0,root.val);
    return left && helper(root.right,pre);
}

第二种方法是根据题目中的定义来实现，其实就是对于每个结点保存左右界，也就是保证结点满足它的左子树的每个结点比当前结点值小，右子树的每个结点比当前结点值大。

对于根节点不用定位界，所以是无穷小到无穷大，接下来当我们往左边走时，上界就变成当前结点的值，下界不变，而往右边走时，下界则变成当前结点值，上界不变。

如果在递归中遇到结点值超越了自己的上下界，则返回false，否则返回左右子树的结果。代码如下：

public boolean isValidBST(TreeNode root) {
    return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE); 
}
boolean helper(TreeNode root, int min, int max)   
{  
    if(root == null)  
       return true;  
    if(root.val <= min || root.val >= max)
         return false;  
     return helper(root.left, min, root.val) && helper(root.right, root.val, max);
}  






