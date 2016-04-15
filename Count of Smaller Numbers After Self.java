public class Solution {
    
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int count = 1;
        
        public TreeNode(int v) {
            this.val = v;
        }
    } 
    
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums==null || nums.length==0) return res;
        res.add(0);
        TreeNode root = new TreeNode(nums[nums.length-1]);
        root.count = 1;
        
        for(int i=nums.length-2; i>=0; i--) {
            res.add(insertNode(nums[i], root));
        }
        Collections.reverse(res);
        return res;
    }
    
    public int insertNode(int val, TreeNode root) {
        int currCount = 0;
        while(true) {
            if(val<=root.val) {
                root.count++;                       // if val<=root.val then one more node that is smaller than root root.count++
                if(root.left==null) {
                    root.left = new TreeNode(val);
                    break;
                }
                else
                    root = root.left;
            }
            else {
                currCount+=root.count;              // if val>root.val then currCount should add root.count
                if(root.right==null) {
                    root.right = new TreeNode(val);
                    break;
                }
                else
                    root = root.right;
            }
        }
        return currCount;
    }
}

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
