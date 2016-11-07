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
        
        // Java 8 lambda expression
        // Collections.sort(intervals, (Interval i1, Interval i2) -> (i1.start==i2.start)?i1.end-i2.end:i1.start-i2.start);
        intervals.sort((Interval i1, Interval i2) -> (i1.start==i2.start)?i1.end-i2.end:i1.start-i2.start);     // in Java 8 no need to use Collections.sort
        for(int i=1; i<intervals.size(); i++) {
            if(intervals.get(i).start<=intervals.get(i-1).end) {
                intervals.get(i-1).end = Math.max(intervals.get(i-1).end, intervals.get(i).end);
                intervals.remove(i);
                i--;
            }
        }
        return intervals;
    }
}




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
        Comparator comp = new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                if (i1.start == i2.start)
                    return i1.end - i2.end;
                return i1.start - i2.start;
            }
        };
        Collections.sort(intervals, comp);
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i-1).end >= intervals.get(i).start) {
                intervals.get(i-1).end = Math.max(intervals.get(i-1).end, intervals.get(i).end);
                intervals.remove(i);
                i--;    
            }
        }
        return intervals;
    }
}



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








