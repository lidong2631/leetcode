# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a list of integers
    def postorderTraversal(self, root):
        list = []
        self.iterativeTraversal(root, list)
        return list
        
    def iterativeTraversal(self, root, list):
        stack = []
        isChild = None                             #isChild用来判断子树是否已遍历完
        if root:
            stack.append(root)
            while stack:
                current = stack[len(stack) - 1]     #每次将栈顶元素附值给current
                if (current.left == None and current.right == None) or\
                    (isChild and (isChild == current.left or isChild == current.right)):    #如果current已没有子树 或current左右子树都已遍历完
                    list.append(current.val)        #将其值加到list里 pop出stack isChild等于新的current
                    stack.pop()
                    isChild = current
                else:
                    if current.right:               #节点有子树的情况下 先push右节点 后push左节点进stack （为了后序遍历的顺序）
                        stack.append(current.right)
                    if current.left:
                        stack.append(current.left)
        return list


Note: Python line continuation: implicit () or explicit \




题意：实现后序遍历。递归实现比较简单，非递归实现。

解题思路：这道题的迭代求解比先序遍历和后序遍历要麻烦一些。假设一棵树是这样的：

　　　　　　　　　　　　　　　　　　　　　　　　1

　　　　　　　　　　　　　　　　　　　　　　　/　　\

　　　　　　　　　　　　　　　　　　　　　　2　　　　3

　　　　　　　　　　　　　　　　　　　　　　　　　　/　\

　　　　　　　　　　　　　　　　　　　　　　　　　 4　　5

使用一个栈。分几个步骤：

一，将根节点入栈，并将根节点的孩子入栈，入栈顺序为：先入右孩子，再入左孩子，顺序不能错。因为这样在弹栈时的顺序就是后序遍历的顺序了。

    如果左孩子还有左孩子或者右孩子，那么继续按先右后左的顺序入栈。那么上面这棵树开始的入栈顺序是：1，3，2。由于2不存在左右孩子，停止入栈。

二，由于2没有左右孩子，遍历2并将2弹出，同时使用prev记录下2这个节点。

三，2出栈后，栈为{1，3}，此时3在栈顶，由于3存在左右孩子，按顺序入栈，此时栈为{1，3，5，4}。

四，将4和5遍历并出栈，此时prev指向5，栈为{1，3}。prev的作用是什么呢？用来判断prev是否为栈顶元素的孩子，如果是，则说明子树的孩子已经遍历完成，需要遍历树根了。

    以上树为例：4和5出栈后，prev指向5，而5是栈顶元素3的孩子，说明孩子已经遍历完毕，则遍历3然后弹出3即可，即完成了子树{3，4，5}的后序遍历。

五，此时栈为{1}，为树根，而左右子树都遍历完了，最后遍历树根并弹出即可。

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return a list of integers
    def recursive_postorder(self, root, list):
        if root:
            self.postorder( root.left, list )
            self.postorder( root.right, list )
            list.append(root.val)
    
    def iterative_postorder(self, root, list):
        stack = []
        pre = None
        if root:
            stack.append(root)
            while stack:
                curr = stack[len(stack) - 1]
                if (curr.left == None and curr.right == None) or (pre and (pre == curr.left or pre == curr.right)):
                    list.append(curr.val)
                    stack.pop()
                    pre = curr
                else:
                    if curr.right: stack.append(curr.right)
                    if curr.left: stack.append(curr.left)
        return list

    def postorderTraversal(self, root):
        list = []
        self.iterative_postorder(root,list)
        return list




from code ganker:

recursion: 时间O(n) 空间O(logn) 递归栈大小

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


iteration: 时间空间同递归

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
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        List<Integer> res = new ArrayList<Integer>();
        if(root==null)
            return res;
        TreeNode pre = null;
        
        while(!stack.isEmpty()||root!=null)
        {
            if(root!=null)
            {
                stack.push(root);
                root = root.left;
            }
            else
            {
                TreeNode peek = stack.peek();
                if(peek.right!=null && pre!=peek.right)
                {
                    root = peek.right;
                }
                else
                {
                    stack.pop();
                    res.add(peek.val);
                    pre = peek;
                }
            }
        }
        return res;
    }
}

Note: from code ganker 跟preorder inorder一个套路 方便记忆

code ganker 还有一种Morris解法 有时间可以看看 先熟练掌握递归和迭代




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



