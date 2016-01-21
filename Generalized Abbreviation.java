public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<String>();
        helper(word, res, 0, "", 0);
        return res;
    }
    
    private void helper(String word, List<String> res, int pos, String cur, int count) {
        if(pos==word.length()) {
            if(count>0)
                cur+=count;
            res.add(cur);
        }
        else {
            helper(word, res, pos+1, cur, count+1);     //abbreviate
            helper(word, res, pos+1, cur+(count>0?count:"")+word.charAt(pos), 0);   //keep
        }
    }
}

for every character, we can keep it or abbreviate it. To keep it, we add it to the current solution and carry on backtracking. 

To abbreviate it, we omit it in the current solution, but increment the count, which indicates how many characters have we 

abbreviated

https://leetcode.com/discuss/75754/java-backtracking-solution

similar: Unique Word Abbreviation, Subsets