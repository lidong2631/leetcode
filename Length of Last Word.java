class Solution:
    # @param s, a string
    # @return an integer
    def lengthOfLastWord(self, s):
        if s.split() == []:
            return 0
        else:
            return len(s.split()[len(s.split())-1])




Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.



public class Solution {
    public int lengthOfLastWord(String s) {
        int j = s.length(), len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ')
                j = i;
            else if (i == 0 || s.charAt(i-1) == ' ') {
                len = j - i;
                break;
            }
        }
        return len;
    }
}

O(n) O(1)

