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
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }    
        });
        PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.end - i2.end;
            }
        });
        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval curr = heap.poll();
            if (curr.end <= intervals[i].start) curr.end = intervals[i].end;
            else heap.offer(intervals[i]);
            heap.offer(curr);
        }
        return heap.size();
    }
}

O(nlogn)

https://leetcode.com/discuss/50911/ac-java-solution-using-min-heap


another solutino
https://leetcode.com/discuss/50783/java-ac-code-using-comparator