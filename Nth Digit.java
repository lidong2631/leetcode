public class Solution {
    public int findNthDigit(int n) {
        int len = 1, start = 1;
        long count = 9;
        while (n > count * len) {
            n -= count * len;
            len++;
            count *= 10;
            start *= 10;
        }
        start += (n - 1) / len;     // locate the number
        String num = String.valueOf(start);
        return Character.getNumericValue(num.charAt((n - 1) % len));    // locate the digit
    }
}

n = 15
1 2 3 4 5 6 7 8 9 10 11 12 13
n = 6
start += (6-1)/2 = 10+2 = 12

find the length of the number where the nth digit is from
find the actual number where the nth digit is from
find the nth digit and return

O(n)

https://discuss.leetcode.com/topic/59314/java-solution



Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.