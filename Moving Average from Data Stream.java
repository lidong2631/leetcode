public class MovingAverage {

    private LinkedList<Integer> list;
    int capacity;
    double sum;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        list = new LinkedList<Integer>();
        this.capacity = size;
        this.sum = 0;
    }
    
    public double next(int val) {
        if (list.size() < capacity) {
            sum += val;
            list.add(val);
            return sum / list.size();
        }
        else {
            sum = sum + val - list.removeFirst();
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