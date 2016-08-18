public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] count = new int[256];
        int distinct = 0;
        int i = 0, j = 0, len = 0;
        while (i < s.length()) {
            if (count[s.charAt(i)] == 0) distinct++;
            count[s.charAt(i)]++;
            while (distinct > 2) {
                count[s.charAt(j)]--;
                if (count[s.charAt(j)] == 0) distinct--;
                j++;
            }
            len = Math.max(len, i - j + 1);
            i++;
        }
        return len;
    }
}

时间O(n) 空间O(n)