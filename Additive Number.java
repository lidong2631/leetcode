public class Solution {
    public boolean isAdditiveNumber(String num) {
        int len = num.length();
        for (int i = 1; i <= len / 2; i++) {
            for (int j = 1; Math.max(i, j) <= len - i - j; j++) {
                if (isValid(i, j, num))
                    return true;
            }
        }
        return false;
    }
    
    private boolean isValid(int i, int j, String num) {
        if (i > 1 && num.charAt(0) == '0') return false;
        if (j > 1 && num.charAt(i) == '0') return false;
        String sum;
        Long x1 = Long.parseLong(num.substring(0, i));
        Long x2 = Long.parseLong(num.substring(i, i+j));
        for (int start = i + j; start != num.length(); start += sum.length()) {
            x2 = x1 + x2;
            x1 = x2 - x1;
            sum = x2.toString();
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }
}

the time complexity should be O(n^2) where n is the length of the number.

https://leetcode.com/discuss/70102/java-recursive-and-iterative-solutions
https://discuss.leetcode.com/topic/30453/java-very-straightforward-solution-with-detailed-explanation


Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, 

each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
1 + 99 = 100, 99 + 100 = 199
Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
How would you handle overflow for very large input integers?