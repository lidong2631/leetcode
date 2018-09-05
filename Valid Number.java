Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.



Python:
class Solution:
    def isNumber(self, s):
        """
        :type s: str
        :rtype: bool
        """
        try:
            float(s)
            return True
        except :
            return False


class Solution:
    def isNumber(self, s):
        """
        :type s: str
        :rtype: bool
        """
        i, n = 0, len(s)
        while i < n and s[i].isspace():
            i += 1
        if i < n and (s[i] == '+' or s[i] == '-'):
            i += 1
        isNumeric = False
        while i < n and s[i].isdigit():
            i += 1
            isNumeric = True
        if i < n and s[i] == '.':
            i += 1
            while i < n and s[i].isdigit():
                i += 1
                isNumeric = True
        if i < n and isNumeric and s[i] == 'e':
            i += 1
            if i < n and (s[i] == '+' or s[i] == '-'):
                i += 1
            isNumeric = False
            while i < n and s[i].isdigit():
                i += 1
                isNumeric = True
        while i < n and s[i].isspace():
            i += 1
        return isNumeric and i == n

isspace()
https://stackoverflow.com/questions/2405292/how-to-check-if-text-is-empty-spaces-tabs-newlines-in-python
isdigit()
https://stackoverflow.com/questions/40097590/detect-whether-a-python-string-is-a-number-or-a-letter



Java:
public class Solution {
    public boolean isNumber(String s) {
        int i = 0, n = s.length();
        while (i < n && Character.isWhitespace(s.charAt(i)))
            i++;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-'))
            i++;
        boolean isNumeric = false;
        while (i < n && Character.isDigit(s.charAt(i))) {
            i++;
            isNumeric = true;
        }
        if (i < n && s.charAt(i) == '.') {
            i++;
            while (i < n && Character.isDigit(s.charAt(i))) {
                i++;
                isNumeric = true;
            }
        }
        while (i < n && Character.isWhitespace(s.charAt(i)))
            i++;
        return isNumeric && i == n;
    }
}



这题题目比较模糊 要注意先提问澄清一些情况再着手解题

官方解法思路很清晰 先是简化版的题目不考虑e的情况 判断4个地方 1 开始的whitespace 2 正负号 3 数字 4 末尾的whitespace

对于3 数字 又分为3部分 1 整数部分 2 小数点 3 小数部分 1 3两部分只能包含数字 要注意的是1和3至少有一个部分要有否则无法构成合法的数字

如3.64 2. .2均为合法数字 但单独.就不是数字 着重理解下程序的结构如何将这些逻辑实现出来的 时间O(n) 空间O(1)

对比看下String to Integer那个题






public class Solution {
    public boolean isNumber(String s) {
        int i = 0, n = s.length();
        while (i < n && Character.isWhitespace(s.charAt(i)))
            i++;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-'))
            i++;
        boolean isNumeric = false;
        while (i < n && Character.isDigit(s.charAt(i))) {
            i++;
            isNumeric = true;
        }
        if (i < n && s.charAt(i) == '.') {
            i++;
            while (i < n && Character.isDigit(s.charAt(i))) {
                i++;
                isNumeric = true;
            }
        }
        if (i < n && isNumeric && s.charAt(i) == 'e') {
            i++;
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-'))
                i++;
            isNumeric = false;								
            while (i < n && Character.isDigit(s.charAt(i))) {
                i++;
                isNumeric = true;
            }
        }
        while (i < n && Character.isWhitespace(s.charAt(i)))
            i++;
        return isNumeric && i == n;
    }
}

增加了对e的考虑 如1.6347e-4 2e10 注意e后面的数字可以接正负号 但不可以是4.这样

时间O(n) 空间O(1)