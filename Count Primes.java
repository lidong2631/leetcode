Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.




Java:
public int countPrimes(int n) {
   boolean[] isPrime = new boolean[n];
   for (int i = 2; i < n; i++) {
      isPrime[i] = true;
   }
   // Loop's ending condition is i * i < n instead of i < sqrt(n)
   // to avoid repeatedly calling an expensive function sqrt().
   for (int i = 2; i * i < n; i++) {
      if (!isPrime[i]) continue;
      for (int j = i * i; j < n; j += i) {
         isPrime[j] = false;
      }
   }
   int count = 0;
   for (int i = 2; i < n; i++) {
      if (isPrime[i]) count++;
   }
   return count;
}

see example https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes

leetcode hint讲得很好
上面的解法跟下面一样

埃拉托色尼筛选法 Sieve of Eratosthenes

时间O(nlog(logn)) 空间O(n)

http://introcs.cs.princeton.edu/java/14array/PrimeSieve.java.html

http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes

复杂度分析
http://stackoverflow.com/questions/2582732/time-complexity-of-sieve-of-eratosthenes-algorithm
http://math.stackexchange.com/questions/317274/how-to-show-that-eratosthenes-sieve-algorithm-has-a-complexity-of-on-log-n
http://en.wikipedia.org/wiki/Divergence_of_the_sum_of_the_reciprocals_of_the_primes
http://apps.topcoder.com/forums/;jsessionid=4883B3FC83082B2741A6033D265FAD1E?module=Thread&threadID=779972&start=0&mc=9#1689982


这题的easy版 check if a given number is prime
private boolean isPrime(int num) {
    if (num <= 1) return false;
    for (int i = 2; i * i <= num; i++) {
        if (num % i == 0) return false;
    }
    return true;
}