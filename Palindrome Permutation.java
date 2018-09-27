Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true




Java:
public class Solution {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) set.remove(s.charAt(i));
            else set.add(s.charAt(i));
        }
        return set.size() == 0 || set.size() == 1;
    }
}

https://leetcode.com/discuss/53295/java-solution-w-set-one-pass-without-counters