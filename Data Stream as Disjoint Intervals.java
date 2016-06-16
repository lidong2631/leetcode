/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class SummaryRanges {

    TreeMap<Integer, Interval> tree;            // the key is the start of the interval

    /** Initialize your data structure here. */
    public SummaryRanges() {
        tree = new TreeMap<>();
    }
    
    public void addNum(int val) {
        if (tree.containsKey(val)) return;
        
        Integer left = tree.lowerKey(val);      // least key strictly greater than the given key
        Integer right = tree.higherKey(val);    // the greatest key strictly less than the given key
        
        if (left != null && right != null && tree.get(left).end + 1 == val && right - 1 == val) {
            tree.get(left).end = tree.get(right).end;   // insert 2 into [1,1], [3,3]
            tree.remove(right);
        }
        else if (left != null && tree.get(left).end + 1 >= val)     // insert 2/3/4 into [1,3]
            tree.get(left).end = Math.max(val, tree.get(left).end);
        else if (right != null && right - 1 == val) {               // insert 6 into [1,3], [7,7]
            tree.put(val, new Interval(val, tree.get(right).end));
            tree.remove(right);
        }
        else                                            // no intersection
            tree.put(val, new Interval(val, val));
    }
    
    public List<Interval> getIntervals() {
        return new ArrayList<>(tree.values());
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */


1, 3, 7, 2, 6

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]


Use TreeMap to easily find the lower and higher keys, the key is the start of the interval. 
Merge the lower and higher intervals when necessary. The time complexity for adding is O(logN) since lowerKey(), 
higherKey(), put() and remove() are all O(logN).

https://leetcode.com/discuss/105756/java-solution-using-treemap-real-o-logn-per-adding