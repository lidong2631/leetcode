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
        def dfs(root, tmpSum, tmpList):              #题目说是root-to-leaf path
            if root.left == None and root.right == None:  #如果为叶子节点 则判断tmpSum是否等于sum 等于则是一种解 将这个解的list加到result list里
                if tmpSum == sum:
                    result.append(tmpList)
            if root.left:                                       #否则递归左右节点
                dfs(root.left, tmpSum + root.left.val, tmpList + [root.left.val])
            if root.right:
                dfs(root.right, tmpSum + root.right.val, tmpList + [root.right.val])
                
        if root == None:
            return []
        result = []
        dfs(root, root.val, [root.val])
        return result

Note: 题目强调为root-to-leaf path所以整个路径必须遍历完 要思考如果不是root-to-leaf该如何做





题意：

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

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
 * Definition for binary tree
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
        if(root==null)
            return res;
        List<Integer> item = new ArrayList<Integer>();
        item.add(root.val);
        helper(root, sum-root.val, item, res);
        return res;
    }
    
    private void helper(TreeNode root, int sum, List<Integer> item, List<List<Integer>> res) {
        if(root==null)
            return;
        if(root.left==null && root.right==null && sum==0)
        {
            res.add(new ArrayList<Integer>(item));  //这里要new一个新的arraylist 如果直接放item进去res 后面的程序会更改item 那么res里之前的放的item也会改变就不对了
            return;
        }
        if(root.left!=null)
        {
            item.add(root.left.val);
            helper(root.left, sum-root.left.val, item, res);
            item.remove(item.size()-1);         //记得将之前加的(116行)再去掉 否则会影响后面的结果
        }
        if(root.right!=null)
        {
            item.add(root.right.val);
            helper(root.right, sum-root.right.val, item, res);
            item.remove(item.size()-1);     //同118
        }
    }
}

Note: 注意下111 118 124的注释 这种题基本都这个套路 只是注意下注释的地方就好






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

