Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.



class Solution:
    # @return an integer
    def divide(self, dividend, divisor):
        if divisor == 0:        #除数为0的情况
            return Integer
        sign = 1; res = a           #sign保存符号位 res保存最终结果
        if abs(dividend) < abs(divisor):        #如果被除数绝对值小于除数绝对值 直接返回0
                return 0
        if (dividend < 0 and divisor > 0) or (dividend > 0 and divisor < 0):    #如果异号 sign为-1
            sign = -1
        dividend = abs(dividend); divisor = abs(divisor)    #去绝对值 准备进行运算
        while dividend >= divisor:         #如果dividend还大于divisor 继续循环寻找下一个最接近被除数的以2为底次幂
            tmp = divisor; count = 1        #每次循环开始tmp = divisor
            while tmp << 1 <= dividend:         #如果tmp*2（位运算左移1位相当于乘以2） 小于等于被除数 将tmp扩大为2倍 count记录倍数
                tmp <<= 1
                count *= 2
            dividend -= tmp             #直到dividend小于tmp 找到第一个最接近被除数的以2为底次幂
            res += count            #res加上count
        return res * sign       #

Note： 这题基于任一个整数可以表示成以2的幂为底的一组线性组合 看照片比较好理解 我的解法用的位运算（左移位） 下面的例子用的加法 实质是一样的

这题要注意divisor不停乘2有可能会导致数据溢出 在python中plain integer会自动转型为long integer 而long interger是可以无穷大的

参见 https://docs.python.org/2/library/stdtypes.html 但如果在java中则要考虑溢出的情况要小心


另外本题可以扩展 如只用加法来计算减法 乘法 除法 对除法的算法思想是，用迭代一个一个加被除数，直到》dividend




题意：Divide two integers without using multiplication, division and mod operator.

解题思路：不许用乘、除和求余实现两数的相除。那就只能用加和减了。正常思路是被除数一个一个的减除数，直到剩下的数比除数小为止，就得到了结果。这样是无法ac的，因为时间复杂度为O(n)，比如一个很大的数除1，就很慢。这里我们可以用二分查找的思路，可以说又是二分查找的变种。

代码：



Python:
class Solution:
    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """
        positive = (dividend < 0) is (divisor < 0)
        dividend, divisor = abs(dividend), abs(divisor)
        res = 0
        while dividend >= divisor:
            temp, i = divisor, 1
            while dividend >= temp:
                dividend -= temp
                res += i
                i <<= 1
                temp <<= 1
        if not positive:
            res = -res
        return min(max(-2147483648, res), 2147483647)





Java:
public class Solution {
    public int divide(int dividend, int divisor) {
        if(divisor==0) return Integer.MAX_VALUE;    //特殊处理当divisor, dividend为0的情况
        //if(dividend==0) return 0;
        
        int res = 0;
        if(dividend==Integer.MIN_VALUE) //当dividend为最小负整数 res初始为1 
        {
            res = 1;
            dividend+=Math.abs(divisor); //dividend加divisor为了防止后面取绝对值时越界
        }
        
        if(divisor==Integer.MIN_VALUE)  //如果除数是最小负整数 直接返回res
            return res;
            
        boolean negative = true;
        if((dividend>0 && divisor>0) || (dividend<0 && divisor<0))  //判断符号 这里code ganker用的另一种方法
            negative = false;
        dividend = Math.abs(dividend);      //取绝对值 方便计算
        divisor = Math.abs(divisor);
        int digit = 0;
        
        while(divisor<=(dividend>>1))   //找到比dividend小的最大基
        {
            divisor<<=1;    //每左移一位相当于*2  digit加1记录divisor一共乘了几次2
            digit+=1;
        }
        while(digit>=0) //每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止
        {
            if(dividend>=divisor)
            {
                dividend-=divisor;  //每次dividend减去divisor res自身+(1左移digit位)
                res+=(1<<digit);
            }
            divisor>>=1;    //取下一个divisor即divisor/2 digit--
            digit--;
        }
        return negative? -res : res;
    }
}

Note: 根据code gakner改编 这题体现了java中数值矗立的问题 要好好体会   

>>>为无符号右移 java中有三种位移动运算符 << >> >>>前两个是有符号移动 若移动数字为正则补0否则补1

带符号右移就是将那个数转为2进制然后在前面补0或1 如果是正数就补0 负数补1
例如11 >> 2，则是将数字11右移2位 
计算过程： 
11的二进制形式为：0000 0000 0000 0000 0000 0000 0000 1011，然后把低位的最后两个数字移出，因为该数字是正数，所以在高位补零。
则得到的最终结果是0000 0000 0000 0000 0000 0000 0000 0010。转换为十进制是2。
http://blog.csdn.net/weizhaozhe/article/details/3936489


EX 512/36=14   512 = 36*8 + 36*4 + 36*2=504

另外看divideTwoNumber.java 

min = -2147483648   -2147483648 + -1 = 2147483647
max = 2147483647    2147483647 + 1 = -2147483648

还有调用Math.abs() 如果传递的参数是Integer.MIN_VALUE 则返回值仍是它自身

Note that if the argument is equal to the value of Integer.MIN_VALUE, the most negative representable int value, 

the result is that same value, which is negative








from code ganker:

这道题属于数值处理的题目，对于整数处理的问题，在Reverse Integer中我有提到过，比较重要的注意点在于符号和处理越界的问题。对于这道题目，

因为不能用乘除法和取余运算，我们只能使用位运算和加减法。比较直接的方法是用被除数一直减去除数，直到为0。这种方法的迭代次数是结果的大小，

即比如结果为n，算法复杂度是O(n)。

那么有没有办法优化呢？ 这个我们就得使用位运算。我们知道任何一个整数可以表示成以2的幂为底的一组基的线性组合，

即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。基于以上这个公式以及左移一位相当于乘以2，我们先让除数左移直到大于被除数之前得到一个最大的基。

然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止。因为这个方法的迭代次数是按2的幂知道超过结果，

所以时间复杂度为O(logn)。代码如下：

public int divide(int dividend, int divisor) {
    if(divisor==0)
        return Integer.MAX_VALUE;
    
    int res = 0;
    if(dividend==Integer.MIN_VALUE)
    {
        res = 1;
        dividend += Math.abs(divisor);
    }
    if(divisor==Integer.MIN_VALUE)
        return res;
    boolean isNeg = ((dividend^divisor)>>>31==1)?true:false;
    dividend = Math.abs(dividend);
    divisor = Math.abs(divisor);
    int digit = 0;
    while(divisor<=(dividend>>1))
    {
        divisor <<= 1;
        digit++;
    }
    while(digit>=0)
    {
        if(dividend>=divisor)
        {
            dividend -= divisor;
            res += 1<<digit;
        }
        divisor >>= 1;
        digit--;
    }
    return isNeg?-res:res;
}

这种数值处理的题目在面试中还是比较常见的，类似的题目有Sqrt(x)，Pow(x, n)等。上述方法在其他整数处理的题目中也可以用到，大家尽量熟悉实现这些问题。

