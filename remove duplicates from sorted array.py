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
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
        return j+1