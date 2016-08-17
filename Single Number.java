public class Solution {
    public int singleNumber(int[] A) {
        if(A==null || A.length==0)
            return -1;
        int num = 0;
        for(int x : A) {
            num^=x;
        }
        return num;
    }
}

Note: 只有出现2次可以用这个异或的解法解 更通用的方法看single number ii