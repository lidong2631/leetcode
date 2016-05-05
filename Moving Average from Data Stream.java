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

O(1) O(n)