class Solution:
    # @return a string
    def longestCommonPrefix(self, strs):
        if len(strs) == 0:      #如果长度为0 return 空字符串‘’
            return ''
        if len(strs) == 1:      #如果只有一个元素字符串 返回这个字符串
            return strs[0]
        len_list = [len(i) for i in strs]   #非上述情况 建立一个新的list 每个元素为原strs中各个字符串的长度值
        common_prefix = ''
        for i in range(min(len_list)):      #循环最小长度字符串次数 因为最大也只可能是这个字符串
            tmp = strs[0][i]                
            for j in range(1, len(strs)):       #循环 比较每一个字符 如果不相等则返回当前 common_prefix 否则一直加下去
                if strs[j][i]!=tmp:
                    return common_prefix
            common_prefix += tmp
        return common_prefix



public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < strs[0].length(); i++) {        // set strs[0] as standard outer loop traverse each char in strs[0]
            boolean flag = true;
            char tmp = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {         // inner loop go through each string
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
