Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.




Java:
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] count = new int[256];
        int i = 0, j = 0, distinct = 0, len = 0;
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