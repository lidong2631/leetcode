public class Solution {
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        int diffColor = k * (k - 1), sameColor = k;
        for (int i = 3; i <= n; i++) {
            int tmp = diffColor;
            diffColor = (sameColor + diffColor) * (k - 1);
            sameColor = tmp;
        }
        return sameColor + diffColor;
    }
}


We divided it into two cases.

the last two posts have the same color, the number of ways to paint in this case is sameColorCounts.

the last two posts have different colors, and the number of ways in this case is diffColorCounts.


O(n) O(1)

https://leetcode.com/discuss/56173/o-n-time-java-solution-o-1-space


There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.