Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.




Java:
public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int i = 0, j = 0, distinct = 0, maxLen = 0;
        while (i < s.length()) {
            if (count[s.charAt(i)] == 0) distinct++;
            count[s.charAt(i)]++;
            while (distinct > k) {
                count[s.charAt(j)]--;
                if (count[s.charAt(j)] == 0) distinct--;
                j++;
            }
            maxLen = Math.max(maxLen, i-j+1);
            i++;
        }
        return maxLen;
    }
}

O(n)

same as Longest Substring With At Most Two Distinct Characters