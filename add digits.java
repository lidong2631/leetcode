Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

Example:

Input: 38
Output: 2 
Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2. 
             Since 2 has only one digit, return it.
Follow up:
Could you do it without any loop/recursion in O(1) runtime?




Java:
public class Solution {
    public int addDigits(int num) {
        int tmp = 0;
        while (num >= 10) {
            int fac = 1;
            while (num / fac >= 10)
                fac *= 10;
            while (num != 0) {
                tmp += num / fac;
                num %= fac;
                fac /= 10;
            }
            num = tmp;
            tmp = 0;
        }
        return num;
    }
}


see https://en.wikipedia.org/wiki/Digital_root

public class Solution {
    public int addDigits(int num) {
        return num-9*(int)Math.floor((num-1)/9);
    }
}