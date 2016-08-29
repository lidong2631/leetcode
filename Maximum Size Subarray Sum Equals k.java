public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int maxLen = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) 
                maxLen = Math.max(maxLen, i+1);
            else if (map.containsKey(sum-k))
                maxLen = Math.max(maxLen, i-map.get(sum-k));
            if (!map.containsKey(sum))      // careful need to check if map has this sum always reserve the earliest one for maximum size
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