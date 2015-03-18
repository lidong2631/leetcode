public class Solution {
    public String convertToTitle(int n) {
        String str = "";
        while(n>0) {
            int diff = (n-1)%26;
            str = (char)('A'+diff) + str;
            n = (n-1)/26;
        }
        return str;
    }
}