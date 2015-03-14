from cleanCode 这个版本cleanCode原始稍有差异

public class Solution {
    public int findMin(int[] num) {
        int left = 0; int right = num.length-1;
        while(left<right && num[left]>=num[right]) {    //这里一定要写= 因为有重复元素
            int mid = (left + right)/2;
            if(num[mid]>num[right])
                left = mid + 1;
            else if(num[mid]<num[right])    //也可以写num[mid]<num[left]
                right = mid;
            else        //这里加一个判断如果想等则right--
                right--;        //也可以写left++
        }
        return num[left];
    }
}

这题时间复杂度最坏可以是O(n)






see code ganker 评论 更简单的解法 仍沿用上一题思路 追寻rotate那边

public class Solution {
    public int findMin(int[] num) {
        int left = 0; int right = num.length - 1;
        while(left<right) {
            int mid = (left+right)/2;
            if(num[mid]<num[right])
                right = mid;
            else if(num[mid]>num[right])
                left = mid + 1;
            else
                right--;
        }
        return num[left];
    }
}



<<<<<<< HEAD
=======
public class Solution {
    public int findMin(int[] num) {
        int left = 0; int right = num.length - 1;
        while(left<right) {
            int mid = (left+right)/2;
            if(num[mid]<num[left])
                left = mid + 1;
            else if(num[mid]>num[left])
                right = mid - 1;
            else
                left++;
        }
        return num[left];
    }
}

这种解法的wront test case [1,3] [1，2，3，4，5] 当mid跟left相等时无法判断究竟是同一个元素还是重复元素




如果要以num[left]判断 以下解法通过OJ 根据i里的第一个解改写 对比i中的解法可以发现加了50 51行判断重复([3,3,1]) 48 49判断当

长度等于2时([3,1],[1,1,3])
public class Solution {
    public int findMin(int[] num) {
        int left = 0; int right = num.length - 1;
        while(left<right) {
            int mid = (left+right)/2;
            if(num[left]==num[mid]&&left==right-1)  //单独处理类似[3,1], [1,3]这种情况 因为这种情况不能left++
                return Math.min(num[left], num[right]);
            else if(num[left]==num[mid])        //正常情况如果相等 就后移一位继续看能不能二分
                left++;
            else if(num[mid]>num[left]&&num[left]>=num[right])  //跟i相同 只是第一个判断没有等于因为前面判断过了 第二个有等于因为有重复[1,2,0,1]
                left = mid + 1;
            else
                right = mid;
        }
        return num[left];
    }
}

i中解法
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









>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120


public class Solution {
    public int findMin(int[] num) {
        int left = 0; int right = num.length - 1;
        int min = num[0];
        while(left<right-1) {   //这里注意是left < right-1 因为当left和mid相等时程序是向右移动一位 不会给min做比较可能会跳过最小值[1,1,2,0,0,1]
            int mid = (left+right)/2;
            if(num[left]<num[mid]) {
                min = Math.min(min, num[left]);
                left = mid + 1;
            }
            else if(num[left]>num[mid]) {
                min = Math.min(min, num[mid]);
                right = mid - 1;
            }
            else
                left++;
        }
        min = Math.min(min, num[left]);     //最后循环完还要和最后的num[left], num[right]比较一下 [1,1,2,0,0,1]
        min = Math.min(min, num[right]);    //[3,1]
        return min;
    }
}

类似search in rotated sorted arry ii 注意两个易出错的地方