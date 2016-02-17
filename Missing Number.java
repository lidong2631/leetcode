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





public class Solution {
    public int missingNumber(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;
        int max = 0, sum = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]>max)
                max = nums[i];
            sum+=nums[i];
        }
        if(nums.length==max+1)
            return nums.length;
        return (1+max)*max/2 - sum;
    }
}

更简单的解法 只适用于只miss一个num的情况 将所有数加和同时找出最大值 如果最大值等于nums.length-1 则miss nums.length 否则将0到最大值求和减去之前的所有数之和即是miss的num