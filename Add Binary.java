Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"



Python:
class Solution:
    def addBinary(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        if len(a) == 0: 
            return b
        if len(b) == 0: 
            return a
        if a[-1] == '1' and b[-1] == '1':
            return self.addBinary(self.addBinary(a[0:-1], b[0:-1]), '1') + '0'
        if a[-1] == '0' and b[-1] == '0':
            return self.addBinary(a[0:-1], b[0:-1]) + '0'
        else:
            return self.addBinary(a[0:-1], b[0:-1]) + '1'

class Solution:
    def addBinary(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        return bin(int(a,2) + int(b,2))[2:]





Java:
public class Solution {
    public String addBinary(String a, String b) {
        StringBuffer res = new StringBuffer();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0, digit = 0;
        while (i >= 0 && j >= 0) {
            digit = (a.charAt(i) - '0' + b.charAt(j) - '0' + carry) % 2;
            carry = (a.charAt(i) - '0' + b.charAt(j) - '0' + carry) / 2;
            res.append(digit);
            i--; j--;
        }
        while (i >= 0) {
            digit = (a.charAt(i) - '0' + carry) % 2;
            carry = (a.charAt(i) - '0' + carry) / 2;
            res.append(digit);
            i--;
        }
        while (j >= 0) {
            digit = (b.charAt(j) - '0' + carry) % 2;
            carry = (b.charAt(j) - '0' + carry) / 2;
            res.append(digit);
            j--;
        }
        if (carry > 0) res.append(carry);
        return res.reverse().toString();
    }
}



