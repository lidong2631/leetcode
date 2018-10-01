题意：

Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints: 
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.

解题思路：不允许用额外空间，所以不能将数转换成字符串来判断。这里有一种精巧的解决方式。

代码：


Python:
class Solution:
    # @return a boolean
    def isPalindrome(self, x):
        if x < 0:
            return False
        div = 1
        while x/div >= 10:
            div = div * 10
            
        while x:
            left = x / div
            right = x % 10
            
            if left != right:
                return False
            
            x = ( x % div ) / 10
            div = div / 100
        return True



Java:
public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int radix = 1;
        while (x / 10 >= radix)
            radix *= 10;
        while (x != 0) {                            // careful cannot write x >= 10  1000021
            if (x / radix != x % 10) return false;  // check
            x = (x % radix) / 10;                   // cut last digit
            radix /= 100;
        }
        return true;
    }
}




Golang:
func isPalindrome(x int) bool {
    if x < 0 {
        return false
    }
    radix := 1
    for x / 10 >= radix {
        radix *= 10
    }
    for x != 0 {
        if x / radix != x % 10 {
            return false
        }
        x = (x % radix) / 10
        radix /= 100
    }
    return true
}




from code ganker:

这道题跟Reverse Integer差不多，也是考查对整数的操作，相对来说可能还更加简单，因为数字不会变化，所以没有越界的问题。

基本思路是每次去第一位和最后一位，如果不相同则返回false，否则继续直到位数为0。代码如下： 

public boolean isPalindrome(int x) {
    if(x<0)
        return false;
    int div = 1;
    while(div<=x/10)
        div *= 10;
    while(x>0)
    {
        if(x/div!=x%10)
            return false;
        x = (x%div)/10;
        div /= 100;
    }
    return true;
}

这个题和Longest Palindromic Substring也很接近，只是处理的数据结构有所不同。如果大家这个题有更好的解法，欢迎讨论哈。

可以留言或者发邮件到linhuanmars@gmail.com给我，多谢~
