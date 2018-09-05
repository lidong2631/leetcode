Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]



Java:
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        for (int i = 1; i < 4 && i < len - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String s1 = s.substring(0, i), s2 = s.substring(i, j), s3 = s.substring(j, k), s4 = s.substring(k, len);
                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return res;
    }
    
    public boolean isValid(String s) {
        if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255)
            return false;
        return true;
    }
}


O(n^3)
https://leetcode.com/problems/restore-ip-addresses/discuss/30949/My-code-in-Java



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
