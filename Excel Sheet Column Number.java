Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
Example 1:

Input: "A"
Output: 1
Example 2:

Input: "AB"
Output: 28
Example 3:

Input: "ZY"
Output: 701




Java:
public class Solution {
    public int titleToNumber(String s) {
        int res = 0, digits = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            res += Math.pow(26, digits) * (s.charAt(i) - 'A' + 1); 	// careful cannot use caret "^"
            digits++;
        }
        return res;
    }
}

In java the caret "^" does not represent exponential operater it is XOR. Use Math.pow to calculate power

时间O(n) 空间O(1)


Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 