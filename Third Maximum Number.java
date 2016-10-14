public class Solution {
    public int thirdMax(int[] nums) {
        int first = nums[0], second = nums[0], third = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > first) {
                third = second;
                second = first;
                first = nums[i];
            }
            else if (nums[i] < first && nums[i] > second) {
                third = second;
                second = nums[i];
            }
            else if ((nums[i] < second && nums[i] > third) || second == third)  // careful [1,2, -123456]
                third = nums[i];
        }
        return (second == third) ? first : third;   // careful
    }
}

O(n)

Given an array of integers, return the 3rd Maximum Number in this array, if it doesn't exist, 

return the Maximum Number. The time complexity must be O(n) or less.