public class Solution {
    public int firstUniqChar(String s) {
        int[][] index = new int[26][2];
        for (int i = 0; i < s.length(); i++) {
            if (index[s.charAt(i)-'a'][0] == 0) {
                index[s.charAt(i)-'a'][0]++;
                index[s.charAt(i)-'a'][1] = i;
            }
            else index[s.charAt(i)-'a'][0]++;
        }
        int minIndex = s.length();
        for (int[] arr : index) {
            if (arr[0] == 1) minIndex = Math.min(minIndex, arr[1]);
        }
        return (minIndex == s.length()) ? -1 : minIndex;
    }
}

create a table key is character value is an array contains count of characters and its first position

O(n) O(1)

http://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/



Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.