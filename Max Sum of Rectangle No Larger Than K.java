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


Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?


Maximum Sum Rectangular Submatrix in Matrix (dp/2D kadane) + Max Sum of Subarray No Larger Than K (see below)
https://leetcode.com/discuss/109749/accepted-c-codes-with-explanation-and-references

O(min(m,n)^2 * max(m,n) * log(max(m,n))) O(max(m,n))





related problem Maximum Sum Rectangular Submatrix in Matrix 2D Kadane O(col*col*row) O(row)
https://www.youtube.com/watch?v=yCQN096CwWM


Max Sum of Subarray No Larger Than K
Given an array of integers A and an integer k, find a subarray that contains the largest sum, subject to a constraint that the sum is less than k
https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k

public int maxSumLessEqualK(int[] nums, int k) {
    int res = 0;
    TreeSet<Integer> set = new TreeSet<>();
    set.add(0);         // careful [2] k = 4 set need to have a initial value
    int currSum = 0;
    for (int i = 0; i < nums.length; i++) {
        currSum += nums[i];
        Integer curr = set.ceiling(currSum - k);
        if (curr != null) res = Math.max(res, currSum - curr);
        set.add(currSum);
    }
    return res;
}

O(nlogn)

