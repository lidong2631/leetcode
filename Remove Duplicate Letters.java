public class Solution {
    public String removeDuplicateLetters(String s) {
        if(s==null || s.length()==0)
            return "";
        int[] count = new int[26];
        int pos = 0;
        for(int i=0; i<s.length(); i++)
            count[s.charAt(i)-'a']++;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)<s.charAt(pos)) pos = i;
            if(--count[s.charAt(i)-'a']==0) break;
        }
        return s.charAt(pos)+removeDuplicateLetters((s.substring(pos+1)).replaceAll(""+s.charAt(pos),""));
    }
}

Given the string s, the greedy choice (i.e., the leftmost letter in the answer) is the smallest s[i], s.t. 

the suffix s[i .. ] contains all the unique letters. (Note that, when there are more than one smallest s[i]'s, 

we choose the leftmost one. Why? Simply consider the example: "abcacb".)

After determining the greedy choice s[i], we get a new string s' from s by

removing all letters to the left of s[i],
removing all s[i]'s from s.
We then recursively solve the problem w.r.t. s'.

O(26*n) = O(n)

if not add replaceAll()
Input:
"cbacdcbc"
Output:
"acdbc"
Expected:
"acdb"

https://leetcode.com/discuss/73761/a-short-o-n-recursive-greedy-solution