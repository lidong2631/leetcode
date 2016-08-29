public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n];
        res[0] = 1;
        int[] index = new int[primes.length];
        for (int i = 1; i < n; i++) {
            res[i] = Integer.MAX_VALUE;
            
            for (int j = 0; j < primes.length; j++) {
                res[i] = Math.min(res[i], primes[j] * res[index[j]]);
            }
            
            for (int j = 0; j < primes.length; j++) {
                if (res[i] == primes[j] * res[index[j]]) index[j]++;
            }
        }
        return res[n-1];
    }
}

Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 

For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given 

primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.

same as ugly number ii

res[i]      primes[j]*res[index[j]]    index                primes
1           2   7   13   19         0   0   0   0       2   7   13  19
2           4   7   13   19         1   0   0   0
4           8   7   13   19         2   0   0   0
7           8   14  13   19         2   1   0   0
8           14  14  13   19         3   1   0   0
13          14  14  26   19         3   1   1   0
14          16  28  26   19         4   2   1   0         


O(n*len(primes)) O(n+len(primes))

https://leetcode.com/discuss/72835/108ms-easy-to-understand-java-solution