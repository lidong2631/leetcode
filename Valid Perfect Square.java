Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Output: true
Example 2:

Input: 14
Output: false



Java:
public class Solution {
    public boolean isPerfectSquare(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }
}

This is a math problem：
1 = 1
4 = 1 + 3
9 = 1 + 3 + 5
16 = 1 + 3 + 5 + 7
25 = 1 + 3 + 5 + 7 + 9
36 = 1 + 3 + 5 + 7 + 9 + 11
....
so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn

https://discuss.leetcode.com/topic/49325/a-square-number-is-1-3-5-7-java-code




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

not convenient to use division because int variable will be cut off. Be careful for overflow.

can also use Newton method for solution

Be careful for overflow need to use long for left and right
2147483647

O(logn)

https://leetcode.com/discuss/110639/o-logn-bisection-method