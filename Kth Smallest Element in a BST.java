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
    public int kthSmallest(TreeNode root, int k) {
        int count = countNode(root.left);   //计算左子树节点个数
        if(k<=count) {  //如果k小于等于count 第k小值在左子树
            return kthSmallest(root.left, k);
        } else if(k>count+1) {  //否则在右子树
            return kthSmallest(root.right, k-1-count);  
        }
        return root.val;
    }
    
    private int countNode(TreeNode curr) {
        if(curr==null)
            return 0;
        return 1+countNode(curr.left)+countNode(curr.right);
    }
}

dfs O(nlogn) O(1)

The best performance is we just have to count the nodes for once (i.e. kth is root), which is O(n); 
the worse/average case when we need count nodes for each subtree traversal, binary search is always log(n), 
and number of traversed subtrees could be n, then as total is O(nlog(n)).


Follow up:
If we can modify the structure I think the answer is, we can add two int value to record how many elements are there in left child tree 
and right child tree. Like this:

struct TreeNode{
    int val;
    TreeNode *left{nullptr}, *right{nullptr};
    int leftCnt{0}, rightCnt{0};
};

When we try to insert or delete, update information from bottom to top.
When we try to query, at most we will travel log n times.

https://discuss.leetcode.com/topic/17676/how-to-find-the-kth-smallest-frequently/3
https://leetcode.com/discuss/43464/what-if-you-could-modify-the-bst-nodes-structure
geeksforgeeks Method 2: Augmented  Tree Data Structure

public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int res = -1;
        if(root!=null) {
            TreeNode p = root;
            while(p!=null) {
                if((p.lCount+1)==k) {
                    res = p.data;
                    break;
                }
                else if(k>p.lCount) {
                    k-=(p.lCount+1);
                    p = p.right
                }
                else 
                    p = p.left;
            }
        }
        return res;
    }
}

O(h)



Expansion find kth largest element in a BST
reverse inorder traversal of BST
The reverse inorder traversal traverses all nodes in decreasing order. While doing the traversal, we keep track of count of nodes
visited so far. When count becomes equal to k, we stop the traversal and print the key.
public class Solution {
    public int kthLargest(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        List<Integer> count = new ArrayList<Integer>();
        count.add(0);
        helper(root, k, count, res);
        return count.get(0);
    }

    private void helper(TreeNode root, int k, List<Integer> count, List<Integer> res) {
        if(root==null || count.get(0)>=k)
            return;
        helper(root.right, k, count);
        count.put(0, count.get(0)+1);
        if(count.get(0)==k) {
            res.add(root.val);
            return;
        }
        helper(root.left, k, count);
    }
}

O(h+k)

http://www.geeksforgeeks.org/kth-largest-element-in-bst-when-modification-to-bst-is-not-allowed/


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
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                k--;
                if (k == 0) return root.val;
                root = root.right;
            }
        }
        return -1;
    }
}

O(n) O(logn)


https://leetcode.com/discuss/43771/implemented-java-binary-search-order-iterative-recursive

两种解法 1 In-Order Traversal 2 Binary Search
http://www.geeksforgeeks.org/find-k-th-smallest-element-in-bst-order-statistics-in-bst/