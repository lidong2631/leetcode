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


