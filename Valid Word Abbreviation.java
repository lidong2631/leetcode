public class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, index = 0, num = 0;
        while (i < abbr.length()) {
            char curr = abbr.charAt(i);
            if (!Character.isDigit(curr)) {     // if not number
                if (index+num >= word.length() || word.charAt(index+num) != curr)   // compare word with abbr
                    return false;
                index += num + 1;       // update index reset num
                num = 0;
            }
            else {
                if (curr == '0' && num == 0) return false;  // careful for cases like '01'
                num = 10 * num + curr - '0';
            }
            i++;
        }
        if (num != 0) return index+num == word.length();    // careful for cases like "a" "2"
        return true;
    }
}

O(n)



Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.