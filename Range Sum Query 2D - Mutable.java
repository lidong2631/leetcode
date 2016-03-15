public class NumMatrix {

    int[][] BITree;
    int[][] arr;
    int m;
    int n;

    public NumMatrix(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return;
            
        m = matrix.length;
        n = matrix[0].length;
        BITree = new int[m+1][n+1];
        arr = new int[m][n];
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                update(i, j, matrix[i][j]);
                arr[i][j] = matrix[i][j];
            }
        }
    }

    public void update(int row, int col, int val) {
        if(m==0 || n==0) return;
        
        int diff = val - arr[row][col];
        arr[row][col] = val;
        
        for(int i=row+1; i<=m; i += i & (-i)) {
            for(int j=col+1; j<=n; j += j & (-j)) {
                BITree[i][j]+=diff;
            }
        }
    }

    public int getSum(int row, int col) {
        if(m==0 || n==0) return 0;
        
        int sum = 0;
        
        for(int i=row; i>0; i -= i & (-i)) {
            for(int j=col; j>0; j -= j & (-j)) {
                sum+=BITree[i][j];
            }
        }
        return sum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(m==0 || n==0) return 0;
        
        return getSum(row2+1, col2+1) - getSum(row1, col2+1) - getSum(row2+1, col1) + getSum(row1, col1);
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);



2D Binary Indexed Tree
similar to Range Sum Query 2D - Mutable

https://leetcode.com/discuss/71169/java-2d-binary-indexed-tree-solution-clean-and-short-17ms