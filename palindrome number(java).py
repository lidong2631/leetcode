<<<<<<< HEAD
class Solution:
    # @return a boolean
    def isPalindrome(self, x):
        if x < 0:               #对负数的处理 负数不算palindrome number
            return False
        div = 1
        while x/div >= 10:      #ex x=121 最终div=100
            div*=10
        while x:                #当x==0 表示所有数都判断完毕 跳出循环
            left = x / div      #求最左右的数字
            right = x % 10
            if left!=right:     #比较 如果不相等 则返回false
                return False
            x = (x % div)/10   #去掉两头的数字 准备判断下一个最左右的数
            div/=100            #div减少两位
        return True

Note: 要熟记其中一些套路 1 求最右位的数字为 x % 10   2 去掉两头数字 (x % div) / 10

这道题要注意几点： 1 负数情况如何处理 2 如果用翻转整个数字来判断 翻转后的数字有可能overflow 如何处理 3 不允许用额外空间 不然可以将数字转成字符串 再用reverse函数处理




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



from cleanCode

public class Solution {
    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        int div = 1;
        while(x/div>=10)
            div*=10;
        
        while(x!=0) {
            if(x/div!=x%10)
                return false;
            x = (x%div)/10;
            div/=100;
        }
        return true;
    }
}





public class Solution {
    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        int div = 1;
        while(x/div>=10)
            div*=10;
        while(x>0)
        {
            int left = x/div;
            int right = x%10;
            if(left!=right)
                return false;
            x = (x%div)/10;     //这里重点
            div/=100;
        }
        return true;
    }
}

Note：根据python版改编 python版和code ganker版差不多一样 简单题熟练





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

=======
class Solution:
    # @return a boolean
    def isPalindrome(self, x):
        if x < 0:               #对负数的处理 负数不算palindrome number
            return False
        div = 1
        while x/div >= 10:      #ex x=121 最终div=100
            div*=10
        while x:                #当x==0 表示所有数都判断完毕 跳出循环
            left = x / div      #求最左右的数字
            right = x % 10
            if left!=right:     #比较 如果不相等 则返回false
                return False
            x = (x % div)/10   #去掉两头的数字 准备判断下一个最左右的数
            div/=100            #div减少两位
        return True

Note: 要熟记其中一些套路 1 求最右位的数字为 x % 10   2 去掉两头数字 (x % div) / 10

这道题要注意几点： 1 负数情况如何处理 2 如果用翻转整个数字来判断 翻转后的数字有可能overflow 如何处理 3 不允许用额外空间 不然可以将数字转成字符串 再用reverse函数处理




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









public class Solution {
    public boolean isPalindrome(int x) {
        if(x<0)
            return false;
        int div = 1;
        while(x/div>=10)
            div*=10;
        while(x>0)
        {
            int left = x/div;
            int right = x%10;
            if(left!=right)
                return false;
            x = (x%div)/10;     //这里重点
            div/=100;
        }
        return true;
    }
}

Note：根据python版改编 python版和code ganker版差不多一样 简单题熟练





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

>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
