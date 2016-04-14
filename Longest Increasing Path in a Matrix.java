public class Solution {
    int[][] dis = {{-1,0},{1,0},{0,1},{0,-1}};
    
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int res = 0;
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                res = Math.max(res, dfs(i,j,matrix,dp));
            }
        }
        return res;
    }
    
    private int dfs(int i, int j, int[][] matrix, int[][] dp) {
        if(dp[i][j]>0) return dp[i][j];                         // cache. If we already has dp[i][j] then return directly
        int max = 0;
        for(int k=0; k<4; k++) {
            if(i+dis[k][0]>=0 && i+dis[k][0]<matrix.length && j+dis[k][1]>=0 && j+dis[k][1]<matrix[0].length 
                && matrix[i][j]<matrix[i+dis[k][0]][j+dis[k][1]])
                max = Math.max(max, dfs(i+dis[k][0],j+dis[k][1],matrix,dp));
        }
        dp[i][j] = max+1;
        return dp[i][j];
    }
}

O(m*n) O(m*n)

https://leetcode.com/discuss/81235/java-dfs-dp-solution

https://leetcode.com/discuss/81389/15ms-concise-java-solution