public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxLen = 0;
        
        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] - '0';
            maxLen = Math.max(maxLen, dp[i][0]);    // careful need to set maxLen accordingly
        }
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i] - '0';
            maxLen = Math.max(maxLen, dp[0][i]);    // careful need to set maxLen accordingly
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                dp[i][j] = (matrix[i][j] == '1') ? Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1 : 0;
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen * maxLen;
    }
}

O(m*n) O(m*n)
dp
递推式是res[i][j] = matrix[i][j]=='1'?Math.min(Math.min(res[i-1][j], res[i][j-1]), res[i-1][j-1]) + 1:0;
res[i][j]表示以i,j为右下角点的全1子正方形矩阵的边长
可以参考http://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/


滚动数组省掉空间一维
public class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return 0;
        int maxLen = 0;
        int res[] = new int[matrix[0].length];
        for(int i=0; i<matrix[0].length; i++) {
            res[i] = matrix[0][i]-'0';
            maxLen = Math.max(res[i], maxLen);
        }
        for(int i=1; i<matrix.length; i++) {
            int[] tmp = new int[matrix[0].length];
            tmp[0] = matrix[i][0]-'0';
            for(int j=1; j<matrix[0].length; j++) {
                tmp[j] = matrix[i][j]=='1'?Math.min(Math.min(res[j-1], res[j]), tmp[j-1])+1:0;
                maxLen = Math.max(maxLen, tmp[j]);
            }
            res = tmp;
        }
        return maxLen*maxLen;
    }
}