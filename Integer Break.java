public class Solution {
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int[] res = new int[n+1];
        res[2] = 2; res[3] = 3; res[4] = 4;
        for (int i = 5; i <= n; i++) {
            res[i] = 3 * res[i-3];
        }
        return res[n];
    }
}

any n>4 will guarantee to have a factor of 3

Why the max product of any n>4 must contain a factor of 3? 

1. It cant contain any factor x that is >= 5, o.w., we can further increase the max product by decomposing x, 
as the decomposed x when x>=5 is strictly greater than x;

2. Out of 1, 2, 3, 4, we know 1 wont be a factor of n when n>4, if n is an odd number, 3 must be there as a factor 
(2 and 4 cant add up to an odd number); 

3. Now say n is an even number (n>4) and only has factor of 2 and 4, we can always split a 6 to 3X3, which is better than 2X2X2.
Therefore, the max product of any n (n>4) must contain a factor of 3. The recurrence relation holds.

Further, as it holds for all n (n>4), we will be only using 3 as factor for n (n>4), we keep subtracting 3 until n<=4, and adopt the remaining factor

O(n)

https://leetcode.com/discuss/98144/java-o-n-dp-solution-store-and-reuse-products