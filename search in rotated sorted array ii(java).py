<<<<<<< HEAD
class Solution:
    # @param A a list of integers
    # @param target an integer
    # @return a boolean
    def search(self, A, target):
        left = 0; right = len(A)-1
        while left<=right:
            mid = (left + right) / 2
            if A[mid] == target:
                return True
            elif A[left] == A[mid] == A[right]:     #唯一区别是这里当中间和边缘相等时我们要将边缘移动一步
                left+=1; right-=1
            elif A[mid]>=A[left]:
                if A[left]<=target<A[mid]:
                    right = mid - 1
                else:
                    left = mid + 1
            elif A[mid]<=A[right]:
                if A[mid]<target<=A[right]:
                    left = mid + 1
                else:
                    right = mid - 1
        return False

Note：二分法变体很多 要深刻理解






public class Solution {
    public boolean search(int[] A, int target) {
        if(A==null || A.length==0)
            return false;
        int left = 0; int right = A.length-1;
        while(left<=right) {
            int mid = (left+right)/2;
            if(A[mid]==target)
                return true;
            if(A[mid]>A[left]) {
                if(A[mid]>target && A[left]<=target)
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            else if(A[mid]<A[right]) {
                if(A[mid]<target && A[right]>=target)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            else {                  //如果前两个if判断不出来 就看左右指针哪个和A[mid]相等 当然可能两个都相等 那就先移动左指针
                if(A[mid]==A[left])
                    left++;
                if(A[mid]==A[right])
                    right--;
            }
        }
        return false;
    }
}

我的解法根据code ganker改编 还是类似上一题的套路 判断左右两边哪边是有序的 如果都判断不出来就去else里面看A[mid]是跟A[left]相等还是A[right]

然后相应的移动左右指针 比code ganker的解法稍微清晰一点










public class Solution {
    public boolean search(int[] A, int target) {
        if(A==null || A.length==0)
            return false;
        int left = 0;
        int right = A.length-1;
        while(left<=right)
        {
            int mid = (left+right)/2;
            if(A[mid]==target)
                return true;
            if(A[mid]>A[left])
            {
                if(target>=A[left] && target<A[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            else if(A[mid]<A[left])
            {
                if(target>A[mid] && target<=A[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            else            //区别 但会影响复杂度
            {
                left++;
            }
        }
        return false;
    }
}

Note: 这题有意思 虽然只加了个有重复数据的条件 但正因此影响了时间复杂度 变成O(n) 如果A[mid]和边缘值A[left]相等 无法判断是左移还是右移

就得移动一位left 从新判断直到A[mid]和A[left]不等为止 最坏可以是O(n)





from Code Ganker:

这道题是二分查找Search Insert Position的变体，思路在Search in Rotated Sorted Array中介绍过了，不了解的朋友可以先看看那道题哈。

和Search in Rotated Sorted Array唯一的区别是这道题目中元素会有重复的情况出现。不过正是因为这个条件的出现，出现了比较复杂的case，甚至影响到了算法的时间复杂度。

原来我们是依靠中间和边缘元素的大小关系，来判断哪一半是不受rotate影响，仍然有序的。而现在因为重复的出现，如果我们遇到中间和边缘相等的情况，我们就丢失了哪边有序的信息，

因为哪边都有可能是有序的结果。假设原数组是{1,2,3,3,3,3,3}，那么旋转之后有可能是{3,3,3,3,3,1,2}，或者{3,1,2,3,3,3,3}，这样的我们判断左边缘和中心的时候都是3，

如果我们要寻找1或者2，我们并不知道应该跳向哪一半。解决的办法只能是对边缘移动一步，直到边缘和中间不在相等或者相遇，这就导致了会有不能切去一半的可能。所以最坏情况（比如全部都是一个元素，

或者只有一个元素不同于其他元素，而他就在最后一个）就会出现每次移动一步，总共是n步，算法的时间复杂度变成O(n)。代码如下：

public boolean search(int[] A, int target) {
    if(A==null || A.length==0)
        return false;
    int l = 0;
    int r = A.length-1;
    while(l<=r)
    {
        int m = (l+r)/2;
        if(A[m]==target)
            return true;
        if(A[m]>A[l])
        {
            if(A[m]>target && A[l]<=target)
            {
                r = m-1;
            }
            else
            {
                l = m+1;
            }
        }
        else if(A[m]<A[l])
        {
            if(A[m]<target && A[r]>=target)
            {
                l = m+1;
            }
            else
            {
                r = m-1;
            }                
        }
        else
        {
            l++;
        }
    }
    return false;
}

以上方法和Search in Rotated Sorted Array是一样的，只是添加了中间和边缘相等时，边缘移动一步，但正是这一步导致算法的复杂度由O(logn)变成了O(n)。

个人觉得在面试中算法复杂度还是很重要的考察点，因为涉及到对算法的理解，大家还是要尽量多考虑哈。


