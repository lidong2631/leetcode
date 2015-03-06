# Definition for an interval.
# class Interval:
#     def __init__(self, s=0, e=0):
#         self.start = s
#         self.end = e

class Solution:
    # @param intervals, a list of Interval
    # @return a list of Interval
    def merge(self, intervals):
        intervals.sort(key = lambda x:x.start)      #首先将intervals按起点位置排序 这里如果start相等应该再检查结束点 在java中用comparator 想想python怎么写
        res = []
        for i in range(len(intervals)):             #遍历一遍intervals
            if i == 0:
                res.append(intervals[i])            #如果res == [] 直接将第一个intervals append到res
            else:
                size = len(res)
                if res[size-1].start <= intervals[i].start <= res[size-1].end:  #否则判断当前intervals起点是否落在结果集res最后一个interval空间内
                    res[size-1].end = max(intervals[i].end, res[size-1].end)    #如果是在检查interval结束点和当前interval[i]结束点哪个大 大的那个可以包含小的 这样interval[i]就被merge进结果集中
                else:                           #否则 直接append interval[i]到res中
                    res.append(intervals[i])
        return res

Note: 理解python lambda表达式




题意：

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

解题思路：先将区间按照每个start的值来排序，排好序以后判断一个区间的start值是否处在前一个区间中，如果在前一个区间中，那么合并；如果不在，就将新区间添加。

代码：


# Definition for an interval.
# class Interval:
#     def __init__(self, s=0, e=0):
#         self.start = s
#         self.end = e

class Solution:
    # @param intervals, a list of Interval
    # @return a list of Interval
    def merge(self, intervals):
        intervals.sort(key = lambda x:x.start)
        length=len(intervals)
        res=[]
        for i in range(length):
            if res==[]:
                res.append(intervals[i])
            else:
                size=len(res)
                if res[size-1].start<=intervals[i].start<=res[size-1].end:
                    res[size-1].end=max(intervals[i].end, res[size-1].end)
                else:
                    res.append(intervals[i])
        return res





/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        if(intervals.size()==0)
            return res;
        Comparator<Interval> cmp = new Comparator<Interval>()       //这里是匿名类 相当于有一个匿名的类先实现了Comparator接口 然后重写了里面的compare方法
        {
            public int compare(Interval i1, Interval i2) {
                if(i1.start==i2.start)
                    return i2.end - i1.end;         //这地方写i1.end-i2.end;也可以
                else return i1.start - i2.start;
            }
        };
        Collections.sort(intervals, cmp);
        
        res.add(intervals.get(0));
        for(int i=1; i<intervals.size(); i++)
        {
            if(res.get(res.size()-1).end>=intervals.get(i).start)
                res.get(res.size()-1).end = Math.max(res.get(res.size()-1).end, intervals.get(i).end);
            else
                res.add(intervals.get(i));
        }
        return res;
    }
}

Note: 根据code ganker改编 这题主要看下comparator接口的用法 还有匿名类 具体看http://www.cnblogs.com/nerxious/archive/2013/01/25/2876489.html

我给你个通俗易懂的解释，其实这一句： 
Comparator OrderIsdn = new Comparator(){ 

相当于（不是标准语法，只是说明原理，帮助理解）： 
Comparator OrderIsdn = new (MyComp implements Comparator)(){ 

只是由于是匿名类没有名字，所以就变成你看到的那样了。 

实际上，你问的这种写法是java的标准写法，它的意义是我将要实现这个接口并且由这个实现创建一个对象。注意并不是直接实例化接口（虽然看起来像），而是先实现（后面的一对大括号中的代码）再实例化。




from code ganker:

这是一道关于interval数组结构的操作，在面试中也是一种比较常见的数据结构。假设这些interval是有序的（也就是说先按起始点排序，

然后如果起始点相同就按结束点排序），那么要把它们合并就只需要按顺序读过来，如果当前一个和结果集中最后一个有重叠，

那么就把结果集中最后一个元素设为当前元素的结束点（不用改变起始点因为起始点有序，因为结果集中最后一个元素起始点已经比当前元素小了）。

那么剩下的问题就是如何给interval排序，在java实现中就是要给interval自定义一个Comparator，规则是按起始点排序，

然后如果起始点相同就按结束点排序。整个算法是先排序，然后再做一次线性遍历，时间复杂度是O(nlogn+n)=O(nlogn)，空间复杂度是O(1)，

因为不需要额外空间，只有结果集的空间。代码如下：

public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
    ArrayList<Interval> res = new ArrayList<Interval>();
    if(intervals==null || intervals.size()==0)
        return intervals;
    Comparator<Interval> comp = new Comparator<Interval>()
    {
        @Override
        public int compare(Interval i1, Interval i2)
        {
            if(i1.start==i2.start)
                return i1.end-i2.end;
            return i1.start-i2.start;
        }
    };
    Collections.sort(intervals,comp);
    
    res.add(intervals.get(0));
    for(int i=1;i<intervals.size();i++)
    {
        if(res.get(res.size()-1).end>=intervals.get(i).start)
        {
            res.get(res.size()-1).end = Math.max(res.get(res.size()-1).end, intervals.get(i).end);
        }
        else
        {
            res.add(intervals.get(i));
        }
    }
    return res;
}

自定义Comparator有时候在面试中也会要求实现，不熟悉的朋友还是要熟悉一下哈。LeetCode中关于interval的题目还有Insert Interval









