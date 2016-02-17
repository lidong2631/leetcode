class Solution:
    # @return a string
    def convert(self, s, nRows):
        if nRows == 1:          #如果输入一行显示 不需要做变换就是原字符串
            return s
        tmp = ['' for i in range(nRows)]        #tmp list中每一个元素用来存储对应行的字符串
        index = -1; step = 1                #index初始值－1 步长step为1
        for i in range(len(s)):             #循环遍历s中所有字符
            index+=step                 #正常情况每次index加1
            if index == nRows:                  #如果index等于显示行数 则应开始斜向往上遍历 index＝nRow-2从倒数第二行开始 step＝－1 开始往上走
                index = nRows-2; step = -1
            elif index == -1:               #如果index等于－1说明已经过了第一行 该开始往下走 index＝1从第二行开始 step＝1 开始往下走
                index = 1; step = 1
            tmp[index] += s[i]          #将对应字符加到tmp[index]对应元素中
        return ''.join(tmp)             #最后将tmp list转成字符串

看下图理解

ABCDEFGHIJKL n = 4
A     G 
B   F H   L 
C E   I K  
D     J


from code ganker

这道题是cc150里面的题目了，其实比较简单，只要看出来他其实每个zigzag是2*m-1个字符就可以，这里m是结果的行的数量。接下来就是对于每一行先把往下走的那一列的字符加进去，

然后有往上走的字符再加进去即可。时间复杂度是O(n),空间复杂度是O(1),

实现上要注意最后如果字符长度到了，就不需要添加了。 这道题也没有什么扩展，我觉得面试中考到的几率不大，基本就是字符串的操作






题意：

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

 

Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

解题思路：字符串处理，没什么知识点，需要一点编程技巧。

代码：

复制代码
class Solution:
    # @return a string
    def convert(self, s, nRows):
        if nRows==1: return s
        tmp=['' for i in range(nRows)]
        index=-1; step=1
        for i in range(len(s)):
            index+=step
            if index==nRows:
                index-=2; step=-1
            elif index==-1:
                index=1; step=1
            tmp[index]+=s[i]
        return ''.join(tmp)






public class Solution {
    public String convert(String s, int nRows) {
        if(s==null || s.length()==0 || nRows==0)
            return s;
        if(nRows==1)
            return s;
        StringBuilder[] sb = new StringBuilder[nRows];
        for(int i=0; i<nRows; i++)
            sb[i] = new StringBuilder("");      //注意这里
        int index = -1;
        int step = 1;
        for(int i=0; i<s.length(); i++)
        {
            index+=step;
            if(index==nRows)
            {
                index = nRows-2;
                step = -1;
            }
            if(index==-1)
            {
                index = 1;
                step = 1;
            }
            sb[index].append(s.charAt(i));
        }
        StringBuilder res = new StringBuilder();
        for(int i=0; i<sb.length; i++)
            res.append(sb[i].toString());
        return res.toString();
    }
}

Note: 根据python版改编 code ganker版没看 这题就是纯字符串操作 看下93到104行就好 感觉考的可能性不大

唯一注意下如果用StringBuilder[]数组 需要显式给每个元素初始化







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



