Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

Example 1:
Input: "UD"
Output: true
Example 2:
Input: "LL"
Output: false


class Solution {
    public boolean judgeCircle(String moves) {
        int countL = 0, countR = 0, countU = 0, countD = 0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'L') countL++;
            else if (moves.charAt(i) == 'R') countR++;
            else if (moves.charAt(i) == 'U') countU++;
            else if (moves.charAt(i) == 'D') countD++;
        }
        return (countL == countR) && (countU == countD);
    }
}

O(n)