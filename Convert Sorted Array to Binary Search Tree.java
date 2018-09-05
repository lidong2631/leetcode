Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5



# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param num, a list of integers
    # @return a tree node
    def sortedArrayToBST(self, num):
        length = len(num)
        if length == 0:
            return None
        if length == 1:
            return TreeNode(num[0])
        root = TreeNode(num[length / 2])
        root.left = self.sortedArrayToBST(num[:length/2])
        root.right = self.sortedArrayToBST(num[length/2 + 1:])
        return root



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
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length-1);
    }
    
    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }
}



from code ganker:

这道题是二分查找树的题目，要把一个有序数组转换成一颗二分查找树。其实从本质来看，如果把一个数组看成一棵树（也就是以中点为根，左右为左右子树，

依次下去）数组就等价于一个二分查找树。所以如果要构造这棵树，那就是把中间元素转化为根，然后递归构造左右子树。所以我们还是用二叉树递归的方法来实现，

以根作为返回值，每层递归函数取中间元素，作为当前根和赋上结点值，然后左右结点接上左右区间的递归函数返回值。时间复杂度还是一次树遍历O(n)，

总的空间复杂度是栈空间O(logn)加上结果的空间O(n)，额外空间是O(logn)，总体是O(n)。代码如下： 

public TreeNode sortedArrayToBST(int[] num) {
    if(num==null || num.length==0)
        return null;
    return helper(num,0,num.length-1);
}
private TreeNode helper(int[] num, int l, int r)
{
    if(l>r)
        return null;
    int m = (l+r)/2;
    TreeNode root = new TreeNode(num[m]);
    root.left = helper(num,l,m-1);
    root.right = helper(num,m+1,r);
    return root;

}

这是一道不错的题目，模型简单，但是考察了遍历和二分查找树的数据结构，比较适合在电面中出现，

类似的题目有Convert Sorted List to Binary Search Tree，是这道题非常好的后续问题，不同数据结构，实现上也会有所不同，有兴趣的朋友可以看看哈。

