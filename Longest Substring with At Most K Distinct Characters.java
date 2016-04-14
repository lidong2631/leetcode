public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int numDis = 0;
        int j = 0, maxLen = 0;
        for (int i=0; i<s.length(); i++) {
            if (count[s.charAt(i)] == 0)
                numDis++;
            count[s.charAt(i)]++;
            while (numDis > k) {
                count[s.charAt(j)]--;
                if (count[s.charAt(j)] == 0)
                    numDis--;
                j++;
            }
            maxLen = Math.max(maxLen, i-j+1);
        }
        return maxLen;
    }
}

O(n)

same as Longest Substring With At Most Two Distinct Characters