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

注意两点 1 必须双向都判断是为了检查s中是否有不同字符map到t中相同字符 test case: "ab" "aa"     

2 必须两个hashmap 是为了检查s中字符map到t中的字符之前出现过在s中 如果不用两个hashmap 程序会认为有重复map test case: "ab" "ca"

O(n) O(n)