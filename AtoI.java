public class AtoI {
    public static void main(String[] args) {
        AtoI a = new AtoI();
        int num = a.atoi("-1");
        System.out.println(num);
    }

    public int atoi(String str) {
        if(str==null || str.length()==0)
            return 0;
        str = str.trim();
        boolean negative = false;
        int i = 0;
        if(str.charAt(0)=='+' || str.charAt(0)=='-')
        {
            i++;
            if(str.charAt(0)=='-')
                negative = true;
        }
        int res = 0;
        int digit = 0;
        while(i<str.length())
        {
            if(str.charAt(i)<'0' || str.charAt(i)>'9')  //非合法数字
                break;
            digit = (int)(str.charAt(i)-'0');
            System.out.println(digit);

            if(!negative && res>(Integer.MAX_VALUE-digit)/10)   //处理越界
                return Integer.MAX_VALUE;
            else if(negative && res>-((Integer.MIN_VALUE+digit)/10))
                return Integer.MIN_VALUE;

            res = res*10 + digit;
            System.out.println(res);
            i++;
        }
        return negative? -res : res;
    }
}

Note: code ganker改编 这题主要是要注意corner case



from code ganker:

这道题还是对于Integer的处理，在Reverse Integer这道题中我有提到，这种题的考察重点并不在于问题本身，而是要注意corner case的处理，整数一般有两点，一个是正负符号问题，

另一个是整数越界问题。思路比较简单，就是先去掉多余的空格字符，然后读符号（注意正负号都有可能，也有可能没有符号），接下来按顺序读数字，

结束条件有三种情况：（1）异常字符出现（按照C语言的标准是把异常字符起的后面全部截去，保留前面的部分作为结果）；（2）数字越界（返回最接近的整数）；（3）字符串结束。代码如下： 

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

这道题主要考察整数处理，注意点上面已经提到过，因为这个问题是C语言的一个基本问题，面试中还是有可能出现，相对比较底层，边缘情况的处理是关键，可能面试tester的职位会更常见一些。

