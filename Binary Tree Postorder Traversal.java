Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?





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
class Solution {
    public ArrayList<Integer> postorderTraversal(TreeNode root, ArrayList<Integer> res) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, ArrayList<Integer> res) {
        if(root==null)
            return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }    
}



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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                TreeNode peek = stack.peek();
                if (peek.right != null && peek.right != prev) root = peek.right;
                else {
                    stack.pop();
                    res.add(peek.val);
                    prev = peek;
                }
            }
        }
        return res;
    }
}




Morris
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode curr = dummy;
        TreeNode pre = null;
        while(curr!=null) {
            if(curr.left!=null) {   //这部分都大致相同
                pre = curr.left;
                while(pre.right!=null && pre.right!=curr)
                    pre = pre.right;
                if(pre.right==null) {
                    pre.right = curr;
                    curr = curr.left;
                }
                else {
                    reverse(curr.left, pre);
                    TreeNode tmp = pre;
                    while(tmp!=curr.left) {
                        res.add(tmp.val);
                        tmp = tmp.right;
                    }
                    res.add(tmp.val);
                    reverse(pre, curr.left);
                    pre.right=null;
                    curr = curr.right;
                }
            }
            else {
                curr = curr.right;
            }
        }
        return res;
    }
    
    private void reverse(TreeNode start, TreeNode end) {    //这里类似于linkedlist反转
        if(start==end)
            return;
        TreeNode pre = start;
        TreeNode curr = start.right;
        TreeNode next = null;
        while(pre!=end) {
            next = curr.right;
            curr.right = pre;
            pre = curr;
            curr = next;
        }
    }
}


时间O(n) 空间O(1)







跟Binary Tree Inorder Traversal以及Binary Tree Preorder Traversal一样，二叉树的后序遍历我们还是介绍三种方法，第一种是递归，第二种是迭代方法，

第三种是用线索二叉树。 递归还是那么简单，算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)。代码如下：

public ArrayList<Integer> postorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    helper(root, res);
    return res;
}
private void helper(TreeNode root, ArrayList<Integer> res)
{
    if(root == null)
        return;
    helper(root.left,res);
    helper(root.right,res);
    res.add(root.val);
}

接下来是迭代的做法，本质就是用一个栈来模拟递归的过程，但是相比于Binary Tree Inorder Traversal和Binary Tree Preorder Traversal，

后序遍历的情况就复杂多了。我们需要维护当前遍历的cur指针和前一个遍历的pre指针来追溯当前的情况（注意这里是遍历的指针，并不是真正按后序访问顺序的结点）。

具体分为几种情况：

（1）如果pre的左孩子或者右孩子是cur，那么说明遍历在往下走，按访问顺序继续，即如果有左孩子，则是左孩子进栈，否则如果有右孩子，则是右孩子进栈，

    如果左右孩子都没有，则说明该结点是叶子，可以直接访问并把结点出栈了。

（2）如果反过来，cur的左孩子是pre，则说明已经在回溯往上走了，但是我们知道后序遍历要左右孩子走完才可以访问自己，所以这里如果有右孩子还需要把右孩子进栈，

    否则说明已经到自己了，可以访问并且出栈了。

（3）如果cur的右孩子是pre，那么说明左右孩子都访问结束了，可以轮到自己了，访问并且出栈即可。
算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。实现的代码如下：

public ArrayList<Integer> postorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    if(root == null)
        return res;
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    stack.push(root);
    TreeNode pre = null;
    while(!stack.isEmpty())
    {
        TreeNode cur = stack.peek();
        if(pre==null || pre.left==cur || pre.right==cur)
        {
            if(cur.left!=null)
            {
                stack.push(cur.left);
            }
            else if(cur.right!=null)
            {
                stack.push(cur.right);
            }
            else
            {
                res.add(cur.val);
                stack.pop();
            }
        }
        else if(cur.left==pre && cur.right!=null)
        {
            stack.push(cur.right);
        }
        else
        {
            res.add(cur.val);
            stack.pop();
        }
        pre = cur;
    }
    return res;
}

最后介绍Morris遍历方法，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了，

所以优势在于不需要额外空间。不过同样相比于Binary Tree Inorder Traversal和Binary Tree Preorder Traversal，后序遍历的情况要复杂得多，

因为后序遍历中一直要等到孩子结点访问完才能访问自己，需要一些技巧来维护。

在这里，我们需要创建一个临时的根节点dummy，把它的左孩子设为树的根root。同时还需要一个subroutine来倒序输出一条右孩子路径上的结点。

跟迭代法一样我们需要维护cur指针和pre指针来追溯访问的结点。具体步骤是重复以下两步直到结点为空：

1. 如果cur指针的左孩子为空，那么cur设为其右孩子。

2. 否则，在cur的左子树中找到中序遍历下的前驱结点pre（其实就是左子树的最右结点）。接下来分两种子情况：

（1）如果pre没有右孩子，那么将他的右孩子接到cur。将cur更新为它的左孩子。

（2）如果pre的右孩子已经接到cur上了，说明这已经是回溯访问了，可以处理访问右孩子了，倒序输出cur左孩子到pre这条路径上的所有结点，

并把pre的右孩子重新设为空（结点已经访问过了，还原现场）。最后将cur更新为cur的右孩子。

空间复杂度同样是O(1)，而时间复杂度也还是O(n)，倒序输出的过程只是加大了常数系数，并没有影响到时间的量级。如果对这个复杂度结果不是很熟悉的朋友，

可以先看看Binary Tree Inorder Traversal中的分析，在那个帖子中讲得比较详细。实现的代码如下：

public ArrayList<Integer> postorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    TreeNode dummy = new TreeNode(0);
    dummy.left = root;
    TreeNode cur = dummy;
    TreeNode pre = null;
    while(cur!=null)
    {
        if (cur.left==null)
        {
            cur = cur.right;
        }
        else
        {
            pre = cur.left;
            while (pre.right!=null && pre.right!=cur)
                pre = pre.right;
            if (pre.right==null)
            {
                pre.right = cur;
                cur = cur.left;
            }
            else
            {
                reverse(cur.left, pre);
                TreeNode temp = pre;
                while (temp != cur.left)
                {
                    res.add(temp.val);
                    temp = temp.right;
                }
                res.add(temp.val);
                reverse(pre, cur.left);
                pre.right = null;
                cur = cur.right;
            }
        }
    } 
    return res;
}
void reverse(TreeNode start, TreeNode end) 
{
    if (start == end)
        return;
    TreeNode pre = start;
    TreeNode cur = start.right;
    TreeNode next;
    while (pre != end)
    {
        next = cur.right;
        cur.right = pre;
        pre = cur;
        cur = next;
    }
}

到目前为止，二叉树的三种遍历的三种方法都介绍过了，后序遍历相比于前面两种，还是要复杂一些，个人觉得面试中可能倾向于靠其他两种遍历，特别是像Morris遍历方法，

如果没有准备过很难在面试中写出这种方法的后序遍历，主要还是要有概念，就是知道方法的存在性以及优劣的分析就可以了，不过递归法和迭代法还是需要掌握一下的。



