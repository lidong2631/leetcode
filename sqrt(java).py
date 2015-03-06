class Solution:
    # @param x, an integer
    # @return an integer
    def sqrt(self, x):
        if x == 0:
            return 0
        left = 1; right = x/2 + 1
        while left <= right:
            mid = (left + right)/2
            if mid**2 == x:
                return mid
            elif mid**2 > x:
                right = mid - 1
            else:
                left = mid + 1
        return right

Note: 又一道二分法的变体 此题要多想想扩展 如不是整数 而是实数该怎么求 还有牛顿法求开发 具体看code ganker的文章




题意：

Implement int sqrt(int x).

Compute and return the square root of x.

解题思路：实现开平方函数。这里要注意的一点是返回的时一个整数。通过这一点我们可以看出，很有可能是使用二分查找来解决问题的。这里要注意折半查找起点和终点的设置。

起点i=1；终点j=x/2+1；因为在(x/2+1)^2 > x，所以我们将折半查找的终点设为x/2+1。

代码：


class Solution:
    # @param x, an integer
    # @return an integer
    def sqrt(self, x):
        if x == 0:
            return 0
        i = 1; j = x / 2 + 1
        while( i <= j ):
            center = ( i + j ) / 2
            if center ** 2 == x:
                return center
            elif center ** 2 > x:
                j = center - 1
            else:
                i = center + 1
        return j






public class Solution {
    public int sqrt(int x) {
        int left = 1; int right = x/2 + 1;
        while(left<=right)
        {
            int mid = (left+right)/2;
            if(mid<=x/mid && x/(mid+1)<mid+1)
                return mid;
            else if(x/mid<mid)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return right;
    }
}

Note: 这题跟python版解法不一样 注意64行是用x/mid==mid来判断 如果用mid＊mid＝＝x来判断 当mid值很大时乘方可能会越整数界要注意一下 另外就是扩展成实数怎么写 还有牛顿法有时间看下




from code ganker:

这是一道数值处理的题目，和Divide Two Integers不同，这道题一般采用数值中经常用的另一种方法：二分法。基本思路是跟二分查找类似，要求是知道结果的范围，取定左界和右界，

然后每次砍掉不满足条件的一半，知道左界和右界相遇。算法的时间复杂度是O(logx)，空间复杂度是O(1)。代码如下：

public int sqrt(int x) {
    if(x<0) return -1;
    if(x==0) return 0;
    int l=1;
    int r=x/2+1;
    while(l<=r)
    {
        int m = (l+r)/2;
        if(m<=x/m && x/(m+1)<m+1)
            return m;
        if(x/m<m)
        {
            r = m-1;
        }
        else
        {
            l = m+1;
        }
    }
    return 0;
}

二分法在数值计算中非常常见，还是得熟练掌握。其实这个题目还有另一种方法，称为牛顿法。不过他更多的是一种数学方法，算法在这里没有太多体现，不过牛顿法是数值计算中非常重要的方法，

所以我还是介绍一下。不太了解牛顿法基本思想的朋友，可以参考一下牛顿法-维基百科。一般牛顿法是用数值方法来解一个f(y)=0的方程（为什么变量在这里用y是因为我们要求的开方是x，避免歧义）。

对于这个问题我们构造f(y)=y^2-x，其中x是我们要求平方根的数，那么当f(y)=0时，即y^2-x=0,所以y=sqrt(x),即是我们要求的平方根。f(y)的导数f'(y)=2*y，

根据牛顿法的迭代公式我们可以得到y_(n+1)=y_n-f(n)/f'(n)=(y_n+x/y_n)/2。最后根据以上公式来迭代解以上方程。

public int sqrt(int x) {
    if (x == 0) return 0;
    double lastY = 0;
    double y = 1;
    while (y != lastY)
    {
        lastY = y;
        y = (y + x / y) / 2;
    }
    return (int)(y);
}

