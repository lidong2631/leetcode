Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.



Java:
public class Solution {
    public int reverse(int x) {
        int ret = 0;
        while (x != 0) {
            if (Math.abs(ret) > Integer.MAX_VALUE/10)
                return 0;
            ret = ret * 10 + x % 10;
            x /= 10;
        }
        return ret;
    }
}

2147483647

Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321


Python:
class Solution:
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        s = str(x)
        res = int('-' + s[1:][::-1]) if s[0] == '-' else int(s[::-1])
        return res if -2147483648 <= res <= 2147483647 else 0

class Solution:
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        result = 0

        if x < 0:
            symbol = -1
            x = -x
        else:
            symbol = 1

        while x:
            result = result * 10 + x % 10
            x = math.floor(x / 10)
        
        return 0 if result > pow(2, 31) else result * symbol

In Python, integer has no true fixed maximum, only limited by available memory.
math.floor() math.ceiling()



Golang:
func reverse(x int) int {
    res := 0
    for ; x != 0; x /= 10 {
        res = res * 10 + x % 10
        if res > math.MaxInt32 || res < -math.MaxInt32 - 1 {
            return 0
        }
    }
    return res
}



from code ganker:

这道题思路非常简单，就是按照数字位反转过来就可以，基本数字操作。但是这种题的考察重点并不在于问题本身，越是简单的题目越要注意细节，

一般来说整数的处理问题要注意的有两点，一点是符号，另一点是整数越界问题。代码如下： 

public int reverse(int x) {
    if(x==Integer.MIN_VALUE)
        return Integer.MIN_VALUE;
    int num = Math.abs(x);
    int res = 0;
    while(num!=0)
    {
        if(res>(Integer.MAX_VALUE-num%10)/10)
            return x>0?Integer.MAX_VALUE:Integer.MIN_VALUE;
        res = res*10+num%10;
        num /= 10;
    }
    return x>0?res:-res;
}

上面的代码为了后面方便处理，先将数字转为正数。注意Integer.MIN_VALUE的绝对值是比Integer.MAX_VALUE大1的，所以经常要单独处理。

如果不先转为正数也可以，只是在后面要对符号进行一下判断。这种题目考察的就是数字的基本处理，面试的时候尽量不能错，

而且对于corner case要尽量进行考虑，一般来说都是面试的第一道门槛。

