<<<<<<< HEAD
class Solution:
    # @param    A       a list of integers
    # @param    elem    an integer, value need to be removed
    # @return an integer
    def removeElement(self, A, elem):
        j = len(A) - 1                      #尾指针从最后一个元素开始
        for i in range(len(A)-1, -1, -1):       #另一个指针i往前遍历
            if A[i] == elem:                #如果A[i]和elem相等 就将它和尾指针j指向的元素交换 这样所有elem元素全在后面 j-=1往前move一个
                A[i], A[j] = A[j], A[i]
                j-=1
        return j+1          #最后返回长度j+1





题意：

Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

解题思路：去掉数组中等于elem的元素，返回新的数组长度，数组中的元素不必保持原来的顺序。使用头尾指针，头指针碰到elem时，与尾指针指向的元素交换，将elem都换到数组的末尾去。

代码：


class Solution:
    # @param    A       a list of integers
    # @param    elem    an integer, value need to be removed
    # @return an integer
    # clrs qsort
    def removeElement(self, A, elem):
        j = len(A)-1
        for i in range(len(A) - 1, -1, -1):
            if A[i] == elem:
                A[i], A[j] = A[j], A[i]
                j -= 1
=======
class Solution:
    # @param    A       a list of integers
    # @param    elem    an integer, value need to be removed
    # @return an integer
    def removeElement(self, A, elem):
        j = len(A) - 1                      #尾指针从最后一个元素开始
        for i in range(len(A)-1, -1, -1):       #另一个指针i往前遍历
            if A[i] == elem:                #如果A[i]和elem相等 就将它和尾指针j指向的元素交换 这样所有elem元素全在后面 j-=1往前move一个
                A[i], A[j] = A[j], A[i]
                j-=1
        return j+1          #最后返回长度j+1





题意：

Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

解题思路：去掉数组中等于elem的元素，返回新的数组长度，数组中的元素不必保持原来的顺序。使用头尾指针，头指针碰到elem时，与尾指针指向的元素交换，将elem都换到数组的末尾去。

代码：


class Solution:
    # @param    A       a list of integers
    # @param    elem    an integer, value need to be removed
    # @return an integer
    # clrs qsort
    def removeElement(self, A, elem):
        j = len(A)-1
        for i in range(len(A) - 1, -1, -1):
            if A[i] == elem:
                A[i], A[j] = A[j], A[i]
                j -= 1
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
        return j+1