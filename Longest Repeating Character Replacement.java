public class Solution {
    public int characterReplacement(String s, int k) {
        int maxLen = 0, maxCount = 0, start = 0;
        int[] count = new int[26];
        for (int end = 0; end < s.length(); end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end)-'A']);
            while (end-start+1 > maxCount + k) {
                --count[s.charAt(start)-'A'];
                start++;
            }
            maxLen = Math.max(maxLen, end-start+1);
        }
        return maxLen;
    }
}

The initial step is to extend the window to its limit, that is, the longest we can get to with maximum number of modifications. 

Until then the variable start will remain at 0.

Then as end increase, the whole substring from 0 to end will violate the rule, so we need to update start accordingly 

(slide the window). We move start to the right until the whole string satisfy the constraint again. 

Then each time we reach such situation, we update our max length.

O(n)

https://discuss.leetcode.com/topic/63494/java-12-lines-o-n-sliding-window-solution-with-explanation/2


Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter 

at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.