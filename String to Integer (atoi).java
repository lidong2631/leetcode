Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
Example 1:

Input: "42"
Output: 42
Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical 
             digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.



Java:
public class Solution {
    public int myAtoi(String str) {
        int i = 0, maxDiv10 = Integer.MAX_VALUE/10;
        while (i < str.length() && Character.isWhitespace(str.charAt(i)))
            i++;
        int sign = 1;
        if (i < str.length() && str.charAt(i) == '+')
            i++;
        else if (i < str.length() && str.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        int num = 0;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            int digit = Character.getNumericValue(str.charAt(i));
            if (num > maxDiv10 || (num == maxDiv10 && digit >= 8))
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            num = 10 * num + digit;
            i++;
        }
        return sign * num;
    }
}

O(n) O(1)



Python:
class Solution:
    def myAtoi(self, str):
        """
        :type str: str
        :rtype: int
        """
        ls = list(str.strip())
        if len(ls) == 0:
            return 0
        sign = -1 if ls[0] == '-' else 1
        if ls[0] in ['-','+']: 
            del ls[0]
        ret, i = 0, 0
        while i < len(ls) and ls[i].isdigit():
            ret = ret*10 + ord(ls[i]) - ord('0')
            i += 1
        return max(-2**31, min(sign * ret,2**31-1))

O(n) O(1)

strip() in-built function of Python is used to remove all the leading and trailing spaces from a string.
string.strip([remove])
https://www.geeksforgeeks.org/python-string-strip-2/

ord() chr()
https://docs.python.org/3/library/functions.html#ord
https://docs.python.org/3/library/functions.html#chr



Golang:
func myAtoi(str string) int {
    sign, idx, res := 1, 0, 0

    // Skip leading spaces
    s := strings.TrimSpace(str)
    if len(s) == 0 {
        return 0
    }

    // +/- Sign
    if s[idx] == '+' {
        sign = 1
        idx++
    } else if s[idx] == '-' {
        sign = -1
        idx++
    }

    // Numbers
    for idx < len(s) && s[idx] >= '0' && s[idx] <= '9'{
        res = res * 10 + int(s[idx]) - '0'
        if sign * res > math.MaxInt32 {
            return math.MaxInt32
        } else if sign * res < math.MinInt32 {
            return math.MinInt32
        }
        idx++
    }
    return res * sign
}




from code ganker:

这道题还是对于Integer的处理，在Reverse Integer这道题中我有提到，这种题的考察重点并不在于问题本身，而是要注意corner case的处理，整数一般有两点，

一个是正负符号问题，另一个是整数越界问题。思路比较简单，就是先去掉多余的空格字符，然后读符号（注意正负号都有可能，也有可能没有符号），接下来按顺序读数字，

结束条件有三种情况：（1）异常字符出现（按照C语言的标准是把异常字符起的后面全部截去，保留前面的部分作为结果）；

（2）数字越界（返回最接近的整数）；（3）字符串结束。代码如下： 

public int atoi(String str) {
    if(str==null)
    {
        return 0;
    }
    str = str.trim();
    if(str.length()==0)
        return 0;
    boolean isNeg = false;
    int i = 0;
    if(str.charAt(0)=='-' || str.charAt(0)=='+')
    {
        i++;
        if(str.charAt(0)=='-')
            isNeg = true;
    }
    int res = 0;
    while(i<str.length())
    {
        if(str.charAt(i)<'0'||str.charAt(i)>'9')
            break;
        int digit = (int)(str.charAt(i)-'0');
        if(isNeg && res>-((Integer.MIN_VALUE+digit)/10))
            return Integer.MIN_VALUE;
        else if(!isNeg && res>(Integer.MAX_VALUE-digit)/10)
            return Integer.MAX_VALUE;
        res = res*10+digit;
        i++;
    }
    return isNeg?-res:res;
} 

这道题主要考察整数处理，注意点上面已经提到过，因为这个问题是C语言的一个基本问题，面试中还是有可能出现，相对比较底层，边缘情况的处理是关键，

可能面试tester的职位会更常见一些。


