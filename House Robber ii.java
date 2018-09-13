You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. 
All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, 
adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.




Java:
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