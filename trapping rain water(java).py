class Solution:
    # @param A, a list of integers
    # @return an integer
    def trap(self, A):
        leftMost = [0 for i in range(len(A))]   #初始化一个leftMost数组记录每个A[i]之前的最高的bar值 初始化leftMax为0
        leftMax = 0
        for i in range(len(A)):         #遍历一遍数组A 如果当前元素A[i]大于leftMax 更新leftMax 并将对应leftMax值放入leftMost[i]
            if A[i] > leftMax:
                leftMax = A[i]
            leftMost[i] = leftMax
        rightMax = 0                #初始化rightMax和sum为0
        sum = 0
        for i in reversed(range(len(A))):           #第二次遍历数组A 这次是从后往前
            if A[i] > rightMax:                     #如果当前元素A[i]值大于rightMax 更新rightMax
                rightMax = A[i]
            if min(leftMost[i], rightMax) > A[i]:               #如果leftMost[i]和rightMax最小值大于A[i] 说明两边都高于A[i] 可以包围A[i]放水 将A[i]可以加入的水量(min(leftMost[i],rightMax) - A[i])加到sum中
                sum += min(leftMost[i], rightMax) - A[i]
        return sum

Note: 这题有三种解法 上面的解为第二种解法 第二遍过时应该熟悉第三种解法


开辟一个数组leftmosthigh，第一遍遍历数组A， 赋值leftmosthigh[i]为A[i]之前的最高的bar值，

然后第二次从后面开始遍历，用rightmax来记录从后向前遍历遇到的最大bar值，

那么min(leftmosthigh[i], rightmax)-A[i]就是在第i个bar可以储存的水量。

例如当i=9时，此时leftmosthigh[9]=3,而rightmax=2，

则储水量为2-1=1，依次类推即可。这种方法还是很巧妙的。时间复杂度为O(N)。






public class Solution {
    public int trap(int[] A) {
        if(A==null)
            return -1;
        int left = 0; int right = A.length-1;
        int water = 0;
        while(left<right) {
            if(A[left]<=A[right]) {     //如果左指针小
                int tmp = A[left];      //先保存当前值
                left++;                 //去下一步
                while(left<right && A[left]<tmp) {  //只要left<right 且后面的值更小 就计算差值加进water 并继续下后一步直到后面值不小于tmp
                    water+=tmp-A[left];
                    left++;
                }
            }
            else {                      //右指针同理
                int tmp = A[right];
                right--;
                while(left<right && A[right]<tmp) {
                    water+=tmp-A[right];
                    right--;
                }
            }
        }
        return water;
    }
}

第二遍写的解法 只需要扫一遍数组 先比较左右两指针谁的值小 然后从小的那边开始走 如果下一个元素更小就把他门的差值加到最终结果中 因为这个格子小于左指针的值

而左指针又小于右指针所以瓶颈是他 两头的值都比它大 所以可以把它可以盛水的值加到最终结果

否则如果下一个元素大于它 就从新判断左右指针谁指的值大 如此直到两指针相遇

类似题目有Two Sum，Container With Most Water





public class Solution {
    public int trap(int[] A) {
        if(A==null)
            return -1;
        int water = 0;
        int leftMost = 0;
        int[] tmp = new int[A.length];
        for(int i=0; i<A.length; i++) {
            leftMost = Math.max(leftMost, A[i]);
            tmp[i] = leftMost;
        }
        int rightMost = 0;
        for(int i=A.length-1; i>=0; i--) {
            rightMost = Math.max(rightMost, A[i]);
            water += Math.min(tmp[i], rightMost)-A[i];
        }
        return water;
    }
}

第二遍写的 左扫一遍右扫一遍 思路类似Candy那题










public class Solution {
    public int trap(int[] A) {
        if(A==null || A.length<3)
            return 0;
        int[] tmp = new int[A.length];
        int leftMost = A[0];
        for(int i=1; i<A.length; i++) {
            if(A[i]>leftMost)
                leftMost = A[i];
            tmp[i] = leftMost;
        }
        int water = 0; int rightMost = A[A.length-1];
        for(int j=A.length-2; j>=1; j--) {
            if(A[j]>rightMost)
                rightMost = A[j];
            water+=Math.min(tmp[j], rightMost)-A[j]>0 ? Math.min(tmp[j], rightMost)-A[j]:0;
        }
        return water;
    }
}

Note: 左扫一遍 右扫一遍 对于i 点 如果它左边最高bar和右边最高bar的较小值大于A[i] 则说明i可以贮水 可以把较小值和A[i]的差加到res中

这种算法要熟记



from code ganker:

这道题比较直接的做法类似Longest Palindromic Substring中的第一种方法，对于每一个bar往两边扫描，找到它能承受的最大水量，然后累加起来即可。

每次往两边扫的复杂度是O(n)，对于每个bar进行处理，所以复杂度是O(n^2)，空间复杂度是O(1)。思路比较清晰，就不列代码了，有兴趣的朋友可以实现一下哈。

下面我们说说优化的算法。这种方法是基于动态规划的，基本思路就是维护一个长度为n的数组，进行两次扫描，一次从左往右，一次从右往左。

第一次扫描的时候维护对于每一个bar左边最大的高度是多少，存入数组对应元素中，第二次扫描的时候维护右边最大的高度，并且比较将左边和右边小的最大高度（我们成为瓶颈）存入数组对应元素中。

这样两遍扫描之后就可以得到每一个bar能承受的最大水量，从而累加得出结果。这个方法只需要两次扫描，所以时间复杂度是O(2*n)=O(n)。

空间上需要一个长度为n的数组，复杂度是O(n)。代码如下： 

public int trap(int[] A) {
    if(A==null || A.length==0)
        return 0;
    int max = 0;
    int res = 0;
    int[] container = new int[A.length];
    for(int i=0;i<A.length;i++)
    {
        container[i]=max;
        max = Math.max(max,A[i]);
    }
    max = 0;
    for(int i=A.length-1;i>=0;i--)
    {
        container[i] = Math.min(max,container[i]);  //当前bar左右两边最大值得较小值
        max = Math.max(max,A[i]);                   //更新max为右边的最大值
        res += container[i]-A[i]>0?container[i]-A[i]:0;     //判断计算
    }    
    return res;
} 

这个方法算是一种常见的技巧，从两边各扫描一次得到我们需要维护的变量，通常适用于当前元素需要两边元素来决定的问题，非常类似的题目是Candy，有兴趣的朋友可以看看哈。

上面的方法非常容易理解，实现思路也很清晰，不过要进行两次扫描，复杂度前面的常数得是2，接下来我们要介绍另一种方法，相对不是那么好理解，但是只需要一次扫描就能完成。

基本思路是这样的，用两个指针从两端往中间扫，在当前窗口下，如果哪一侧的高度是小的，那么从这里开始继续扫，如果比它还小的，肯定装水的瓶颈就是它了，

可以把装水量加入结果，如果遇到比它大的，立即停止，重新判断左右窗口的大小情况，重复上面的步骤。这里能作为停下来判断的窗口，说明肯定比前面的大了，

所以目前肯定装不了水（不然前面会直接扫过去）。这样当左右窗口相遇时，就可以结束了，因为每个元素的装水量都已经记录过了。代码如下：
public int trap(int[] A) {
    if(A==null || A.length ==0)
        return 0;
    int l = 0;
    int r = A.length-1;
    int res = 0;
    while(l<r)
    {
        int min = Math.min(A[l],A[r]);
        if(A[l] == min)
        {
            l++;
            while(l<r && A[l]<min)
            {
                res += min-A[l];
                l++;
            }
        }
        else
        {
            r--;
            while(l<r && A[r]<min)
            {
                res += min-A[r];
                r--;
            }
        }
    }
    return res;
} 

这个算法每个元素只被访问一次，所以时间复杂度是O(n)，并且常数是1，比前面的方法更优一些，不过理解起来需要想得比较清楚。

这种两边往中间夹逼的方法也挺常用的，它的核心思路就是向中间夹逼时能确定接下来移动一侧窗口不可能使结果变得更好，所以每次能确定移动一侧指针，直到相遇为止。
这种方法带有一些贪心，用到的有Two Sum，Container With Most Water，都是不错的题目，有兴趣的朋友可以看看哈


