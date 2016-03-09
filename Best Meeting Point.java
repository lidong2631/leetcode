public class Solution {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                row[i]+=grid[i][j];
                col[j]+=grid[i][j];
            }
        }
        return minDis1D(row) + minDis1D(col);           //  sum the columns and rows into two vectors and calculate them
    }
    
    private int minDis1D(int[] vector) {                // calcuate min distance for 1D vector
        int i=-1, j=vector.length;
        int left = 0, right = 0, d = 0;
        while(i!=j) {
            if(left<right) {
                d+=left;
                left+=vector[++i];
            }
            else {
                d+=right;
                right+=vector[--j];
            }
        }
        return d;
    }
}

O(m*n) O(m+n)

Moreover, the solution is still O(mn) with the follow up:
What if there are people sharing same home? In other words the number in the grid can be more than 1

https://leetcode.com/discuss/65464/java-python-40ms-pointers-solution-median-sort-explanation

https://leetcode.com/discuss/65336/14ms-java-solution