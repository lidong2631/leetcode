public class Solution {
    public int numSquares(int n) {
        int[] res = new int[n+1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, res[i-j*j]+1);
            }
            res[i] = min;
        }
        return res[n];
    }
}

dp解 递推式为 min(1+dp[i-j*j]) for i>=j*j
 DP array stands for the least number of perfect square numbers for its index. For example DP[13]=2 stands for if you want to decompose 13 
 into some perfect square numbers, it will contains at least two terms which are 33 and 22

O(n*sqrt(n)) O(n)

https://leetcode.com/discuss/57850/explanation-of-the-dp-solution
https://leetcode.com/discuss/62526/an-easy-understanding-dp-solution-in-java



Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.