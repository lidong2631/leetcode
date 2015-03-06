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