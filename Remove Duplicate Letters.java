public class Solution {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return "";
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        boolean[] visited = new boolean[26];
        
        for (char c : s.toCharArray()) {
            count[c-'a']++;
        }
        for (char c : s.toCharArray()) {
            count[c-'a']--;
            if (visited[c-'a']) continue;
            
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek()-'a'] > 0) {
                visited[stack.peek()-'a'] = false;
                stack.pop();
            }
            stack.push(c);
            visited[c-'a'] = true;
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}

O(n) O(n)

https://discuss.leetcode.com/topic/43469/java-o-n-solution-using-stack-with-detail-explanation/2

Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
