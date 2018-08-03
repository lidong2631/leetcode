Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: 3
Output: "III"
Example 2:

Input: 4
Output: "IV"
Example 3:

Input: 9
Output: "IX"
Example 4:

Input: 58
Output: "LVIII"
Explanation: C = 100, L = 50, XXX = 30 and III = 3.
Example 5:

Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


Python:
class Solution:
    def intToRoman(self, num):
        """
        :type num: int
        :rtype: str
        """
        values = [ 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 ]
        numerals = [ "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" ]
        list = ''
        for i in range(len(values)):
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



Golang:

func intToRoman(num int) string {
    roman := []string{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"}
    nums := []int{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}
    var res bytes.Buffer
    for i, v := range nums {
        for num >= v {
            num -= v
            res.WriteString(roman[i])
        }
    }
    return res.String()
}

how to concatenate string in Golang using bytes.Buffer
https://stackoverflow.com/questions/1760757/how-to-efficiently-concatenate-strings-in-go









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

