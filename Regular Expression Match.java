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
                if(j>0 && res[0][j-1])  //因为'*'可以抵消掉之前的一个字符 所以只要res[0][j-1]=true 不管res[0][j]是什么 只要p.charAt(j)是'*'
                    res[0][j+1] = true; //res[0][j+1]就是true
                if(p.charAt(j-1)!='.') {    //对应2
                    for(int i=0; i<s.length(); i++) {
                        //对应2.1，                2.2，         2.3 必须要有s.charAt(i-1)==p.charAt(j-1) 因为'*'可以抵消一个前面的字符 case aaa  ab*a
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

难题 想了很久 三种情况

注意p[j+1]对应的是p的第j个字符 因为0到j一共有j+1个字符所以是p[j+1] 而res[j]对应的是p的第j-1个字符 因为res是从1开始 res[1]对应p的第0个字符
                                                                res[j+1]对应p的第j个字符


1 p[j+1]不是'*' 只要判断如果当前s的i位置和p的j位置上的字符是否一样(或者如果p在j上的字符是'.'也可以) 同时res[i][j]==true
则res[i+1][j+1]为true 否则为false    ex s:aab    p:aab

2 p[j+1]是'*', 但是p[j]!='.' 则只要以下条件有一个满足即可对res[i+1][j+1]赋值为true
    1 res[i+1][j]=true '*'只取前面字符一次           ex s:aab   p:aab*   aab和aab对应res[i+1][j]=true 后面的'*'只取前面字符b一次
    2 res[i+1][j-1]=true '*'前面字符一次都不取 也就是抹掉这个字符     ex s:aab    p:aabb* aab和aab对应res[i+1][j-1]=true 后面的'*'抹掉前面b一次
    3 res[i][j+1] &&　s[i]==s[i-1] && s[i-1]==p[j-1] 如果下面的字符一直重复 并且就是'*'前面的字符 则下面的串可以依次匹配    
                                                    ex s:aabbbb p:aab* aab和aab*对应res[i][j+1]=true 第四个b和第三个b相等相当于s[i]=s[i-1]
                                                        s的第三个b和p的第三个b相等对应s[i-1]==p[j-1]
3 p[j+1](p的第j个字符)是'*' 并且p[j](p的第j-1个字符)=='.' 因为'.*'可以匹配任何字符串 
所以前面的res[i+1][j-1]('.'前面的字符是匹配的)或res[i+1][j]('.'是匹配的)中只要有i+1是true 那么后面的res[i+1][j+1], res[i+2][j+1],
...res[s.length()][j+1]就都是true




code ganker 看评论 O(n^2) O(n^2)

这道题有个很重要的点，就是实现的时候外层循环应该是p,然后待匹配串s内层循环扫过来

你在递归方法里提的p[j+1]，其实你是不是想说的是p[j]啊？因为p[j]代表的是p里面第j+1个字符，对应的res也就是res[j+1]