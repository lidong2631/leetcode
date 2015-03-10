# Definition for a point
# class Point:
#     def __init__(self, a=0, b=0):
#         self.x = a
#         self.y = b

class Solution:
    # @param points, a list of Points
    # @return an integer
    def maxPoints(self, points):
        length = len(points)
        if(length < 3):                 #如果小于三个点(2点，1点，0点) 则所有点都在一条直线
            return length
        res = -1                        #res为最大同一直线的点数 初始为－1
        for i in range(length):         #外循环所有的点
            slope = {'inf':0}           #slope字典初始存一个无穷大斜率inf的索引 值为0
            samepointNum = 1
            for j in range(length):     #内循环
                if i == j:              #如果是同一个点continue
                    continue
                elif points[i].x == points[j].x and points[i].y != points[j].y:     #斜率无穷大情况
                    slope['inf'] += 1
                elif points[i].x != points[j].x:
                    k = 1.0 * (points[i].y - points[j].y) / (points[i].x - points[j].x)     #算斜率 注意乘1.0处理python负数除法情况
                    if k not in slope:      #如果这个值不在字典里加入进去
                        slope[k] = 1
                    else:                   #否则对应value加1
                        slope[k] += 1
                else:                       #注意如果是坐标相同的两个点也要算进去
                    samepointNum += 1                                      
            res = max(res, samepointNum + max(slope.values()))
        return res

Note: dictionary, dict.values()





思路：

1 外循环遍历所有点

2 内循环遍历i后所有的点

3 判断 相同点还是x或y相同或算斜率

4 更新map

4 取本轮循环最大数＋numSame 更新max


/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {
        if(points==null || points.length==0)
            return 0;
        int globalMax = 1;  //初始值是1 ！！
        for(int i=0; i<points.length; i++)
        {
            HashMap<Double, Integer> map = new HashMap<Double, Integer>();  //
            int localMax = 1;   //初始值是1 !!
            int numSame = 0;
            double ratio = 0.0;
            
            for(int j=i+1; j<points.length; j++)    //j从i+1开始 ！！前面的点都计算过了
            {
                if(points[j].x==points[i].x && points[j].y==points[i].y)
                {
                    numSame++;
                    continue;
                }
                else if(points[j].x==points[i].x)
                    ratio = (double)(Integer.MAX_VALUE);
                else if(points[j].y==points[i].y)
                    ratio = 0.0;
                else
                    ratio = (double)(points[j].y-points[i].y)/(double)(points[j].x-points[i].x);    //要转型成double再计算 ！！
                if(map.containsKey(ratio))
                    map.put(ratio, map.get(ratio)+1);
                else
                    map.put(ratio, 2);
            }
            for(Integer num : map.values())     //map的遍历方式 ！！ iterator方式如何做
                localMax = Math.max(localMax, num);
            globalMax = Math.max(globalMax, localMax+numSame);
        }
        return globalMax;
    }
}

Note: 这题有很多小细节要注意！1 HashMap,maxDiff,numSame要放在for里面这样每次循环才能把上次的清空

2 判断两个点重合后要记得continue

3 算ratio记得转成double

4 两个for循环的条件

java相关 HashMap 基本操作 values() put,get, Integer.MAX_VALUE


T:O(n^2) S:O(n)

brute force方法见code ganker第一种解法 O(n^3)

注意 在code ganker评论里提到
java的编译器会判断两个double是否相等是判断了类似于fabs(a,b)<10e-10这样的语句，而c++则直接比他们的位，所以double在C++中不能作为key





from code ganker:

这道题属于计算几何的题目，要求给定一个点集合，是求出最多点通过一条直线的数量。我的思路是n个点总共构成n*(n-1)/2条直线，然后对每条直线看看是有多少点在直线上，

记录下最大的那个，最后返回结果。而判断一个点p_k在p_i, p_j构成的直线上的条件是(p_k.y-p_i.y)*(p_j.x-p_i.x)==(p_j.y-p_i.y)*(p_k.x-p_i.x)。算法总共是三层循环，

时间复杂度是O(n^3),空间复杂度则是O(1),因为不需要额外空间做存储。代码如下：

public int maxPoints(Point[] points) {
    if(points==null || points.length==0)
    {
        return 0;
    }
    if(allSamePoints(points))
        return points.length;
    int max = 0;
    for(int i=0;i<points.length-1;i++)
    {
        for(int j=i+1;j<points.length;j++)
        {
            if(points[j].y==points[i].y && points[j].x==points[i].x)
                continue;
            int cur = 2;
            for(int k=0;k<points.length;k++)
            {
                if(k!=i && k!=j 
                && (points[k].y-points[i].y)*(points[j].x-points[i].x)
                    ==(points[j].y-points[i].y)*(points[k].x-points[i].x))
                    cur++;
            }
            max = Math.max(max,cur);
        }
    }
    return max;
}
private boolean allSamePoints(Point[] points)
{
    for(int i=1;i<points.length;i++)
    {
        if(points[0].y!=points[i].y || points[0].x!=points[i].x)
            return false;
    }
    return true;
}

大家看到代码中还写了一个allSamePoints的函数，这是一个非常corner的情况，就是所有点都是一个点的情况，因为下面我们要跳过重复的点（否则两个重合点会认为任何点都在他们构成的直线上），

但是偏偏当所有点都重合时，我们需要返回所有点。除了这种情况，只要有一个点不重合，我们就会从那个点得到结果，这是属于比较tricky的情况。

经过一个朋友的提醒， 这道题还可以有另一种做法， 基本思路是这样的， 每次迭代以某一个点为基准， 看后面每一个点跟它构成的直线， 维护一个HashMap， key是跟这个点构成直线的斜率的值， 

而value就是该斜率对应的点的数量， 计算它的斜率， 如果已经存在， 那么就多添加一个点， 否则创建新的key。 这里只需要考虑斜率而不用考虑截距是因为所有点都是对应于一个参考点构成的直线， 

只要斜率相同就必然在同一直线上。 最后取map中最大的值， 就是通过这个点的所有直线上最多的点的数量。 对于每一个点都做一次这种计算， 并且后面的点不需要看扫描过的点的情况了， 

因为如果这条直线是包含最多点的直线并且包含前面的点， 那么前面的点肯定统计过这条直线了。 因此算法总共需要两层循环， 外层进行点的迭代， 内层扫描剩下的点进行统计， 

时间复杂度是O（n^2), 空间复杂度是哈希表的大小， 也就是O（n), 比起上一种做法用这里用哈希表空间省去了一个量级的时间复杂度。 代码如下：

public int maxPoints(Point[] points) {
    if (points == null || points.length == 0) return 0;
    int max = 1;
    double ratio = 0.0;
    for (int i = 0; i < points.length - 1; i++) 
    {
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        int numofSame = 0;
        int localMax = 1;
        for (int j = i + 1; j < points.length; j++) 
        {
            if(points[j].x == points[i].x && points[j].y == points[i].y) {
                numofSame++;
                continue;
            }
            else if(points[j].x == points[i].x)
            {
                ratio = (double) Integer.MAX_VALUE;
            }
            else if(points[j].y == points[i].y)
            {
                ratio = 0.0;
            }
            else
            {
                ratio = (double) (points[j].y - points[i].y) / (double) (points[j].x - points[i].x);
            }
            int num;
            if (map.containsKey(ratio)) 
            {
                num = map.get(ratio)+1;
                
            }
            else 
            {
                num = 2;
            }
            map.put(ratio, num);
        }
        for (Integer value : map.values()) 
        {
            localMax = Math.max(localMax, value);
        }
        localMax += numofSame;
        max = Math.max(max, localMax);
    }
    return max;
}

计算几何的题目在现在的面试中挺常见的，可能因为有些问题比较实用的缘故，而且实现中一般细节比较多，容易出bug，所以还是得重视

