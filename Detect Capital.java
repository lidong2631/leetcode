public class Solution {
    public boolean detectCapitalUse(String word) {
        int countCapital = 0;
        boolean startCapital = false;
        for (int i = 0; i < word.length(); i++) {
            if (i == 0 && isCapital(word.charAt(i))) {
                startCapital = true;
                countCapital++;
            }
            else if (isCapital(word.charAt(i)))
                countCapital++;
        }
        return ((countCapital == 1 && startCapital) || countCapital == 0 || countCapital == word.length());
    }
    
    private boolean isCapital(char c) {
        if (c - 'A' >= 0 && c - 'A' < 26)
            return true;
        return false;
    }
}

O(n)


Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False
Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.

Hide Company Tags Google
Hide Tags String
