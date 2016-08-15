题意：

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
 

For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

解题思路：类似于二分查找。

代码：

复制代码
class Solution:
    # @param matrix, a list of lists of integers
    # @param target, an integer
    # @return a boolean
    def searchMatrix(self, matrix, target):
        i = 0; j = len(matrix[0]) - 1
        while i < len(matrix) and j >= 0:
            if matrix[i][j] == target: return True
            elif matrix[i][j] > target: j -= 1
            else: i += 1
        return False






public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i=0;
        int j = matrix[0].length-1;
        while(i<matrix.length && j>=0)
        {
            if(matrix[i][j]==target)
                return true;
            else if(matrix[i][j]>target)
                j-=1;
            else i+=1;
        }
        return false;
    }
}

Note: 此解法由python版改编 复杂度为O(m+n) code ganker的解法为O(logm+logn)更优 用的二分法




public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return false;
        int left = 0;
        int right = matrix.length-1;
        while(left<=right)          //确定行数
        {
            int mid = (left+right)/2;
            if(matrix[mid][0]==target)
                return true;
            else if(matrix[mid][0]<target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        int row = right;            //注意这里最终right就是target所在行数
        if(row<0)               //如果row小于0 则target比matrix中最小元素还小 返回false
            return false;
        left = 0;
        right = matrix[0].length-1;
        while(left<=right)              //确定列数
        {
            int mid = (left+right)/2;
            if(matrix[row][mid]==target)
                return true;
            else if(matrix[row][mid]<target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return false;               //最终找不到也返回false
    }
}

Note: 这解法是根据code ganker写的 用两次二分 一次确定行数 一次确定列数 时间复杂度优于上一种







from code ganker:

这道题是二分查找Search Insert Position的变体，思路并不复杂，就是先用二分查找找到其中一个target，然后再往左右找到target的边缘。找边缘的方法跟二分查找仍然是一样的，

只是切半的条件变成相等，或者不等（往左边找则是小于，往右边找则是大于）。这样下来总共进行了三次二分查找，所以算法的时间复杂度仍是O(logn)，空间复杂度是O(1)。 代码如下：

public boolean searchMatrix(int[][] matrix, int target) {
    if(matrix == null || matrix.length==0 || matrix[0].length==0)
        return false;
    int l = 0;
    int r = matrix.length-1;
    while(l<=r)
    {
        int mid = (l+r)/2;
        if(matrix[mid][0] == target) return true;
        if(matrix[mid][0] > target)
        {
            r = mid-1;
        }
        else
        {
            l = mid+1;
        }
    }
    int row = r;
    if(row<0)
        return false;
    l = 0;
    r = matrix[0].length-1;
    while(l<=r)
    {
        int mid = (l+r)/2;
        if(matrix[row][mid] == target) return true;
        if(matrix[row][mid] > target)
        {
            r = mid-1;
        }
        else
        {
            l = mid+1;
        }
    }   
    return false;
}

二分查找是面试中出现频率不低的问题，但是很少直接考二分查找，会考一些变体，除了这道题，还有Search in Rotated Sorted Array和Search for a Range，思路其实差不多，

稍微变化一下即可，有兴趣可以练习一下哈

