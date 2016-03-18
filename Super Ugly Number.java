public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;
        int[] index = new int[primes.length];
        
        for(int i=1; i<n; i++) {
            res[i] = Integer.MAX_VALUE;
            
            for(int j=0; j<primes.length; j++) {
                res[i] = Math.min(res[i], primes[j]*res[index[j]]);
            }
            
            for(int j=0; j<primes.length; j++) {
                if(res[i]==primes[j]*res[index[j]])
                    index[j]++;
            }
        }
        return res[n-1];
    }
}

same as ugly number ii

https://leetcode.com/discuss/72835/108ms-easy-to-understand-java-solution