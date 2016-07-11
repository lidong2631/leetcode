public class Solution {
    public boolean isPerfectSquare(int num) {
        long left = 0, right = num;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (mid * mid < num)
                left = mid + 1;
            else if (mid * mid > num)
                right = mid - 1;
            else return true;
        }
        return false;
    }
}

Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False

Be careful for overflow need to use long for left and right
2147483647

O(logn)

https://leetcode.com/discuss/110639/o-logn-bisection-method