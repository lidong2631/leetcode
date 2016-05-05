public class Solution {
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int[] res = new int[n+1];
        res[2] = 2;
        res[3] = 3;
        for (int i = 4; i <= n; i++) {
            res[i] = Math.max(res[i/2] * res[i-i/2], 
                     Math.max(res[2] * res[i-2], res[3] * res[i-3]));
        }
        return res[n];
    }
}

https://leetcode.com/discuss/98144/java-o-n-dp-solution-store-and-reuse-products