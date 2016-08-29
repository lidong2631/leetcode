public class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        while (i < nums.length) {
            if (nums[i] != 0) nums[j++] = nums[i];
            i++;
        }
        while (j < nums.length) {
            nums[j++] = 0;
        }
    }
}

Two pointers 每次右指针碰到非0元素时覆盖掉左指针的值 当右指针到尾部时左指针将剩余元素设为0

O(n) O(1)


Given an array nums, write a function to move all 0 to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.




public void moveZeroes(int[] nums) {
    int p = 0;// Index of none-zero number
    for (int i : nums) 
        if (i != 0) 
          nums[p++] = i;
    while (p < nums.length) nums[p++] = 0;
}

 save all none-zero numbers in place in the front part, then fill 0 into the remaining part. No need to swap in this way