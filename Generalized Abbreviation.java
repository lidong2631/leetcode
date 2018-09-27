Write a function to generate the generalized abbreviations of a word.

Example:

Given word = "word", return the following list (order does not matter):

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]



Java:
public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        helper(word, res, 0, "", 0);
        return res;
    }
    
    private void helper(String word, List<String> res, int index, String curr, int count) {
        if (index == word.length()) {
            if (count != 0) curr += count;  // careful
            res.add(curr);
            return;
        }
        helper(word, res, index+1, curr, count+1);      // 1. add count number not add character
        helper(word, res, index+1, curr + (count == 0 ? "" : count) + word.charAt(index), 0);   // 2. add character and not add count number
    }
}

for every character, we can keep it or abbreviate it. To keep it, we add it to the current solution and carry on backtracking. 

To abbreviate it, we omit it in the current solution, but increment the count, which indicates how many characters have we 

abbreviated

"word"
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

https://leetcode.com/discuss/75754/java-backtracking-solution
