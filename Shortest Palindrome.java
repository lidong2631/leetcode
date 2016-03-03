https://leetcode.com/discuss/51223/my-7-lines-recursive-java-solution


KMP
https://leetcode.com/discuss/52564/a-kmp-based-java-solution-with-explanation
https://leetcode.com/discuss/64309/clean-kmp-solution-with-super-detailed-explanation


public class Solution {
    public String shortestPalindrome(String s) {
        if(s==null || s.length()==0)
            return "";
        return getLongestPalindrome(s);
    }
    
    private char[] preProcess(String s) {
        int len = s.length();
        int n = 2*len+3;
        char[] arr = new char[n];
        arr[0] = '^'; arr[1] = '#';
        for(int i=0; i<len; i++) {
            arr[2*i+2] = s.charAt(i);
            arr[2*i+3] = '#';
        }
        arr[2*len+2] = '$';
        return arr;
        /*  用下面的程序产生插入＃的字符OJ会TLE 可能是字符串没有char数组效率高 应该用上面的写法
        int len = s.length();
        String str = "^";
        for(int i=0; i<len; i++) {
            str+="#"+s.charAt(i);
        }
        str+="#$";
        return str;
        */
    }
    
    private String getLongestPalindrome(String str) {
        char[] s = preProcess(str);
        int N = s.length;
        int[] p = new int[N+1];
        int id = 0, mx = 0, maxLen = 0;
        for(int i=1; i<N-1; i++) {
            p[i] = mx>i?Math.min(p[2*id-i], mx-i):1;
            while(s[i+p[i]]==s[i-p[i]])
                p[i]++;
            if(i+p[i]>mx) {
                mx = i + p[i];
                id = i;
                if(i==p[i])     //跟Manacher Algorithm唯一的不同 如果p[i]==i 说明当前回文串包含第一个字符 更新maxLen
                    maxLen = mx;
            }
        }
        maxLen = maxLen/2-1;
        StringBuilder res = new StringBuilder();
        for(int i=str.length()-1; i>=maxLen; i--) {
            res.append(str.charAt(i));
        }
        res.append(str);
        return res.toString();
    }
}

只能从前插入字符 所以不是求最长公共子序列 是要找能包含第一个字符的最长回文子串 然后剩下的字符就是要添加在前面的 Manacher Algorithm找第一个字符的最长回文子串即可

求最长回文的Manacher算法时间复杂度为O(n)，可以用在这里，但是在判断最长回文的时候需要加一个条件，就是只比较那些以s[0]开始的回文，而根据Manacher算法的特性，

id == p[id]时回文一定是从s[0]开始的

O(n) O(n)

还有KMP的解法 见http://bookshadow.com/weblog/2015/05/22/leetcode-shortest-palindrome/



http://www.meetqun.com/thread-9508-1-1.html

public class Solution {
    public String shortestPalindrome(String s) {
        int len = s.length();
        int n = 2 * len + 3;
        char[] cs = new char[n];
        cs[0] = '$';
        cs[1] = '#';
        for (int i = 0; i < len; i++) {
                cs[2 * i + 2] = s.charAt(i);
                cs[2 * i + 3] = '#';
        }
        cs[2 * len + 2] = '!';
        int mx = 0;
        int id = 0;
        int p[] = new int[n];
        int maxlenfrombegin = 0;
        for (int i = 1; i < n - 1; i++) {
            if (mx > i) {
                p[i] = Math.min(p[2 * id - i], mx - i);
            } else {
                p[i] = 1;
            }
            while (cs[i + p[i]] == cs[i - p[i]])
                p[i]++;
            if (i + p[i] > mx) {
                mx = i + p[i];
                id = i;
                if (i - p[i] == 0) {
                    maxlenfrombegin = mx;
                }
            }
        }
        maxlenfrombegin = maxlenfrombegin / 2 - 1;
        StringBuffer sb = new StringBuffer();
        for (int i = len - 1; i >= maxlenfrombegin; i--) {
            sb.append(s.charAt(i));
        }
        sb.append(s);
        return sb.toString();
    }
}