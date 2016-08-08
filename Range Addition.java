public class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update : updates) {
            int val = update[2], start = update[0], end = update[1];
            res[start] += val;
            if (end < length - 1)
                res[end + 1] -= val;
        }
        
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += res[i];
            res[i] = sum;
        }
        return res;
    }
}

Assume you have an array of length n initialized with all 0 and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Given:

    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]

Output:

    [-2, 0, 3, 5, 3]
Explanation:

Initial state:
[ 0, 0, 0, 0, 0 ]

After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]

After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]

After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]



Just store every start index for each value and at end index plus minus value

for example it will look like:

[1 , 3 , 2] , [2, 3, 3] (length = 5)

res[ 0, 2, 0, 0,-2 ]

res[ 0, 2, 3, 0,-5]

sum 0, 2, 5, 5, 0

res[0, 2, 5, 5, 0]


O(n+k)

https://discuss.leetcode.com/topic/49691/java-o-k-n-time-complexity-solution/2