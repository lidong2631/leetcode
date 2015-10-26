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
        List<Integer> res = new ArrayList<Integer>();
        
        Stack<Integer> s1 = new Stack<Integer>();   //save predecessors
        Stack<Integer> s2 = new Stack<Integer>();   //save successor
        
        Inorder(root, target, false, s1);
        Inorder(root, target, true, s2);
        
        while(k-->0) {  //loop and find closest value from predecessor and successor each time
            if(s1.isEmpty())
                res.add(s2.pop());
            else if(s2.isEmpty())
                res.add(s1.pop());
            else if(Math.abs(s1.peek()-target)<=Math.abs(s2.peek()-target))
                res.add(s1.pop());
            else
                res.add(s2.pop());
        }
        return res;
    }
    
    private void Inorder(TreeNode root, double target, boolean reverse, Stack<Integer> s) {
        if(root==null)
            return;
        Inorder(reverse?root.right:root.left, target, reverse, s);  //recursion
        if((!reverse && root.val>target) || (reverse && root.val<=target))  //early terminate if predecessor is greater than target or successor is smaller than target
            return;
        s.push(root.val);
        Inorder(reverse?root.left:root.right, target, reverse, s);
    }
}
compare the predecessors and successors of the closest node to the target, we can use two stacks to track the predecessors and successors

O(n+k)

https://leetcode.com/discuss/55240/ac-clean-java-solution-using-two-stacks


For this problem, another solution would be using heap and it should be O(logn+k) ?
https://leetcode.com/discuss/55164/simple-c-solution-with-priority-queue