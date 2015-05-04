public class Solution {
    public boolean isMatch(String s, String p) {
        if(s.length()==0 && p.length()==0)
            return true;
        if(p.length()==0)
            return false;
        boolean[][] res = new boolean[s.length()+1][p.length()+1];
        res[0][0] = true;
        for(int j=0; j<p.length(); j++) {
            if(p.charAt(j)=='*') {      //对应2和3
                if(j==0)
                    continue;
                if(j>0 && res[0][j-1])
                    res[0][j+1] = true;
                if(p.charAt(j-1)!='.') {    //对应2
                    for(int i=0; i<s.length(); i++) {
                        if(res[i+1][j] || j>0&&res[i+1][j-1] || i>0&&j>0&&res[i][j+1]&&s.charAt(i-1)==s.charAt(i)&&s.charAt(i-1)==p.charAt(j-1))
                            res[i+1][j+1] = true;
                    }
                }
                else {      //对应3
                    int i = 0;
                    while(i<s.length() && j>0 && !res[i+1][j-1] && !res[i+1][j])
                        i++;
                    for(; i<s.length(); i++)
                        res[i+1][j+1] = true;
                }
            }
            else {      //对应1
                for(int i=0; i<s.length(); i++) {
                    if(s.charAt(i)==p.charAt(j) || p.charAt(j)=='.')
                        res[i+1][j+1] = res[i][j];
                }
            }
        }
        return res[s.length()][p.length()];
    }
}

code ganker 看评论 O(n^2) O(n^2)

这道题有个很重要的点，就是实现的时候外层循环应该是p,然后待匹配串s内层循环扫过来

你在递归方法里提的p[j+1]，其实你是不是想说的是p[j]啊？因为p[j]代表的是p里面第j+1个字符，对应的res也就是res[j+1]