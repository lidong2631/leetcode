public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int res = Integer.MIN_VALUE;
        
        for (int left = 0; left < n; left++) {
            int[] sum = new int[m];
            for (int right = left; right < n; right++) {
                for (int i = 0; i < m; i++)
                    sum[i] += matrix[i][right];
                
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int curSum = 0;
                
                for (int i = 0; i < m; i++) {
                    curSum += sum[i];
                    Integer num = set.ceiling(curSum - k);
                    if (num != null)
                        res = Math.max(res, curSum - num);
                    set.add(curSum);
                }
            }
        }
        return res;
    }
}

related problem Maximum Sum Rectangular Submatrix in Matrix 2D Kadane O(col*col*row) O(row)
https://www.youtube.com/watch?v=yCQN096CwWM

Given an array of integers A and an integer k, find a subarray that contains the largest sum, subject to a constraint that the sum is less than k
https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k

https://leetcode.com/discuss/109749/accepted-c-codes-with-explanation-and-references