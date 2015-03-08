<<<<<<< HEAD
class Solution:
    # @param a list of integers
    # @return an integer
    def removeDuplicates(self, A):
        if len(A) == 0:
            return 0
        j = 0
        for i in range(len(A)):             #i从头向后遍历所有的元素跟j做对比 如果A[j]==A[i]则i一直循环下去
            if A[j]!=A[i]:                  #一旦不相等 交换j的下一个元素和i 即将下一个跟j不重复的元素放到j的后面 同时j+1比较下一个
                A[j+1], A[i] = A[i], A[j+1]
                j+=1
        return j + 1                        #j经过的即是所有不重复的元素 最后数组长度即是j+1





题意：

Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].

解题思路：使用一个指针j，当i向后遍历数组时，如果遇到与A[j]不同的，将A[i]和A[j+1]交换，同时j=j+1，即j向后移动一个位置，然后i继续向后遍历。

代码：


class Solution:
    # @param a list of integers
    # @return an integer
    def removeDuplicates(self, A):
        if len(A) == 0:
            return 0
        j = 0
        for i in range(0, len(A)):
            if A[i] != A[j]:
                A[i], A[j+1] = A[j+1], A[i]
                j = j + 1
        return j+1





public class Solution {
    public int removeDuplicates(int[] A) {
        if(A==null || A.length<=0)
            return 0;
        int index = 1;
        for(int i=1; i<A.length; i++) {
            if(A[i]!=A[index-1]) {
                A[index] = A[i];
                index++;
            }
        }
        return index;
    }
}

from code ganker评论 更好的解法 尤其体现在ii题中 通过比较 A[i]与A[index-1]








public class Solution {
    public int removeDuplicates(int[] A) {
        if(A==null || A.length==0)
            return 0;
        int index=1;
        for(int i=1; i<A.length; i++)
        {
            if(A[i]!=A[i-1])
            {
                A[index] = A[i];
                index++;
            }
        }
        return index;
    }
}

Note: 简单题 因为数组是排好序 重复必相邻 双指针就可以搞定 要非常熟练





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


=======
class Solution:
    # @param a list of integers
    # @return an integer
    def removeDuplicates(self, A):
        if len(A) == 0:
            return 0
        j = 0
        for i in range(len(A)):             #i从头向后遍历所有的元素跟j做对比 如果A[j]==A[i]则i一直循环下去
            if A[j]!=A[i]:                  #一旦不相等 交换j的下一个元素和i 即将下一个跟j不重复的元素放到j的后面 同时j+1比较下一个
                A[j+1], A[i] = A[i], A[j+1]
                j+=1
        return j + 1                        #j经过的即是所有不重复的元素 最后数组长度即是j+1





题意：

Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].

解题思路：使用一个指针j，当i向后遍历数组时，如果遇到与A[j]不同的，将A[i]和A[j+1]交换，同时j=j+1，即j向后移动一个位置，然后i继续向后遍历。

代码：


class Solution:
    # @param a list of integers
    # @return an integer
    def removeDuplicates(self, A):
        if len(A) == 0:
            return 0
        j = 0
        for i in range(0, len(A)):
            if A[i] != A[j]:
                A[i], A[j+1] = A[j+1], A[i]
                j = j + 1
        return j+1





public class Solution {
    public int removeDuplicates(int[] A) {
        if(A==null || A.length<=0)
            return 0;
        int index = 1;
        for(int i=1; i<A.length; i++) {
            if(A[i]!=A[index-1]) {
                A[index] = A[i];
                index++;
            }
        }
        return index;
    }
}

from code ganker评论 更好的解法 尤其体现在ii题中 通过比较 A[i]与A[index-1]








public class Solution {
    public int removeDuplicates(int[] A) {
        if(A==null || A.length==0)
            return 0;
        int index=1;
        for(int i=1; i<A.length; i++)
        {
            if(A[i]!=A[i-1])
            {
                A[index] = A[i];
                index++;
            }
        }
        return index;
    }
}

Note: 简单题 因为数组是排好序 重复必相邻 双指针就可以搞定 要非常熟练





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


>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
