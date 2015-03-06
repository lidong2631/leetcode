class Solution:
    # @return an integer
    def atoi(self, str):
        INT_MAX = 2147483647; INT_MIN = -2147483648
        str = str.strip()           #去掉字符串两边多余的空格
        res = []
        for i in range(len(str)):               #循环每一个字符 如果首字符是'+'/'-'或首字符石'+'/'-'或从首字开始一直是数字 append到res
            if (i==0 and str[i] in ('+', '-')) or '0' <= str[i] <='9':
                res.append(str[i])
            else:       #一旦碰到其他字符 跳出循环
                break
        if not res or res in (['+'], ['-']):    #判断'0', '+', '-'情况
            return 0
        elif int(''.join(res)) > INT_MAX:       #超出范围的整数
            return INT_MAX
        elif int(''.join(res)) < INT_MIN:
            return INT_MIN
        else:                           #输出正常结果
            return int(''.join(res))

Note: 1 python strip()相当于java 的trim()  

    2 另外python里list转换成字符床可以用 ''.join(list)这种方式








解题思路：atoi的实现，有很多细节需要注意。两个判断条件很关键。INT_MAX/10 >= sum和INT_MAX - digit >= sum这两个判断条件确保了不会溢出。

代码：


class Solution:
    # @return an integer
    def atoi(self, str):
        INT_MAX = 2147483647; INT_MIN = -2147483648
        sum = 0
        sign = 1
        i = 0
        if str == '':
            return 0
        while i < len(str) and str[i].isspace():
            i += 1
        if i < len(str) and str[i] == '-':
            sign = -1
        if i < len(str) and (str[i] == '-' or str[i] == '+'):
            i += 1
        while i < len(str) and str[i].isdigit():
            digit = int(str[i])
            if INT_MAX/10 >= sum:
                sum *= 10
            else:
                if sign == 1:
                    return INT_MAX
                else:
                    return INT_MIN
            if INT_MAX - digit >= sum:
                sum += digit
            else:
                if sign == 1:
                    return INT_MAX
                else:
                    return INT_MIN
            i += 1
        return sign*sum




public class Solution {
    public int atoi(String str) {
        if(str==null || str.length()==0)
            return 0;
        str = str.trim();
        boolean negative = false;
        int i = 0;
        if(str.charAt(0)=='+' || str.charAt(0)=='-')    //判断符号
        {
            i++;
            if(str.charAt(0)=='-')
                negative = true;
        }
        int res = 0;
        int digit = 0;
        while(i<str.length())
        {
            if(str.charAt(i)<'0' || str.charAt(i)>'9')  //判断数字
                break;
            digit = (int)(str.charAt(i)-'0');
            if(!negative && res>(Integer.MAX_VALUE-digit)/10)   //判断越界
                return Integer.MAX_VALUE;
            else if(negative && res>-((Integer.MIN_VALUE+digit)/10))    //Integer.MIN_VALUE+digit 是'+'
                return Integer.MIN_VALUE;
            res = res*10 + digit;
            i++;
        }
        return negative? -res : res;
    }
}

Note: 根据code ganker版改编 注意越界问题和符号位




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



