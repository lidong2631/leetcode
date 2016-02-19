<<<<<<< HEAD
class Solution:
    def dfs(self, currIP, restStr, currIndex):
        if currIndex == 3:      #如果当前数字已经有3位
            if len(restStr) > 0:        #就要看下是否还有剩下字符串
                if str(int(restStr)) == restStr and int(restStr) <= 255:    #如果有且数字小于255 就可以将结果(currIP + restStr)append进结果集
                    Solution.res.append(currIP + restStr)
            return
        length = len(restStr)   #计算长度
        for i in range(1, 4):       #循环3次 每次多加1位 递归调用dfs遍历所有情况
            if length >= i and str(int(restStr[0:i])) == restStr[0:i] and int(restStr[0:i]) <= 255:
                self.dfs(currIP + restStr[0:i] + '.', restStr[i:], currIndex + 1)
        return
    
    # @param s, a string
    # @return a list of strings
    def restoreIpAddresses(self, s):
        Solution.res = []
        self.dfs('', s, 0)
        return Solution.res

递归方法，str(int(s)) == s是为了去掉前导0，即Input＝"010010"时, Output不能是"0.1.0.010"之类的。

这段代码from kitt



public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        if(s==null || s.length()>12)
            return res;
        helper(s, 0, 1, "", res);
        return res;
    }
    
    private void helper(String s, int index, int segment, String item, List<String> res) {
        if(segment==4) {
            String sub = s.substring(index);
            if(isValid(sub)) {
                res.add(item+"."+sub);
            }
            return;
        }
        for(int i=1; i<4&&(index+i<=s.length()); i++) {
            String str = s.substring(index, index+i);
            if(isValid(str)) {
                if(segment==1)
                    helper(s, index+i, segment+1, str, res);
                else
                    helper(s, index+i, segment+1, item+"."+str, res);
            }
        }
    }
    
    private boolean isValid(String s) {
        if(s.charAt(0)=='0' && s.length()>1)
            return false;
        else if(Integer.parseInt(s)>=0 && Integer.parseInt(s)<=255)
            return true;
        return false;
    }
}

NP问题套路 但因为ip地址的限制实际不是NP问题

看code ganker评论 复杂度类似于O(2^k)



from code ganker:

这道题的解法非常接近于NP问题，也是采用递归的解法。基本思路就是取出一个合法的数字，作为IP地址的一项，然后递归处理剩下的项。可以想象出一颗树，

每个结点有三个可能的分支（因为范围是0-255，所以可以由一位两位或者三位组成）。并且这里树的层数不会超过四层，因为IP地址由四段组成，

到了之后我们就没必要再递归下去，可以结束了。这里除了上述的结束条件外，另一个就是字符串读完了。可以看出这棵树的规模是固定的，不会像平常的NP问题那样，

时间复杂度取决于输入的规模，是指数量级的，所以这道题并不是NP问题，因为他的分支是四段，有限制。代码如下：

public ArrayList<String> restoreIpAddresses(String s) {
    ArrayList<String> res = new ArrayList<String>();
    if(s==null || s.length()==0)
        return res;
    helper(s,0,1,"",res);
    return res;
}
private void helper(String s, int index, int segment, String item, ArrayList<String> res)
{
    if(index>=s.length())
        return;
    if(segment == 4)
    {
        String str = s.substring(index);
        if(isValid(str))
        {
            res.add(item+"."+str);
        }
        return;
    }
    for(int i=1;i<4&&(i+index<=s.length());i++)
    {
        String str = s.substring(index, index+i);
        if(isValid(str))
        {
            if(segment==1)
                helper(s,index+i,segment+1,str,res);
            else
                helper(s,index+i,segment+1,item+"."+str,res);
        }
    }
}
private boolean isValid(String str)
{
    if(str==null || str.length()>3)
        return false;
    int num = Integer.parseInt(str);
    if(str.charAt(0)=='0' && str.length()>1)
        return false;
    if(num>=0 && num<=255)
        return true;
    return false;
}

实现中需要一个判断数字是否为合法ip地址的一项的函数，首先要在0-255之间，其次前面字符不能是0。剩下的就是NP问题的套路了，递归中套一个for循环，

不熟悉的朋友可以看看N-Queens哈。
