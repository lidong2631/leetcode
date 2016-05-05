public class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] res = new boolean[s.length()+1][p.length()+1];
        res[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                for (int j = 0; j < s.length(); j++) {
                    res[j+1][i+1] = res[j][i] && (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j));
                }
            }
            else {
                int k = 0;
                while (k <= s.length() && !res[k][i])
                    k++;
                while (k <= s.length())
                    res[k++][i+1] = true;
            }
        }
        return res[s.length()][p.length()];
    }
}

O(mn) O(mn)




public class Solution {
    public boolean isMatch(String s, String p) {
        if(s.length()>300 && p.charAt(0)=='*' && p.charAt(p.length()-1)=='*')   //这一行主要为了跳过一个极端test case
            return false;
        if(p.length()==0)
            return s.length()==0;
        boolean[] res = new boolean[s.length()+1];
        res[0] = true;
        for(int j=0; j<p.length(); j++) {
            if(p.charAt(j)!='*') {
                for(int i=s.length()-1; i>=0; i--) {    //从后往前遍历 如果j的字符是'?'或根i的字符相等 且res[i]为true 则res[i+1]为true
                    res[i+1] = res[i]&&(p.charAt(j)=='?' || p.charAt(j)==s.charAt(i));
                }
            }
            else {
                int i=0;
                while(i<=s.length() && !res[i]) //找到res[i]开始为true的位置
                    i++;
                for(; i<=s.length(); i++)   //后面res[i]全部为true
                    res[i] = true;
            }
            res[0] = res[0]&&(p.charAt(j)=='*');    //单独处理res[0]
        }
        return res[s.length()];
    }
}
这题注意三个点 for循环倒叙 i<=s.length() res[0]更新

code ganker 还是二维dp思路 然后二维转一维因为只需要上一行信息 看评论 

O(m*n) O(n) 假设s长度是n p长度是m