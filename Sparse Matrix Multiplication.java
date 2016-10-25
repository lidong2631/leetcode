public class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int rowA = A.length, colA = A[0].length, colB = B[0].length;    // rowB = colA
        int[][] res = new int[rowA][colB];
        
        List[] tmp = new List[rowA];
        for(int i=0; i<rowA; i++) {
            List<Integer> curr = new ArrayList<Integer>();
            for(int j=0; j<colA; j++) { //判断矩阵A中元素是否为0 非0存入list
                if(A[i][j]!=0) {
                    curr.add(j);        // first is col number second is value
                    curr.add(A[i][j]);
                }
            }
            tmp[i] = curr;
        }
        
        for(int i=0; i<rowA; i++) {
            List<Integer> curr = tmp[i];
            for(int j=0; j<curr.size()-1; j+=2) {   //计算时只取A中非0元素与B对应元素相乘
                int col = curr.get(j);
                int valA = curr.get(j+1);
                for(int k=0; k<colB; k++) {     // valA要和B中col行所有元素乘
                    int valB = B[col][k];
                    res[i][k]+=valA*valB;
                }
            }
        }
        return res;
    }
}

O(m*n) O(m*n)

注意矩阵相乘时是哪个元素乘以哪个元素
A决定行 B决定列

     |  1 1 0 |   | 7 0 |   |  7 0 |
AB = | -1 0 3 | x | 0 0 | = | -7 3 |
                  | 0 1 |
          A           B            res
        2 * 3       3 * 2     2 * 2
final res is rowA * colB

tmp = {{0,1,1,1}, {0,-1,2,3}}

https://leetcode.com/discuss/71912/easiest-java-solution