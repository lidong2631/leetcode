public class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for(int i=2; i<n; i++)
            isPrime[i] = true;
        for(int i=2; i*i<n; i++) {
            for(int j=i; j*i<n; j++) {
                isPrime[j*i] = false;
            }
        }
        int count = 0;
        for(int i=2; i<n; i++) {
            if(isPrime[i])
                count++;
        }
        return count;
    }
}

埃拉托色尼筛选法 Sieve of Eratosthenes

时间O(nlog(logn)) 空间O(n)

http://introcs.cs.princeton.edu/java/14array/PrimeSieve.java.html

http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes

复杂度分析
http://stackoverflow.com/questions/2582732/time-complexity-of-sieve-of-eratosthenes-algorithm
http://math.stackexchange.com/questions/317274/how-to-show-that-eratosthenes-sieve-algorithm-has-a-complexity-of-on-log-n
http://en.wikipedia.org/wiki/Divergence_of_the_sum_of_the_reciprocals_of_the_primes
http://apps.topcoder.com/forums/;jsessionid=4883B3FC83082B2741A6033D265FAD1E?module=Thread&threadID=779972&start=0&mc=9#1689982