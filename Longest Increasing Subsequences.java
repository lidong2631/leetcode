public class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length, max = 0;
        int[] dp = new int[n];
        for(int i=0; i<n; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(nums[j]<nums[i] && dp[j]+1>dp[i])
                    dp[i] = dp[j]+1;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

dp solution O(n^2) O(n)

https://leetcode.com/discuss/67553/share-java-dp-solution