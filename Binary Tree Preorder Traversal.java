Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?



Java:
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }
    
    private void helper(TreeNode root, List<Integer> res) {
        if(root != null) {
            res.add(root.val);
            helper(root.left, res);
            helper(root.right, res);
        }  
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                root = root.right;
            }
        }
        return res;
    }
}




from code ganker:

跟Binary Tree Inorder Traversal一样，二叉树的先序遍历我们仍然介绍三种方法，第一种是递归，第二种是迭代方法，第三种是用线索二叉树。
递归是最简单的方法，算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)。代码如下：

public ArrayList<Integer> preorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    helper(root, res);
    return res;
}
private void helper(TreeNode root, ArrayList<Integer> res)
{
    if(root == null)
        return;
    res.add(root.val);
    helper(root.left,res);
    helper(root.right,res);
}

接下来是迭代的做法，其实就是用一个栈来模拟递归的过程。所以算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。实现的代码如下： 

public ArrayList<Integer> preorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    if(root == null)
        return res;
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    while(root!=null || !stack.isEmpty())
    {
        if(root!=null)
        {
            stack.push(root);
            res.add(root.val);
            root = root.left;
        }
        else
        {
            root = stack.pop();
            root = root.right;
        }
    }
    return res;
}

最后使用Morris遍历方法，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。具体的分析可以参见Binary Tree Inorder Traversal，中序和先序方法上是完全一样的，只是访问结点的时机不同而已。这种方法的缺陷在于会暂时性的改变结点的内容，从编程健壮性和封装性来说不是特别好（比如说传进来的树结点很可能是const的变量，不希望对它做任何改变）。时间复杂度和空间复杂度如同Binary Tree Inorder Traversal中分析的，分别是O(n)和O(1)。代码如下：

public ArrayList<Integer> preorderTraversal(TreeNode root) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    TreeNode cur = root;
    TreeNode pre = null;
    while(cur != null)
    {
        if(cur.left == null)
        {
            res.add(cur.val);
            cur = cur.right;
        }
        else
        {
            pre = cur.left;
            while(pre.right!=null && pre.right!=cur)
                pre = pre.right;
            if(pre.right==null)
            {
                res.add(cur.val);
                pre.right = cur;
                cur = cur.left;
            }
            else
            {
                pre.right = null;
                cur = cur.right;
            }
        }
    }
    return res;
}

上面介绍了三种方法来实现树的先序遍历，这种题目看上去很简单，但是大家还是不能满足于递归的方法，有些onsite面试还是会在简单问题上要求很高的。


