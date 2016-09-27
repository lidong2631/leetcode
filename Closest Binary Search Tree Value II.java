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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        
        inorder(root, false, s1, target);   // inorder
        inorder(root, true, s2, target);    // reverse inorder
        
        while (k-- > 0) {       // get k closest value
            if (s1.isEmpty())
                res.add(s2.pop());
            else if (s2.isEmpty())
                res.add(s1.pop());
            else if (Math.abs(target - s1.peek()) < Math.abs(target-s2.peek()))
                res.add(s1.pop());
            else
                res.add(s2.pop());
        }
        return res;
    }
    
    private void inorder(TreeNode root, boolean reverse, Stack<Integer> s, double target) {
        if (root == null) return;
        inorder(reverse?root.right:root.left, reverse, s, target);

        // early terminate
        if ((!reverse && root.val > target) || (reverse && root.val <= target)) return;     // careful "<="
        
        s.push(root.val);
        inorder(reverse?root.left:root.right, reverse, s, target);
    }
}

        5
       / \
      3   8
     / \   \
    2  4    9
target = 6.0 k = 3
s1: 2,3,4,5
s2: 9,8

res: 5, 4, 8


compare the predecessors and successors of the closest node to the target, we can use two stacks to track the predecessors and successors

O(n+k)

https://leetcode.com/discuss/55240/ac-clean-java-solution-using-two-stacks


For this problem, another solution would be using heap and it should be O(logn+k) ?
https://leetcode.com/discuss/55164/simple-c-solution-with-priority-queue




Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.