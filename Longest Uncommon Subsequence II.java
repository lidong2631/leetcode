public class Solution {
    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s2.length() - s1.length();
            }    
        });
        
        boolean flag = false;
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (i != j) {
                    if (isSubsequence(strs[i], strs[j])) {
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) return strs[i].length();
            flag = false;
        }
        return -1;
    }
    
    private boolean isSubsequence(String s1, String s2) {
        int i = 0;
        for (char c : s2.toCharArray()) {
            if (i < s1.length() && s1.charAt(i) == c)
                i++;
        }
        return i == s1.length();
    }
}

O(n^2)


Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.

Example 1:
Input: "aba", "cdc", "eae"
Output: 3
Note:

All the given strings' lengths will not exceed 10.
The length of the given list will be in the range of [2, 50].
Hide Company Tags Google
Hide Tags String
Hide Similar Problems (E) Longest Uncommon Subsequence I
