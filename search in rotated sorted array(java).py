class Solution:
    # @param A, a list of integers
    # @param target, an integer to be searched
    # @return an integer
    def search(self, A, target):
        left = 0; right = len(A)-1
        while left <= right:
            mid = (left + right) / 2
            if A[mid] == target:
                return mid
            if A[mid] >= A[left]:           #首先分两种情况 mid在left到分界点之间 即第一个递增序列 如 3 4 5 6(mid) 7 0 1 2 
                if A[left] <= target < A[mid]:      #然后 再分target在left到mid之间 即在递增序列里 如 3 4(target) 5 6(mid) 7 0 1 2 
                    right = mid - 1
                else:                               #或target在mid到right之间 不在递增序列里 如 3 4 5 6(mid) 7 0 1(target) 2
                    left = mid + 1
            else:                           #mid在分界点到right之间 即第二个递增序列 如 6 7 0 1(mid) 2 3 4 5 也可以写作elif A[mid]<=right:
                if A[mid] < target <= A[right]:     #然后 再分target在mid到right之间 在递增序列里 如 6 7 0 1(mid) 2 3(target) 4 5
                    left = mid + 1
                else:                               #target在left到mid之间 不在递增序列里 如 6 7(target) 0 1(mid) 2 3 4 5
                    right = mid - 1
        return -1                       #最后没找到 返回-1

Note: 二分法变体 顺便重温二分法 注意比较大小是否带等于 是>还是>= 或<还是<=

if A[mid] >= A[left]: 这里要有等号 反例如3,1 1

if A[left] <= target < A[mid]:  这里要有等号 反例如1,3,5 1 下面情况同理

扩展 解法假设元素是升序排列 要考虑降序解法 及更通用的情况 要先判断是升序还是降序 还有此题没有重复元素 如果有会更复杂 参考 search in rotated sorted arry ii










public class Solution {
    public int search(int[] A, int target) {
        if(A==null || A.length==0)
            return -1;
        int left = 0; int right = A.length-1;
        while(left<=right) {
            int mid = (left+right)/2;
            if(A[mid]==target)
                return mid;
            else if(A[mid]>=A[left]) {      //这里要等于 不然碰到[1] 0这个case会TLE
                if(A[mid]>target && A[left]<=target)    //这里都要写等于号 case [1,3,5] 1
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            else if(A[mid]<=A[right]) {     //这里写不写等于都可以 这跟left right指针的特性有关当元素数为偶数时mid会为left 所以不会出现上面[1] 0一上来就A[mid]==A[left]的情况
                if(A[mid]<target && target<=A[right])      //这里都要写等于 case [5,1,3] 3
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}

这题的思路跟find minimum in rotated sorted array i一样 先判断左右哪边有序然后判断target是落在有序还是无序那边

另外这题一个重要的扩展就是如果是递减序列 如果处理这种更通用的情况 看code ganker 评论 







public class Solution {
    public int search(int[] A, int target) {
        if(A==null || A.length==0)
            return -1;
        int left = 0;
        int right = A.length-1;
        while(left<=right)
        {
            int mid = (left+right)/2;
            if(A[mid]==target)
                return mid;
            if(A[mid]<A[right])
            {
                if(target>A[mid] && target<=A[right])   //注意下这一句
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            else
            {
                if(target>=A[left] && target<A[mid])        //注意下这一句
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return -1;
    }
}

Note: 看code ganker说明 具体跟二分查找类似 只是多加了两个限制条件





from Code Ganker

原题链接: http://oj.leetcode.com/problems/search-in-rotated-sorted-array/ 
这道题是二分查找Search Insert Position的变体，看似有点麻烦，其实理清一下还是比较简单的。因为rotate的缘故，当我们切取一半的时候可能会出现误区，所以我们要做进一步的判断。

具体来说，假设数组是A，每次左边缘为l，右边缘为r，还有中间位置是m。在每次迭代中，分三种情况：

（1）如果target==A[m]，那么m就是我们要的结果，直接返回；

（2）如果A[m]<A[r]，那么说明从m到r一定是有序的（没有受到rotate的影响），那么我们只需要判断target是不是在m到r之间，如果是则把左边缘移到m+1，否则就target在另一半，

即把右边缘移到m-1。

（3）如果A[m]>=A[r]，那么说明从l到m一定是有序的，同样只需要判断target是否在这个范围内，相应的移动边缘即可。

根据以上方法，每次我们都可以切掉一半的数据，所以算法的时间复杂度是O(logn)，空间复杂度是O(1)。

public int search(int[] A, int target) {
    if(A==null || A.length==0)
        return -1;
    int l = 0;
    int r = A.length-1;
    while(l<=r)
    {
        int m = (l+r)/2;
        if(target == A[m])
            return m;
        if(A[m]<A[r])
        {
            if(target>A[m] && target<=A[r])
                l = m+1;
            else
                r = m-1;
        }
        else
        {
            if(target>=A[l] && target<A[m])
                r = m-1;
            else
                l = m+1;                    
        }
    }
    return -1;
}

注意在这道题中我们假设了这个数组中不会出现重复元素。如果允许出现重复元素，那么我们要对中间和边缘的相等的情况继续处理，进而影响到时间复杂度，

这个问题会在Search in Rotated Sorted Array II中具体讨论，大家有兴趣可以看看。



