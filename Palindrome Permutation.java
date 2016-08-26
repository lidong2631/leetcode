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