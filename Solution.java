import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.wordPattern("abba", "dog cat cat dog"));
    }

    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map1 = new HashMap<Character, String>();
        Map<String, Character> map2 = new HashMap<String, Character>();
        int index = 0;
        for(Character c : pattern.toCharArray()) {
            StringBuilder sb = new StringBuilder();
            while(index<str.length() && str.charAt(index)!=' ') {
                sb.append(str.charAt(index++));
            }
            index++;
            if(sb.length()==0)
                return false;
            String tmp = sb.toString();
            if(map1.containsKey(c)) {
                if(!map1.get(c).equals(tmp))
                    return false;
            }
            if(map2.containsKey(tmp)) {
                if(map2.get(tmp)!=c)
                    return false;
            }
            map1.put(c, tmp);
            map2.put(tmp, c);
        }
        System.out.println(index);
        return index==str.length();
    }
}