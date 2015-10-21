public class Solution {
    public boolean canWin(String s) {
        if(s==null || s.length()<2)
            return false;
        for(int i=0; i<s.length(); i++) {
            if(s.startsWith("++", i)) {
                String tmp = s.substring(0,i) + "--" + s.substring(i+2);
                if(!canWin(tmp))
                    return true;
            }
        }
        return false;
    }
}

Backtracking
The idea is try to replace every "++" in the current string s to "--" and see if the opponent can win or not, 
if the opponent cannot win, great, we win!

O(n!!) double factorial

https://leetcode.com/discuss/64291/share-my-java-backtracking-solution


Another great post about DP solution O(n^2)
https://leetcode.com/discuss/64344/theory-matters-from-backtracking-128ms-to-dp-0ms