题意：

Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

解题思路：整数（1~3999）到罗马数字的转换。字母前置表示减法，例如CM表示M-C=1000-100=900，XL表示L-X=50-10=40。

代码：


class Solution:
    # @return a string
    def intToRoman(self, num):
        values = [ 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 ]
        numerals = [ "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" ]
        list = ''
        for i in range(0, len(values)):
            while num >= values[i]:
                num -= values[i]
                list += numerals[i]
        return list




public class Solution {
    public String intToRoman(int num) {
        String[] roman = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] n = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < n.length; i++) {
            while (num >= n[i]) {
                num -= n[i];
                res.append(roman[i]);
            }
        }
        return res.toString();
    }
}


from code ganker:

这道题比较简单，只要搞清楚每个数字在每个位置应该如何表示就可以，罗马数字对于每个位有三个单位：1,5,10，对于1到9，表示方法如下：
1-3：用1表示；
4:1：5左边加一个1；
5： 直接用5表示； 
6-8: 5右边加相应的1；
9： 10左边加一个1。
以下的代码用一个函数来对某一个位用相应的1,5,10进行转换，然后求出每一位依次转换得到结果，因为知道不会超过4000，所以直接求4位出来，

算法时间复杂度是O(整数的位数），空间复杂度是O(1)。 代码如下：

public String intToRoman(int num) {
    //I 1
    //V 5
    //X 10
    //L 50
    //C 100
    //D 500
    //M 1,000
    if(num<1 || num>3999)
        return "";
    int digit = 1000;
    ArrayList<Integer> digits = new ArrayList<Integer>();
    while(digit>0)
    {
        digits.add(num/digit);
        num %= digit;
        digit /= 10;
    }
    StringBuilder res = new StringBuilder();
    res.append(convert(digits.get(0),'M',' ', ' '));
    res.append(convert(digits.get(1),'C','D', 'M'));
    res.append(convert(digits.get(2),'X','L', 'C'));
    res.append(convert(digits.get(3),'I','V', 'X'));
    return res.toString();
}
public String convert(int digit, char one, char five, char ten)
{
    StringBuilder res = new StringBuilder();
    switch(digit)
    {
        case 9:
            res.append(one);
            res.append(ten);
            break;
        case 8:
        case 7:
        case 6:
        case 5:
            res.append(five);
            for(int i=5;i<digit;i++)
                res.append(one);
            break;
        case 4:
            res.append(one);
            res.append(five);
            break;   
        case 3:
        case 2:
        case 1:
            for(int i=0;i<digit;i++)
                res.append(one);
            break;   
        default:
            break;
    }
    return res.toString();
}

题目思路很简单，主要就是考察一下对于整数和字符串的操作，属于比较基本的题目。
