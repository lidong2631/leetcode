class Solution:
    # @param A, a list of integers
    # @param target, an integer to be searched
    # @return a list of length 2, [index1, index2]
    def searchRange(self, A, target):
        left = 0; right = len(A) - 1
        res = [-1, -1]              #res存放结果
        while left <= right:            #二分法寻找target
            mid = (left + right) / 2
            if A[mid] == target:        #一旦找到 break出循环
                break;
            elif A[mid] > target:       #否则继续二分寻找
                right = mid - 1
            else:
                left = mid + 1
        if A[mid]!=target:      #判断下是否有找到target 如果没有直接返回[-1, -1]
            return res
        
        newLeft = 0; newRight = mid     #在（0，mid）之间寻找target的开始位置
        while newLeft <= newRight:          #二分查找
            mid = (newLeft + newRight) / 2
            if A[mid] == target:        #如果中值等于target 移动newRight 否则移动newLeft
                newRight = mid - 1
            else:
                newLeft = mid + 1
        res[0] = newLeft            #开始索引为newLeft的值
        
        newLeft = mid; newRight = len(A) - 1        #找target结束位置 同理
        while newLeft <= newRight:
            mid = (newLeft + newRight) / 2
            if A[mid] == target:
                newLeft = mid + 1
            else:
                newRight = mid - 1
        res[1] = newRight           #结束索引为newRight的值
        return res

Note: 下面的算法在找到第一个target后会线性寻找开始和结尾索引 最坏情况(如序列所有值都是target 8 8 8 8 )复杂度为O(n) 我的算法全部用二分法复杂度为O(logn)




class Solution:
    # @param A, a list of integers
    # @param target, an integer to be searched
    # @return a list of length 2, [index1, index2]
    def searchRange(self, A, target):
        left = 0; right = len(A) - 1
        while left <= right:
            mid = (left + right) / 2
            if A[mid] > target:
                right = mid - 1
            elif A[mid] < target:
                left = mid + 1
            else:
                list = [0, 0]
                if A[left] == target: list[0] = left
                if A[right] == target: list[1] = right
                for i in range(mid, right+1):
                    if A[i] != target: list[1] = i - 1; break
                for i in range(mid, left-1, -1):
                    if A[i] != target: list[0] = i + 1; break
                return list
        return [-1, -1]





public class Solution {
    public int[] searchRange(int[] A, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if(A==null || A.length==0)
            return res;
        int left = 0;
        int right = A.length-1;
        int mid = 0;
        
        while(left<=right)          // first while check if target exists in array
        {
            mid = (left+right)/2;
            if(A[mid]==target)
                break;
            else if(A[mid]<target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        if(A[mid]!=target)
            return res;
            
        int newL = 0;
        int newR = mid;
        while(newL<=newR)           // second while check left side
        {
            int m = (newL+newR)/2;
            if(A[m]==target)        //区别
                newR = m - 1;
            else
                newL = m + 1;
        }
        res[0] = newL;
        
        newL = mid;
        newR = A.length-1;
        while(newL<=newR)           // third while check right side
        {
            int m = (newL+newR)/2;
            if(A[m]==target)            //区别
                newL = m + 1;
            else
                newR = m - 1;
        }
        res[1] = newR;
        return res;
    }
}

Note: 根据code ganker程序写的 三遍二分发 第一遍找值是否存在 第二遍在(0,mid)找range开始索引 第三遍在(mid,A.length-1）找range结束索引


Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].





from code ganker:

这道题是二分查找Search Insert Position的变体，思路并不复杂，就是先用二分查找找到其中一个target，然后再往左右找到target的边缘。找边缘的方法跟二分查找仍然是一样的，

只是切半的条件变成相等，或者不等（往左边找则是小于，往右边找则是大于）。这样下来总共进行了三次二分查找，所以算法的时间复杂度仍是O(logn)，空间复杂度是O(1)。 代码如下： 

public int[] searchRange(int[] A, int target) {
    int[] res = new int[2];
    res[0] = -1;
    res[1] = -1;
    if(A==null || A.length==0)
    {
        return res;
    }
    int l=0;
    int r=A.length-1;
    int m=(l+r)/2;
    while(l<=r)
    {
        m=(l+r)/2;
        if(A[m]==target)
        {
            res[0]=m;
            res[1]=m;
            break;
        }
        else if(A[m]>target)
        {
            r = m-1;
        }
        else
        {
            l = m+1;
        }
    }
    if(A[m]!=target)
        return res;
    int newL = m;
    int newR = A.length-1;
    while(newL<=newR)
    {
        int newM=(newL+newR)/2;
        if(A[newM]==target)
        {
            newL = newM+1;
        }
        else
        {
            newR = newM-1;
        }            
    }
    res[1]=newR;
    newL = 0;
    newR = m;
    while(newL<=newR)
    {
        int newM=(newL+newR)/2;
        if(A[newM]==target)
        {
            newR = newM-1;
        }
        else
        {
            newL = newM+1;
        }            
    }
    res[0]=newL;        
    
    return res;
}

实现中用到了在Search Insert Position中提到的方法，可以保证当搜索结束时，l和r所停的位置正好是目标数的后面和前面。二分查找的题目还是比较常考的，既带有一点算法思想，

实现上也不会过于复杂，很适合作为面试题，类似的题目还有Search in Rotated Sorted Array。






