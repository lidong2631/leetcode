public class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, maxArea = 0;
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



Python:
class Solution:
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        l, r, maxArea = 0, len(height) - 1, 0
        while l < r:
            if height[l] > height[r]:
                maxArea = max(maxArea, height[r]*(r-l))
                r -= 1
            else:
                maxArea = max(maxArea, height[l]*(r-l))
                l += 1
        return maxArea



Golang:
func maxArea(height []int) int {
    l, r, maxArea := 0, len(height) - 1, 0
    for l < r {
        if height[l] > height[r] {
            maxArea = max(maxArea, height[r]*(r-l))
            r--
        } else {
            maxArea = max(maxArea, height[l]*(r-l))
            l++
        }
    }
    return maxArea
}

func max(x, y int) int {
    if x > y {
        return x
    } else {
        return y
    }
}