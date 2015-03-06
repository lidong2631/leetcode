# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a list of integers
    def preorderTraversal(self, root):
        stack = []
        list = []
        while root or stack:                #只要root stack不都为空 遍历就没结束
            if root:                        #只要root还不为None 就将它压入栈 遍历 接着访问这个root左孩子
                stack.append(root)
                list.append(root.val)
                root = root.left
            else:
                root = stack.pop()          #如果root为空 即上一节点没有左孩子 就弹出栈顶元素（上一节点） 访问其右孩子
                root = root.right
        return list





题意：这题用递归比较简单。应该考察的是使用非递归实现二叉树的先序遍历。先序遍历的遍历顺序是：根，左子树，右子树。

解题思路：如果树为下图：

　　　　　　　　　　　　　　　　　　　　　　1

　　　　　　　　　　　　　　　　　　　　　/     \

　　　　　　　　　　　　　　　　　　　　2         3

　　　　　　　　　　　　　　　　　　　/     \    /    \

　　　　　　　　　　　　　　　　　　 4       5  6     7 

　　　　使用一个栈。步骤为：

　　　　一，先遍历节点1，并入栈，如果有左孩子，继续遍历并入栈，一直到栈为{1，2，4}。

　　　　二，开始弹栈，当栈顶元素为2时，弹出2，并检测2存在右孩子5，于是遍历5并入栈，此时栈为{1，5}。

　　　　三，弹出5，5没有左右孩子，继续弹栈，将1弹出后，栈为{}。

　　　　四，由于1存在右孩子，则继续按照以上步骤入栈出栈。{3, 6}->{7}->{}，结束。

　　　　栈的状态变化：{1}->{1,2}->{1,2,4}->{1,2}->{1}->{1,5}->{1}->{}->{3}->{3,6}->{3}->{}->{7}->{}。

代码：
# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a list of integers
    def iterative_preorder(self, root, list):
        stack = []
        while root or stack:
            if root:
                list.append(root.val)
                stack.append(root)
                root = root.left
            else:
                root = stack.pop()
                root = root.right
        return list
    
    def recursive_preorder(self, root, list):
        if root:
            list.append(root.val)
            self.preorder(root.left,list)
            self.preorder(root.right,list)

    def preorderTraversal(self,root):
        list = []
        self.iterative_preorder(root,list)
        return list




recursion: 时间O(n) 空间O(logn) 递归栈大小

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
        if(root==null)
            return;
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
    }
}


iteration: 时间空间跟递归一样

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
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        ArrayList<Integer> res = new ArrayList<Integer>();
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
}

同postorderTraversal 也有Morris遍历 有时间再看吧




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


