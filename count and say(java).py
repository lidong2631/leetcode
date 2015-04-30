<<<<<<< HEAD
class Solution:
    def countAndSayStr(self, tmpStr):
        result = ''; count = 0; curr = '#'          #result存放每次得到的字符串结果 count用来记录有几个连续重复的字符 curr记录当前字符的值
        for i in tmpStr:                        #遍历当前字符串中的每一个字符
            if i != curr:                           #如果下一个字符跟当前字符不同 则做如下处理：
                if curr != '#':                     #如果curr不是‘#’(等于'#'只可能在第一次时出现) 将之前字符得到的结果加到result里去
                    result += str(count) + curr
                curr = i                            #将curr更新为新的i 将count赋值1代表一个新的字符出现
                count = 1
            else:                   #否则代表下一个字符是重复字符 count加1
                count += 1
        result += str(count) + curr     #最后剩余的字符 即str(count)+curr加到result
        return result
    
    # @return a string
    def countAndSay(self, n):
        s = '1'                             #初始化s从‘1’开始
        for i in range(2, n+1):             #循环 每次调用countAndSayStr()根据前一个字符串求下一个字符串
            s = self.countAndSayStr(s)
        return s



public class Solution {
    public String countAndSay(int n) {
        String res = "1";
        for(int i=2; i<=n; i++) {
            StringBuilder tmp = new StringBuilder();
            int count = 1;
            for(int j=1; j<res.length(); j++) {
                if(res.charAt(j)==res.charAt(j-1))
                    count++;
                else {
                    tmp.append(count);
                    tmp.append(res.charAt(j-1));
                    count = 1;
                }
            }
            tmp.append(count);
            tmp.append(res.charAt(res.length()-1));
            res = tmp.toString();
        }
        return res;
    }
}

code ganker版 具体实现题 注意复杂度的分析






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















=======
class Solution:
    def countAndSayStr(self, tmpStr):
        result = ''; count = 0; curr = '#'          #result存放每次得到的字符串结果 count用来记录有几个连续重复的字符 curr记录当前字符的值
        for i in tmpStr:                        #遍历当前字符串中的每一个字符
            if i != curr:                           #如果下一个字符跟当前字符不同 则做如下处理：
                if curr != '#':                     #如果curr不是‘#’(等于'#'只可能在第一次时出现) 将之前字符得到的结果加到result里去
                    result += str(count) + curr
                curr = i                            #将curr更新为新的i 将count赋值1代表一个新的字符出现
                count = 1
            else:                   #否则代表下一个字符是重复字符 count加1
                count += 1
        result += str(count) + curr     #最后剩余的字符 即str(count)+curr加到result
        return result
    
    # @return a string
    def countAndSay(self, n):
        s = '1'                             #初始化s从‘1’开始
        for i in range(2, n+1):             #循环 每次调用countAndSayStr()根据前一个字符串求下一个字符串
            s = self.countAndSayStr(s)
        return s





public class Solution {
    public String countAndSay(int n) {
        if(n<=0)
            return "";
        String s = "1";         //从1开始
        for(int i=2; i<n+1; i++)    //从2开始循环
            s = CountAndSay(s);
        return s;
    }
    
    private String CountAndSay(String str) {
        StringBuilder tmp = new StringBuilder();
        int count = 1;
        for(int i=1; i<str.length(); i++)       //循环从1开始
        {
            if(str.charAt(i)!=str.charAt(i-1))  //两个相邻字符不相同
            {   
                tmp.append(count);
                tmp.append(str.charAt(i-1));
                count = 1;
            }
            else                //重复字符
                count++;
        }
        tmp.append(count);                      //最后循环完将剩余字符加到tmp中 因为i从1开始所以最后一定有剩余
        tmp.append(str.charAt(str.length()-1));
        return tmp.toString();
    }
}

Note: python版和code ganker版结合 其实两个版本差不多 只是python版单独写了个函数 这题主要考对既定的算法写程序实现




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















>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
