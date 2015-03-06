class Solution:
    # @param A a list of integers
    # @return an integer
    def removeDuplicates(self, A):
        if len(A) <= 2:             #如果长度小于等于2 返回len(A)
            return len(A)
        prev = 1; curr = 2          #初始化prev, curr
        while curr < len(A):        #循环 如果A[curr]跟A[prev]和它前面的值A[prev-1]都相等 则说明有两个以上重复的值出现 只有curr移动 直到出现下一个与A[prev]不同的值
            if A[curr] == A[prev] and A[curr] == A[prev-1]:
                curr+=1
            else:                   #正常情况下 prev往后移一位 将后面A[curr]值赋给前面A[prev] curr也往后移一位
                prev+=1
                A[prev] = A[curr]
                curr+=1
        return prev+1               #最后返回prev+1 为长度






题意：

Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].

解题思路：一种巧妙的解法。使用两个指针prev和curr，判断A[curr]是否和A[prev]、A[prev-1]相等，如果相等curr指针继续向后遍历，直到不相等时，将curr指针指向的值赋值给A[prev+1]，这样多余的数就都被交换到后面去了。最后prev+1值就是数组的长度。

代码：

复制代码
class Solution:
    # @param A a list of integers
    # @return an integer
    # @it's a good solution!
    def removeDuplicates(self, A):
        if len(A) <= 2: return len(A)
        prev = 1; curr = 2
        while curr < len(A):
            if A[curr] == A[prev] and  A[curr] == A[prev - 1]:
                curr += 1
            else:
                prev += 1
                A[prev] = A[curr]
                curr += 1
        return prev + 1