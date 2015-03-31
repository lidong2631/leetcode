<<<<<<< HEAD

from cleanCode

public class Solution {
    public int findMin(int[] num) {
        int left = 0; int right = num.length-1;
        while(left<right && num[left]>=num[right]) { //这里写不写= 都可以ac 主要是判断有没有pivot 但在ii里一定要写= 因为有重复元素
            int mid = (left+right)/2;
            if(num[mid]>num[right])
                left = mid +1;
            else            //这里换成else if(nuj[mid]<num[right])也可以 while循环内部不会有num[mid]==num[right]的情况 因为没有重复元素
                right = mid;
        }
        return num[left];
    }
}

看cleanCode 说的很清楚 当A[mid]<A[right] 最小值在A[left]到A[mid]之间 当A[mid]>A[right] 最小值在A[mid+1]到A[right]之间





from code ganker评论的解法 比code ganker解法简单 算法关键始终去rotate那边

public class Solution {
    public int findMin(int[] num) {
        int left = 0; int right = num.length - 1;
        while(left<right) {
            int mid = (left+right)/2;
            if(num[mid]>=num[left] && num[left]>num[right]) //rotate在右边
                left = mid + 1; //
            else    //否则rotate在左
                right = mid;    //
        }
        return num[left];
    }
}


from code ganker评论的解法

2楼 vupiggy 2015-01-08 11:47发表 [回复]

不用维护min，二分之后只有两种情况，一旦发现子数组都有序，右子数组的第一个元素就是解，直接返回它（考虑到给的数组可能压根没rotate过，

那也只需要取左右子数组的第一个元素小的那个返回）；一边有序另外一边无序，去无序的那边找。循环退出后的停在哪就是解。

public class Solution {
    public int findMin(int[] num) {
        int left = 0; int right = num.length - 1;
        while(left<right) {
            int mid = (left+right)/2;
            if(num[mid]>num[left] && num[mid]<num[right])
                return Math.min(num[left], num[right]);
            else if(num[mid]>num[left])
                left = mid;
            else if(num[mid]<num[right])
                right = mid;
            else
                left++;
        }
        return num[left];
    }
}





public class Solution {
    public int findMin(int[] num) {
        if(num==null||num.length==0)
            return -1;
        int left = 0;
        int right = num.length-1;
        int min = Integer.MAX_VALUE;
        while(left<=right)
        {
            int mid = (left+right)/2;
            if(num[mid]>=num[left])
            {
                min = Math.min(min, num[left]);
                left = mid + 1;
            }
            else
            {
                min = Math.min(min, num[mid]);
                right = mid - 1;
            }
        }
        return min;
    }
}
