Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.



Python:
class Solution:
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        if len(strs) == 0:
            return ''
        if len(strs) == 1:
            return strs[0]
        res = ''
        for i in range(len(strs[0])):
            flag = True
            tmp = strs[0][i]
            for j in range(1, len(strs)):
                if i >= len(strs[j]) or strs[j][i] != tmp:
                    flag = False
                    break
            if not flag:
                return res
            res += tmp
        return res





public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < strs[0].length(); i++) {        
            boolean flag = true;
            char tmp = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {         
                if (i >= strs[j].length() || strs[j].charAt(i) != tmp) {
                    flag = false;
                    break;
                }
            }
            if (!flag) return res.toString();
            res.append(tmp);
        }
        return res.toString();
    }
}




Golang:
func longestCommonPrefix(strs []string) string {
    if len(strs) == 0 {
        return ""
    }
    var res bytes.Buffer
    for i := 0; i < len(strs[0]); i++ {
        flag, tmp := true, strs[0][i]
        for j := 1; j < len(strs); j++ {
            if i >= len(strs[j]) || strs[j][i] != tmp {
                flag = false
                break
            }
        }
        if !flag {
            return res.String()
        }
        res.WriteString(string(tmp))
    }
    return res.String()
}

in Go you can compare string with their byte value

O(n^2)






from code ganker:

这道题属于字符串处理的题目，思路比较简单，就是brute force的想法，以第一个字符串为标准，对于每个字符串从第一个字符开始，看看是不是和标准一致，如果不同，

则跳出循环返回当前结果，否则继续下一个字符。时间复杂度应该是O(m*n)，m表示字符串的最大长度，n表示字符串的个数，空间复杂度应该是O(m),即字符串的长度，代码如下：

public String longestCommonPrefix(String[] strs) {
    StringBuilder res = new StringBuilder();
    if(strs == null || strs.length==0)
        return res.toString();
    boolean sameFlag = true;
    int idx = 0;
    while(sameFlag)
    {
        for(int i=0;i<strs.length;i++)
        {
            if(strs[i].length()<=idx || strs[i].charAt(idx)!=strs[0].charAt(idx))
            {
                sameFlag = false;
                break;
            }
        }
        if(sameFlag)
            res.append(strs[0].charAt(idx));
        idx++;
    }
    return res.toString();
}

以上代码设置了一个标记，来标记是否结束。细心的读者可能发现中间那个循环index是从0开始，按理说不用对第一个字符串进行判断了，因为他是标准，

这么做的目的其实是因为leetcode有一个测试集是空串，如果不对0进行判断，那么就没有设置flag为false，跑到第二层就会越界。当然也可以手动判断一下，这个其实是小问题。
