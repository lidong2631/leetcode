#my initial code. Wrong!

# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def zigzagLevelOrderTraversal(self, root, level, res):
        if root:
            if len(res) < level + 1:
                res.append([])
            res[level].append(root.val)
            if level % 2 == 0:                  #This is not right!!
                self.zigzagLevelOrderTraversal(root.right, level+1, res)
                self.zigzagLevelOrderTraversal(root.left, level+1, res)
            else:
                self.zigzagLevelOrderTraversal(root.left, level+1, res)
                self.zigzagLevelOrderTraversal(root.right, level+1, res)
    
    # @param root, a tree node
    # @return a list of lists of integers
    def zigzagLevelOrder(self, root):
        res = []
        self.zigzagLevelOrderTraversal(root, 0, res)
        return res




# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def zigzagLevelOrderTraversal(self, root, level, res):
        if root:
            if len(res) < level + 1:
                res.append([])
            if level % 2 == 0:              #if even number line we use append to add the value to the end of the list
                res[level].append(root.val)
            else:                           #if odd number line we insert value to the beginning of the list. 逆序插入 从右往左插入
                res[level].insert(0, root.val)
            self.zigzagLevelOrderTraversal(root.left, level+1, res)
            self.zigzagLevelOrderTraversal(root.right, level+1, res)
    
    # @param root, a tree node
    # @return a list of lists of integers
    def zigzagLevelOrder(self, root):
        res = []
        self.zigzagLevelOrderTraversal(root, 0, res)
        return res

Note: list.insert(index, x)

第一种写法不对 反向插入数据应该用list.insert（）函数

        1
    2       3
4               5
用第一种写法会得到结果 [[1], [3,2], [5,4]]而不是[[1], [3,2], [4,5]], 因第一次递归后就会从右节点开始插入数据 而不是从最右边的值插入









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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(root, 0, res);
        return res;
    }
    
    private void helper(TreeNode root, int level, List<List<Integer>> res) {
        if(root!=null)
        {
            if(res.size()<level+1)
            {
                List<Integer> tmpList = new ArrayList<Integer>();
                res.add(new ArrayList(tmpList));
            }
            if(level%2==0)
                res.get(level).add(root.val);
            else
                res.get(level).add(0, root.val);        //这里要注意 如果是偶数行就要往前append 用到了add(int index, E element) 具体查看java doc
            helper(root.left, level+1, res);
            helper(root.right, level+1, res);
        }
    }
}

Note: 以上为python版改编 下面为code ganker版本 分别为递归和非递归版



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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root==null)
            return res;
        LinkedList<TreeNode> currStack = new LinkedList<TreeNode>();
        LinkedList<TreeNode> nextStack = new LinkedList<TreeNode>();
        List<Integer> tmp = new ArrayList<Integer>();
        currStack.push(root);
        int level = 2;          //直接从level 2开始
        tmp.add(root.val);
        res.add(tmp);           //先将头节点加进来
        tmp = new ArrayList<Integer>();
        
        while(!currStack.isEmpty())
        {
            TreeNode node = currStack.pop();
            if(level%2==0)              //三段体 if 奇偶 + if 栈空
            {
                if(node.right!=null)
                {
                    nextStack.push(node.right);
                    tmp.add(node.right.val);
                }
                if(node.left!=null)
                {
                    nextStack.push(node.left);
                    tmp.add(node.left.val);
                }
            }
            else
            {
                if(node.left!=null)
                {
                    nextStack.push(node.left);
                    tmp.add(node.left.val);
                }
                if(node.right!=null)
                {
                    nextStack.push(node.right);
                    tmp.add(node.right.val);
                }
            }
            if(currStack.isEmpty())
            {    
                currStack = nextStack;
                nextStack = new LinkedList<TreeNode>();
                if(tmp.size()>0)
                {
                    res.add(tmp);
                    tmp = new ArrayList<Integer>();
                }
                level++;
            }
        }
        return res;
    }
}

Note: 自己写的非递归实现 用了两个stack 一个currStack用来遍历当前行的节点 nextStack用来保存下一行节点

根据level取余判断是从左往右 或 从右往左 偶数行从右往左进栈 奇数行从左往右进栈




from code ganker:


这道题其实还是树的层序遍历Binary Tree Level Order Traversal，如果不熟悉的朋友可以先看看哈。不过这里稍微做了一点变体，

就是在遍历的时候偶数层自左向右，而奇数层自右向左。在Binary Tree Level Order Traversal中我们是维护了一个队列来完成遍历，

而在这里为了使每次都倒序出来，我们很容易想到用栈的结构来完成这个操作。有一个区别是这里我们需要一层一层的来处理（原来可以按队列插入就可以，

因为后进来的元素不会先处理），所以会同时维护新旧两个栈，一个来读取，一个存储下一层结点。总体来说还是一次遍历完成，所以时间复杂度是O(n)，

空间复杂度最坏是两层的结点，所以数量级还是O(n)（满二叉树最后一层的结点是n/2个）。代码如下：

public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(root==null)
        return res;
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    int level=1;
    ArrayList<Integer> item = new ArrayList<Integer>();
    item.add(root.val);
    res.add(item);
    stack.push(root);
    while(!stack.isEmpty())
    {
        LinkedList<TreeNode> newStack = new LinkedList<TreeNode>();
        item = new ArrayList<Integer>();
        while(!stack.isEmpty())
        {
            TreeNode node = stack.pop();
            if(level%2==0)
            {
                if(node.left!=null)
                {
                    newStack.push(node.left);
                    item.add(node.left.val);
                }
                if(node.right!=null)
                {
                    newStack.push(node.right);
                    item.add(node.right.val);
                }
            }
            else
            {
                if(node.right!=null)
                {
                    newStack.push(node.right);
                    item.add(node.right.val);
                }
                if(node.left!=null)
                {
                    newStack.push(node.left);
                    item.add(node.left.val);
                }                   
            }
        }
        level++;
        if(item.size()>0)
            res.add(item);
        stack = newStack;
    }
    return res;
}

上面的算法其实还是一次广度优先搜索，只是在读取每一层结点交替的交换顺序。毕竟面试中像Binary Tree Level Order Traversal有时候考得太多了，

面试官会觉得你肯定练过，所以会稍作变体，来考察对于编程和算法的基本理解。

