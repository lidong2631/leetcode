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
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3
Example 2:

Input: "IV"
Output: 4
Example 3:

Input: "IX"
Output: 9
Example 4:

Input: "LVIII"
Output: 58
Explanation: C = 100, L = 50, XXX = 30 and III = 3.
Example 5:

Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


Python:
class Solution:
    # @return an integer
    def romanToInt(self, s):
        dict = {'M':1000, 'D':500, 'C':100, 'L':50, 'X':10, 'V':5, 'I':1}  
        res, prev, curr = 0, 0, 0
        for i in range(len(s)):              
            curr = dict[s[i]]
            res += curr-2*prev if curr > prev else curr
            prev = curr
        return res




public class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
        
        int res = 0, prev = 0, curr = 0;
        for (char c : s.toCharArray()) {
            curr = map.get(c);
            res += (curr > prev) ? curr-2*prev : curr;
            prev = curr;
        }
        return res;
    }
}




Golang:
func romanToInt(s string) int {
    m := make(map[string]int)
    m["M"] = 1000
    m["D"] = 500
    m["C"] = 100
    m["L"] = 50
    m["X"] = 10
    m["V"] = 5
    m["I"] = 1
    
    res, curr, prev := 0, 0, 0
    for _, v := range s {
        curr = m[string(v)]
        if curr > prev {
            res += curr - 2*prev
        } else {
            res += curr
        }
        prev = curr
    }
    return res
}





from code ganker:

这道题和Integer to Roman一样，也是整数和罗马数字的转换。思路也比较简单，就是维护一个整数，然后如果1下一个字符是对应位的5或者10则减对应位的1，

否则加之。遇到5或者10就直接加上对应位的5或者10。时间复杂度是O(字符串的长度)，空间复杂度是O(1)。代码如下：

public int romanToInt(String s) {

    if(s==null || s.length()==0)
        return 0;
    int res = 0;
    for(int i=0;i<s.length();i++)
    {
        switch(s.charAt(i))
        {
            case 'I':
                if(i<s.length()-1 && (s.charAt(i+1)=='V'||s.charAt(i+1)=='X'))
                {
                    res -= 1;
                }
                else
                {
                    res += 1;
                }
                break;
            case 'V':
                res += 5;
                break;
            case 'X':
                if(i<s.length()-1 && (s.charAt(i+1)=='L'||s.charAt(i+1)=='C'))
                {
                    res -= 10;
                }
                else
                {
                    res += 10;
                }
                break;
            case 'L':
                res += 50;
                break;
            case 'C':
                if(i<s.length()-1 && (s.charAt(i+1)=='D'||s.charAt(i+1)=='M'))
                {
                    res -= 100;
                }
                else
                {
                    res += 100;
                }
                break;
            case 'D':
                res += 500;
                break;
            case 'M':
                res += 1000;
                break;
            default:
                return 0;
        }
    }
    return res;
}


