public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}

Given [10, 9, 2, 5, 3, 7, 101, 18],

The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4

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
        int left = 0, right = size;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < n)
                left = mid + 1;
            else
                right = mid;
        }
        tails[left] = n;
        if (left == size) size++;
    }
    return size;
}

tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i]. For example, say we have nums = [4,5,6,3], 

then all the available increasing subsequences are

nums = [4,5,6,3]

len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
len = 3   :      [4, 5, 6]            => tails[2] = 6

We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails array to find the one needs update.

(1) if x is larger than all tails, append it, increase the size by 1
(2) if tails[i-1] < x <= tails[i], update tails[i]

Doing so will maintain the tails invariant. The the final answer is just the size.

http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/


