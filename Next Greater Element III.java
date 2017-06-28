public class Solution {
    public int nextGreaterElement(int n) {
        char[] nums = (n + "").toCharArray();
        int i = nums.length - 1;
        while (i > 0 && nums[i-1] >= nums[i]) {
            i--;    
        }
        if (i == 0)
            return -1;
        int j = i - 1;
        while (i < nums.length && nums[i] > nums[j])
            i++;
        swap(nums, i-1, j);
        reverse(nums, j+1);
        long res = 0, digit = 1;
        for (int k = nums.length - 1; k >= 0; k--) {
            res += digit * (nums[k] - '0');
            digit *= 10;
        }
        return (res <= Integer.MAX_VALUE) ? (int)res : -1;
    }
    
    private void swap(char[] nums, int l, int r) {
        char tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
    
    private void reverse(char[] nums, int start) {
        int l = start, r = nums.length - 1;
        while (l < r) {
            swap(nums, l, r);
            l++; r--;
        }
    }
}

same as Next Permutation


Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:
Input: 12
Output: 21
Example 2:
Input: 21
Output: -1
Hide Company Tags Bloomberg
Hide Tags String
Hide Similar Problems (E) Next Greater Element I (M) Next Greater Element II
