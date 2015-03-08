<<<<<<< HEAD
class Solution:
    # @return an integer
    def romanToInt(self, s):
        dict = {'M':1000, 'D':500, 'C':100, 'L':50, 'X':10, 'V':5, 'I':1}   #首先建立一个字典
        last = ''; num = 0
        last = s[0]; num+=dict[s[0]]        #初始化last用来保存上一个字符   num用来记录结果值初始为第一个字符对应的数字
        for i in range(1, len(s)):              #从第二个位置开始循环遍历
            if dict[s[i]] == dict[last]:    #如果当前值和last对应值相等 将当前值加入num ex： MMM
                num += dict[s[i]]
            elif dict[s[i]] < dict[last]:   #如果当前值比last对应值小 将当前值加入num 并更新last ex：MD
                    num += dict[s[i]]
                    last = s[i]
            else:                               #如果当前值比last对应值大 将当前值加入num 并减去两倍的last对应值 ex： MCD
                num = num + dict[s[i]] - 2*dict[last]
        return num

Note: 这题就是分三种情况处理即可




from wikipedia:

羅馬數字共有7個，即I（1）、V（5）、X（10）、L（50）、C（100）、D（500）和M（1000）。按照下述的規則可以表示任意正整數。需要注意的是罗马数字中没有“0”，與進位制無關。

一般認為羅馬數字只用來記數，而不作演算。

重複數次：一個羅馬數字重複幾次，就表示這個數的幾倍。



右加左減： 
在較大的羅馬數字的右邊記上較小的羅馬數字，表示大數字加小數字。

在較大的羅馬數字的左邊記上較小的羅馬數字，表示大數字减小數字。

左减的数字有限制，仅限于I、X、C。比如45不可以写成VL，只能是XLV

但是，左減時不可跨越一個位數。比如，99不可以用IC（）表示，而是用XCIX（）表示。（等同於阿拉伯數字每位數字分別表示。）

左減數字必須為一位，比如8寫成VIII，而非IIX。

右加數字不可連續超過三位，比如14寫成XIV，而非XIIII。（見下方“數碼限制”一項。）



加線乘千： 
在羅馬數字的上方加上一條橫線或者加上下標的Ⅿ，表示將這個數乘以1000，即是原數的1000倍。

同理，如果上方有兩條橫線，即是原數的1000000（）倍。



數碼限制： 
同一數碼最多只能出現三次，如40不可表示為XXXX，而要表示為XL。

例外：由於IV是古羅馬神話主神朱庇特（即IVPITER，古羅馬字母裡沒有J和U）的首字，因此有時用IIII代替IV。




public class Solution {
    public int romanToInt(String s) {
        if(s==null || s.length()==0)
            return 0;
        
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        dict.put('M', 1000);
        dict.put('D', 500);
        dict.put('C', 100);
        dict.put('L', 50);
        dict.put('X', 10);
        dict.put('V', 5);
        dict.put('I', 1);
        
        char last = s.charAt(0);
        int num = dict.get(last);
        for(int i=1; i<s.length(); i++)
        {
            if(dict.get(s.charAt(i))==dict.get(last))       //表示罗马数字的三种情况 两个数相等
                num+=dict.get(s.charAt(i));
            else if(dict.get(s.charAt(i))<dict.get(last))   //前一个数大于后一个数 右加左减
            {
                num+=dict.get(s.charAt(i));
                last = s.charAt(i);
            }
            else
                num = num + dict.get(s.charAt(i)) - 2*dict.get(last);   //前一个数小于后一个数
        }
        return num;
    }
}

Note: 根据python版改编 code ganker版没看 都差不多 这题思路很简单 就是搞清楚罗马数字的表示方式就可以了





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

题目思路比较清晰，就是简单的字符串操作。
=======
class Solution:
    # @return an integer
    def romanToInt(self, s):
        dict = {'M':1000, 'D':500, 'C':100, 'L':50, 'X':10, 'V':5, 'I':1}   #首先建立一个字典
        last = ''; num = 0
        last = s[0]; num+=dict[s[0]]        #初始化last用来保存上一个字符   num用来记录结果值初始为第一个字符对应的数字
        for i in range(1, len(s)):              #从第二个位置开始循环遍历
            if dict[s[i]] == dict[last]:    #如果当前值和last对应值相等 将当前值加入num ex： MMM
                num += dict[s[i]]
            elif dict[s[i]] < dict[last]:   #如果当前值比last对应值小 将当前值加入num 并更新last ex：MD
                    num += dict[s[i]]
                    last = s[i]
            else:                               #如果当前值比last对应值大 将当前值加入num 并减去两倍的last对应值 ex： MCD
                num = num + dict[s[i]] - 2*dict[last]
        return num

Note: 这题就是分三种情况处理即可




from wikipedia:

羅馬數字共有7個，即I（1）、V（5）、X（10）、L（50）、C（100）、D（500）和M（1000）。按照下述的規則可以表示任意正整數。需要注意的是罗马数字中没有“0”，與進位制無關。

一般認為羅馬數字只用來記數，而不作演算。

重複數次：一個羅馬數字重複幾次，就表示這個數的幾倍。



右加左減： 
在較大的羅馬數字的右邊記上較小的羅馬數字，表示大數字加小數字。

在較大的羅馬數字的左邊記上較小的羅馬數字，表示大數字减小數字。

左减的数字有限制，仅限于I、X、C。比如45不可以写成VL，只能是XLV

但是，左減時不可跨越一個位數。比如，99不可以用IC（）表示，而是用XCIX（）表示。（等同於阿拉伯數字每位數字分別表示。）

左減數字必須為一位，比如8寫成VIII，而非IIX。

右加數字不可連續超過三位，比如14寫成XIV，而非XIIII。（見下方“數碼限制”一項。）



加線乘千： 
在羅馬數字的上方加上一條橫線或者加上下標的Ⅿ，表示將這個數乘以1000，即是原數的1000倍。

同理，如果上方有兩條橫線，即是原數的1000000（）倍。



數碼限制： 
同一數碼最多只能出現三次，如40不可表示為XXXX，而要表示為XL。

例外：由於IV是古羅馬神話主神朱庇特（即IVPITER，古羅馬字母裡沒有J和U）的首字，因此有時用IIII代替IV。




public class Solution {
    public int romanToInt(String s) {
        if(s==null || s.length()==0)
            return 0;
        
        HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
        dict.put('M', 1000);
        dict.put('D', 500);
        dict.put('C', 100);
        dict.put('L', 50);
        dict.put('X', 10);
        dict.put('V', 5);
        dict.put('I', 1);
        
        char last = s.charAt(0);
        int num = dict.get(last);
        for(int i=1; i<s.length(); i++)
        {
            if(dict.get(s.charAt(i))==dict.get(last))       //表示罗马数字的三种情况 两个数相等
                num+=dict.get(s.charAt(i));
            else if(dict.get(s.charAt(i))<dict.get(last))   //前一个数大于后一个数 右加左减
            {
                num+=dict.get(s.charAt(i));
                last = s.charAt(i);
            }
            else
                num = num + dict.get(s.charAt(i)) - 2*dict.get(last);   //前一个数小于后一个数
        }
        return num;
    }
}

Note: 根据python版改编 code ganker版没看 都差不多 这题思路很简单 就是搞清楚罗马数字的表示方式就可以了





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

题目思路比较清晰，就是简单的字符串操作。
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
