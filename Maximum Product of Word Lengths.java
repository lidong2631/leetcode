public class Solution {
    public int maxProduct(String[] words) {
        int maxLen = 0;
        int[] arr = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String tmp = words[i];
            for (int j = 0; j < tmp.length(); j++) {
                arr[i] |= 1 << (tmp.charAt(j) - 'a');
            }
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((arr[i] & arr[j]) == 0) maxLen = Math.max(maxLen, words[i].length() * words[j].length());
            }
        }
        return maxLen;
    }
}

https://discuss.leetcode.com/topic/35539/java-easy-version-to-understand

Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.

O(n^2)