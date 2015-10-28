public class Solution {
    public int minCostII(int[][] costs) {
        if(costs==null || costs.length==0 || costs[0].length==0)
            return 0;
        int n = costs.length, k = costs[0].length;
        int m1 = -1, m2 = -1;
        for(int i=0; i<n; i++) {
            int last1 = m1, last2 = m2;
            m1 = -1; m2 = -1;
            for(int j=0; j<k; j++) {
                if(j!=last1)
                    costs[i][j]+=last1<0?0:costs[i-1][last1];
                else
                    costs[i][j]+=last2<0?0:costs[i-1][last2];
                
                if(m1<0 || costs[i][j]<costs[i][m1]) {
                    m2 = m1;
                    m1 = j;
                } else if(m2<0 || costs[i][j]<costs[i][m2])
                    m2 = j;
            }
        }
        return costs[n-1][m1];
    }
}
The idea is similar to the problem Paint House I, for each house and each color, the minimum cost of painting the house with that color should be 

the minimum cost of painting previous houses, and make sure the previous house doesnt paint with the same color.

We can use min1 and min2 to track the indices of the 1st and 2nd smallest cost till previous house, if the current color index is same as min1, 

then we have to go with min2, otherwise we can safely go with min1.

O(kn) O(1)

https://leetcode.com/discuss/54415/ac-java-solution-without-extra-space