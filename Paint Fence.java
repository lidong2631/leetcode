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

O(n) O(1)

https://leetcode.com/discuss/56173/o-n-time-java-solution-o-1-space