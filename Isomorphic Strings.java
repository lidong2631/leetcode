public class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mapS = new HashMap<Character, Character>();
        Map<Character, Character> mapT = new HashMap<Character, Character>();
        for(int i=0; i<s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(mapS.containsKey(c1)) {
                if(mapS.get(c1)!=c2)
                    return false;
            }
            if(mapT.containsKey(c2)) {
                if(mapT.get(c2)!=c1)
                    return false;
            }
            mapS.put(c1, c2);
            mapT.put(c2, c1);
        }
        return true;
    }
}