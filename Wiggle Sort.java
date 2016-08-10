public class Solution {
    public void wiggleSort(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            if(i%2==1) {
                if(nums[i-1]>nums[i])
                    swap(nums, i);
            }
            else if(i!=0 && nums[i-1]<nums[i])
                swap(nums, i);
        }
    }
    
    private void swap(int[] nums, int i) {
        int tmp = nums[i];
        nums[i] = nums[i-1];
        nums[i-1] = tmp;
    }
}

如果数组是偶数个元素则当nums[i-1]<nums[i]时要交换元素
如果数组是奇数个元素则当nums[i-1]>nums[i]时要交换元素 

O(n) O(1)

https://leetcode.com/discuss/57113/java-o-n-solution
https://leetcode.com/discuss/57120/4-lines-o-n-c



Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].