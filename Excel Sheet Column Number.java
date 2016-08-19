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