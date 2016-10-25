public class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        int u1 = 2, u2 = 3, u3 = 5;
        int i1 = 0, i2 = 0, i3 = 0;     // i1 = 1, i2 = 1, i3 = 1
        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            ugly[i] = Math.min(u1, Math.min(u2, u3));
            if (ugly[i] == u1) {
                u1 = 2 * ugly[++i1];    // u1 = 2 * ugly[i1++]
            }
            if (ugly[i] == u2) {
                u2 = 3 * ugly[++i2];
            }
            if (ugly[i] == u3) {
                u3 = 5 * ugly[++i3];
            }
        }
        return ugly[n-1];
    }
}

                                        2           3           5
res[i]     ugly1  ugly2  ugly3      index1      index2      index3
1           2       3       5           0           0           0
2           4       3       5           1           0           0
3           4       6       5           1           1           0-
4           6       6       5           2           1           0
5           6       6       10          2           1           1
6           8       9       10          3           2           1
8           10      9       10          4           2           1


The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below:

(1) 1×2, 2×2, 3×2, 4×2, 5×2, 6×2, (no 7 cause 7 is not ugly number)8×2…
(2) 1×3, 2×3, 3×3, 4×3, 5×3, 6×3, (no 7 cause 7 is not ugly number)8×3…
(3) 1×5, 2×5, 3×5, 4×5, 5×5, 6×5, (no 7 cause 7 is not ugly number)8×5…
We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5.

Then we use similar merge method as merge sort, to get every ugly number from the three subsequence.

Every step we choose the smallest one, and move one step after,including nums with same value.


O(n) O(n)

https://leetcode.com/discuss/52716/o-n-java-solution

http://www.geeksforgeeks.org/ugly-numbers/