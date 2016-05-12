From cleanCode

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(); int n = t.length();
        if(m>n)                                 //如果m大于n 交换他们的位置 总是保证m<=n 这样方便处理
            return isOneEditDistance(t, s);
        if(n-m>1)       
            return false;
        int i = 0;
        while(i<m && s.charAt(i)==t.charAt(i))  
            i++;
        if(i==m)        
            return n-m==1;                      // append
        if(n==m)      
            i++;                                // modify
        while(i<m && s.charAt(i)==t.charAt(i+n-m))
            i++;
        return i==m;
    }
}

这题首先通过swap保证m一定小于等于n 然后判断m和n是否是三种关系(append, modify, insert)中的一种 思路还是比较清晰的 时间O(n) 空间O(1)