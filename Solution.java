public class Solution {
    public static void main(String[] args) {
    	Solution s = new Solution();
    	System.out.println(s.titleToNumber("A"));
    }

    public int titleToNumber(String s) {
        int count = 0;
        int res = 0;
        for(int i=s.length()-1; i>=0; i--) {
            System.out.println("26^count:　" + 26^count);
            res+=(s.charAt(i)-'A'+1)*(26^count);
            count++;
        }
        return res;
    }
}