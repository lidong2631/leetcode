Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].



Python:
# Definition for an interval.
# class Interval:
#     def __init__(self, s=0, e=0):
#         self.start = s
#         self.end = e

class Solution:
    def insert(self, intervals, newInterval):
        """
        :type intervals: List[Interval]
        :type newInterval: Interval
        :rtype: List[Interval]
        """
        if len(intervals) == 0:
            return [newInterval]
        i = 0
        while i < len(intervals) and intervals[i].end < newInterval.start:
            i += 1
        if i == len(intervals):
            intervals.append(newInterval)
            return intervals
        newInterval.start = min(newInterval.start, intervals[i].start)
        intervals.insert(i, newInterval)
        i += 1
        while i < len(intervals) and intervals[i-1].end >= intervals[i].start:
            intervals[i-1].end = max(intervals[i-1].end, intervals[i].end)
            del intervals[i]
        return intervals

how to remove list item by index in Python
use del a[-1]
https://stackoverflow.com/questions/627435/how-do-i-remove-an-element-from-a-list-by-index-in-python



Java:
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.size() == 0) 
            intervals.add(newInterval);
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) 
            i++;
        if (i == intervals.size()) 
            intervals.add(newInterval);
        newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
        intervals.add(i, newInterval);
        i++;
        while (i < intervals.size() && intervals.get(i-1).end >= intervals.get(i).start) {
            intervals.get(i-1).end = Math.max(intervals.get(i-1).end, intervals.get(i).end);
            intervals.remove(i);
        }
        return intervals;
    }
}










from code ganker:

这道题跟Merge Intervals很类似，都是关于数据结构interval的操作。事实上，Merge Intervals是这道题的子操作，就是插入一个interval，

如果出现冲突了，就进行merge。跟Merge Intervals不一样的是，这道题不需要排序，因为插入之前已经默认这些intervals排好序了。

简单一些的是这里最多只有一个连续串出现冲突，因为就插入那么一个。基本思路就是先扫描走到新的interval应该插入的位置，

接下来就是插入新的interval并检查后面是否冲突，一直到新的interval的end小于下一个interval的start，

然后取新interval和当前interval中end大的即可。因为要进行一次线性扫描，所以时间复杂度是O(n)。而空间上如果我们重新创建一个ArrayList返回，

那么就是O(n)。有朋友可能会说为什么不in-place的进行操作，这样就不需要额外空间，但是如果使用ArrayList这个数据结构，那么删除操作是线性的，

如此时间就不是O(n)的。如果这道题是用LinkedList那么是可以做到in-place的，并且时间是线性的。代码如下：

public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
    ArrayList<Interval> res = new ArrayList<Interval>();
    if(intervals.size()==0)
    {
        res.add(newInterval);
        return res;
    }
    int i=0;
    while(i<intervals.size() && intervals.get(i).end<newInterval.start)		#如果当前结果集的结束点比插入的起始点小 将当前结果集加入res中 继续循环 直到有重叠
    {
        res.add(intervals.get(i));
        i++;
    }
    if(i<intervals.size())				#如果没有到结果集末尾 更新newInterval起始位置为newInterval.start和跟它重叠的结果集起始位置较小值
        newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
    res.add(newInterval);				#将newInterval插入到最终结果集
    while(i<intervals.size() && intervals.get(i).start<=newInterval.end)	#循环到新的interval的end小于下一个interval的start，然后取新interval和当前interval中end大的值
    {
        newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
        i++;
    }
    while(i<intervals.size())
    {
        res.add(intervals.get(i));
        i++;
    }
    return res;
}

这道题有一个变体，就是如果插入的时候发现冲突，那就返回失败，不插入了。看起来好像比上面这道题还要简单，但是要注意的是，

如此我们就不需要进行线性扫描了，而是进行二分查找，如果不冲突，则进行插入，否则直接返回失败。这样时间复杂度可以降低到O(logn)。

当然这里需要用二分查找树去维护这些intervals。所以一点点变化可能可以使复杂度降低，还是应该多做思考哈。
同时，这种题目还可以问一些关于OO设计的东西，比如就直接问你要实现一个intervals的类，要维护哪些变量，实现哪些功能，用什么数据结构，等等。

这些你可以跟面试官讨论，然后根据他的功能要求用相应的数据结构。所以扩展性还是很强的，大家可以考虑的深入一些