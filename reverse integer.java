<<<<<<< HEAD
public class Solution {
    public int reverse(int x) {
        if(x==Integer.MIN_VALUE)        //因为最小整数绝对值比最大整数大1 所以无法去绝对值 单独处理 直接返回Integer.MIN_VALUE
            return Integer.MIN_VALUE;
        int num = Math.abs(x);
        int res = 0;
        while(num!=0)
        {
            if(res>(Integer.MAX_VALUE-num%10)/10)           //这里直接写res>Integer.MAX_VALUE/10也可以ac!!!!! 这里是处理reverse后会越界的整数 它们的位数一定跟最大整数位数相同 但是翻转后大于最大整数
                return x>0? Integer.MAX_VALUE:Integer.MIN_VALUE;    //如果判断条件成立 根据x符号取最大整数或最小整数
            res = num%10 + res*10;
            num/=10;
        }
        return x>0? res:-res;
    }
}

Note: 根据code ganker版改编 容易题 但是corner case要特别注意 熟记套路9，10行 以及11，12行

记住java MAX_INT, MIN_INT 2^31-1(2147483647), -2^31(-2147483648)





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


=======
public class Solution {
    public int reverse(int x) {
        if(x==Integer.MIN_VALUE)        //因为最小整数绝对值比最大整数大1 所以无法去绝对值 单独处理 直接返回Integer.MIN_VALUE
            return Integer.MIN_VALUE;
        int num = Math.abs(x);
        int res = 0;
        while(num!=0)
        {
            if(res>(Integer.MAX_VALUE-num%10)/10)           //这里直接写res>Integer.MAX_VALUE/10也可以ac!!!!! 这里是处理reverse后会越界的整数 它们的位数一定跟最大整数位数相同 但是翻转后大于最大整数
                return x>0? Integer.MAX_VALUE:Integer.MIN_VALUE;    //如果判断条件成立 根据x符号取最大整数或最小整数
            res = num%10 + res*10;
            num/=10;
        }
        return x>0? res:-res;
    }
}

Note: 根据code ganker版改编 容易题 但是corner case要特别注意 熟记套路9，10行 以及11，12行

记住java MAX_INT, MIN_INT 2^31-1(2147483647), -2^31(-2147483648)





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


>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
