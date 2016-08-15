class Solution:
    # @return a list of strings, [s1, s2]
    def letterCombinations(self, digits):
        def dfs(num, tmpString, res):
            if num == length:           #如果num等于length 则表示递归到底 得到一个解 将它append到res
                res.append(tmpString)
                return
            for letter in dict[digits[num]]:        #每一次递归循环将当前数字对应的字符遍历 然后继续递归下一个数字
                dfs(num+1, tmpString + letter, res)
        
        dict = {
                '2':['a','b','c'],
                '3':['d','e','f'],
                '4':['g','h','i'],
                '5':['j','k','l'],
                '6':['m','n','o'],
                '7':['p','q','r','s'],
                '8':['t','u','v'],
                '9':['w','x','y','z']
            }
        
        length = len(digits)
        res = []
        dfs(0, '', res)
        return res

Note: 这题思路跟combination一样 只是多一个dict映射数字和字母的关系 dfs()函数在类似的几题中均有出现 如subset, combination等 要熟记其套路

另扩展可以考虑不用递归如何做 看code ganker


这道题目和求组合的思路差不多，比较简单。依次读取数字，然后把数字可以代表的字符依次加到当前的所有结果中，然后进入下一次迭代。假设总共有n个digit，

每个digit可以代表k个字符，那么时间复杂度是O(n^k)，就是结果的数量，空间复杂度也是一样。

这道题个人觉得没有太多算法和数据结构的思想，但是自己在facebook的面试中遇到了，所以还是要重视一下，就是一些数组操作的小细节。相关的扩展是这道题如何用递归来解，

思路其实类似，就是对于当前字符，递归剩下的数字串，然后得到结果后加上自己返回回去，大家可以试试




public class Solution {
    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        map.put('0', " ");
        
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        helper(digits, map, 0, "", res);
        return res;
    }
    
    private void helper(String digits, Map<Character, String> map, int index, String item, List<String> res) {
        if (index == digits.length()) {
            res.add(item);
            return;
        }
        String str = map.get(digits.charAt(index));
        for (int i = 0; i < str.length(); i++) {
            helper(digits, map, index+1, item + String.valueOf(str.charAt(i)), res);
        }
    }
}



from code ganker:

这道题目和求组合的思路差不多，比较简单。依次读取数字，然后把数字可以代表的字符依次加到当前的所有结果中，然后进入下一次迭代。假设总共有n个digit，

每个digit可以代表k个字符，那么时间复杂度是O(n^k)，就是结果的数量，空间复杂度也是一样。代码如下： 代码如下： 

public ArrayList<String> letterCombinations(String digits) {
    ArrayList<String> res = new ArrayList<String>();
        res.add("");
    if(digits==null || digits.length()==0)
        return res;
    for(int i=0;i<digits.length();i++)
    {
        String letters = getLetters(digits.charAt(i));
        ArrayList<String> newRes = new ArrayList<String>();
        for(int j=0;j<res.size();j++)
        {
            for(int k=0;k<letters.length();k++)
            {    
                newRes.add(res.get(j)+Character.toString(letters.charAt(k)));
            }
        }
        res = newRes;
    }
    return res;
}
private String getLetters(char digit)
{
    switch(digit)
    {
        case '2':
            return "abc";
        case '3':
            return "def";
        case '4':
            return "ghi";
        case '5':
            return "jkl";
        case '6':
            return "mno";
        case '7':
            return "pqrs";
        case '8':
            return "tuv";
        case '9':
            return "wxyz";
        case '0':
            return " ";
        default:
            return "";
    }
}

这道题个人觉得没有太多算法和数据结构的思想，但是自己在facebook的面试中遇到了，所以还是要重视一下，就是一些数组操作的小细节。相关的扩展是这道题如何用递归来解，

思路其实类似，就是对于当前字符，递归剩下的数字串，然后得到结果后加上自己返回回去，大家可以试试。如果有问题，欢迎留言讨论哈。

