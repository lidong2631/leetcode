题意：

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

A    G    M
B  F H  L N
C E  I K
D    J 




from code ganker:

这道题是cc150里面的题目了，其实比较简单，只要看出来他其实每个zigzag是2*m-1个字符就可以，这里m是结果的行的数量。接下来就是对于每一行先把往下走的那一列的字符加进去，

然后有往上走的字符再加进去即可。时间复杂度是O(n),空间复杂度是O(1),代码如下： 

public String convert(String s, int nRows) {
    if(s == null || s.length()==0 || nRows <=0)
        return "";
    if(nRows == 1)
        return s;
    StringBuilder res = new StringBuilder();
    int size = 2*nRows-2;
    for(int i=0;i<nRows;i++)
    {
        for(int j=i;j<s.length();j+=size)
        {
            res.append(s.charAt(j));
            if(i!=0 && i!=nRows-1 && j+size-2*i<s.length())     // j+size-2*i为zigzag中间的字符
                res.append(s.charAt(j+size-2*i));
        }                
    }
    return res.toString();
}

实现上要注意最后如果字符长度到了，就不需要添加了。 这道题也没有什么扩展，我觉得面试中考到的几率不大，基本就是字符串的操作。



