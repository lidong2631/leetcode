Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]




Java:
public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int count1 = 0, count2 = 0, num1 = 0, num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count1 > 0 && nums[i] == num1) count1++;    // careful the order of the 5 if cannot be changed Ex [8,8,7,7,7]
            else if (count2 > 0 && nums[i] == num2) count2++;
            else if (count1 == 0) {
                num1 = nums[i];
                count1++;
            }
            else if (count2 == 0) {
                num2 = nums[i];
                count2++;
            }
            else {
                count1--;
                count2--;
            }
        }
        count1 = 0; count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (num1 == nums[i]) count1++;
            else if (num2 == nums[i]) count2++;
        }
        if (count1 > nums.length / 3) res.add(num1);
        if (count2 > nums.length / 3) res.add(num2);    // it is guarantee two nums are different so no need to check their equality
        return res;
    }
}

http://www.cnblogs.com/grandyang/p/4606822.html