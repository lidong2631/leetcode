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
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals==null || intervals.length==0)
            return 0;
        Arrays.sort(intervals, new Comparator<Interval>() { //sort intervals by start time
            public int compare(Interval i1, Interval i2) {
                return i1.start-i2.start;
            } 
        });
        PriorityQueue<Interval> heap = new PriorityQueue<Interval>(
        intervals.length, new Comparator<Interval>(){       //min heap sort by end time
            public int compare(Interval i1, Interval i2) {
                return i1.end-i2.end;
            }   
        });
        heap.offer(intervals[0]);
        for(int i=1; i<intervals.length; i++) { //每次取堆顶元素和当前元素比较 堆顶始终是最早结束的meeting元素
            Interval curr = heap.poll();
            if(curr.end<=intervals[i].start)    //如果没有overlap 则将当前元素merge到堆顶元素中
                curr.end = intervals[i].end;
            else
                heap.offer(intervals[i]);   //否则需要一个新的meeting room
            heap.offer(curr);   //最后将堆顶元素放回heap 重新排序
        }
        return heap.size();
    }
}

O(nlogn)

https://leetcode.com/discuss/50911/ac-java-solution-using-min-heap


another solutino
https://leetcode.com/discuss/50783/java-ac-code-using-comparator