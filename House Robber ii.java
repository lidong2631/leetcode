public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length < 2)
            return nums[0];
        return Math.max(helper(nums, 0, nums.length-2), helper(nums, 1, nums.length-1));
    }
    
    private int helper(int[] nums, int start, int end) {
        int[] maxRob = new int[2];
        maxRob[1] = nums[start];
        for (int i = start + 1; i <= end; i++) {
            int tmp = maxRob[1];
            maxRob[1] = Math.max(maxRob[1], maxRob[0] + nums[i]);
            maxRob[0] = tmp;
        }
        return maxRob[1];
    }
}

执行两遍House rob i 第一次从第一个元素开始 到倒数第二个元素结束 第二次从第二个元素开始到倒数第一个元素结束

O(2n) O(1)

        4
    5       9
      2   8