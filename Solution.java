public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] matrix = {{'1', '1'}, {'1', '1'}};
        int i = s.maximalSquare(matrix);
        System.out.println(i);
    }

    public int maximalSquare(char[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return 0;
        int maxLen = 0;
        int res[][] = new int[matrix.length][matrix[0].length];
        for(int i=0; i<matrix.length; i++) {
            res[i][0] = Character.getNumericValue(matrix[i][0]);
            System.out.println(res[i][0]);
            maxLen = Math.max(maxLen, res[i][0]);
        }
        for(int j=0; j<matrix[0].length; j++) {
            res[0][j] = Character.getNumericValue(matrix[0][j]);
            System.out.println(res[0][j]);
            maxLen = Math.max(maxLen, res[0][j]);
        }
        for(int i=1; i<matrix.length; i++) {
            for(int j=1; j<matrix[0].length; j++) {
                System.out.println("res[i-1][j] " + Character.getNumericValue(res[i-1][j]));
                System.out.println("res[i][j-1] " + Character.getNumericValue(res[i][j-1]));
                System.out.println("res[i-1][j-1] " + Character.getNumericValue(res[i-1][j-1]));

                res[i][j] = Math.min(Math.min(res[i-1][j], res[i][j-1]),res[i-1][j-1]) + 1;
                System.out.println("res[i][j] " + res[i][j]);
                maxLen = Math.max(maxLen, res[i][j]);
            }
        }
        return maxLen*maxLen;
    }
}