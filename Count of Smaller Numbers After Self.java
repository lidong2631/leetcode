public class Solution {
    
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int count;
        
        public TreeNode(int v) {
            this.val = v;
            this.count = 1;     // careful
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        TreeNode root = new TreeNode(nums[nums.length-1]);
        res.add(0);     // careful
        for (int i = nums.length - 2; i >= 0; i--) {
            res.add(insertNode(nums[i], root));
        }
        Collections.reverse(res);   // careful
        return res;
    }
    
    private int insertNode(int val, TreeNode root) {
        int count = 0;
        while (true) {
            if (val <= root.val) {
                root.count++;
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                }
                else root = root.left;
            }
            else {
                count += root.count;
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    break;
                }
                else root = root.right;
            }
        }
        return count;
    }
}


You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].



Traverse from nums[len - 1] to nums[0], and build a binary search tree, which stores:

val: value of nums[i]
count: if val == root.val, there will be count number of smaller numbers on the right

            1(1)        0
             \
              6(3)      1
             /
            2(1)        1
             \
              5(2)      2


average O(nlogn) worse O(n^2) due to unbalanced tree

每次val和root做比较 如果val大于root 则加上当前root的count值 同时去root的右节点 否则当前root的count值加1 去root左节点

https://leetcode.com/discuss/73803/easiest-java-solution
