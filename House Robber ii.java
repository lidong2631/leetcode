public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int maxRob = Math.max(rob(1, nums.length-1, nums), rob(2, nums.length, nums));
        return maxRob;
    }
    
    private int rob(int start, int end, int[] nums) {
        int prev = 0, curr = nums[start-1];
        for (int i = start; i < end; i++) {
            int tmp = curr;
            curr = Math.max(prev + nums[i], curr);
            prev = tmp;
        }
        return curr;
    }
}




public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int prev = 0, curr = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {     // careful first -> last 2
            int tmp = curr;
            curr = Math.max(prev + nums[i], curr);
            prev = tmp;
        }
        int maxRob = curr;
        prev = 0; curr = nums[1];
        for (int i = 2; i < nums.length; i++) {         // careful second -> last 1
            int tmp = curr;
            curr = Math.max(prev + nums[i], curr);
            prev = tmp;
        }
        maxRob = Math.max(maxRob, curr);
        return maxRob;
    }
}

O(2n) O(1)

        4
    5       2
      2   8