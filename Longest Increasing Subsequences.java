public class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;
        int[] dp = new int[nums.length];
        int max = 1;
        for(int i=0; i<nums.length; i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(nums[j]<nums[i])
                    dp[i] = Math.max(dp[j]+1, dp[i]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

dp[i] represents the the length of the LIS till nums[i].

For each nums[i], we need to compare all the nums[j] where 0 <= j < i, if nums[i] > nums[j], then dp[i] = dp[j] + 1, 

that means we have found a potential LIS.

Let i go through the nums[] array, eventually we will get the longest length of LIS.

Time complexity is O(n^2).

dp solution O(n^2) O(n)

https://leetcode.com/discuss/67553/share-java-dp-solution


longest increasing subsequence has O(nlogn) solution
https://leetcode.com/discuss/67643/java-python-binary-search-o-nlogn-time-with-explanation

public int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];
    int size = 0;
    for (int n : nums) {
        int i = 0, j = size;
        while (i < j) {
            int mid = (i+j)/2;
            if (nums[mid] < n)
                i = mid + 1;
            else
                j = mid;
        }
        tails[i] = n;
        if (i == size) size++;
    }
    return size;
}



http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/