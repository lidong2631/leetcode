public class Solution {
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int index1=0, index2=0, index3=0;
        int ugly1=2, ugly2=3, ugly3=5;
        for(int i=1; i<n; i++) {
            res[i] = Math.min(ugly1, Math.min(ugly2, ugly3));
            if(res[i]==ugly1)
                ugly1 = 2*res[++index1];
            if(res[i]==ugly2)
                ugly2 = 3*res[++index2];
            if(res[i]==ugly3)
                ugly3 = 5*res[++index3];
        }
        return res[n-1];
    }
}

O(n) O(n)

https://leetcode.com/discuss/52716/o-n-java-solution

http://www.geeksforgeeks.org/ugly-numbers/