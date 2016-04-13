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
    public int largestBSTSubtree(TreeNode root) {
        int []min = new int[1];
        int []max = new int[1];
        int[] maxNodes = new int[1];
        findLargestBSTSubtree(root, min, max, maxNodes);
        return maxNodes[0];
    }
    
    private int findLargestBSTSubtree(TreeNode root, int min[], int max[], int[] maxNodes) {
        if (root == null) return 0;
        
        boolean isBST = true;
        
        int leftNodes = findLargestBSTSubtree(root.left, min, max, maxNodes);
        int curMin = (leftNodes == 0) ? root.val : min[0];
        if (leftNodes == -1 || (leftNodes != 0 && root.val <= max[0]))
            isBST = false;
        
        int rightNodes = findLargestBSTSubtree(root.right, min, max, maxNodes);
        int curMax = (rightNodes == 0) ? root.val : max[0];
        if (rightNodes == -1 || (rightNodes != 0 && root.val >= min[0]))
            isBST = false;
        
        if (isBST) {
            int totalNodes = leftNodes + rightNodes + 1;
            min[0] = curMin;
            max[0] = curMax;
            if (totalNodes > maxNodes[0])
                maxNodes[0] = totalNodes;
            return totalNodes;
        } else {
            return -1;
        }
    }
}

O(n) bottom-up approach. Be careful about value for max[0] and min[0] and how they change


http://articles.leetcode.com/largest-binary-search-tree-bst-in/



TreeNode findLargestBSTSubtree(TreeNode root) {
    TreeNode largestBST = null;
    int min = 0, max = 0;
    int maxNodes = Integer.min_value;
    findLargestBSTSubtree(root, min, max, maxNodes, largestBST);
    return largestBST;
}

int findLargestBSTSubtree(TreeNode p, int min, int max, int maxNodes, TreeNode largestBST) {
    if (p == null) return 0;
    
    boolean isBST = true;
    
    int leftNodes = findLargestBSTSubtree(p.left, min, max, maxNodes, largestBST);
    int currMin = (leftNodes == 0) ? p.val : min;
    if (leftNodes == -1 || (leftNodes != 0 && p.val <= max))
        isBST = false;
    int rightNodes = findLargestBSTSubtree(p.right, min, max, maxNodes, largestBST);
    int currMax = (rightNodes == 0) ? p.val : max;
    if (rightNodes == -1 || (rightNodes != 0 && p.val >= min))
        isBST = false;
    
    if (isBST) {
        min = currMin;
        max = currMax;
        int totalNodes = leftNodes + rightNodes + 1;
        if (totalNodes > maxNodes) {
            maxNodes = totalNodes;
            largestBST = p;
        }
        return totalNodes;
    } else {
        return -1;
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
    public int largestBSTSubtree(TreeNode root) {
        if(root==null)
            return 0;
        if(helper(root, null, null))
            return nodeCount(root);
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }
    
    private int nodeCount(TreeNode p) {
        if(p==null)
            return 0;
        return 1+nodeCount(p.left)+nodeCount(p.right);
    }
    
    private boolean helper(TreeNode p, Integer left, Integer right) {
        if(p==null)
            return true;
        return (left==null || p.val>left) && (right==null || p.val<right) &&
            helper(p.left, left, p.val) && helper(p.right, p.val, right);
    }
}

O(nlogn)

use Validate BST as a subroutine