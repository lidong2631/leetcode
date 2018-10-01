题意：

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

解题思路：要求时间复杂度为O(n)和常数空间。又是一个tricky的解法。



Python:
class Solution:
    def firstMissingPositive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        i = 0
        while i < len(nums):
            if nums[i] > 0 and nums[i] <= len(nums) and nums[i] != nums[nums[i]-1]:
                nums[nums[i]-1], nums[i] = nums[i], nums[nums[i]-1]
                i -= 1
            i += 1
        for i in range(len(nums)):
            if nums[i] != i + 1:
                return i + 1
        return len(nums) + 1

In Python if you use for i in range(len(nums)) you cannot control i in each iteration




Java:
public class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i]-1] != nums[i]) {
                int tmp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = tmp;
                i--;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i+1;
        }
        return nums.length + 1;
    }
}

O(n) O(1)



from code ganker:

这道题要求用线性时间和常量空间，思想借鉴到了Counting sort中的方法，不了解的朋友可以参见Counting sort - Wikipedia。

既然不能用额外空间，那就只有利用数组本身，跟Counting sort一样，利用数组的index来作为数字本身的索引，把正数按照递增顺序依次放到数组中。

即让A[0]=1, A[1]=2, A[2]=3, ... , 这样一来，最后如果哪个数组元素违反了A[i]=i+1即说明i+1就是我们要求的第一个缺失的正数。

对于那些不在范围内的数字，我们可以直接跳过，比如说负数，0，或者超过数组长度的正数，这些都不会是我们的答案。代码如下：

public int firstMissingPositive(int[] A) {
    if(A==null || A.length==0)
    {
        return 1;
    }
    for(int i=0;i<A.length;i++)
    {
        if(A[i]<=A.length && A[i]>0 && A[A[i]-1]!=A[i])
        {
            int temp = A[A[i]-1];
            A[A[i]-1] = A[i];
            A[i] = temp;
            i--;
        }
    }
    for(int i=0;i<A.length;i++)
    {
        if(A[i]!=i+1)
            return i+1;
    }
    return A.length+1;
}

实现中还需要注意一个细节，就是如果当前的数字所对应的下标已经是对应数字了，那么我们也需要跳过，因为那个位置的数字已经满足要求了，

否则会出现一直来回交换的死循环。这样一来我们只需要扫描数组两遍，时间复杂度是O(2*n)=O(n)，而且利用数组本身空间，只需要一个额外变量，

所以空间复杂度是O(1)。

这道题个人还是比较喜欢的，既有一点算法思想，在实现中又有一些注意细节，而且整体来说模型比较简单，很适合在面试中出现。
