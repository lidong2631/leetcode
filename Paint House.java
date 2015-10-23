public class Solution {
    public int minCost(int[][] costs) {
        if(costs==null || costs.length==0)
            return 0;
        int r = 0, b = 0, g = 0, len = costs.length;
        for(int i=0; i<len; i++) {
            int rr = r, bb = b, gg = g;
            r = costs[i][0] + Math.min(bb, gg);
            b = costs[i][1] + Math.min(rr, gg);
            g = costs[i][2] + Math.min(rr, bb);
        }
        return Math.min(r, Math.min(b, g));
    }
}

O(n) O(1)

https://leetcode.com/discuss/51721/simple-java-dp-solution