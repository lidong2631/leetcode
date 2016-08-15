public class Solution {
    public String countAndSay(int n) {
        String str = "1";
        for (int i = 2; i <= n; i++) {
            StringBuffer res = new StringBuffer();
            int count = 1;
            for (int j = 1; j < str.length(); j++) {
                if (str.charAt(j) == str.charAt(j-1))
                    count++;
                else {
                    res.append(count);
                    res.append(str.charAt(j-1));
                    count = 1;
                }
            }
            res.append(count);
            res.append(str.charAt(str.length()-1));     // careful
            str = res.toString();
        }
        return str;
    }
}



from code ganker:

这道题属于字符串操作的类型，算法上提高空间不大，只能是对于某一个数的串，读过去然后得到计数并生成下一个串。时间复杂度是O(n*串的长度)，

空间复杂度是O(串的长度)。代码如下：

public String countAndSay(int n) {
    if(n<=0)
        return "";
    String curRes = "1";
    for(int i=2;i<=n;i++)
    {
        StringBuilder res = new StringBuilder();
        int count = 1;
        for(int j=1;j<curRes.length();j++)
        {
            if(curRes.charAt(j)==curRes.charAt(j-1))
                count++;
            else
            {
                res.append(count);
                res.append(curRes.charAt(j-1));
                count = 1;
            }
        }
        res.append(count);
        res.append(curRes.charAt(curRes.length()-1));
        curRes = res.toString();
    }
    return curRes;
}

小陷阱就是跑完循环之后记得把最后一个字符也加上，因为之前只是计数而已。这道题属于字符串或者说数组操作的类型，考察对于明确问题和算法的实现能力，

一般会在电面，或者最容易的第一道题中出现，力求一遍搞定，不留bug哈。
