public class Solution {
    public char findTheDifference(String s, String t) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c-'a']++;
        }
        for (char c : t.toCharArray()) {
            count[c-'a']--;
        }
        char res = 'a';
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                res = (char)('a' + i);
                break;
            }
        }
        return res;
    }
}

O(n) O(1)