public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int maxLen = 0, sum = 0;
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            if(sum==k)
                maxLen = Math.max(maxLen, i+1); //sum等于k 则是潜在解
            else if(map.containsKey(sum-k))     //或者之前sum包含sum-k的值 则之前到当前的sum等于k 是潜在解
                maxLen = Math.max(maxLen, i-map.get(sum-k));
            if(!map.containsKey(sum))
                map.put(sum, i);
        }
        return maxLen;
    }
}

Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isnt one, return 0 instead.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?

O(n) O(n)

nums = [-2, -1, 2, 1], k = 1

对比这题和Minimum Size Subarray Sum的差别 一个用two pointer 一个用hashtable

https://leetcode.com/discuss/77879/o-n-super-clean-9-line-java-solution-with-hashmap