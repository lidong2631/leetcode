Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}




Python:
class Solution:
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return 0
        j = 1
        for i in range(1, len(nums)):
            if nums[i] != nums[i-1]:
                nums[j] = nums[i]
                j += 1
        return j



Java:
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) return 1;
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1])
                nums[j++] = nums[i];
        }
        return j;
    }
}

O(n)




Golang:
func removeDuplicates(nums []int) int {
    if len(nums) < 2 {
        return 1
    }
    j := 1
    for i := 1; i < len(nums); i++ {
        if nums[i] != nums[i-1] {
            nums[j] = nums[i]
            j++
        }
    }
    return j
}







from code ganker:

这道题跟Remove Element类似，也是考察数组的基本操作，属于面试中比较简单的题目。做法是维护两个指针，一个保留当前有效元素的长度，

一个从前往后扫，然后跳过那些重复的元素。因为数组是有序的，所以重复元素一定相邻，不需要额外记录。时间复杂度是O(n)，空间复杂度O(1)。代码如下：

public int removeDuplicates(int[] A) {
    if(A == null || A.length==0)
        return 0;
    int index = 1;
    for(int i=1;i<A.length;i++)
    {
        if(A[i]!=A[i-1])
        {
            A[index]=A[i];
            index++;
        }
    }
    return index;
}

类似的题目有Remove Duplicates from Sorted List，那道题是在数组中操作，还有Remove Duplicates from Sorted Array II，

这个题目操作有所不同，不过难度也差不多，有兴趣的朋友可以看看。

