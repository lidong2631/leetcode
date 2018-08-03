Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]


Python:
class Solution:
    def searchRange(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        l, r, m, found = 0, len(nums)-1, 0, False
        res = [-1, -1]
        while l <= r:
            m = (l + r) // 2
            if nums[m] == target:
                found = True
                break
            elif nums[m] < target:
                l = m + 1
            else:
                r = m -1
        if not found:
            return res
        l, r = 0, m
        while l <= r:
            m = (l + r) // 2
            if nums[m] == target:
                r = m - 1
            else:
                l = m + 1
        res[0] = l
        l, r = m, len(nums)-1
        while l <= r:
            m = (l + r) // 2
            if nums[m] == target:
                l = m + 1
            else:
                r = m - 1
        res[1] = r
        return res



Java:
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        boolean founded = false;
        int[] res = {-1, -1};
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                founded = true;
                break;
            }
            else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        if (!founded) return res;
        left = 0; right = mid;              // careful right = mid
        while (left <= right) {
            int m = (left + right) / 2;
            if (nums[m] == target)
                right = m - 1;
            else
                left = m + 1;
        }
        res[0] = left;
        left = mid; right = nums.length - 1; // careful left = mid
        while (left <= right) {
            int m = (left + right) / 2;
            if (nums[m] == target)
                left = m + 1;
            else
                right = m - 1;
        }
        res[1] = right;
        return res;
    }
}



Golang:
func searchRange(nums []int, target int) []int {
    l, r, m := 0, len(nums)-1, 0
    found := false
    res := []int{-1, -1}
    for l <= r {
        m = (l + r) / 2
        if nums[m] == target {
            found = true
            break
        } else if nums[m] < target {
            l = m + 1
        } else {
            r = m - 1
        }
    }
    if !found {
        return res
    }
    l, r = 0, m
    for l <= r {
        m = (l + r) / 2
        if nums[m] == target {
            r = m - 1
        } else {
            l = m + 1
        }
    }
    res[0] = l
    l, r = m, len(nums) - 1
    for l <= r {
        m = (l + r) / 2
        if nums[m] == target {
            l = m + 1
        } else {
            r = m - 1
        }
    }
    res[1] = r
    return res
}





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






