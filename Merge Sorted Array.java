Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]


class Solution:
    # @param A  a list of integers
    # @param m  an integer, length of A
    # @param B  a list of integers
    # @param n  an integer, length of B
    # @return nothing
    def merge(self, A, m, B, n):
        tmp = [0 for i in range(m + n)]
        i = 0; j = 0; k = 0
        while i < m and j < n:
            if A[i] <= B[j]:
                tmp[k] = A[i]; i += 1
            else:
                tmp[k] = B[j]; j += 1
            k += 1
        if i == m:
            while k < m + n:
                tmp[k] = B[j]; k += 1; j += 1
        else:
            while k < m + n:
                tmp[k] = A[i]; i += 1; k += 1
        for i in range(0, m + n):
            A[i] = tmp[i]





Java:
public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        int indexA = m - 1;   //这里不能用A.length-1 因为A的长度超过m+n 要用m-1
        int indexB = n - 1;
        int len = m + n - 1;
        while (indexA >= 0 && indexB >= 0) {
            if(A[indexA] > B[indexB])
                A[len--] = A[indexA--];
            else
                A[len--] = B[indexB--];
        }
        while(indexB >= 0)
            A[len--] = B[indexB--];
    }
}

Note: 没什么好说的 熟练！！






from code ganker:

这是一道数组操作的题目，思路比较明确，就是维护三个index，分别对应数组A，数组B，和结果数组。然后A和B同时从后往前扫，

每次迭代中A和B指向的元素大的便加入结果数组中，然后index-1，另一个不动。代码如下：

public void merge(int A[], int m, int B[], int n) {
    if(A==null || B==null)
        return;
    int idx1 = m-1;
    int idx2 = n-1;
    int len = m+n-1;
    while(idx1>=0 && idx2>=0)
    {
        if(A[idx1]>B[idx2])
        {
            A[len--] = A[idx1--];
        }
        else
        {
            A[len--] = B[idx2--];
        }
    }
    while(idx2>=0)
    {
        A[len--] = B[idx2--];
    }        
}

这里从后往前扫是因为这个题目中结果仍然放在A中，如果从前扫会有覆盖掉未检查的元素的可能性。算法的时间复杂度是O(m+n),m和n分别是两个数组的长度，

空间复杂度是O(1)。这个题类似的有Merge Two Sorted Lists，只是后者是对于LinkedList操作，面试中可能会一起问到