Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?



Java:
public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] map = new int[256];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        for (int j = 0; j < t.length(); j++) {
            map[t.charAt(j)]--;
            if (map[t.charAt(j)] < 0)
                return false;
        }
        return true;
    }
}

cc150原题 O(n) O(1)


public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
        	return false;
        return sortStr(s).equals(sortStr(t));
    }
    
    private String sortStr(String str) {
        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);
        return new String(charArr);
    }
}

O(nlogn) O(1)