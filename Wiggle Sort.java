Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]




Java:
public class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) {   // careful cannot write i % 2 == 1 && nums[i-1] > nums[i] otherwise it will goto else if
                if (nums[i-1] > nums[i]) swap(nums, i-1, i);
            }
            else if (nums[i-1] < nums[i]) swap(nums, i-1, i);
        }
    }
    
    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}

如果数组是偶数个元素则当nums[i-1]<nums[i]时要交换元素
如果数组是奇数个元素则当nums[i-1]>nums[i]时要交换元素 

O(n) O(1)

https://leetcode.com/discuss/57113/java-o-n-solution
https://leetcode.com/discuss/57120/4-lines-o-n-c
