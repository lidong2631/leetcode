Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.






# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return an integer
    def sum(self, root, preSum):
        if root==None: return 0
        preSum = preSum*10 + root.val
        if root.left==None and root.right==None: return preSum
        return self.sum(root.left, preSum)+self.sum(root.right, preSum)
        
    def sumNumbers(self, root):
        return self.sum(root, 0)




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
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }
    
    private int helper(TreeNode root, int sum) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return sum * 10 + root.val;
        return helper(root.left, sum*10+root.val) + helper(root.right, sum*10+root.val);
    }
}




from code ganker:

这是一道树的题目，一般使用递归来做，主要就是考虑递归条件和结束条件。这道题思路还是比较明确的，

目标是把从根到叶子节点的所有路径得到的整数都累加起来，递归条件即是把当前的sum乘以10并且加上当前节点传入下一函数，

进行递归，最终把左右子树的总和相加。结束条件的话就是如果一个节点是叶子，那么我们应该累加到结果总和中，如果节点到了空节点，

则不是叶子节点，不需要加入到结果中，直接返回0即可。算法的本质是一次先序遍历，所以时间是O(n)，空间是栈大小，O(logn)。代码如下： 

public int sumNumbers(TreeNode root) {
    return helper(root,0);
}
private int helper(TreeNode root, int sum)
{
    if(root == null)
        return 0;
    if(root.left==null && root.right==null)
        return sum*10+root.val;
    return helper(root.left,sum*10+root.val)+helper(root.right,sum*10+root.val);
}

树的题目在LeetCode中还是有比较大的比例的，不过除了基本的递归和非递归的遍历之外，其他大部分题目都是用递归方式来求解特定量，大家还是得熟悉哈