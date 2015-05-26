public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = s.shortestPalindrome("aacecaaa");
        System.out.println(str);
    }

    public String shortestPalindrome(String s) {
        if(s==null || s.length()==0)
            return "";
        return getLongestPalindrome(s);
    }
    
    private String preProcess(String s) {
        int len = s.length();
        String str = "^";
        for(int i=0; i<len; i++) {
            str+="#"+s.charAt(i);
        }
        str+="#$";
        return str;
    }
    
    private String getLongestPalindrome(String str) {
        char[] s = preProcess(str).toCharArray();
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
                if(i==p[i])
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