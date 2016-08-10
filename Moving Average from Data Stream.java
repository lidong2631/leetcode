public class MovingAverage {

    private double sum;                     // careful need to be double
    private LinkedList<Integer> list;
    private int capacity;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.capacity = size;
        sum = 0;
        list = new LinkedList<Integer>();
    }
    
    public double next(int val) {
        if (list.size() < capacity) {
            sum += val;
            list.add(val);
            return sum / list.size();
        }
        else {
            int tmp = list.removeFirst();
            sum = sum + val - tmp;
            list.add(val);
            return sum / list.size();
        }
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */


Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3


O(1) O(n)


https://discuss.leetcode.com/topic/44113/java-o-1-using-deque