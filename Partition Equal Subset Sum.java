public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0, n = nums.length;
        for (int num : nums)
            sum += num;
        if (sum % 2 == 1) return false;
        boolean[][] dp = new boolean[sum/2+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= sum/2; i++)
            dp[i][0] = false;
        for (int i = 1; i <= n; i++)
            dp[0][i] = true;
        for (int i = 1; i <= sum/2; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j-1];
                if (i >= nums[j-1])
                    dp[i][j] = dp[i][j] || dp[i-nums[j-1]][j-1];
            }
        }
        return dp[sum/2][n];
    }
}

The problem can be solved using dynamic programming when the sum of the elements is not too big. We can create a 

2D array part[][] of size (sum/2)*(n+1). 

And we can construct the solution in bottom up manner such that every filled entry has following property

part[i][j] = true if a subset of {arr[0], arr[1], ..arr[j-1]} has sum 
             equal to i, otherwise false


O(sum*n) O(sum*n)

http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/


Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets 

such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.