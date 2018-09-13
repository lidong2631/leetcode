Given an integer, write a function to determine if it is a power of two.

Example 1:

Input: 1
Output: true 
Explanation: 20 = 1
Example 2:

Input: 16
Output: true
Explanation: 24 = 16
Example 3:

Input: 218
Output: false




Java:
public class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n == 0 || n == Integer.MIN_VALUE)
            return false;
        return (n & (n-1)) == 0;
    }
}

cc150原题 5.4 利用位运算 注意判断下是不是最小整数
http://www.geeksforgeeks.org/write-one-line-c-function-to-find-whether-a-no-is-power-of-two/


public class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n == 0)
            return false;
        while (n != 1) {
            if (n % 2 == 1)
                return false;
            n /= 2;
        }
        return true;
    }
}

超时！