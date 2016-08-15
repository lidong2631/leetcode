public class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1, maxArea = 0;
        while (left < right) {
            if (height[left] > height[right])
                maxArea = Math.max(maxArea, (right-left)*height[right--]);
            else
                maxArea = Math.max(maxArea, (right-left)*height[left++]);
        }
        return maxArea;
    }
}

夹逼+贪心 每次比较左右高度 取小的那个继续移动 时间O(n) 空间O(1)