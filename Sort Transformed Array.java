public class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 0, right = n - 1;
        int index = a >= 0 ? n - 1 : 0;
        while (left <= right) {
            if (a >= 0) {
                res[index--] = helper(nums[left], a, b, c) >= helper(nums[right], a, b, c) ? 
                                helper(nums[left++], a, b, c) : helper(nums[right--], a, b, c);
            }
            else {
                res[index++] = helper(nums[left], a, b, c) >= helper(nums[right], a, b, c) ?
                                helper(nums[right--], a, b, c) : helper(nums[left++], a, b, c);
            }
        }
        return res;
    }
    
    private int helper(int num, int a, int b, int c) {
        return a*num*num + b*num + c;
    }
}

1.a>0, two ends in original array are bigger than center if you learned middle school math before.

2.a<0, center is bigger than two ends.

For a==0 case, it does not matter what b sign is. The function is monotonically increasing or decreasing. 
you can start with either beginning or end

O(n)

https://leetcode.com/discuss/108831/java-o-n-incredibly-short-yet-easy-to-understand-ac-solution