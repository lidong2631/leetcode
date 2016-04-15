public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;
        int[] index = new int[primes.length];
        
        for(int i=1; i<n; i++) {
            res[i] = Integer.MAX_VALUE;
            
            for(int j=0; j<primes.length; j++) {
// System.out.println("j " + j + " primes[j] " + primes[j] + " index[j] " + index[j] + " res[index[j]] " + res[index[j]]);
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

res[i]      primes                 index
1           2   7   13   19         0   0   0   0
2           4   7   13   19         1   0   0   0
4           8   7   13   19         2   0   0   0
7           8   14  13   19         2   1   0   0


O(n*len(primes)) O(n+len(primes))

https://leetcode.com/discuss/72835/108ms-easy-to-understand-java-solution