public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<Character, String>();
        Set<String> set = new HashSet<String>();
        return helper(pattern, str, map, set, 0, 0);
    }
    
    private boolean helper(String pattern, String str, Map<Character, String> map, Set<String> set, int lenP, int lenS) {
        if(lenP==pattern.length() && lenS==str.length()) return true;
        if(lenP==pattern.length() || lenS==str.length()) return false;
        
        char c = pattern.charAt(lenP);
        if(map.containsKey(c)) {
            String s = map.get(c);
            if(!str.startsWith(s, lenS))
                return false;
            return helper(pattern, str, map, set, lenP+1, lenS+s.length());
        }
        for(int i=1; i<=str.length()-lenS; i++) {
            String s = str.substring(lenS, lenS+i);
            if(set.contains(s)) continue;
            map.put(c, s);
            set.add(s);
            if(helper(pattern, str, map, set, lenP+1, lenS+i))
                return true;
            map.remove(c);
            set.remove(s);
        }
        return false;
    }
}

very clear
https://leetcode.com/discuss/63252/share-my-java-backtracking-solution