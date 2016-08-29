public class Solution {
    public boolean canWin(String s) {
        if (s.length() < 2) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.startsWith("++", i)) {
                String str = s.substring(0, i) + "--" +s.substring(i+2);
                if (!canWin(str)) return true;
            }
        }
        return false;
    }
}


You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm runtime complexity.


Backtracking
The idea is try to replace every "++" in the current string s to "--" and see if the opponent can win or not, 
if the opponent cannot win, great, we win!

O(n!!) double factorial?



 If we use HashMap to memorize both win string & lose string, we can further reduce time from 208ms to 18ms

public boolean canWin(String s) {
    if(s == null || s.length() < 2) return false;
    Map<String, Boolean> map = new HashMap<>();
    return canWin(s, map);
}

public boolean canWin(String s, Map<String, Boolean> map){
    if(map.containsKey(s)) return map.get(s);
    for(int i = 0; i < s.length() - 1; i++) {
        if(s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
            String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
            if(!canWin(opponent, map)) {
                map.put(s, true);
                return true;
            }
        }
    }
    map.put(s, false);
    return false;
}

I think this solution reduce the complexity from factorial to exponential. With memorization, the complexity depends on how many 
states the string can be changed to

https://leetcode.com/discuss/64291/share-my-java-backtracking-solution


Another great post about DP solution O(n^2)
https://leetcode.com/discuss/64344/theory-matters-from-backtracking-128ms-to-dp-0ms