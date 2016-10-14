public class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        StringBuffer sb = new StringBuffer();
        int curr = 0, carry = 0;
        while (i >= 0 && j >= 0) {
            curr = (carry + num1.charAt(i) - '0' + num2.charAt(j) - '0') % 10;
            carry = (carry + num1.charAt(i) - '0' + num2.charAt(j) - '0') / 10;
            sb.append(curr);
            i--; j--;
        }
        while (i >= 0) {
            curr = (carry + num1.charAt(i) - '0') % 10;
            carry = (carry + num1.charAt(i) - '0') / 10;
            sb.append(curr);
            i--;
        }
        while (j >= 0) {
            curr = (carry + num2.charAt(j) - '0') % 10;
            carry = (carry + num2.charAt(j) - '0') / 10;
            sb.append(curr);
            j--;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}


Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.