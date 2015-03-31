public class Solution {
    public int maxArea(int[] height) {
        if(height==null || height.length<2)
            return 0;
        int left = 0, right = height.length-1;
        int maxWater = 0;
        while(left<right) {
            int diff = height[right]-height[left];
            if(diff>0) {
                int localWater = (right-left)*height[left];
                maxWater = maxWater<localWater ? localWater:maxWater;
                left++;
            }
            else {
                int localWater = (right-left)*height[right];
                maxWater = maxWater<localWater ? localWater:maxWater;
                right--;
            }
        }
        return maxWater;
    }
}

夹逼+贪心 每次比较左右高度 取小的那个继续移动 时间O(n) 空间O(1)