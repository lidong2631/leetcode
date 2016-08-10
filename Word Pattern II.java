public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<Character, String>();
        Set<String> set = new HashSet<String>();
        return helper(pattern, str, map, set, 0, 0);
    }
    
    private boolean helper(String pattern, String str, Map<Character, String> map, Set<String> set, int lenP, int lenS) {
        if(lenP==pattern.length() && lenS==str.length()) return true;
        if(lenP==pattern.length() || lenS==str.length()) return false;
        
        char c = pattern.charAt(lenP);  // get current pattern character
        
        if(map.containsKey(c)) {        // if the pattern character exists
            String s = map.get(c);
            
            if(!str.startsWith(s, lenS))    // then check if we can use it to match str[i...i+s.length()]
                return false;
            
            return helper(pattern, str, map, set, lenP+1, lenS+s.length());  // if it can match, great, continue to match the rest
        }
        
        for(int i=1; i<=str.length()-lenS; i++) {       // pattern character does not exist in the map
            String s = str.substring(lenS, lenS+i);
            if(set.contains(s)) continue;
            map.put(c, s);
            set.add(s);
            
            if(helper(pattern, str, map, set, lenP+1, lenS+i))      // continue to match the rest
                return true;
            
            map.remove(c);          // backtracking
            set.remove(s);
        }
        return false;
    }
}


Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.

very clear
https://leetcode.com/discuss/63252/share-my-java-backtracking-solution