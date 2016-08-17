题意：

Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
 

Return 6.

解题思路：这道题是在树中寻找一条路径，这条路径上的节点的和为最大，起点和终点只要是树里面的节点就可以了。

这里需要注意的一点是：节点值有可能为负值。解决这道二叉树的题目还是来使用递归。例如下面这棵树：

　　　　　　　　　　　　1

　　　　　　　　　　   /     \

　　　　　　　　　　 2        3

    　　　　　　　　/    \    /    \

　　　　　　　　　4     5  6     7

对于这棵树而言，和为最大的路径为：5->2->1->3->7。

那么这条路径是怎么求出来的呢？这里需要用到一个全局变量Solution.max，可以随时被更大的路径和替换掉。

在函数递归到左子树时：最大的路径为：4->2->5。但此时函数的返回值应当为4->2和5->2这两条路径中和最大的一条。

右子树同理。而Solution.max用来监控每个子树中的最大路径和。那么思路就是：（左子树中的最大路径和，右子树中的最大路径和，

以及左子树中以root.left为起点的最大路径（需要大于零）+右子树中以root.right为起点的最大路径（需要大于零）+root.val），

这三者中的最大值就是最大的路径和。

代码：


# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return an integer
    def maxsum(self, root):
        if root == None: return 0
        sum = root.val
        lmax = 0; rmax = 0
        if root.left:
            lmax = self.maxsum(root.left)
            if lmax > 0:
                sum += lmax
        if root.right:
            rmax = self.maxsum(root.right)
            if rmax > 0:
                sum += rmax
        if sum > Solution.max: Solution.max = sum
        return max(root.val, max(root.val + lmax, root.val + rmax))
    
    def maxPathSum(self, root):
        Solution.max = -10000000
        if root == None: return 0
        self.maxsum(root)
        return Solution.max






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
    public int maxPathSum(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        res.add(null);
        helper(root, res);
        return res.get(0);
    }
    
    private int helper(TreeNode root, List<Integer> res) {
        if (root == null) return 0;
        int left = Math.max(helper(root.left, res), 0);         // careful need to compare with 0
        int right = Math.max(helper(root.right, res), 0);
        int currPath = root.val + left + right;
        if (res.get(0) == null || res.get(0) < currPath) res.set(0, currPath);
        return Math.max(left, right) + root.val;
    }
}




from code ganker:

这道题是求树的路径和的题目，不过和平常不同的是这里的路径不仅可以从根到某一个结点，而且路径可以从左子树某一个结点，

然后到达右子树的结点，就像题目中所说的可以起始和终结于任何结点。在这里树没有被看成有向图，而是被当成无向图来寻找路径。

因为这个路径的灵活性，我们需要对递归返回值进行一些调整，而不是通常的返回要求的结果。

在这里，函数的返回值定义为以自己为根的一条从根到子结点的最长路径（这里路径就不是当成无向图了，必须往单方向走）。

这个返回值是为了提供给它的父结点计算自身的最长路径用，而结点自身的最长路径（也就是可以从左到右那种）则只需计算然后更新即可。

这样一来，一个结点自身的最长路径就是它的左子树返回值（如果大于0的话），加上右子树的返回值（如果大于0的话），再加上自己的值。

而返回值则是自己的值加上左子树返回值，右子树返回值或者0（注意这里是“或者”，而不是“加上”，因为返回值只取一支的路径和）。

在过程中求得当前最长路径时比较一下是不是目前最长的，如果是则更新。算法的本质还是一次树的遍历，所以复杂度是O(n)。

而空间上仍然是栈大小O(logn)。代码如下：

public int maxPathSum(TreeNode root) {
    if(root==null)
        return 0;
    ArrayList<Integer> res = new ArrayList<Integer>();
    res.add(Integer.MIN_VALUE);
    helper(root,res);
    return res.get(0);
}
private int helper(TreeNode root, ArrayList<Integer> res)
{
    if(root == null)
        return 0;
    int left = helper(root.left, res);
    int right = helper(root.right, res);
    int cur = root.val + (left>0?left:0)+(right>0?right:0);
    if(cur>res.get(0))
        res.set(0,cur);
    return root.val+Math.max(left, Math.max(right,0));
}

树的题目大多是用递归方式，但是根据要求的量还是比较灵活多变的，这道题是比较有难度的，他要用返回值去维护一个中间量，而结果值则通过参数来维护，

需要一点技巧。




