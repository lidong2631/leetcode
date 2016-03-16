class MedianFinder {

    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        if(maxHeap.size()==0 && minHeap.size()==0)
            maxHeap.offer(num);
        else if(minHeap.size()==maxHeap.size()) {           // even before insertion
            maxHeap.offer(num);
            if(minHeap.size()!=0 && num>minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(maxHeap.poll());
            }
        }
        else {                                              // odd before insertion
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        return (minHeap.size()==maxHeap.size()) ? (minHeap.peek()+maxHeap.peek())/2.0 : maxHeap.peek();
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();


1 max-heap contains the smallest half of the numbers and min-heap contains the largest half
2 the number of elements in max-heap is either equal to or 1 more than the number of elements in the min-heap

once we’re asked for the median, if the total number of received elements is odd, the median is the root of the max-heap. 
If it’s even, then the median is the average of the roots of the max-heap and min-heap

http://www.ardendertat.com/2011/11/03/programming-interview-questions-13-median-of-integer-stream/