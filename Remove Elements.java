Given an array nums and a value val, remove all instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example 1:

Given nums = [3,2,2,3], val = 3,

Your function should return length = 2, with the first two elements of nums being 2.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,1,2,2,3,0,4,2], val = 2,

Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.

Note that the order of those five elements can be arbitrary.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeElement(nums, val);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}



Python:
class Solution:
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        j = 0
        for i in range(len(nums)):
            if nums[i] != val:
                nums[j] = nums[i]
                j += 1
        return j


Java:
public class Solution {
    public int removeElement(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val)
                nums[j++] = nums[i];
        }
        return j;
    }
}

O(n)



Golang:
func removeElement(nums []int, val int) int {
    j := 0
    for _, v := range(nums) {
        if v != val {
            nums[j] = v
            j++
        }
    }
    return j
}





code ganker的写法 代码更简洁 从前往后

这题虽然简单 但三种解法代表的思想要好好理解 从前往后遍历因为后面的元素不确定性 有可能换过来的值仍是elem 所以下一轮仍要停在原处 继续判断当前位置

而从后往前由于后面的元素都判断过了 换过来的元素不需要再判断 可以保证每次都能往前走一步 第三种解法最直接但没有前两种高效


public class Solution {
    public int removeElement(int[] A, int elem) {
        int j = A.length-1;
        for(int i=A.length-1; i>-1; i--)
        {
            if(A[i]==elem)  
            {
                A[i] = A[j--];    //A[j] no--
            }
        }
        return j+1;
    }
}

另一种写法 稍微多两行 从后往前遍历 好处是交换完值不需要不用再判断新换的值是不是elem 因为是从后往前 如果之前有值是elem已经被判断过并交换到j后面去了

这里可以保证j要么跟i同步 要么指的永远是非elem



public int removeElement(int[] A, int elem) {
    if(A==null ||A.length==0)
        return 0;
    int index=0;
    for(int i=0;i<A.length;i++)
    {
        if(A[i]!=elem)
        {
            A[index]=A[i];
            index++;
        } 
    }
    return index; 
}

第三种解法 from code ganker评论 直接扫一遍数组 记下非elem的次数即可 最简单但是前面两种解法在得到length的同时还先数组值分成elem类和非elem类 并且上面两种实现方式

可以尽量减少赋值次数，如果没有重复就没有写操作，从这个角度来说，从后面搬过来更加高效一些哈~

