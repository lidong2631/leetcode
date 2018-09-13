Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)



Java:
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        int right = nums[nums.length-1];
        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}

左扫一遍记录每一个数前面的乘积 又扫一遍 记录每一个数后面的乘积相乘

O(n) O(1)

https://leetcode.com/discuss/46104/simple-java-solution-in-o-n-without-extra-space



Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of 

nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity 

analysis.)