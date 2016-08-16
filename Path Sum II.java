题意：

Given a binary tree and a sum, find all root-to-leaf paths where each path sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return

[
   [5,4,11,2],
   [5,8,4,5]
]
解题思路：这题需要将根到叶子的路径和为sum的路径都枚举出来。一样是使用递归。

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
    # @param sum, an integer
    # @return a list of lists of integers
    def pathSum(self, root, sum):
        def dfs(root, currsum, valuelist):
            if root.left==None and root.right==None:
                if currsum==sum: res.append(valuelist)
            if root.left:
                dfs(root.left, currsum+root.left.val, valuelist+[root.left.val])
            if root.right:
                dfs(root.right, currsum+root.right.val, valuelist+[root.right.val])
        
        res=[]
        if root==None: return []
        dfs(root, root.val, [root.val])
        return res



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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        List<Integer> list = new ArrayList<>(); list.add(root.val);
        helper(root, sum-root.val, list, res);
        return res;
    }
    
    private void helper(TreeNode root, int sum, List<Integer> list, List<List<Integer>> res) {
        if (root == null) return;
        if (root.left == null && root.right == null && sum == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        if (root.left != null) {
            list.add(root.left.val);
            helper(root.left, sum - root.left.val, list, res);
            list.remove(list.size()-1);
        }
        if (root.right != null) {
            list.add(root.right.val);
            helper(root.right, sum - root.right.val, list, res);
            list.remove(list.size()-1);
        }
    }
}






from code ganker:

这道题是树的题目，跟Path Sum的要求很接近，都是寻找从根到叶子的路径。这道题目的要求是求所有满足条件的路径，所以我们需要数据结构来维护中间路径结果以及保存满足条件的所有路径。

这里的时间复杂度仍然只是一次遍历O(n)，而空间复杂度则取决于满足条件的路径和的数量（假设是k条），则空间是O(klogn)。代码如下：

public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(root==null)
        return res;
    ArrayList<Integer> item = new ArrayList<Integer>();
    item.add(root.val);
    helper(root,sum-root.val,item,res);
    return res;
}
private void helper(TreeNode root, int sum, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)
{
    if(root == null)
        return;
    if(root.left==null && root.right==null && sum==0)
    {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    if(root.left!=null)
    {
        item.add(root.left.val);
        helper(root.left,sum-root.left.val,item,res);
        item.remove(item.size()-1);
    }
    if(root.right!=null)
    {
        item.add(root.right.val);
        helper(root.right,sum-root.right.val,item,res);
        item.remove(item.size()-1);
    }        
}

这类求解树的路径的题目是一种常见题型，类似的有Binary Tree Maximum Path Sum，那道题更加复杂一些，路径可以起始和结束于任何结点（把二叉树看成无向图），有兴趣的朋友可以看看哈。

