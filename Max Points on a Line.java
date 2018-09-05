Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6




Java:
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
        int maxPoints = 0;
        for (int i = 0; i < points.length; i++) {
            int samePoints = 1, sameXPoints = 0, sameYPoints = 0, currMax = 0;      // careful for initialize value
            HashMap<Double, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) samePoints++;     // careful for 4 conditions
                else if (points[i].x == points[j].x) sameXPoints++;
                else if (points[i].y == points[j].y) sameYPoints++;
                else {
                    double k = (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x);
                    if (map.containsKey(k)) map.put(k, map.get(k)+1);
                    else map.put(k, 1);             // careful
                }
            }
            for (Integer num : map.values())
                currMax = Math.max(currMax, num);
            currMax = Math.max(currMax, Math.max(sameXPoints, sameYPoints));
            maxPoints = Math.max(maxPoints, samePoints + currMax);
        }
        return maxPoints;
    }
}




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

