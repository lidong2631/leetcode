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

O(n+k)

https://discuss.leetcode.com/topic/49691/java-o-k-n-time-complexity-solution/2