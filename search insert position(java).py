class Solution:
    def binarySearch(self, A, start, end, target):  #递归
        midIndex = (start + end) / 2    #算中间index
        if start > end:             #if头大于尾 则对于A已经全部搜索了
            return end + 1
        elif A[midIndex] == target:     #如果中值等于target 直接返回
            return midIndex
        elif A[midIndex] < target:      #如果中值大于或小于target 递归二分搜索
            return self.binarySearch(A, midIndex+1, end, target)
        else:
            return self.binarySearch(A, start, midIndex-1, target)
    # @param A, a list of integers
    # @param target, an integer to be inserted
    # @return integer
    def searchInsert(self, A, target):
        res = self.binarySearch(A, 0, len(A)-1, target)
        return res


Note: binary search 两种方式 recursive iterative都要熟记 看下面例子from wikipedia



题意：

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0

解题思路：二分查找的变种。注意最后一句是return left。

代码：


class Solution:
    # @param A, a list of integers
    # @param target, an integer to be inserted
    # @return integer
    def searchInsert(self, A, target):
        left = 0; right = len(A) - 1
        while left <= right:
            mid = ( left + right ) / 2
            if A[mid] < target:
                left = mid + 1
            elif A[mid] > target:
                right = mid - 1
            else:
                return mid
        return left




public class Solution {
    public int searchInsert(int[] A, int target) {
        if(A==null || A.length==0)
            return 0;
        int left = 0;
        int right = A.length-1;
        while(left<=right)
        {
            int mid = (left+right)/2;
            if(A[mid]==target)
                return mid;
            else if(A[mid]<target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
}

Note: 这种写法如果target不在A中 最后left就停留在要插入的地方 当然其实递归 非递归都可以



from code ganker:

这道题比较简单，就是二分查找。思路就是每次取中间，如果等于目标即返回，否则根据大小关系切去一半。因此算法复杂度是O(logn)，空间复杂度O(1)。代码如下： 

public int searchInsert(int[] A, int target) {
    if(A == null || A.length == 0)
    {
        return 0;
    }
    int l = 0;
    int r = A.length-1;
    while(l<=r)
    {
        int mid = (l+r)/2;
        if(A[mid]==target)
            return mid;
        if(A[mid]<target)
            l = mid+1;
        else
            r = mid-1;
    }
    return l;
}

注意以上实现方式有一个好处，就是当循环结束时，如果没有找到目标元素，那么l一定停在恰好比目标大的index上，r一定停在恰好比目标小的index上，所以个人比较推荐这种实现方式。

二分查找是一个非常经典的方法，不过一般在面试中很少直接考二分查找，会考一些变体，例如Search in Rotated Sorted Array，Search for a Range和Search a 2D Matrix，

思路其实是类似的，稍微变体一下即可，有兴趣可以练习一下哈。





recursive:

int binary_search(int A[], int key, int imin, int imax)
{
  // test if array is empty
  if (imax < imin)
    // set is empty, so return value showing not found
    return KEY_NOT_FOUND;
  else
    {
      // calculate midpoint to cut set in half
      int imid = midpoint(imin, imax);
 
      // three-way comparison
      if (A[imid] > key)
        // key is in lower subset
        return binary_search(A, key, imin, imid-1);
      else if (A[imid] < key)
        // key is in upper subset
        return binary_search(A, key, imid+1, imax);
      else
        // key has been found
        return imid;
    }
}


iterative:

int binary_search(int A[], int key, int imin, int imax)
{
  // continue searching while [imin,imax] is not empty
  while (imax >= imin)
    {
      // calculate the midpoint for roughly equal partition
      int imid = midpoint(imin, imax);
      if(A[imid] == key)
        // key found at index imid
        return imid; 
      // determine which subarray to search
      else if (A[imid] < key)
        // change min index to search upper subarray
        imin = imid + 1;
      else         
        // change max index to search lower subarray
        imax = imid - 1;
    }
  // key was not found
  return KEY_NOT_FOUND;
}
