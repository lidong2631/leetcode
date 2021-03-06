题意：There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

解题思路：这道题要求两个已经排好序的数列的中位数。中位数的定义：如果数列有偶数个数，那么中位数为中间两个数的平均值；如果数列有奇数个数，那么中位数为中间的那个数。比如{1，2，3，4，5}的中位数为3。

{1，2，3，4，5，6}的中位数为（3+4）/ 2 = 3.5。那么这题最直接的思路就是将两个数列合并在一起，然后排序，然后找到中位数就行了。可是这样最快也要O((m+n)log(m+n))的时间复杂度，

而题目要求O(log(m+n))的时间复杂度。这道题其实考察的是二分查找，是《算法导论》的一道课后习题，难度还是比较大的。

　　　　   首先我们来看如何找到两个数列的第k小个数，即程序中getKth(A, B , k)函数的实现。用一个例子来说明这个问题：A = {1，3，5，7}；B = {2，4，6，8，9，10}；如果要求第7个小的数，

A数列的元素个数为4，B数列的元素个数为6；k/2 = 7/2 = 3，而A中的第3个数A[2]=5；B中的第3个数B[2]=6；而A[2]<B[2]；则A[0]，A[1]，A[2]中必然不可能有第7个小的数。因为A[2]<B[2]，

所以比A[2]小的数最多可能为A[0], A[1], B[0], B[1]这四个数，也就是说A[2]最多可能是第5个大的数，由于我们要求的是getKth(A, B, 7)；现在就变成了求getKth(A', B, 4)；即A' = {7}；

B不变，求这两个数列的第4个小的数，因为A[0]，A[1]，A[2]中没有解，所以我们直接删掉它们就可以了。这个可以使用递归来实现。

代码：

复制代码
class Solution:
    # @return a float
    # @line20 must multiply 0.5 for return a float else it will return an int
    def getKth(self, A, B, k):
        lenA = len(A); lenB = len(B)
        if lenA > lenB: return self.getKth(B, A, k)
        if lenA == 0: return B[k - 1]
        if k == 1: return min(A[0], B[0])
        pa = min(k/2, lenA); pb = k - pa
        if A[pa - 1] <= B[pb - 1]:
            return self.getKth(A[pa:], B, pb)
        else:
            return self.getKth(A, B[pb:], pa)
    
    def findMedianSortedArrays(self, A, B):
        lenA = len(A); lenB = len(B)
        if (lenA + lenB) % 2 == 1: 
            return self.getKth(A, B, (lenA + lenB)/2 + 1)
        else:
            return (self.getKth(A, B, (lenA + lenB)/2) + self.getKth(A, B, (lenA + lenB)/2 + 1)) * 0.5







public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1.length + nums2.length) % 2 == 1)
            return (double)helper(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, (nums1.length+nums2.length)/2+1);
        else 
            return (helper(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, (nums1.length+nums2.length)/2) +
                    helper(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, (nums1.length+nums2.length)/2+1))/2.0;
    }
    
    private int helper(int[] A, int startA, int endA, int[] B, int startB, int endB, int k) {
        int lenA = endA - startA + 1, lenB = endB - startB + 1;
        if (lenA > lenB) 
            return helper(B, startB, endB, A, startA, endA, k);
        if (lenA == 0) 
            return B[startB+k-1];
        if (k == 1) 
            return Math.min(A[startA], B[startB]);
            
        int posA = Math.min(k/2, lenA), posB = k - posA;
        if (A[startA+posA-1] == B[startB+posB-1]) 
            return A[startA+posA-1];
        else if (A[startA+posA-1] < B[startB+posB-1])
            return helper(A, startA+posA, endA, B, startB, startB+posB-1, k-posA);  // careful startA+posA
        else
            return helper(A, startA, startA+posA-1, B, startB+posB, endB, k-posB);
    }
}

A = {1，3，5，7}；B = {2，4，6，8，9，10}；如果要求第7个小的数

k/2 = 7/2 = 3，而A中的第3个数A[2]=5；B中的第3个数B[2]=6；而A[2]<B[2]

A = {7}, B = {2, 4, 6}

这题要好好理解 主要是k/2那里 每次通过比较值踢掉小的那个数组start到pos的值 另外扩展看topK问题还有算法导论10.3 order statistics











from code ganker:

这道题比较直接的想法就是用Merge Sorted Array这个题的方法把两个有序数组合并，当合并到第(m+n)/2个元素的时候返回那个数即可，而且不用把结果数组存起来。

算法时间复杂度是O(m+n)，空间复杂度是O(1)。因为代码比较简单，就不写出来了，跟Merge Sorted Array比较类似，大家可以参照这个题目的解法。

接下来我们考虑有没有优化的算法。优化的思想来源于order statistics，在算法导论10.3节中提到。

问题等价于求两个array的第k=(m+n)/2（假设m和n分别是两个数组的元素个数）大的数是多少。基本思路是每次通过查看两个数组的第k/2大的数(假设是A[k/2],B[k/2])，

如果两个A[k/2]=B[k/2]，说明当前这个数即为两个数组剩余元素的第k大的数，如果A[k/2]>B[k/2], 那么说明B的前k/2个元素都不是我们要的第k大的数，

反之则排除A的前k/2个，如此每次可以排除k/2个元素，最终k=1时即为结果。总的时间复杂度为O(logk),空间复杂度也是O(logk)，即为递归栈大小。

在这个题目中因为k=(m+n)/2,所以复杂度是O(log(m+n))。比起第一种解法有明显的提高

public double findMedianSortedArrays(int A[], int B[]) {
    if((A.length+B.length)%2==1)
        return helper(A,B,0,A.length-1,0,B.length-1,(A.length+B.length)/2+1);
    else
        return (helper(A,B,0,A.length-1,0,B.length-1,(A.length+B.length)/2)  
               +helper(A,B,0,A.length-1,0,B.length-1,(A.length+B.length)/2+1))/2.0;
}
private int helper(int A[], int B[], int i, int i2, int j, int j2, int k)
{
    int m = i2-i+1;
    int n = j2-j+1;
    if(m>n)
        return helper(B,A,j,j2,i,i2,k);
    if(m==0)
        return B[j+k-1];
    if(k==1)
        return Math.min(A[i],B[j]);
    int posA = Math.min(k/2,m);
    int posB = k-posA;
    if(A[i+posA-1]==B[j+posB-1])
        return A[i+posA-1];
    else if(A[i+posA-1]<B[j+posB-1])
        return helper(A,B,i+posA,i2,j,j+posB-1,k-posA);
    else
        return helper(A,B,i,i+posA-1,j+posB,j2,k-posB);
}




实现中还是有些细节要注意的，比如有时候剩下的数不足k/2个，那么就得剩下的，而另一个数组则需要多取一些数。但是由于这种情况发生的时候，不是把一个数组全部读完，

就是可以切除k/2个数，所以不会影响算法的复杂度。 

这道题的优化算法主要是由order statistics派生而来，原型应该是求topK的算法，这个问题是非常经典的问题，一般有两种解法，

一种是用quick select(快速排序的subroutine),另一种是用heap。 复杂度是差不多的，有兴趣可以搜一下，网上资料很多，topK问题在海量数据处理中也是一个非常经典的问题，

所以还是要重视