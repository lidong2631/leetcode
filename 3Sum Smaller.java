public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] < target - nums[i]) {
                    count += right - left;
                    left++;
                }
                else right--;
            }
        }
        return count;
    }
}

O(n^2) O(1)

https://leetcode.com/discuss/56164/simple-and-easy-understanding-o-n-2-java-solution