public class Solution {
    public int maxProduct(String[] words) {
        int max = 0;
        int[] bytes = new int[words.length];
        for(int i=0; i<words.length; i++) {
            int val = 0;
            for(int j=0; j<words[i].length(); j++) {
                val |= 1<<(words[i].charAt(j)-'a');     //每个字符对应一位 abceefghijklmnopqrstuvwxyz
            }
            bytes[i] = val;
        }
        for(int i=0; i<words.length; i++) {
            for(int j=i+1; j<words.length; j++) {
                if((bytes[i]&bytes[j])==0)      //判断是否有相同字符
                    max = Math.max(max, words[i].length()*words[j].length());
            }
        }
        return max;
    }
}

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

https://leetcode.com/discuss/74528/bit-manipulation-java-o-n-2


can use int[] array also

public class Solution {
    public int maxProduct(String[] words) {
        int maxLen = 0;
        int[] checkArr = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int val = 0;
            for (int j = 0; j < words[i].length(); j++)
                val |= 1<<(words[i].charAt(j));
            checkArr[i] = val;
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((checkArr[i] & checkArr[j]) == 0)
                    maxLen = Math.max(maxLen, words[i].length() * words[j].length());
            }
        }
        return maxLen;
    }
}