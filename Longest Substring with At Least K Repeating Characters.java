public class Solution {
    public int longestSubstring(String s, int k) {
        int[] count = new int[26];
        int maxLen = 0;
        for (char c : s.toCharArray()) {
            count[c-'a']++;
        }
        char tmp = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && count[i] < k) {     // careful need to check count[i] > 0
                tmp = (char)('a' + i);
                break;
            }
        }
        if (tmp == 0) return s.length();
        String[] str = s.split(String.valueOf(tmp));
        for (String ss : str)
            maxLen = Math.max(maxLen, longestSubstring(ss, k));
        return maxLen;
    }
}

split the string by any character that does not appear for k times

String s = "aaabbccbbd";
s.split("b") => ["aaa", "", "cc", "", "d"];


https://discuss.leetcode.com/topic/57092/4-lines-python/19

Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.