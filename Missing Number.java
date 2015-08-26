public class Solution {
    public int missingNumber(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]<nums.length && nums[i]!=nums[nums[i]]) {
                int tmp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
                i--;
            }
        }
        for(int i=0; i<nums.length; i++) {
            if(nums[i]!=i)
                return i;
        }
        return nums.length;
    }
}

O(n) O(1)

同 first missing positive integer 只是这里是找不符合nums[i]=nums[nums[i]]的元素
