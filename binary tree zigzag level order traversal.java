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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        helper(root, 0, res);
        return res;
    }
    
    private void helper(TreeNode root, int level, List<List<Integer>> res) {
        if (root != null) {
            if (res.size() < level + 1) res.add(new LinkedList<Integer>());
            if (level % 2 == 0) res.get(level).add(root.val);
            else res.get(level).add(0, root.val);
            helper(root.left, level + 1, res);
            helper(root.right, level + 1, res);
        }
    }
}

resursion is much easier



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
        
        LinkedList<TreeNode> currLevelStack = new LinkedList<TreeNode>();
        currLevelStack.push(root);
        
        List<Integer> item = new ArrayList<Integer>();
        item.add(root.val);
        res.add(item);
        
        int level = 1;
        while(!currLevelStack.isEmpty())
        {
            LinkedList<TreeNode> nextLevelStack = new LinkedList<TreeNode>();	//每行开始前要清空nextLevelStack和item
            item = new ArrayList<Integer>();
            
            while(!currLevelStack.isEmpty())
            {
                TreeNode node = currLevelStack.pop();
                if(level%2==0)					//2， 4， 6行。。
                {
                    if(node.left!=null)
                    {
                        nextLevelStack.push(node.left);
                        item.add(node.left.val);
                    }
                    if(node.right!=null)
                    {
                        nextLevelStack.push(node.right);
                        item.add(node.right.val);
                    }
                }
                else					//奇数行 头节点行。。
                {
                    if(node.right!=null)
                    {
                        nextLevelStack.push(node.right);
                        item.add(node.right.val);
                    }
                    if(node.left!=null)
                    {
                        nextLevelStack.push(node.left);
                        item.add(node.left.val);
                    }
                }
            }
            level++;
            currLevelStack = nextLevelStack;	//每一行遍历完将nextLevelStack值给currLevelStack 将结果放到res中
            if(item.size()>0)
                res.add(item);
        }
        return res;
    }
}

Note: 根据code ganker写的 非递归实现 用了两个stack 一个currLevelStack用来遍历当前行的节点 nextLevelStack用来保存下一行节点

根据level取余判断是从左往右 或 从右往左




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

