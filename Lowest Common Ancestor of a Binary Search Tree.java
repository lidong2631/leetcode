We can solve this problem using BST properties. We can recursively traverse the BST from root. 

The main idea of the solution is, while traversing from top to bottom, the first node n we encounter with value 

between n1 and n2, i.e., n1 < n < n2 or same as one of the n1 or n2, is LCA of n1 and n2 (assuming that n1 < n2). 

So just recursively traverse the BST in, if node's value is greater than both n1 and n2 then our LCA lies in 

left side of the node, if it's is smaller than both n1 and n2, then LCA lies on right side. Otherwise root 

is LCA (assuming that both n1 and n2 are present in BST)


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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }
}

递归解 O(logn) O(logn)
http://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
http://articles.leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-search-tree.html

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val)
                root = root.left;
            else if (root.val < p.val && root.val < q.val)
                root = root.right;
            else
                return root;
        }
    }
}

非递归解 O(logn) O(1)

https://leetcode.com/discuss/44946/my-java-solution
https://leetcode.com/discuss/44959/3-lines-o-1-space-some-alternatives