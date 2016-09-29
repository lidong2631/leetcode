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