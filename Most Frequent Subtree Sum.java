Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private Map<Integer, Integer> map = new HashMap<>();
    private int maxCount = 0;
    
    public int[] findFrequentTreeSum(TreeNode root) {
        postOrder(root);
        List<Integer> l = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) == maxCount)
                l.add(key);
        }
        int[] res = new int[l.size()];
        for (int i = 0; i < l.size(); i++)
            res[i] = l.get(i);
        return res;
    }
    
    private int postOrder(TreeNode root) {
        if (root != null) {
            int left = postOrder(root.left);
            int right = postOrder(root.right);
            int sum = left + right + root.val;
            int count = map.getOrDefault(sum, 0) + 1;
            map.put(sum, count);
            maxCount = Math.max(maxCount, count);
            return sum;
        }
        return 0;
    }
}

O(n)