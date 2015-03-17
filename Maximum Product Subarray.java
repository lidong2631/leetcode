public class Solution {
    public int maxProduct(int[] A) {
        if(A==null || A.length==0)
            return 0;
        int maxLocal = A[0];
        int minLocal = A[0];
        int global = A[0];
        for(int i=1; i<A.length; i++) {
            int maxCopy = maxLocal;
            maxLocal = Math.max(maxLocal*A[i], Math.max(A[i], minLocal*A[i]));
            minLocal = Math.min(minLocal*A[i], Math.min(A[i], maxCopy*A[i]));
            global = Math.max(global, maxLocal);
        }
        return global;
    }
}