public class Solution {
    public void moveZeroes(int[] nums) {
        int p1 = 0, p2 = 0;
        while(p2<nums.length) {
            if(nums[p2]!=0)
                nums[p1++] = nums[p2];
            p2++;
        }
        while(p1<nums.length)
            nums[p1++] = 0;
    }
}

Two pointers 每次右指针碰到非0元素时覆盖掉左指针的值 当右指针到尾部时左指针将剩余元素设为0

O(n) O(1)




public void moveZeroes(int[] nums) {
    int p = 0;// Index of none-zero number
    for (int i : nums) 
        if (i != 0) 
          nums[p++] = i;
    while (p < nums.length) nums[p++] = 0;
}

 save all none-zero numbers in place in the front part, then fill 0 into the remaining part. No need to swap in this way