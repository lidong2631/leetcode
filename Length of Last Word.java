Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5




Python:
class Solution:
    def lengthOfLastWord(self, s):
        """
        :type s: str
        :rtype: int
        """
        if s.split() == []:
            return 0
        else:
            return len(s.split()[len(s.split())-1])



Java:
class Solution {
    public int lengthOfLastWord(String s) {
        String[] strs = s.split("\\s+");
        if (strs.length == 0)
            return 0;
        else
            return strs[strs.length-1].length();
    }
}

https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space

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



Golang:
func lengthOfLastWord(s string) int {
    strs := strings.Fields(s)
    if len(strs) == 0 {
        return 0
    } else {
        return len(strs[len(strs)-1])
    }
}

The Fields function breaks a string around each instance of one or more consecutive white space characters into an Array.
http://www.golangprograms.com/how-to-split-a-string-on-white-space.html












