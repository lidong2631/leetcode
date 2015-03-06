class Solution:
    # @return an integer
    def maxArea(self, height):
        left = 0; right = len(height)-1     #设置左右指针left right从左右两端开始往中间夹逼
        res = 0
        while left < right:             #在没相遇前
            water = min(height[left]*(right-left), height[right]*(right-left))  #水量为左右高度最小值乘以左右宽度差
            if res < water:         #维护一个res变量存放最大水量 如果water大于res 就更新res
                res = water
            if height[left] < height[right]:        #如果左高度小于右高度 则左高度会是水量的瓶颈 移动右边指针无法影响水量 所以左指针要右移
                left+=1
            else:               #如果右高度小于左高度 则右高度会是瓶颈 右高度左移
                right-=1
        return res

首先一般我们都会想到brute force的方法，思路很简单，就是对每一对pair都计算一次容积，然后去最大的那个，总共有n*(n-1)/2对pair，所以时间复杂度是O(n^2)。

接下来我们考虑如何优化。思路有点类似于Two Sum中的第二种方法--夹逼。从数组两端走起，每次迭代时判断左pointer和右pointer指向的数字哪个大，如果左pointer小，

意味着向左移动右pointer不可能使结果变得更好，因为瓶颈在左pointer，移动右pointer只会变小，所以这时候我们选择左pointer右移。反之，则选择右pointer左移。

在这个过程中一直维护最大的那个容积。

以上的算法，因为左pointer只向右移，右pointer只向左移，直到相遇为止，所以两者相加只走过整个数组一次，时间复杂度为O(n),空间复杂度是O(1)。

该算法利用了移动指针可确定的规律，做了一步贪心，实现了时间复杂度的降低。




当从两边向中间考虑时，乘水的面积是由（两端较小的高度）×（两个板之间的宽度）决定的。

假定初始的盛水面积为ans=0，lh为左边的高度，rh为右边的高度，如果lh < rh, 则向右运动，寻找第一个比当前lh大的左节点。

同理，如果lh > rh，则向左运动，寻找第一个比当前rh大的右节点。

截止条件为坐标L >= R。